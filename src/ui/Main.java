package ui;

import model.GreenSQA;

//java library
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.ParseException;

/**
 * This isn the Main class 
 */
public class Main{

    /**
     * the scanner of the users input
     */
	private Scanner reader;

    /**
     * the call of the controller class
     */
	private GreenSQA controller;

    /**
     * the format in which some dates are going to be.
     */
	private SimpleDateFormat format;

    /**
     * The constructor method
     */
	public Main() {

		reader = new Scanner(System.in);
		controller = new GreenSQA();
		format = new SimpleDateFormat("dd/M/yy");
	}

     /**
     * This is the main method where the other methods are going to be executed.
     * @param args : the string arguments
     */
	public static void main(String[] args) {

		Main exe = new Main();
		int answer; 

		do{
			exe.menu();
			answer = exe.validateInteger(": ");
			exe.executeOption(answer);

		}while(answer != 0);
	}

    /**
	 * this method shows the user the valid options to execute.
	 */
	public void menu() {
        System.out.println("\n---Select One Option---");
		System.out.println("0. Exit");
		System.out.println("1. Register new project");
		System.out.println("2. Culminate projects phase");
		System.out.println("3. Register a knowledge capsule");
        System.out.println("4. Approve a knowledge capsule");
		System.out.println("5. Publish a knowledge capsule");
        System.out.println("6. Consult how many capsules are registered for each type of capsule.");
		System.out.println("7. Consult a list of lessons learned corresponding to the capsules registered in the projects for a particular stage");
		System.out.println("8. Consult the name of the project with the most capsules registered.");
        System.out.println("9. Consult if a collaborator has registered capsules in a project.");
		System.out.println("10. Consult the situations and learned lessons from approved and published capsules.");
	}

    /**
     * This method executes the option previously selected by the user from the menu or 
     * @param ans : int the option selected by the user.
     */
	public void executeOption(int ans){
		switch(ans){
			case 0:
				System.out.println("Thanks for using our app!");
			break;

			case 1:
				registerProject();
			break;

			case 2:
				culminatePhase();
			break;

			case 3:
				isRegistrableCapsule();
			break;

            case 4:
                approveCapsule();
            break;

            case 5:
                publishCapsule();
            break;

            case 6:
                showAmountCapsulesPerType();
            break;

            case 7:
                showPhaseLearnedLessons();
            break;

            case 8:
                showProjectWithMostCapsules();
            break;

            case 9:
                showCollaboratorsCapsules();
            break;

            case 10:
                showApprovedCapsulesDescriptionsAndLearnedLessons();
            break;

			default:
			System.out.println("Invalid option. Please, try again. ");
            break;
		}
	}

    //Functional Requierement 0: Project Creation

    /**
     * This method asks the user to type some of the atributes of the project that is going to be register,
     * such as: the project's name, the client's name, the start and final date, the budget and the phases. 
     * Also, it asks the user for the managers name and cellphone.
     * All this information is send to the class controller in order to register the project. 
     */
	public void registerProject(){

        Calendar initialDate;
        Calendar finalDate;

        System.out.println("\n---REGISTRATION OF A NEW PROJECT---");
        //Makes sure if its posible to register the project
		boolean isProyectAvailable = controller.isRegistrableProyect();

		if(isProyectAvailable == false){
            //if it isn't posible to register the project, the system will show a warning message.
			System.out.println("The maximun amount of proyects posible has alredy been reached");
		}else{
            // if it is posible to register the project, then the system asks the user for the projects necessary information.
			System.out.print("Type the project's name: ");
			String projectName = reader.nextLine();
		
			System.out.print("Type the client's name: ");
			String clientName = reader.nextLine();

            //Checks if the planned start date is greater or equal to the end date 
            initialDate = readDate("\nType the start date of the proyect");
            do{
			    finalDate = readDate("\nType the end date of the proyect");

                if(initialDate.compareTo(finalDate)>= 0){
                    System.out.println("the start date can't be greater or equal to the end date. Please, try again");
                }
            }while(initialDate.compareTo(finalDate)>= 0);
			
            double budget = validateDouble("\nType the project's budget: ");
            
            System.out.print("Type the proyect's manager name: ");
            String proyectsManagerName = reader.next();

            System.out.print("Type the proyect's manager cellphone: ");
            String proyectsManagerCellphone = reader.next();

            System.out.print("Type the client's manager name: ");
            String clientsManagerName = reader.next();

            System.out.print("Type the client's manager cellphone: ");
            String clientsManagerCellphone = reader.next();

            //sends the requested information to the controller class.
			int projectsPosition = controller.registerProject(projectName, clientName, initialDate, finalDate, budget, proyectsManagerName, proyectsManagerCellphone, clientsManagerName, clientsManagerCellphone);
            
            addPhases(projectsPosition);
        }
	}

    //Functional Requierement 2: Creation of project phases

    /**
     * This method asks the user for the duration in months of each project's phase and send them to the controller.
     * @param projectsPosition : int the position in the array of projects in which the phases are going to be add.
     */
    public void addPhases(int projectsPosition){
        System.out.println("\n--Add the duration of each phase to the project ");
        int ph1  = validateInteger("Type the duration in months of Start phase: ");
        int ph2  = validateInteger("Type the duration in months of Analysis phase: ");
        int ph3  = validateInteger("Type the duration in months of Desing phase: ");
        int ph4  = validateInteger("Type the duration in months of Execution phase: ");
        int ph5  = validateInteger("Type the duration in months of Closing phase: ");
        int ph6  = validateInteger("Type the duration in months of Project's Control phase: ");

        //the months are stored in an array.
        int[] months = {ph1, ph2, ph3, ph4, ph5, ph6};
        controller.addPhases(months, projectsPosition);
    }

    //Functional Requeriment 4: Culmination of a phase of the project.

    /**
     * This method aks the user to enter the name of the project that they want to complete the active phase for.
     * and checks whether a project with that name exists or if it has an active phase. 
     * Then it sends this information to the controller class.
     */
    public void culminatePhase(){
        //asks the user for the name of the project that they want to complete the active phase for.
        System.out.print("Type the name of the proyect you want to search: ");
        String proyectSearch = reader.nextLine();

        //this information is sent to the controller class and stores the projects position in the array
        int pos = controller.searchProject(proyectSearch); 

        //if the returned position is -1 it means that there is no project with that name
        if(pos != -1){
            System.out.println(controller.culminatePhase(pos));
        }else{
            System.out.println("There is no proyect with that name");
        }
    }

    //Functional Requeriment 5: Knowledge capsule regisration.

    /**
     * This method checks if its posible to register a capsule and if it is, calls the method that does that.
     */
    public void isRegistrableCapsule(){
        //asks the user for the name of the project in which they are going to register the capsule.
        System.out.print("Type the name of the proyect you want to search: ");
        String proyectSearch = reader.nextLine();

        //this information is sent to the controller class and stores the projects position in the array
        int pos = controller.searchProject(proyectSearch);
        
        //if the returned position is -1 it means that there is no project with that name
        if(pos != -1){
            //Checks if there is an active phase in the project in which the user wants to register the capsule
            if(controller.isProjectAvailable(pos)== true){
                //Checks if the maximum number of capsules possible to register has been reached.
                if(controller.isPhaseCapsulesLimitReached(pos) == false){
                    //Calls to the method that can register a capsule
                    registerCapsule(pos);
                }else{
                    System.out.println("the maximum number of capsules that can be registered has reached its limit");
                }
            }else{
                System.out.println("There is no active phase in the project "+proyectSearch);
            }
        }else{
            System.out.println("The is no proyect with that name");
        }
    }

    /**
     * This method asks the user to type some of the atributes of the capsule that is going to be register,
     * such as the id, the description, the type, the name and the position of the collaborator that registers the capsule and the learned lesson.
     * All this information is sent to the controller so it can register the capsule.
     * @param pos : int the position in the array of projects in which the capsule is going to be add.
     */
    public void registerCapsule(int pos){
        String id;
        int selectedOption;
        boolean isValid;
        String description;
        String learnedLesson;

        //Checks if the id input already exists.
        do{
            System.out.print("Type the id of the capsule: ");
            id = reader.next();

            if(controller.isIdAlreadyRegistered(pos, id) == true){
                System.out.println("There is already a capsule with that name. Please, try again");
            }

        }while(controller.isIdAlreadyRegistered(pos, id) == true);

        //Checks if the description has the "#"
        do{
            System.out.println("\nThe description should have at least one keyword highlighted by two hashtags.\nExample: #Apples# are fruits, but #potatoes# #carrots# are not.");
            System.out.print("-Type the description of the situation: ");
            description = reader.next();
            isValid = validateHashtags(description);

            if (isValid == false) {
                System.out.println("the description should have at least one keyword highlighted by two hashtags");
            }
        }while(!isValid);
    
        //Checks if the selected option matches with the valid ones.
        do{ 
            capsuleTypesMenu();
            selectedOption = validateInteger("Select one option: ");
        }while(selectedOption<0 || selectedOption>4);

        System.out.print("Type the collaborator's name: ");
        String collaboratorsName = reader.next();

        System.out.print("Type the collaborators position: ");
        String collaboratorsPosition = reader.next();
        
        //Checks if the learnedLesson has the "#"
        do{
            System.out.println("\nThe learned lesson should have at least one keyword highlighted by two hashtags.\n Example: #Apples# are fruits, but #potatoes# #carrots# are not");
            System.out.print("-Write what you had learn with the situation: ");
            learnedLesson = reader.next();
            isValid = validateHashtags(learnedLesson);

                if (isValid == false) {
                    System.out.println("the description should have at least one keyword highlighted by two hashtags");
                }
        }while(!isValid);

        //this information is sent to the controller and prints the returned message.
        System.out.println(controller.registerCapsule(pos, id, description, selectedOption, collaboratorsName, collaboratorsPosition, learnedLesson));
    }

    /**
	 * this method shows the valid types of capsule
	 */
    public void capsuleTypesMenu(){
        System.out.println("\n--Available types for the capsule: ");
        System.out.println("1. Technical");
        System.out.println("2. Managerment");
        System.out.println("3. Domain");
        System.out.println("4. Experiences");        
    }

    /**
     * This method checks if a String input has placed rigth the hashtags.
     * @param input : String text input.
     * @return this method returns false if the hashtags are misplaced or true if they are not .
     */
    public boolean validateHashtags(String input) {
        boolean isFound = false;
        int contHash = 0;
        char[] charArray = input.toCharArray();

        for(int i = 0; i<input.length(); i++){
            if(charArray[i] == '#'){
                contHash++;
            }
        }

        if(contHash%2 == 0 && contHash>0){
            isFound = true;
        }
        
        return isFound;
    }

    //Functional Requeriment 6: Capsule approval.

    /**
     * This method asks the user where is the capsule they are going to approve and, if the system finds it, it calls the method that can approve it.
     */
    public void approveCapsule(){
        //asks the user for the name of the project in which is the registered capsule that they are going to approve.
        System.out.print("Type the name of the proyect you want to search: ");
        String proyectSearch = reader.nextLine();

        //this information is sent to the controller class and stores the projects position in the array.
        int pos = controller.searchProject(proyectSearch);

        //if the returned position is -1 it means that there is no project with that name.
        if(pos != -1){
            //asks the user for the id of the registered capsule that they are going to approve.
            System.out.print("Type the identifier of the capsule you want to search");
            String capsuleIdSearch = reader.next();

            //this information is sent to the controller and prints the returned message.
            System.out.println(controller.approveCapsule(pos, capsuleIdSearch));

        }
    }

    //Functional Requeriment 7: Capsule publication to the organization.

    /**
     * This method asks the user where is the capsule they are going to publish and, if the system finds it, it calls the method that can publish it.
     */
    public void publishCapsule(){
        //asks the user for the name of the project in which is the approved capsule that they are going to publish.
        System.out.print("Type the name of the proyect you want to search: ");
        String proyectSearch = reader.nextLine();
        int pos = controller.searchProject(proyectSearch);

        //if the returned position is -1 it means that there is no project with that name.
        if(pos != -1){
            //asks the user for the name of the approved capsule that they are going to publish
            System.out.print("Type the identifier of the capsule you want to search");
            String capsuleIdSearch = reader.next();

            //this information is sent to the controller and prints the returned message.
            System.out.println(controller.isCapsuleApprove(pos, capsuleIdSearch));

        }

    }

    //Functional Requeriment 8: Consult Knowledges Capsules information.

    public void showAmountCapsulesPerType(){
       System.out.println(controller.calculateProjectsCapsulePerType());
    }

    public void showPhaseLearnedLessons(){
        int selectedOption;

        do{ 
            phasesTypeMenu();
            selectedOption = validateInteger("Select one option: ");
        }while(selectedOption<0 || selectedOption>6);
        
    }

    public void phasesTypeMenu(){
        System.out.println("\n--Available types of phases: ");
        System.out.println("1. Start");
        System.out.println("2. Analysis");
        System.out.println("3. Design");
        System.out.println("4. Executiom");
        System.out.println("5. Closing and Tracking");
        System.out.println("6. Projects Control");
    }

    public void showProjectWithMostCapsules(){

    }

    public void showCollaboratorsCapsules(){

    }

    public void showApprovedCapsulesDescriptionsAndLearnedLessons(){

    }

    // Other functionalities.

    /**
     * This method reads and validate a date typed by the user.
     * @param msg : String the message that will be displayed to the user to indicate what or where to write their response.
     * @return : Calendar the input of the user.
     */
	public Calendar readDate(String msg){
		
		Calendar calendarTime = new GregorianCalendar();
		String date = "";
		boolean isDateSet; //flag type variable.

		do{
            isDateSet = false;
            System.out.println(msg); 
            System.out.println("The date must follow the format: dd/M/yyyy");
            System.out.print(": ");
            date = reader.next();
		
            try {
                calendarTime.setTime(format.parse(date));
                isDateSet= true;
            } catch (ParseException error) {
                System.out.println("Invalid option. Please, try again");
            }

        }while(isDateSet == false);
		
		return calendarTime;
	}

    /**
     * This method validates an integer input by the user.
     * @param message : String the message that will be displayed to the user to indicate what or where to write their response.
     * @return : int the input of the user.
     */
	public int validateInteger(String message){
        int input = -1;
        boolean stopCondition = false; //flag type variable.
        
        do{
            System.out.print(message);

            if(reader.hasNextInt()){
                stopCondition = true;
                input = reader.nextInt();
                reader.nextLine();
                                      
            }
            else{
                reader.nextLine();
                System.out.println("Invalid option. Please, try again.\n");
            }
        }while(stopCondition != true);

        return input;
    }

     /**
     * This method validates a double input by the user.
     * @param message : String the message that will be displayed to the user to indicate what or where to write their response.
     * @return : double the input of the user.
     */
    public double validateDouble(String message){
        Double input = -1.0;
        boolean stopCondition = false; //flag type variable

        do{
            System.out.print(message);

            if(reader.hasNextDouble()){
                stopCondition = true;
                input = reader.nextDouble();          
            }
            else{
                reader.next();
                System.out.println("Invalid option. Please, try again");
            }
        }while(stopCondition != true);

        return input;
    }
}
