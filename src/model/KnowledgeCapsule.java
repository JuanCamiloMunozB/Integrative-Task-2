package model;

//java library
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    //The constructor method. 
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
        // Create a pattern to match hashtags in the description and learned lesson
        Pattern pattern = Pattern.compile("#(.*?)#");

        // Create a matcher object for the pattern and apply it to the description and learned 
        Matcher matcher = pattern.matcher(description + learnedLesson);

        // Iterate over the matches and add them to the 'hashtags' list
        while (matcher.find()) {
            String hashtag = matcher.group(1);
            if (!hashtags.contains(hashtag)) {
                hashtags.add(hashtag);
            }
        }
    }

    /**
     * This method sets the type of the capsule.
     * @param pos : int the option that represents the type of the capsule.
     */
    private void setCapsulesType(int pos){

        switch(pos){
            case 0:
            type = CapsuleType.TECHNICAL.getType();
            break;

            case 1:
            type = CapsuleType.MANAGEMENT.getType();
            break;

            case 2:
            type = CapsuleType.DOMAIN.getType();
            break;

            case 3:
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
