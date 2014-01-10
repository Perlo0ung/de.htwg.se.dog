package de.htwg.se.dog.models;

/**
 * Interface for a card
 * 
 * @author Michael
 * 
 */
public interface CardInterface extends Comparable<CardInterface> {
    /**
     * returns the Value of the card
     * 
     * @return int: value of card between [0,14]
     */
    int getValue();

    /**
     * Return the CardName of a Card
     * 
     * @param value
     *            : Int value of the card
     * @return String: the cardname as a string
     */
    String getCardName();

    /**
     * returns a hashcode for this card
     * 
     * @return
     */
    @Override
    int hashCode();

    /**
     * Tests if two cards a equals
     * 
     * @param obj
     * @return
     */
    @Override
    boolean equals(Object obj);
}
