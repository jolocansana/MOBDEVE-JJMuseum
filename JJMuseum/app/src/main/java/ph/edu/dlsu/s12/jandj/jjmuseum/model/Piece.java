package ph.edu.dlsu.s12.jandj.jjmuseum.model;

import java.util.ArrayList;

public class Piece {
    private String ID, Name, Collection, CollectionID, Time, Description;
    private ArrayList<String> Asset;

    public Piece(String ID, String name, String collection, String collectionID, String time, String description, ArrayList<String> asset) {
        this.ID = ID;
        Name = name;
        Collection = collection;
        CollectionID = collectionID;
        Time = time;
        Description = description;
        Asset = asset;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCollection() {
        return Collection;
    }

    public void setCollection(String collection) {
        Collection = collection;
    }

    public String getCollectionID() {
        return CollectionID;
    }

    public void setCollectionID(String collectionID) {
        CollectionID = collectionID;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getAsset(int index) {
        return Asset.get(index);
    }
}
