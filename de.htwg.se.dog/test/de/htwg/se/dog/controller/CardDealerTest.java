package de.htwg.se.dog.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.dog.controller.impl.CardDealer;
import de.htwg.se.dog.models.impl.Player;

public class CardDealerTest {
    private static final int NUMCARDS = 6;
    private static final int ZERO = 0;
    private static final int PLAYERS = 4;
    de.htwg.se.dog.models.impl.Player p;
    CardDealer dealer;

    @Before
    public void setUp() {
        p = new Player(0, 0);
        dealer = new CardDealer(PLAYERS);
    }

    @Test
    public void testDealCards() {
        dealer.dealCards(p);
        assertEquals(p.getCardList().size(), NUMCARDS); //6 Karten
        dealer.newRound();
        dealer.newRound();
        dealer.newRound();
        p.getCardList().clear();
        dealer.dealCards(p);
        assertEquals(p.getCardList().size(), NUMCARDS - 3); //3 Karten
        p.getCardList().clear();
        dealer.newRound();
        dealer.newRound();
        dealer.dealCards(p);
        dealer.getObject().getStack().clear();
        assertEquals(dealer.getObject().getSize(), ZERO);
        dealer.dealCards(p);
        assertEquals(dealer.getObject().getSize(), 54 - 6);
    }

    @Test
    public void testNewRound() {
        assertEquals(dealer.getRound(), ZERO);
    }
}
