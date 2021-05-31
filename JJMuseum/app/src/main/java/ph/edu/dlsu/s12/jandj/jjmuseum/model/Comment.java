package ph.edu.dlsu.s12.jandj.jjmuseum.model;

import java.io.Serializable;

public class Comment implements Serializable {
    private String id, username, text;

    /*
     * Comment
     * constructs the Comment object
     * String id - unique id of the piece
     * String username - username of the commenter
     * String text - text of the comment
     * void
     */
    public Comment(String id, String username, String text) {
        this.id = id;
        this.username = username;
        this.text = text;
    }

    /*
     * getUsername
     * gets the username string
     * void
     * username string
     */
    public String getUsername() {
        return username;
    }

    /*
     * setUsername
     * sets the username string
     * String username - username of the commenter
     * void
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /*
     * getText
     * gets the text string
     * void
     * text string
     */
    public String getText() {
        return text;
    }

    /*
     * setText
     * sets the text string
     * String text - text of the comment
     * void
     */
    public void setText(String text) {
        this.text = text;
    }

    /*
     * getId
     * gets the id string
     * void
     * id string
     */
    public String getId() {
        return id;
    }

    /*
     * setId
     * sets the id string
     * String id - unique id of the piece
     * void
     */
    public void setId(String id) {
        this.id = id;
    }
}
