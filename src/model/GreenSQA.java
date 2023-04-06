package model;

//java library
import java.util.Calendar;

/**
 * GreenSQA this class represents the controller class of the system 
 */
public class GreenSQA {
    /**
     * MAX_PROYECTS represents the total of projects that can be register.
     */
	private static final int MAX_PROYECTS = 10;

    /**
     * projectCounter counts how much projects have been registered.
     */
	private int projectCounter;

    /**
     * projects represents the array of projects.
     */
	private Project[] projects;

    /**
     * The constructor method.
     */
	public GreenSQA() {

		projects = new Project[MAX_PROYECTS];
	
	}
	
    //Functional Requierement 0: Project Creation

    /**
     * This method checks if its posible to register another project.
     * @return : boolean false if it isn't posible to register another project or true if it is possible.
     */
	public boolean isRegistrableProyect(){
		if(projectCounter == MAX_PROYECTS){
			return false;
		}else{
			return true;
		}
	}

    /**
     * This method adds a new project to the array of projects.
     * @param projectName : String the name of the new project to add.
     * @param clientName : String the name of the client of GreenSQA
     * @param initialDate : Calendar the projects planned start date.
     * @param finalDate : Calendar the projects planned end date.
     * @param budget : Double the projects budget.
     * @param proyectsManagerName : String the name of the manager of the project.
     * @param proyectsManagerCellphone : String the cellphone of the manager of the project.
     * @param clientsManagerName : String the name of the clients manager.
     * @param clientsManagerCellphone : String the cellphone of the clients manager.
     * @return : int the position in which the project is going to.
     */
	public int registerProject(String projectName, String clientName, Calendar initialDate, Calendar finalDate, double budget, String proyectsManagerName, String proyectsManagerCellphone, String clientsManagerName, String clientsManagerCellphone){
		
        //initialization of the managers objects
        Manager proyectsManager = new Manager(proyectsManagerName, proyectsManagerCellphone);
        Manager clientsManager = new Manager(clientsManagerName, clientsManagerCellphone);

        //initialization of the project object
		Project project = new Project(projectName, clientName, initialDate, finalDate, budget, proyectsManager, clientsManager);

        //the project is saved on the array of projects.
		int pos = getFirstValidPosition();
        projects[pos]= project;

        //the counter of created projects is increased.
        projectCounter++;
		
        return pos;
    }

    /**
	 * search in the array of projects if exists one valid position
	 * @return :int the method returns -1 if the position does not exist, a number in [0, 9] if exist a valid position.
	 */
    public int getFirstValidPosition(){
		int pos = -1; 
		boolean isFound = false; 
		for(int i = 0; i < MAX_PROYECTS && !isFound; i++){
			if(projects[i] == null){
				pos = i; 
				isFound = true;
			}
		}
		return pos; 
	}

    //Functional Requierement 2: Creation of project phases

    /**
     * this method adds the phases to a project.
     * @param monthsPerPhase : the array of the duration of each phase in months.
     * @param projectPosition : the position of the project in which the phases are to be added, in the projects array.
     */
    public void addPhases(int[] monthsPerPhase, int projectPosition){
        for(int i = 0; i<monthsPerPhase.length; i++){
            //initialization of the ProjectsPhase object
            ProjectsPhase phase = new ProjectsPhase(monthsPerPhase[i], i);
            projects[projectPosition].addPhase(phase);
        }

        //sets start phase status as active 
        projects[projectPosition].getPhase(0).setStatus(true);

        //gets the current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year, month, day);

        //sets the current date to the first phase
        projects[projectPosition].getPhase(0).setStartDate(calendar);
        projects[projectPosition].getPhase(0).setPlannedStartDate(calendar);

        //sends to the method the project in which the planned dates are going to be calculated
        setPhasesPlannedDates(projectPosition, calendar, monthsPerPhase);

    }

    /**
     * This method calculates the planned dates for each phase based on its duration in months.
     * @param projectPosition : the position of the project in which the phases are to be added, in the projects array.
     * @param startDate : the start date of the first phase.
     * @param monthsPerPhase : the array of the duration of each phase in months.
     */
    public void setPhasesPlannedDates(int projectPosition, Calendar startDate, int[] monthsPerPhase){
        int acumMonths = 0; //number of months accumulator.
        
        //runs through the array of months to calculate the planned dates from the startDate and assigns them to each phase.
        for(int i = 0; i<monthsPerPhase.length; i++){
            acumMonths += monthsPerPhase[i];
            startDate.add(Calendar.MONTH, acumMonths);

            projects[projectPosition].getPhase(i).setPlannedEndDate(startDate);  

            //if there is a next phase, then it assigns the same date as its planned start date. 
            if((i+1)<monthsPerPhase.length){
                projects[projectPosition].getPhase(i+1).setPlannedStartDate(startDate);
            }
        }
    }

    //Functional Requeriment 4: Culmination of a phase of the project.

    /**
     * This method calls the method that culminates the current active phase in a registered proyect and sends a message.
     * @param positionProject : the position of the project in which the current active phase is going to be culimanated, in the projects array.
     * @return : String a message indicating that there is no active phase in the project or indicating the phase that has end.
     */
    public String culminatePhase(int positionProject){
        String msg = projects[positionProject].culminateActivePhase();
        return msg;
    }

    //Functional Requeriment 5: Knowledge capsule regisration.

    /**
     * This method calls the method that adds a new capsule and sends a message.
     * @param projecPosition : int the position of the project in which the capsule is going to be add in the current active phase.
     * @param id : String the id of the capsule.
     * @param description : String the description of the new capsule. 
     * @param capsuleTypeOption : int the type selected by the user.
     * @param collaboratorsName : String the name of the collaborator that registered the capsule.
     * @param collaboratorsPosition : String the position of the collaborator that registered th capsule.
     * @param learnedLesson : String the collaborators learned lesson. 
     * @return a message indicating that the capsuloe has been registered.
     */
	public String registerCapsule(int projecPosition, String id, String description, int capsuleTypeOption, String collaboratorsName, String collaboratorsPosition, String learnedLesson){
       
        String msg = projects[projecPosition].sendCapsuleToPhase(id, description, capsuleTypeOption, collaboratorsName, collaboratorsPosition, learnedLesson);;

        return msg;
    }

    /**
     * Checks if there is an active phase in a proyect.
     * @param pos : int the position of the project in the array of projects.
     * @return false if there is no active phase in the project or true if there is an active phase.
     */
    public boolean isProjectAvailable(int pos){
        boolean isProjectAvailable = false;  
        
        if(projects[pos].getActivePhase() != -1){
            isProjectAvailable = true;
        }

        return isProjectAvailable;
    }

    /**
     * This method checks if its posible to register another capsule. 
     * @param pos : int the position of the project in the array of projects.
     * @return : boolean false if it isn't posible to register another capsule or true if it is possible.
     */
    public boolean isPhaseCapsulesLimitReached(int pos){
        boolean isCapsulesLimiteReached = false;
        if(projects[pos].getPhase(projects[pos].getActivePhase()).isCapsulesLimitReached() == true){
            isCapsulesLimiteReached = true;
        }

        return isCapsulesLimiteReached;
    }

    /**
     * Checks if an id input already exists in another capsule.
     * @param pos : int the position of the project in the array of projects.
     * @param idInput : String the id that was typed by the user.
     * @return false if the id typed by the user doesn't exist or true if the id typed by the user already exists.
     */
    public boolean isIdAlreadyRegistered(int pos, String idInput){
        boolean isIdAvailable = false;
        ProjectsPhase view = projects[pos].getPhase(projects[pos].getActivePhase());

        for(int i = 0; i<view.getMaxCapsules(); i++){
            if(view.getCapsule(i) != null && view.getCapsule(i).getId().equalsIgnoreCase(idInput)){
                isIdAvailable = true;
            }
        }

        return isIdAvailable;
    }
    
    //Functional Requeriment 6: Capsule approval.

    /**
     * Checks if there is an capsule with an especified id and, if it finds it, approves capsule.
     * @param projecPosition : int the position of the project in the array of projects.
     * @param capsuleIdSearch : String the especified id.
     * @return : String a message indicating that the capsule was not found or indicating that it was approved.
     */
    public String approveCapsule(int projecPosition, String capsuleIdSearch){
        String msg = "There is no capsule with that id";
        ProjectsPhase view = projects[projecPosition].getPhase(projects[projecPosition].getActivePhase());

        int requestedCapsule = view.searchKnowledgeCapsule(capsuleIdSearch);

        if(requestedCapsule != -1){
            view.getCapsule(requestedCapsule).setApprovalStatus(false);
            msg = "the capsule was approved sucessfully";

            Calendar calendarTime = Calendar.getInstance();
            int year = calendarTime.get(Calendar.YEAR);
            int month = calendarTime.get(Calendar.MONTH);
            int day = calendarTime.get(Calendar.DAY_OF_MONTH);
            calendarTime.set(year, month, day);
            view.getCapsule(requestedCapsule).setApprovalDate(calendarTime);
        }

        return msg;
    }

    //Functional Requeriment 7: Capsule publication to the organization.

    /**
     * Checks if there is an capsule with an especified id and, if it finds it, publish the capsule.
     * @param projecPosition : int the position of the project in the array of projects.
     * @param capsuleIdSearch : String the especified id.
     * @return : String a message indicating that the capsule was not found or indicating that it was published and shows the url.
     */
    public String isCapsuleApprove(int projecPosition, String capsuleIdSearch){
        String msg = "There is no capsule with that id";
        ProjectsPhase view = projects[projecPosition].getPhase(projects[projecPosition].getActivePhase());

        int requestedCapsule = view.searchKnowledgeCapsule(capsuleIdSearch);

        if(requestedCapsule != -1){
            if(view.getCapsule(requestedCapsule).getApprovalStatus() == true){
                msg = "the capsule has been published successfully\nurl: https://intranet.GreenSQA.com/"+ projects[projecPosition].getName() +"/"+ view.getNamePhase()+"/"+view.getCapsule(requestedCapsule).getId()+"/";
            }
        }
        return msg;
    }

    //Other functionalities
    /**
     * This method searchs if there is a project with an especified name.
     * @param projectSearch : String the especified projects name.
     * @return : int it returns -1 if there is no project with that especified name or a number between [0,9] if it is.
     */
    public int searchProject(String projectSearch){

        int pos = -1;
        boolean isFound = false;

        for(int i = 0; i<MAX_PROYECTS && !isFound; i++){
            if(projects[i] != null){
                if(projectSearch.equalsIgnoreCase(projects[i].getName())){
                    pos = i;
                }
            }
        }

        return pos;
    }

}