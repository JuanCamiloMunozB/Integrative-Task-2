package model;

//java library
import java.util.Calendar;

/**
 * An enumeration representing the different types of phases in a project.
 */
enum PhaseType {
    /**
     * Represents the start phase of a project.
     */
    START("start"), 

    /**
     * Represents the analysis phase of a project.
     */
    ANALYSYS("analysis"), 

    /**
     * Represents the design phase of a project.
     */
    DESGIN("design"), 

    /**
     * Represents the execution phase of a project.
     */
    EXECUTION("execution"), 

    /**
     * Represents the closing and tracking phase of a project.
     */
    CLOSING_TRACKING("closing and tracking"), 

    /**
     * Represents the projects control phase of a project.
     */
    PROYECTS_CONTROL("projects control");

    /**
     * the name of the phase.
     */
    private final String name;
    
    // constructor
    private PhaseType(String name) {
        this.name = name;
    }
    
    /**
     * Returns the name of the phase as a string. 
     * @return : String the name of the phase.
     */
    public String getName() {
        return name;
    }
}

/**
 * this class represents a phase in a project.
 */
public class ProjectsPhase {
    
    /**
     * MAX_CAPSULES represents the total of capsules that can be added to the phase.
     */
    private static final int MAX_CAPSULES = 50;

    /**
     * capsulesCounter counts how much capsules have been registered.
     */
    private int capsulesCounter;

    /**
     * Represents the name of the project
     */
    private String name;

    /**
     * Represents the real start date of the phase.
     */
    private Calendar startDate;

    /**
     * Represents the real end date of the phase.
     */
    private Calendar endDate;

    /**
     * Represents the duration in months of the phase.
     */
    private int months;

    /**
     * Represents the planned start date of the phase.
     */
    private Calendar plannedStartDate;

    /**
     * Represents the planned end date of the phase.
     */
    private Calendar plannedEndDate;

    /**
     * Represents if the phase is active or not.
     */
    private boolean status;

    /**
     * Represents the array of capsules that are going to be added to the phase.
     */
    private KnowledgeCapsule[] capsules;

    /**
     * Represents whether the date has been approved or not. 
     */
    private boolean approvalStatus;

    /**
     * The constructor method
     * @param months : int  Represents the duration in months of the phase.
     * @param phaseNameOption : int the option that represents the name/type of the phase.
     */
    public ProjectsPhase(int months, int phaseNameOption){
        
        this.months = months;
        startDate = Calendar.getInstance();
        endDate = Calendar.getInstance();
        plannedStartDate = Calendar.getInstance();
        plannedEndDate = Calendar.getInstance();
        status = false;
        approvalStatus = false;
        capsules = new KnowledgeCapsule[MAX_CAPSULES];

        setNamePhase(phaseNameOption);

    }

    /**
     * This method sets the name/type of the phase.
     * @param phaseNameOption : int the option that represents the name/type of the phase.
     */
    private void setNamePhase(int phaseNameOption){

        switch(phaseNameOption){
            case 0:
            name = PhaseType.START.getName();
            break;

            case 1:
            name = PhaseType.ANALYSYS.getName();
            break;

            case 2:
            name = PhaseType.DESGIN.getName();
            break;

            case 3:
            name = PhaseType.EXECUTION.getName();
            break;

            case 4:
            name = PhaseType.CLOSING_TRACKING.getName();
            break;

            case 5:
            name = PhaseType.PROYECTS_CONTROL.getName();
            break;
        }
    }

    /**
     * this method consults the current status information of the attribute name/type.
     * @return : String the name/type of the phase.
     */
    public String getNamePhase(){
        return name;
    }

    /**
     * this method consults the current status information of the phase real start date.
     * @return : Calendar the phase real start date.
     */
    public Calendar getStartDate(){
        return startDate;
    }

    /**
     * this method consults the current status information of the phase real end date.
     * @return : Calendar the phase real end date.
     */
    public Calendar getEndDate(){
        return endDate;
    }

    /**
     * this method consults the current status information of the phase planned start date.
     * @return : Calendar the phase planned start date.
     */
    public Calendar getPlannedStarDate(){
        return plannedStartDate;
    }

    /**
     * this method consults the current status information of the phase planned end date.
     * @return : Calendar the phase real end date.
     */
    public Calendar getPlannedEndDate(){
        return plannedEndDate;
    }

    /**
     * this method consults the current status information of the duration in months of the phase.
     * @return : int the duration in months of the phase.
     */
    public int getMonths(){
        return months;
    }
    
    /**
     * this method consults the current status information of the phase status.
     * @return : boolean the status of the phase.
     */
    public boolean getStatus(){
        return status;
    }

    /**
     * this method consults the current status information of the phase approval status.
     * @return : boolean the approval status of the phase.
     */
    public boolean getApprovalStatus(){
        return approvalStatus;
    }
    
    /**
     * This method consults the current status information of one of the capsules.
     * @param position : int the position of the Knowledge capsule in the array of capsules.
     * @return : KnowledgeCapsule the instance of the KnowledgeCapsule class in that position.
     */
    public KnowledgeCapsule getCapsule(int position){
        return capsules[position];
    }

    /**
     * This method returns the maximun amount of capsules that can be register.
     * @return : int the maximun amount of capsules that can be register.
     */
    public int getMaxCapsules(){
        return MAX_CAPSULES;
    }

    /**
     * changes the current status attribute information
     * @param status : boolean the new status.
     */
    public void setStatus(boolean status){
        this.status = status;
    }

    /**
     * changes the current information of the attribute approval status.
     * @param isApproved : boolean the new status. 
     */
    public void setApprovalStatus(boolean isApproved){
        this.approvalStatus = isApproved;
    }

    /**
     * changes the current information of the attribute real start date.
     * @param date : Calendar the new date.
     */
    public void setStartDate(Calendar date){
        startDate = date;
    }

    /**
     * changes the current information of the attribute real end date.
     * @param date : Calendar the new date.
     */
    public void setEndDate(Calendar date){
        endDate = date;
    }

    /**
     * changes the current information of the attribute planned start date.
     * @param date : Calendar the new date.
     */
    public void setPlannedStartDate(Calendar date){
        plannedStartDate = date;
    }

    /**
     * changes the current information of the attribute planned end date.
     * @param date : Calendar the new date.
     */
    public void setPlannedEndDate(Calendar date){
        plannedEndDate = date;
    }

    /**
     * This method adds to the array of capsules a new capsule. 
     * @param capsule : KnowledgeCapsule a new capsule that is going to be add.
     * @return : String a message indicating that the capsule has been registered succesfully
     */
    public String addKnowledgeCapsule(KnowledgeCapsule capsule){
        int pos = getFirstValidPosition();
        String msg = "";
        if(pos != -1){
			capsules[pos] = capsule; 
            capsulesCounter++;
            msg = "the capsule has been registered successfully";
		}
        return msg;
    }

    /**
     * search in the array of capsules if exists one valid position.
     * @return :int the method returns -1 if the position does not exist, a number in [0, 49] if exist a valid position.
     */
    public int getFirstValidPosition(){
		int pos = -1; 
		boolean isFound = false; 
		for(int i = 0; i < MAX_CAPSULES && !isFound; i++){
			if(capsules[i] == null){
				pos = i; 
				isFound = true;
			}
		}
		return pos; 
	}

    /**
     * This method checks if its posible to register another capsule.
     * @return : boolean false if it isn't posible to register another capsule or true if it is possible.
     */
    public boolean isCapsulesLimitReached(){
        boolean isLimitReached = false;

        if(MAX_CAPSULES == capsulesCounter){
            isLimitReached = true;
        }

        return isLimitReached;
    }

    /**
     * This method searchs if there is a project with an especified id.
     * @param searchCapsule : String the especified projects id.
     * @return : int it returns -1 if there is no capsule with that especified id or a number between [0,49] if it is.
     */
    public int searchKnowledgeCapsule(String searchCapsule){
        int pos = -1; 
		boolean isFound = false; 
		for(int i = 0; i < MAX_CAPSULES && !isFound; i++){
			if(capsules[i] == null){
                if(capsules[i].getId().equalsIgnoreCase(searchCapsule)){
                    pos = i; 
				    isFound = true;
                }
			}
		}
		return pos; 
    }
}
