package model;

//java library
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * this class represents a Green SQA project.
 */
public class Project{
    /**
     * MAX_PHASES represents the total of phases that can be added to the project.
     */
    private static final int MAX_PHASES = 6;
	
    /**
     * Represents the name of the project.
     */
	private String name;

    /**
     * Represents the name of the client of GreenSQA.
     */
	private String clientName;

    /**
     * Represents the budget of the project.
     */
	private double budget;

    /**
     * Represents the planned start date of the project.
     */
	private Calendar initialDate;

    /**
     * Represents the planned end date of the project.
     */
	private Calendar finalDate;

    /**
     * Represents the phases in which the project is divided.
     */
	private ProjectsPhase[] phases;

    /**
     * Represents the manager of the projects.
     */
    private Manager proyectsManager;

    /**
     * Represents the clients manager.
     */
    private Manager clientsManager;
    
    /**
     * Represents the format in which the dates are going to be saved as.
     */
	private DateFormat formatter;

    //The constructor method.
	public Project(String name, String clientName, Calendar initialDate, Calendar finalDate, double budget, Manager proyectsManager, Manager clientsManager){
		
		this.formatter = new SimpleDateFormat("dd/M/yy");
        phases = new ProjectsPhase[MAX_PHASES];

		this.name = name;	
		this.clientName = clientName;
		this.initialDate = initialDate;
		this.finalDate = finalDate;
		this.budget = budget;
        this.proyectsManager = proyectsManager;
        this.clientsManager = clientsManager;
        
	}

    /**
     * this method consults the current status information of the attribute name.
     * @return : String the name of the project.
     */
	public String getName(){
		return name;
	}
	
    /**
     * this method consults the current status information of the attribute clients name
     * @return : String the name of Green SQA client.
     */
	public String getClientName(){
		return clientName;
	}

    /**
     * this method consults the current status information of the planned start date.
     * @return : Calendar the planned start date.
     */
	public Calendar getInitialDate(){
		return initialDate;
	}
	
    /**
     * Returns the initial date formatted as a String using the current date-time formatter.
     * @return : String the initial date formatted as a String.
     * @throws ParseException if there is an error parsing the initial date.
     */
	public String getInitialDateFormated() throws ParseException{
		return formatter.format(this.initialDate.getTime());
	}

    /**
     * this method consults the current status information of the planned end date.
     * @return  Calendar the planned end date. 
     */
	public Calendar getFinalDate(){
		return finalDate;
	}

    /**
     * Returns the final date formatted as a String using the current date-time formatter.
     * @return : String the final date formatted as a String.
     * @throws ParseException if there is an error parsing the final date.
     */
	public String getFinalDateFormated() throws ParseException{
		return formatter.format(this.finalDate.getTime());
	}		

    /**
     * this method consults the current status information of the attribute budget.
     * @return : double the budget of the project.
     */
	public double getBudget(){
		return budget;
	}

    /**
     * this method consults the current status information of one of the phases.
     * @param pos : int the position of the phase in the array of phases.
     * @return : ProjectsPhase the instance of the ProjectsPhase class in that position.
     */
    public ProjectsPhase getPhase(int pos){
        return phases[pos];
    }

    /**
     * this method consults the current status information of the atributte projects manager
     * @return : Manager the instance of the Manager class that represents the projects manager.
     */
    public Manager getProjectsManager(){
        return proyectsManager;
    }

    /**
     * this method consults the current status information of the atributte clients manager
     * @return : Manager the instance of the Manager class that represents the clients manager.
     */
    public Manager getClientsManager(){
        return clientsManager;
    }
    
    /**
     * This method returns the position in the array of phases of the current active phase.
     * @return : int it returns -1 if there is no active phase or a number between [0,5] if there is an active phase.
     */
    public int getActivePhase(){
        boolean isFound = false;
        int pos = -1;
        for(int i = 0; i < MAX_PHASES && !isFound; i++){
            if(phases[i].getStatus() == true){
                pos = i;
                isFound = true;
            }
        }
        return pos;
    }

    /**
     * This method adds a new phase to the array of phases.
     * @param phase : ProjectsPhase the phase that is going to be add.
     */
    public void addPhase(ProjectsPhase phase){
        int pos = getFirstValidPosition(); 
		if(pos != -1){
			phases[pos] = phase; 
		}
    }
    
    /**
     * search in the array of phases if exists one valid position.
     * @return :int the method returns -1 if the position does not exist, a number in [0, 5] if exist a valid position.
     */
    public int getFirstValidPosition(){
		int pos = -1; 
		boolean isFound = false; // flag type variable
		for(int i = 0; i < MAX_PHASES && !isFound; i++){
			if(phases[i] == null){
				pos = i; 
				isFound = true;
			}
		}
		return pos; 
	}

    /**
     * This method culminates the current active phase in the project by changing their status and the approval status.
     * Then, if there is one, it activates the next phase. 
     * @return : String a message indicating that is no active phase in the project or indicating the phases that has end.
     */
    public String culminateActivePhase(){
        boolean isFound = false; //flag type variable

        //the message that indicates if there is no active phase in the project.
        String msg = "there is no active phase in this project";

        //runs through the array of phases and checks if there is an active phase.
        for(int i = 0; i< MAX_PHASES && !isFound; i++){
            if(phases[i].getStatus() == true){ 
                phases[i].setStatus(false); //Changes the status of the active phase to false.
                phases[i].setApprovalStatus(true); //Changes the approval status of the active phase to false.

                //gets the current date and sets it as the real end date of the phase that ended.
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                calendar.set(year, month, day);

                phases[i].setEndDate(calendar);

                //The message that indicates the name of the phase that has end.
                msg = ("The "+phases[i].getNamePhase()+" phase has end");

                //if there is another phase after the phase that ended, it activates the phase.  
                if((i+1)< MAX_PHASES){
                    phases[i+1].setStatus(true); // Changes the status of the phase.
                    phases[i+1].setStartDate(calendar); // Saves the current date as the real start date.
                    msg += (" and " +phases[i+1].getNamePhase()+ " phase has been activated"); // adds to the message the name of the phase that has activated
                }
                isFound = true;
            }
        }

        return msg;
    }
    
    /**
     * This method register a new capsule and sends it to the ProjectsPhase class
     * @param id : String the id of the capsule.
     * @param description : String the description of the new capsule. 
     * @param capsuleTypeOption  : int the type selected by the user.
     * @param collaboratorsName  : String the name of the collaborator that registered the capsule.
     * @param collaboratorsPosition : String the position of the collaborator that registered th capsule.
     * @param learnedLesson : String the collaborators learned lesson. 
     * @return 
     */
    public String sendCapsuleToPhase(String id, String description, int capsuleTypeOption, String collaboratorsName, String collaboratorsPosition, String learnedLesson){

        Collaborator collaborator = new Collaborator(collaboratorsName, collaboratorsPosition);
        KnowledgeCapsule capsule = new KnowledgeCapsule(id, description, collaborator, learnedLesson, capsuleTypeOption);

        String msg = phases[getActivePhase()].addKnowledgeCapsule(capsule);

        return msg;
    }

}