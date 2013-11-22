package de.htwg.se.dog.controller;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

import de.htwg.se.dog.models.Card;
import de.htwg.se.dog.models.Player;

public class Turn {
    final LinkedList<Player> players;
    ArrayDeque<Player> turn;
    CardDealer dealer;
    Player currentPlayer;
    
    /**
     * Standard Konstruktor
     * @param playerCount
     * @param figCount
     */
    public Turn(int playerCount, int figCount) {
        players = new LinkedList<Player>();
        dealer = new CardDealer(playerCount);
        addPlayers(playerCount, figCount);
    }
    
    /**
     * Add all Players to playerlist
     * @param playerCount
     * @param figCount
     */
    private void addPlayers(int playerCount, int figCount) {
        for (int i = 1; i <= playerCount; i++) {
            players.add(new Player(playerCount, figCount));
        }
    }
    
    /**
     * Deal new cards;
     */
    public void newRound() {
        for (Player p : players) {
            dealer.dealCards(p);
        }
        dealer.newRound();
        turn = new ArrayDeque<Player>(players);
    }
    /**
     *     
     * @return the current Player
     */
    public Player getCurrentPlayer() {
        currentPlayer = turn.pollFirst();
        return currentPlayer;
    }
    /**
     * TODO
     * @param p
     * @return
     */
    public boolean canPlay(Player p) {
        boolean returnval = false;
        List<Card> cards = p.getCardList();
        if (cards.isEmpty()) {
            return returnval;
        }
        for (Card c: cards) {
            for (Integer field : p.getFigureRegister()){
                Movement.validMove();
            }
        }
    }
    
}
