package de.htwg.se.dog.controller;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.htwg.se.dog.models.Card;
import de.htwg.se.dog.models.GameField;
import de.htwg.se.dog.models.Player;

public class GameTable {

	private static final int FIELDSTILLHOUSE = 16;
	private static final int HOUSECOUNT = 4;

	private GameField game;
	private final List<Player> players;
	private ArrayDeque<Player> turnPlayer;
	private CardDealer dealer;

	/**
	 * Constructor to generate a new gametable
	 * 
	 * @param playerCount
	 *            number of players
	 * @param figCount
	 *            number of figures per player
	 */
	public GameTable(int playerCount, int figCount) {
		game = new GameField(FIELDSTILLHOUSE, playerCount, HOUSECOUNT);
		players = new LinkedList<Player>();
		dealer = new CardDealer(playerCount);
		addPlayers(playerCount, figCount);
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
	 * Add all Players to playerlist
	 * 
	 * @param playerCount
	 * @param figCount
	 */
	private void addPlayers(int playerCount, int figCount) {
		for (int i = 1; i <= playerCount; i++) {
			players.add(new Player(playerCount, figCount));
		}
	}

	/**
	 * Refills the Playerqueue and deals cards to every player
	 */
	public void dealCards() {

		for (Player p : players) {
			dealer.dealCards(p);
		}
		turnPlayer = new ArrayDeque<Player>(players);
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
	 * @return
	 */
	public Player getCurrentPlayer() {
		// IDs start at 1 so we need the position 0 from real player list
		return players.get(turnPlayer.pollFirst().getPlayerID() - 1);
	}

	/**
	 * Returns true if the Player has a card that can be played
	 * 
	 * @param p
	 *            the Player that wants to play
	 * @return true if he can play, otherwise false
	 */
	public boolean canPlay(Player p) {
		return !possibleCards(p).isEmpty();
	}

	/**
	 * Returns a list containing the cards that can be played by Player p
	 * 
	 * @param p
	 *            the player that wants to play
	 * @return a list containing the cards that can be played
	 */
	public List<Card> possibleCards(Player p) {
		boolean returnval = false;
		List<Card> cards = new LinkedList<Card>(p.getCardList());
		Iterator<Card> it = cards.iterator();
		while (it.hasNext()) {
			Card c = it.next();
			for (Integer field : p.getFigureRegister()) {
				if (Movement.validMove(game, c.getValue(), field)) {
					returnval = true;
				}
			}
			if (!returnval) {
				it.remove();
			}
		}
		return cards;
	}
}
