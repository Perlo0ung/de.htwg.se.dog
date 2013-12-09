package de.htwg.se.dog.controller;

import de.htwg.se.dog.models.PlayerInterface;

public class Turn {
    private final GameTable table;

    /**
     * Standard Konstruktor
     * 
     * @param playerCount
     * @param figCount
     */
    public Turn(int playerCount, int figCount) {
        table = new GameTable(playerCount, figCount);
    }

    /**
     * Deal new cards;
     */
    public void newRound() {
        table.newRound();
    }

    /**
     * 
     * @return the current Player
     */
    public PlayerInterface getNextPlayer() {
        return table.getNextPlayer();
    }
}
