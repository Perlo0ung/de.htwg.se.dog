package de.htwg.se.dog.models.impl;

import de.htwg.se.dog.models.CardInterface;

public class Card implements CardInterface {

    private final int value;
    private static final int ONE = 1;
    private static final int FOURTEEN = 14;

    private static final String[] CARDNAMES = { "Ass", "Zwei", "Drei", "Vier", "Fuenf", "Sechs", "Sieben", "Acht", "Neun", "Zehn", "Bube", "Dame", "Koenig", "Joker" };

    /**
     * Constructor to create a new card
     * 
     * @param value
     *            : int, is the value of the card
     */
    public Card(int value) {
        if (value < ONE || value > FOURTEEN) {
            throw new IllegalArgumentException("Failed to create card with value:" + value);
        } else {
            this.value = value;
        }
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
        return String.format("%2d: %-6s", this.value, getCardName());
    }

    @Override
    public int compareTo(CardInterface o) {
        return ((Integer) this.value).compareTo(o.getValue());
    }
}
