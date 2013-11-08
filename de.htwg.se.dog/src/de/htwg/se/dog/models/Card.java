package de.htwg.se.dog.models;

public class Card {

    private final int value;
    private static final int ZERO = 0;
    private static final int FOURTEEN = 14;
    
    private static final String[] CARDNAMES = {"Ass","Zwei","Dre","Vier","Fuenf"
            ,"Sechs","Sieben","Acht","Neun","Zehn","Bube","Dame","Koenig","Joker"};

    
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
        return CARDNAMES[value-1];
    }
}
