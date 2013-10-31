package de.htwg.se.dog.models;

public class Card {

    private final int value;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int ELEVEN = 11;
    private static final int TWELVE = 12;
    private static final int THIRTEEN = 13;
    private static final int FOURTEEN = 14;

    protected Card(int value) {
        if (value > ZERO && value <= FOURTEEN) {
            this.value = value;
        } else {
            throw new IllegalArgumentException(
                    "Failed to create card with value:" + value);
        }
    }

    /* return value of a card */
    public int getValue() {
        return this.value;
    }

    /*
     * Return the CardName of a Card
     * 
     * @param value
     * 
     * @return the cardname as a string
     */
    public String getCardName(int value) {
        String str = "" + value;
        if (value == ONE) {
            str = "Ass";
        } else if (value == ELEVEN) {
            str = "Bube";
        } else if (value == TWELVE) {
            str = "Dame";
        } else if (value == THIRTEEN) {
            str = "König";
        } else if (value == FOURTEEN) {
            str = "JOKER";
        }
        return str;
    }
}
