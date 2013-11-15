package de.htwg.se.dog.controller;



import de.htwg.se.dog.models.GameField;

public class GameTable {

    private static final int FIELDSTILLHOUSE = 16;
    private static final int HOUSECOUNT = 4;

    GameField game;
    Turn turn;

    public GameTable(int playerCount, int figCount) {
        game = new GameField(FIELDSTILLHOUSE, playerCount, HOUSECOUNT);
        turn = new Turn(playerCount, figCount);

    }
  
}
