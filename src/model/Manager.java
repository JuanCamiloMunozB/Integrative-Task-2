package model;

/**
 * This represents the manager
 */
public class Manager {

    /**
     * This represents the manager name
     */
    private String name;

    /**
     * This represents the managet cellphone
     */
    private String cellphone;

    //The constructor method
    public Manager(String name, String cellphone){
        this.name = name;
        this.cellphone = cellphone;
    }

    /**
     * this method consults the current status information of the attribute name.
     * @return : String the manager's name.
     */
    public String getName(){
        return name;
    }

    /**
     * this method consults the current status information of the attribute cellphone.
     * @return : String the manager's cellphone.
     */
    public String getCellphone(){
        return cellphone;
    }

}