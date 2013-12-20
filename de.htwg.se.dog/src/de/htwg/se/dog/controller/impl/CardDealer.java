package de.htwg.se.dog.controller.impl;

import com.google.inject.Inject;

import de.htwg.se.dog.controller.CardDealerInterface;
import de.htwg.se.dog.models.CardStackInterface;
import de.htwg.se.dog.models.PlayerInterface;
import de.htwg.se.dog.models.impl.CardStack;

public class CardDealer implements CardDealerInterface {
    private CardStackInterface stackObj;
    private static final int MAXCARDS = 6;
    private static final int MAXROUND = 5;
    private final int players;

    private static final int CARDS = 12;
    private int round = 0;

    /**
     * 
     * @param players
     */
    @Inject
    public CardDealer(int players) {
        this.players = players;
        stackObj = new CardStack(CARDS, players);
    }

    /**
     * 
     * @param p
     */
    @Override
    public void dealCards(PlayerInterface p) {
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
    @Override
    public void newRound() {
        round = (round + 1) % MAXROUND;
    }

    /**
     * returns roundNumber
     * 
     * @return int: roundnumber
     */
    @Override
    public int getRound() {
        return this.round;
    }

    /**
     * Returns number of cards used this cards
     * 
     * @return int: cards to be dealed this round
     */
    @Override
    public int expectedNumOfCards() {
        return (MAXCARDS - round) * players;
    }

    /**
     * returns the cardstack-OBJ
     * 
     * @return CardStack: current cardstack
     */
    @Override
    public CardStackInterface getObject() {
        return stackObj;
    }
}
