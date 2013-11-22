package de.htwg.se.dog.controller;



import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

import de.htwg.se.dog.models.Card;
import de.htwg.se.dog.models.GameField;
import de.htwg.se.dog.models.Player;

public class GameTable {

    private static final int FIELDSTILLHOUSE = 16;
    private static final int HOUSECOUNT = 4;

    GameField game;
    final LinkedList<Player> players;
    ArrayDeque<Player> turnPlayer;
    CardDealer dealer;
    
    /**
     * Consturctor to generates a new gametable
     * @param playerCount: int number of players
     * @param figCount: int number of figures per player
     */
    public GameTable(int playerCount, int figCount) {
        game = new GameField(FIELDSTILLHOUSE, playerCount, HOUSECOUNT);
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
     * Returns reference to gamefield 
     * @return
     */
    public GameField getGameField() {
        return this.game;
    }
    
    public void newRound() {
        for (Player p : players) {
            dealer.dealCards(p);
        }
        dealer.newRound();
        turnPlayer = new ArrayDeque<Player>(players);
    }
    /**
     * Returns the player that can playnow
     * @return
     */
    public Player getCurrentPlayer() {
        return turnPlayer.pollFirst();
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
                Movement.validMove(
            }
        }
     }
}
