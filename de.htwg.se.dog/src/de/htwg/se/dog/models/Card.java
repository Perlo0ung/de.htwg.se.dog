package de.htwg.se.dog.models;

public class Card {

    private final int value;
    private static final int ZERO = 0;
    private static final int FOURTEEN = 14;

    private static final String[] CARDNAMES = { "Ass", "Zwei", "Dre", "Vier", "Fuenf", "Sechs", "Sieben", "Acht", "Neun", "Zehn", "Bube", "Dame", "Koenig", "Joker" };

    /**
     * Constructor to create a new card
     * 
     * @param value
     *            : int, is the value of the card
     */
    public Card(int value) {
        if (value > ZERO && value <= FOURTEEN) {
            this.value = value;
        } else {
            throw new IllegalArgumentException("Failed to create card with value:" + value);
        }
    }

    /**
     * returns the Value of the card
     * 
     * @return int: value of card between [0,14]
     */
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
    public String getCardName(int value) {
        return CARDNAMES[value - 1];
    }
}
