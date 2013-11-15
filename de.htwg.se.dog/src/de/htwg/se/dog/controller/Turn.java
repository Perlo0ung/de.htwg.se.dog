package de.htwg.se.dog.controller;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import de.htwg.se.dog.models.Player;

public class Turn {
    final LinkedList<Player> players;
    ArrayDeque<Player> turn;
    CardDealer dealer;
    
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
     * Remove the current Player from the list    
     * @return the current Player
     */
    public Player getCurrentPlayer() {
        return turn.pollFirst();
    }
}
