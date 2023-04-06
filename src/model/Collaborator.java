package model;

/**
 * This class represents the collaborator.
 */
public class Collaborator {

    /**
     * represents the name of the collaborator
     */
    private String name;

    /**
     * represents the position of the collaborator
     */
    private String position;

    //The constructor method
    public Collaborator(String name, String position){
        this.name = name;
        this.position = position;
    }

    /**
     * this method consults the current status information of the attribute name.
     * @return : String the collaborators name.
     */
    public String getName(){
        return name;
    }

    /**
     * this method consults the current status information of the attribute description.
     * @return : String the collaboratos position.
     */
    public String getPosition(){
        return position;
    }
}


