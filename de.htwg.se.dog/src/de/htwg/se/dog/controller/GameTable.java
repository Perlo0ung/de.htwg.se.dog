package de.htwg.se.dog.controller;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import de.htwg.se.dog.models.CardInterface;
import de.htwg.se.dog.models.FieldInterface;
import de.htwg.se.dog.models.GameFieldInterface;
import de.htwg.se.dog.models.PlayerInterface;
import de.htwg.se.dog.models.impl.GameField;
import de.htwg.se.dog.models.impl.Player;

public class GameTable {

    private static final int FIELDSTILLHOUSE = 16;
    private static final int HOUSECOUNT = 4;

    private final GameField game;
    private final List<PlayerInterface> players;
    private Queue<PlayerInterface> turnPlayer;
    private final CardDealer dealer;
    private final Movement movement;

    /**
     * Constructor to generate a new gametable
     * 
     * @param playerCount
     *        number of players
     * @param figCount
     *        number of figures per player
     */
    public GameTable(int playerCount) {
        game = new GameField(FIELDSTILLHOUSE, playerCount, HOUSECOUNT);
        players = new LinkedList<PlayerInterface>();
        dealer = new CardDealer(playerCount);
        movement = new Movement();
        addPlayers(playerCount, HOUSECOUNT);
    }

    /**
     * Returns the Gamefield currently playing on
     * 
     * @return
     */
    public GameField getGameField() {
        return this.game;
    }

    /**
     * Returns weather the playerqueue is empty or not
     * 
     * @return true if empty otherwise false
     */
    public boolean playerQueueIsEmpty() {
        return turnPlayer.isEmpty();
    }

    /**
     * Add all Players to playerlist
     * 
     * @param playerCount
     * @param figCount
     */
    private void addPlayers(int playerCount, int figCount) {
        for (int i = 1; i <= playerCount; i++) {
            players.add(new Player(i, figCount));
        }
    }

    /**
     * Sets the player that can play first
     * 
     * @param playernum
     */
    public void setStartingPlayer(int playernum) {
        Collections.rotate(players, -playernum + 1);
    }

    /**
     * Refills the Playerqueue and deals cards to every player
     */
    public void dealCards() {

        //now put all players into the queue
        turnPlayer = new LinkedList<PlayerInterface>();
        for (PlayerInterface p : players) {
            dealer.dealCards(p);
            turnPlayer.add(p);

        }
        //rotate player list clockwise
        Collections.rotate(players, -1);
    }

    /**
     * Starts a new round
     */
    public void newRound() {
        dealCards();
        dealer.newRound();
    }

    /**
     * Returns the player that can play now
     * 
     * @return the player that is allowed to play
     */
    public PlayerInterface getNextPlayer() {
        return turnPlayer.poll();
    }

    /**
     * Adds the speciefied Player to the Playerqueue
     */
    public void addPlayerToQueue(PlayerInterface p) {
        turnPlayer.offer(p);
    }

    /**
     * Returns true if the Player has a card that can be played
     * 
     * @param p
     *        the Player that wants to play
     * @return true if he can play, otherwise false
     */
    public boolean canPlay(PlayerInterface p) {
        return !possibleCards(p).isEmpty();
    }

    /**
     * Returns a list containing the cards that can be played by Player p
     * 
     * @param p
     *        the player that wants to play
     * @return a list containing the cards that can be played
     */
    // TODO: implement moveStart as possible playable Card
    public List<CardInterface> possibleCards(PlayerInterface p) {
        boolean returnval = false;

        List<CardInterface> cards = new LinkedList<CardInterface>(p.getCardList());
        Iterator<CardInterface> it = cards.iterator();
        while (it.hasNext()) {
            CardInterface c = it.next();
            for (Integer field : p.getFigureRegister()) {
                movement.setMoveStrategie(c.getValue());
                if (movement.validMove(game, c.getValue(), field)) {
                    returnval = true;
                }
            }
            if (!returnval) {
                it.remove();
            }
        }
        return cards;
    }

    public boolean playerHaswon(GameFieldInterface gamefield, PlayerInterface player) {
    	boolean retval = false;
        FieldInterface[] array = gamefield.getField();
    	if (player.getFigureRegister().size() == gamefield.getHouseCount()) {
	        for (Integer fieldID : player.getFigureRegister()) {
	            if (!array[fieldID].isHouse()) {
	                retval = false;
	                break;
	            }
	            retval = true;
	        }
    	}
    	return retval;
    }
}