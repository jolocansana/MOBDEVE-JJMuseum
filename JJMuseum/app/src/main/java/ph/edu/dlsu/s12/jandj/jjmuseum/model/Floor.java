package ph.edu.dlsu.s12.jandj.jjmuseum.model;

public class Floor {
    private String ID, Name, Description, Floorplan;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Floor(String ID, String name, String description, String floorplan) {
        this.ID = ID;
        Name = name;
        Description = description;
        Floorplan = floorplan;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getFloorplan() {
        return Floorplan;
    }

    public void setFloorplan(String floorplan) {
        Floorplan = floorplan;
    }
}

