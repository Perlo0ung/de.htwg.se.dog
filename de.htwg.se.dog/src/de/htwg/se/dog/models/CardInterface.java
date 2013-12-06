package de.htwg.se.dog.models;

public interface CardInterface {
    /**
     * returns the Value of the card
     * 
     * @return int: value of card between [0,14]
     */
    public int getValue();

    /**
     * Return the CardName of a Card
     * 
     * @param value
     *            : Int value of the card
     * @return String: the cardname as a string
     */
    public String getCardName(int value);
}
