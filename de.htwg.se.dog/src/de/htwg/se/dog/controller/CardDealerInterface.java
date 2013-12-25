package de.htwg.se.dog.controller;

import de.htwg.se.dog.models.CardStackInterface;
import de.htwg.se.dog.models.PlayerInterface;

public interface CardDealerInterface {
    /**
     * 
     * @param p
     */
    void dealCards(PlayerInterface p);

    /**
     * increments the roundcounter between 1 and Maxround
     */
    void newRound();

    /**
     * returns roundNumber
     * 
     * @return int: roundnumber
     */
    int getRound();

    /**
     * Returns number of cards used this cards
     * 
     * @return int: cards to be dealed this round
     */
    int expectedNumOfCards();

    /**
     * returns the cardstack-OBJ
     * 
     * @return CardStack: current cardstack
     */
    CardStackInterface getObject();
}
