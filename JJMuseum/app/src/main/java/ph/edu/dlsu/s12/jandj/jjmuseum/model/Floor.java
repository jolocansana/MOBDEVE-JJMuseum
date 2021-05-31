package ph.edu.dlsu.s12.jandj.jjmuseum.model;

public class Floor {
    private String ID, Name, Description, Floorplan;

    /*
     * Floor
     * constructs the Floor object
     * String ID - unique id of the floor
     * String name - name of the floor
     * String description - description of the floor
     * String floorplan - unique id of the floorplan image
     * void
     */
    public Floor(String ID, String name, String description, String floorplan) {
        this.ID = ID;
        Name = name;
        Description = description;
        Floorplan = floorplan;
    }

    /*
     * getID
     * gets the ID string
     * void
     * ID string
     */
    public String getID() {
        return ID;
    }

    /*
     * setID
     * sets the ID string
     * String ID - unique id of the collection
     * void
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /*
     * getName
     * gets the Floor Name string
     * void
     * Name string
     */
    public String getName() {
        return Name;
    }

    /*
     * setName
     * sets the Floor Name string
     * String name - name of the floor
     * void
     */
    public void setName(String name) {
        Name = name;
    }

    /*
     * getDescription
     * gets the Floor Description string
     * void
     * Description string
     */
    public String getDescription() {
        return Description;
    }

    /*
     * setDescription
     * sets the Floor Description string
     * String description - description of the floor
     * void
     */
    public void setDescription(String description) {
        Description = description;
    }

    /*
     * getFloorplan
     * gets the Floorplan image string
     * void
     * Floorplan string
     */
    public String getFloorplan() {
        return Floorplan;
    }

    /*
     * setFloorplan
     * sets the Floorplan image string
     * String floorplan - unique id of the floorplan image
     * void
     */
    public void setFloorplan(String floorplan) {
        Floorplan = floorplan;
    }
}

