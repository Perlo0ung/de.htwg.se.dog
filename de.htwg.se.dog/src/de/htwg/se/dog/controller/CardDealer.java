package de.htwg.se.dog.controller;

import de.htwg.se.dog.models.*;

public class CardDealer {
    private CardStack stackObj;
    private static final int MAXCARDS = 6;
    private static final int MAXROUND = 5;
    private final int players;

    private static final int CARDS = 12;
    private int round = 0;

    public CardDealer(int players) {
        this.players = players;
        stackObj = new CardStack(CARDS, players);
    }

    public void dealCards(Player p) {
        if (expectedNumOfCards() > stackObj.getSize()) {
            stackObj = new CardStack(CARDS, players);
        }
        for (int i = 0; i < (MAXCARDS - round); i++) {
            p.addCard(stackObj.dealCard(0, stackObj.getSize()));
        }
    }

    public void newRound() {
        round = (round + 1) % MAXROUND;
    }

    public int getRound() {
        return this.round;
    }

    public int expectedNumOfCards() {
        return (MAXCARDS - round) * players;
    }

    public CardStack getObject() {
        return stackObj;
    }
}
