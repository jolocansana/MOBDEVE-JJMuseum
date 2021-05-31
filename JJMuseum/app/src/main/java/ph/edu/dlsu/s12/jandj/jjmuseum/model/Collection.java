package ph.edu.dlsu.s12.jandj.jjmuseum.model;

public class Collection {
    private String ID, Collection, Description, Header;

    /*
     * Collection
     * constructs the Collection object
     * String ID - unique id of the collection
     * String collection - name of the collection
     * String description - description of the collection
     * String header - unique id of the header image
     * void
     */
    public Collection(String ID, String collection, String description, String header) {
        this.ID = ID;
        this.Collection = collection;
        this.Description = description;
        this.Header = header;
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
     * getCollection
     * gets the Collection Name string
     * void
     * Collection string
     */
    public String getCollection() {
        return Collection;
    }

    /*
     * setCollection
     * sets the Collection Name string
     * String collection - name of the collection
     * void
     */
    public void setCollection(String collection) {
        this.Collection = collection;
    }

    /*
     * getDescription
     * gets the Collection Description string
     * void
     * Description string
     */
    public String getDescription() {
        return Description;
    }

    /*
     * setDescription
     * sets the Collection Description string
     * String description - description of the collection
     * void
     */
    public void setDescription(String description) {
        this.Description = description;
    }

    /*
     * getHeader
     * gets the Collection Header string
     * void
     * Header string
     */
    public String getHeader() {
        return Header;
    }

    /*
     * setHeader
     * sets the Collection Header string
     * String header - unique id of the header image
     * void
     */
    public void setHeader(String header) {
        this.Header = header;
    }
}
