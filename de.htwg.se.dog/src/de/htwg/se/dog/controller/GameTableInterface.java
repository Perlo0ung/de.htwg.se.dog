package de.htwg.se.dog.controller;

import java.util.List;

import de.htwg.se.dog.models.CardInterface;
import de.htwg.se.dog.models.GameFieldInterface;
import de.htwg.se.dog.models.PlayerInterface;
import de.htwg.se.dog.util.IObservable;

/**
 * Interface for the GameController
 * 
 * @author Michael,Christian
 * 
 */
public interface GameTableInterface extends IObservable {

    /**
     * Returns the playerlist
     * 
     * @return
     */
    List<PlayerInterface> getPlayerList();

    /**
     * Returns the Gamefield currently playing on
     * 
     * @return
     */
    GameFieldInterface getGameField();

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
     * returns the playerid of current player
     * 
     * @return the playerid
     */
    int getCurrentPlayerID();

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
    boolean currentPlayerHaswon();

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

    /**
     * Wraps the Movement
     * 
     * @param cardNr
     * @param steps
     * @param startFieldNr
     * @return true, if move was possible, otherwise false
     */
    boolean playCard(int cardNr, int steps, int startFieldNr);

    /**
     * Returns the round playing in
     * 
     * @return the round
     */
    int getRound();

    /**
     * delegates to movement, returns the tagetfield for a move
     * 
     * @param steps
     * @param startfieldnr
     * @return
     */
    int getTargetField(int steps, int startfieldnr);

    /**
     * returns wheater the field is empty or not
     * 
     * @param fieldnr
     *        the field to be empty
     * @return true if empty otherwise false
     */
    boolean fieldIsEmpty(int fieldnr);

    /**
     * Returns the owner for the figure on field given with fieldnr
     * 
     * @param fieldnr
     *        the field
     * @return
     */
    int getFigureOwnerID(int fieldnr);

    /**
     * move a figure to startpoint
     * 
     * @param card
     *        the card that is used for the startmove
     * @return true if movestart was possible
     */
    boolean moveFigureToStart(int card);

    /**
     * is the players startfield blocked?
     * 
     * @return true if blocked, else false
     */
    boolean isPlayerStartfieldBlocked();

    /**
     * checks if the moves in moves map are valid
     * 
     * @param cardNr
     *        the card that was used
     * @param moves
     *        maps with moves
     * @return true if the moves are valid
     */
    boolean isValidMove(int cardNr, int steps, int startfieldNr);

    /**
     * Switches Jokercard with a Card with cardVal
     * 
     * @param cardVal
     *        number of new card
     * @return New card if switch possible, otherwise the joker card
     */
    CardInterface playJoker(int cardVal);
}
