package ph.edu.dlsu.s12.jandj.jjmuseum.controllers;

public class Pebble {
    private String background_image, text_caption;

    /*
     * Pebble
     * constructs the Pebble object
     * String background_image - unique id of the background image
     * String text_caption - set of strings used for the text caption
     * void
     */
    public Pebble(String background_image, String text_caption) {
        this.background_image = background_image;
        this.text_caption = text_caption;
    }

    /*
     * getBackground_image
     * gets the background_image string
     * void
     * background_image string
     */
    public String getBackground_image() {
        return background_image;
    }

    /*
     * setBackground_image
     * sets the background_image string
     * String background_image - unique id of the background image
     * void
     */
    public void setBackground_image(String background_image) {
        this.background_image = background_image;
    }

    /*
     * getText_caption
     * gets the text_caption string
     * void
     * text_caption string
     */
    public String getText_caption() {
        return text_caption;
    }

    /*
     * setText_caption
     * sets the text_caption string
     * String text_caption - set of strings used for the text caption
     * void
     */
    public void setText_caption(String text_caption) {
        this.text_caption = text_caption;
    }
}
