package model;

//java library
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

/**
 * An enumeration representing the different types of capsules in a project.
 */
enum CapsuleType {
    /**
     * Represents the capsule type technical. 
     */
    TECHNICAL("technical"), 

    /**
     * Represents the capsule type management.
     */
    MANAGEMENT("management"), 

    /**
     * Represents the capsule type domain.
     */
    DOMAIN("domain"), 

    /**
     * Represents the capsule type experiences.
     */
    EXPERIENCES("experiences"); 

    /**
     * Represents the type of the capsule. 
     */
    private final String type;
    
    //The constructor method
    private CapsuleType(String type){
        this.type = type;
    }
    
    /**
     * Returns the name of the phase as a string. 
     * @return : String the name of the phase.
     */
    public String getType() {
        return type;
    }
}

/**
 * This method represents a capsule that is registered in a phase.
 */
public class KnowledgeCapsule {
    
    /**
     * Represents the unique identifier of the knowledge capsule. 
     */
    private String id;

    /**
     * Represents the description of the situation during a phase.
     */
    private String description;

    /**
     * Represents the type of the knowledge capsule
     */
    private String type;

    /**
     * Represents the collaborator that registered the knowledge capsule
     */
    private Collaborator collaborator;

    /**
     * Represents the collaborators learned lesson in a certain situation during a phase
     */
    private String learnedLesson;

    /**
     * Represents the approval status of the capsule.
     */
    private boolean approvalStatus;

    /**
     * Represents the date when the capsule was approved.
     */
    private Calendar approvalDate;

    /**
     * Represents the array of keywords highlighted in two '#'.
     */
    private List<String> hashtags;

    /**
     * the constructor method.
     * @param id : String the unique identifier of the knowledge capsule. 
     * @param description : String the description of the situation during a phase.
     * @param collaborator : Collaborator the collaborator that registered the knowledge capsule
     * @param learnedLesson : String the collaborators learned lesson in a certain situation during a phase
     * @param capsuleTypeOption : int the option that represents the type of the capsule.
     */ 
    public KnowledgeCapsule(String id, String description, Collaborator collaborator, String learnedLesson, int capsuleTypeOption){

        this.id = id;
        this.description = description;
        this.collaborator = collaborator;
        this.learnedLesson = learnedLesson;
        this.approvalStatus = false;
        this.hashtags = new ArrayList<>();

        extractHashtags();
        setCapsulesType(capsuleTypeOption);
    }

    /**
     * Extracts hashtags from the capsules attributes description and learned lesson and adds them to a list.
     * Hashtags are identified using the '#' symbol, and are defined as any text between two '#' symbols.
     * The extracted hashtags are added to the 'hashtags' list if they are not already present.
     */
    private void extractHashtags(){
        char[] descriptionCharArray = description.toCharArray();
        char[] learnedLessonCharArray = learnedLesson.toCharArray();
        int contHashDescription= 0;
        int contHashLearnedLesson = 0;
        String word = "";
        

        for(int i = 0; i<descriptionCharArray.length; i++){
            if(descriptionCharArray[i] == '#'){
                contHashDescription++;
            }else if(contHashDescription%2 != 0){
                word += descriptionCharArray[i];
            }else{
                hashtags.add(word);
                word = "";
            }
            
        }

        for(int i = 0; i<learnedLessonCharArray.length; i++){
          
            if(learnedLessonCharArray[i] == '#'){
                contHashLearnedLesson++;
            }else if(contHashLearnedLesson%2 != 0){
                word += learnedLessonCharArray[i];
            }else{
                hashtags.add(word);
                word = "";
            }
            
        }
    }

    /**
     * This method sets the type of the capsule.
     * @param pos : int the option that represents the type of the capsule.
     */
    private void setCapsulesType(int pos){

        switch(pos){
            case 1:
            type = CapsuleType.TECHNICAL.getType();
            break;

            case 2:
            type = CapsuleType.MANAGEMENT.getType();
            break;

            case 3:
            type = CapsuleType.DOMAIN.getType();
            break;

            case 4:
            type = CapsuleType.EXPERIENCES.getType();
            break;
        }
    }

    /**
     * this method consults the current status information of the attribute id.
     * @return : String the id of the capsule.
     */
    public String getId(){
        return id;
    }

    /**
     * this method consults the current status information of the attribute type.
     * @return : String the type of the capsule.
     */
    public String getType(){
        return type;
    }

    /**
     * this method consults the current status information of the attribute description.
     * @return : String the description described in the capsule.
     */
    public String getDescription(){
        return description;
    }

    /**
     * this method consults the current status information of the attribute collaborator.
     * @return : Collaborator the collaborator that registered the capsule.
     */
    public Collaborator getCollaborator(){
        return collaborator;
    }

    /**
     * this method consults the current status information of the attribute learned lesson.
     * @return : String the learned lesson described in the capsule.
     */
    public String getLearnedLesson(){
        return learnedLesson;
    }

    /**
     * this method consults the current status information of the attribute approval status.
     * @return : boolean the approval status of the capsule. 
     */
    public boolean getApprovalStatus(){
        return approvalStatus;
    }

    /**
     * this method consults the current status information of the list of hashtags extracted from an capsule's description and learned lesson.
     * @return the list of hashtags.
     */
    public List<String> getHashtags() {
        return hashtags;
    }

    /**
     * this method consults the current status information of the date when the knowledge capsule was approved.
     * @return : Calendar the date when the knowledge capsule was approved.
     */
    public Calendar getApprovalDate(){
        return approvalDate;
    }

    /**
     * changes the current approval status attribute information.
     * @param newStatus : boolean the new approval status.
     */
    public void setApprovalStatus(boolean newStatus){
        approvalStatus = newStatus;
    }

    /**
     * changes the current approval date attribute information.
     * @param date : Calendar the new date.
     */
    public void setApprovalDate(Calendar date){
        approvalDate = date;
    }

}
