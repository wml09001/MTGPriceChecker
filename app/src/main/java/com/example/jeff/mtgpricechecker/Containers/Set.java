package com.example.jeff.mtgpricechecker.Containers;

/**
 * Created by Jeff on 12/3/2018.
 */

// Container for main menu set info

public class Set {
    private String setname;
    private String svguri;

    private int image;

    public Set() {
        setname = null;
        svguri = null;
        image = 0;
    }
    public void setSetname(String setname) {
        this.setname = setname;
    }
    public void setSvguri(String svguri) {
        this.svguri = svguri;
    }
    public void setImage(int image) {
        this.image = image;
    }
    public String getSetname() {
        return this.setname;
    }
    public String getSvguri() {
        return this.svguri;
    }
    public int getImage() {
        return image;
    }
}
