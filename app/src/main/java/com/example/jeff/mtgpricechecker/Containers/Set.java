package com.example.jeff.mtgpricechecker.Containers;

/**
 * Created by Jeff on 12/3/2018.
 */

// Container for main menu set info

public class Set {
    private String setCode;
    private String setName;
    private String svguri;

    private int image;

    public Set() {
        setCode = null;
        svguri = null;
        setName = null;
        image = 0;
    }
    public void setSetCode(String setCode) {
        this.setCode = setCode;
    }
    public void setSetName(String setName) {
        this.setName = setName;
    }
    public void setSvguri(String svguri) {
        this.svguri = svguri;
    }
    public void setImage(int image) {
        this.image = image;
    }
    public String getSetCode() {
        return this.setCode;
    }
    public String getSetName() {
        return this.setName;
    }
    public String getSvguri() {
        return this.svguri;
    }

    public int getImage() {
        return image;
    }
}
