package de.htwg.se.dog.models.impl;

import de.htwg.se.dog.models.CardInterface;

/**
 * implementation of cardinterface
 * 
 * @author Michael
 * 
 */
public class Card implements CardInterface {

    private final int value;
    private static final int PRIM = 31;

    public static final String[] CARDNAMES = { "Ass", "Zwei", "Drei", "Vier", "Fuenf", "Sechs", "Sieben", "Acht", "Neun", "Zehn", "Bube", "Dame", "Koenig", "Joker" };

    /**
     * Constructor to create a new card
     * 
     * @param value
     *            : int, is the value of the card
     */
    public Card(int value) {
            this.value = value;
    }

    /**
     * returns the Value of the card
     * 
     * @return int: value of card between [0,14]
     */
    @Override
    public int getValue() {
        return this.value;
    }

    /**
     * Return the CardName of a Card
     * 
     * @param value
     *            : Int value of the card
     * @return String: the cardname as a string
     */
    @Override
    public String getCardName() {
        return CARDNAMES[this.value - 1];
    }

    @Override
    public String toString() {
        return String.format("%s(%d)", getCardName(), this.value);
    }

    @Override
    public int compareTo(CardInterface o) {
        return ((Integer) this.value).compareTo(o.getValue());
    }

    @Override
    public int hashCode() {
        return PRIM + value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Card)) {
            return false;
        }
        Card other = (Card) obj;
        if (value != other.value) {
            return false;
        }
        return true;
    }

}
