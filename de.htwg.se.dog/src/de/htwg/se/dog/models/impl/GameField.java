package de.htwg.se.dog.models.impl;

import de.htwg.se.dog.models.GameFieldInterface;

public class GameField implements GameFieldInterface<Field> {
    private static final int NOOWNER = 0;

    private Field[] gamefield;
    private final int fieldsTillHouse;
    private final int playerCount;
    private final int houseCount;
    private final int fieldSize;

    /**
     * Setup for a GameField
     * 
     * @param fieldsTillHouse
     *            : field until there is a house
     * 
     * @param houseCount
     *            : number of housefields
     * 
     * @param playerCount
     *            : number of players
     */
    public GameField(int fieldsTillHouse, int playerCount, int houseCount) {
        this.fieldsTillHouse = fieldsTillHouse;
        this.houseCount = houseCount;
        this.playerCount = playerCount;
        this.fieldSize = (houseCount + fieldsTillHouse) * playerCount;
        gamefield = new Field[fieldSize];
        generateGamefield();
    }

    private void generateGamefield() {
        for (int i = 0; i < playerCount; i++) {
            int startfield = (fieldsTillHouse + houseCount) * i;
            for (int j = startfield; j < (startfield + fieldsTillHouse); j++) {
                gamefield[j] = new Field(NOOWNER);
            }

            for (int j = startfield + fieldsTillHouse; j < startfield + fieldsTillHouse + houseCount; j++) {
                gamefield[j] = new Field(i + 1);
            }

        }
    }

    @Override
    public int getOwner(int fieldNr) {
        return this.gamefield[fieldNr].getOwner();
    }

    @Override
    public Field[] getGamefield() {
        return gamefield;
    }

    @Override
    public int getFieldSize() {
        return fieldSize;
    }

    @Override
    public int getHouseCount() {
        return houseCount;
    }
}
