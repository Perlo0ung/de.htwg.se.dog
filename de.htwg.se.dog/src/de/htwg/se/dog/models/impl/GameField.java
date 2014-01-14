package de.htwg.se.dog.models.impl;

import de.htwg.se.dog.models.FieldInterface;
import de.htwg.se.dog.models.GameFieldInterface;

/**
 * Implementation of GamefieldInterface
 * 
 * @author Michael
 * 
 */
public class GameField implements GameFieldInterface {
    private static final int NOOWNER = 0;

    private final FieldInterface[] gamefield;
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
        gamefield = new FieldInterface[fieldSize];
        generateGamefield(gamefield);
    }

    private void generateGamefield(FieldInterface[] field) {

        for (int i = 0; i < playerCount; i++) {
            int startfield = (fieldsTillHouse + houseCount) * i;
            for (int j = startfield; j < (startfield + fieldsTillHouse); j++) {
                field[j] = new Field(NOOWNER);
            }

            for (int j = startfield + fieldsTillHouse; j < startfield + fieldsTillHouse + houseCount; j++) {
                field[j] = new Field((i + 1) % playerCount + 1);
            }

        }
    }

    @Override
    public int getOwner(int fieldNr) {
        return this.gamefield[fieldNr].getOwner();
    }

    @Override
    public FieldInterface[] getGameArray() {
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

    @Override
    public int getFieldsTillHouse() {
        return fieldsTillHouse;
    }

    @Override
    public int getPlayerCount() {
        return this.playerCount;
    }

    @Override
    public String toString() {
        StringBuilder upper = new StringBuilder();
        int houseFields = this.getHouseCount();
        int normalFields = this.getFieldsTillHouse();
        int playercount = this.getPlayerCount();
        int range = houseFields + normalFields;
        FieldInterface[] array = gamefield;

        for (int p = 0; p < playercount; p++) {
            StringBuilder lower = new StringBuilder();
            int startFieldNr = range * p;
            int endFieldNr = startFieldNr + range;
            for (int f = startFieldNr; f < endFieldNr; f++) {
                if (array[f].isHouse()) {
                    upper.append(String.format("(%2d: %2d )", array[f].getOwner(), f));
                    lower.append(String.format("( %5s )", array[f]));
                } else {
                    upper.append(String.format("| %2d |", f));
                    lower.append(String.format("| %2s |", array[f]));
                }

            }
            upper.append('\n');
            lower.append('\n');
            upper.append(lower).append('\n');
        }
        return upper.toString();
    }

    @Override
    public FieldInterface[] copyField() {
        FieldInterface[] copy = new FieldInterface[fieldSize];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = gamefield[i].copy(gamefield[i]);
        }
        return copy;
    }

    @Override
    public FieldInterface getFieldForNum(int fieldnr) {
        FieldInterface field = null;
        if (gamefield.length > fieldnr) {
            field = gamefield[fieldnr];
        }
        return field;
    }

    @Override
    public int calculatePlayerStart(int playerID) {
        return (getHouseCount() + getFieldsTillHouse()) * (playerID - 1);
    }

}
