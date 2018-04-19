package com.example.jeff.mtgpricechecker.EchoMTG;

/**
 * Created by Jeff on 4/16/2018.
 */

public class Card {

    private String cardname;
    private String rarity;
    private String set;

    public Card(String cardname, String rarity, String set) {
        this.cardname = cardname;
        this.rarity = rarity;
        this.set = set;
    }

    public Card() {

    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }
}
