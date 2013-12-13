package de.htwg.se.dog.controller;

import java.util.List;

import de.htwg.se.dog.models.CardInterface;
import de.htwg.se.dog.models.GameFieldInterface;
import de.htwg.se.dog.models.PlayerInterface;
import de.htwg.se.dog.util.IObservable;

public interface GameTableInterface extends IObservable {

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
     * Choose the next Player
     */
    void nextPlayer();

    /**
     * returns the current playing Player
     * 
     * @returns the currentPlayer
     */
    PlayerInterface getCurrentPlayer();

    /**
     * Returns true if the Player has a card that can be played
     * 
     * @param p
     *        the Player that wants to play
     * @return true if he can play, otherwise false
     */
    boolean canPlay(PlayerInterface p);

    /**
     * Returns a list containing the cards that can be played by Player p
     * 
     * @param p
     *        the player that wants to play
     * @return a list containing the cards that can be played
     */
    List<CardInterface> possibleCards(PlayerInterface p);

    /**
     * Tells if the specified Player has won the game
     * 
     * @param gamefield
     * @param player
     * @return true if player has won otherwise false
     */
    boolean playerHaswon(GameFieldInterface gamefield, PlayerInterface player);

    /**
     * returns the GameField as a string
     * 
     * @return
     */
    String getGameFieldString();

    /**
     * returns the Players cardlist as a String
     * 
     * @return
     */
    String getPlayerHandString();

    /**
     * returns the Players as a String
     * 
     * @return
     */
    String getPlayerString();

    /**
     * Tells if the Player has a Card with speciefied value
     * 
     * @param cardval
     * @return true if he has the card
     */
    boolean playerHasCard(int cardval);
}
