package de.htwg.se.dog.controller;


import de.htwg.se.dog.models.Player;

public class Turn {
    private GameTable table; 
    /**
     * Standard Konstruktor
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
    public Player getCurrentPlayer() {
        return table.getCurrentPlayer();
    }
}
