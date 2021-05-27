package ph.edu.dlsu.s12.jandj.jjmuseum.model;

import java.io.Serializable;

public class Comment implements Serializable {
    private String id, username, text;

    public Comment(String id, String username, String text) {
        this.id = id;
        this.username = username;
        this.text = text;
    }

    public Comment(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
