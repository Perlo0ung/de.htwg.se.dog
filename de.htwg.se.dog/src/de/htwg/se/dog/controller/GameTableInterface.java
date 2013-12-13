package de.htwg.se.dog.controller;

import java.util.List;

import de.htwg.se.dog.models.CardInterface;
import de.htwg.se.dog.models.GameFieldInterface;
import de.htwg.se.dog.models.PlayerInterface;

public interface GameTableInterface {

    /**
     * Returns the Gamefield currently playing on
     * 
     * @return
     */
    public GameFieldInterface getGameField();

    /**
     * Returns weather the playerqueue is empty or not
     * 
     * @return true if empty otherwise false
     */
    boolean playerQueueIsEmpty();

    /**
     * Sets the player that can play first
     * 
     * @param playernum
     */
    void setStartingPlayer(int playernum);

    /**
     * Refills the Playerqueue and deals cards to every player
     */
    void dealCards();

    /**
     * Starts a new round
     */
    void newRound();

    /**
     * Returns the player that can play now
     * 
     * @return the player that is allowed to play
     */
    PlayerInterface getNextPlayer();

    /**
     * Adds the speciefied Player to the Playerqueue
     */
    void addPlayerToQueue(PlayerInterface p);

    /**
     * Returns true if the Player has a card that can be played
     * 
     * @param p
     *            the Player that wants to play
     * @return true if he can play, otherwise false
     */
    boolean canPlay(PlayerInterface p);

    /**
     * Returns a list containing the cards that can be played by Player p
     * 
     * @param p
     *            the player that wants to play
     * @return a list containing the cards that can be played
     */
    List<CardInterface> possibleCards(PlayerInterface p);

    boolean playerHaswon(GameFieldInterface gamefield, PlayerInterface player);
}
