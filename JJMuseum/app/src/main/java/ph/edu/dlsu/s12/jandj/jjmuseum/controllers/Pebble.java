package ph.edu.dlsu.s12.jandj.jjmuseum.controllers;

public class Pebble {
    private String background_image, text_caption;

    public Pebble(String background_image, String text_caption) {
        this.background_image = background_image;
        this.text_caption = text_caption;
    }

    public String getBackground_image() {
        return background_image;
    }

    public void setBackground_image(String background_image) {
        this.background_image = background_image;
    }

    public String getText_caption() {
        return text_caption;
    }

    public void setText_caption(String text_caption) {
        this.text_caption = text_caption;
    }
}
