package ph.edu.dlsu.s12.jandj.jjmuseum.model;

import java.util.ArrayList;

public class Piece {
    private String ID, Name, Collection, CollectionID, Time, Description, Header;
    private ArrayList<String> Asset;

    /*
     * Piece
     * constructs the Piece object
     * String ID - unique id of the piece
     * String name - name of the piece
     * String collection - collection of the piece
     * String time - time period of the piece
     * String description - description of the collection
     * String header - unique id of the piece header image
     * ArrayList<String> asset - arraylist of images under the piece
     * void
     */
    public Piece(String ID, String name, String collection, String collectionID, String time, String description, String header, ArrayList<String> asset) {
        this.ID = ID;
        Name = name;
        Collection = collection;
        CollectionID = collectionID;
        Time = time;
        Description = description;
        Asset = asset;
        Header = header;
    }

    /*
     * getHeader
     * gets the Piece Header string
     * void
     * Header string
     */
    public String getHeader() {
        return Header;
    }

    /*
     * setHeader
     * sets the Piece Header string
     * String header - unique id of the piece header image
     * void
     */
    public void setHeader(String header) {
        Header = header;
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
     * String ID - unique id of the piece
     * void
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /*
     * getName
     * gets the Piece Name string
     * void
     * Name string
     */
    public String getName() {
        return Name;
    }

    /*
     * setName
     * sets the Piece Name string
     * String name - name of the piece
     * void
     */
    public void setName(String name) {
        Name = name;
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
        Collection = collection;
    }

    /*
     * getID
     * gets the ID string
     * void
     * collectionID string
     */
    public String getCollectionID() {
        return CollectionID;
    }

    /*
     * setID
     * sets the ID string
     * String collectionID - unique id of the collection
     * void
     */
    public void setCollectionID(String collectionID) {
        CollectionID = collectionID;
    }

    /*
     * getTime
     * gets the Time string
     * void
     * Time string
     */
    public String getTime() {
        return Time;
    }

    /*
     * setTime
     * sets the Time string
     * time string
     * void
     */
    public void setTime(String time) {
        Time = time;
    }

    /*
     * getDescription
     * gets the Piece Description string
     * void
     * Description string
     */
    public String getDescription() {
        return Description;
    }

    /*
     * setDescription
     * sets the Piece Description string
     * String description - description of the piece
     * void
     */
    public void setDescription(String description) {
        Description = description;
    }

    /*
     * getAsset
     * gets the image id from the given position of the arraylist
     * int index
     * String image ID at index position
     */
    public String getAsset(int index) {
        return Asset.get(index);
    }

    /*
     * getAssets
     * gets the arraylist of images of the piece
     * void
     * ArrayList<String> Asset - arraylist of images under the piece
     */
    public ArrayList<String> getAssets(){ return Asset;}
}
