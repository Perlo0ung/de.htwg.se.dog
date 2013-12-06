package de.htwg.se.dog.controller;

import de.htwg.se.dog.models.impl.CardStack;
import de.htwg.se.dog.models.impl.Player;

public class CardDealer {
    private CardStack stackObj;
    private static final int MAXCARDS = 6;
    private static final int MAXROUND = 5;
    private final int players;

    private static final int CARDS = 12;
    private int round = 0;

    /**
     * 
     * @param players
     */
    public CardDealer(int players) {
        this.players = players;
        stackObj = new CardStack(CARDS, players);
    }

    /**
     * 
     * @param p
     */
    public void dealCards(Player p) {
        if (expectedNumOfCards() > stackObj.getSize()) {
            stackObj = new CardStack(CARDS, players);
        }
        for (int i = 0; i < (MAXCARDS - round); i++) {
            p.addCard(stackObj.dealCard(0, stackObj.getSize()));
        }
    }

    /**
     * increments the roundcounter between 1 and Maxround
     */
    public void newRound() {
        round = (round + 1) % MAXROUND;
    }

    /**
     * returns roundNumber
     * 
     * @return int: roundnumber
     */
    public int getRound() {
        return this.round;
    }

    /**
     * Returns number of cards used this cards
     * 
     * @return int: cards to be dealed this round
     */
    public int expectedNumOfCards() {
        return (MAXCARDS - round) * players;
    }

    /**
     * returns the cardstack-OBJ
     * 
     * @return CardStack: current cardstack
     */
    public CardStack getObject() {
        return stackObj;
    }
}
