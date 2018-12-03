package com.example.jeff.mtgpricechecker.EchoMTG;

/**
 * Created by Jeff on 12/3/2018.
 */

// Container for main menu set info

public class Set {
    private String setname;
    private String svguri;

    public Set() {
        setname = null;
        svguri = null;
    }
    public void setSetname(String setname) {
        this.setname = setname;
    }
    public void setSvguri(String svguri) {
        this.svguri = svguri;
    }
    public String getSetname() {
        return this.setname;
    }
    public String getSvguri() {
        return this.svguri;
    }
}
