package ph.edu.dlsu.s12.jandj.jjmuseum.model;

public class Collection {
    private String ID, Collection, Description, Header;

    public Collection(String ID, String collection, String description, String header) {
        this.ID = ID;
        this.Collection = collection;
        this.Description = description;
        this.Header = header;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCollection() {
        return Collection;
    }

    public void setCollection(String collection) {
        this.Collection = collection;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getHeader() {
        return Header;
    }

    public void setHeader(String header) {
        this.Header = header;
    }
}
