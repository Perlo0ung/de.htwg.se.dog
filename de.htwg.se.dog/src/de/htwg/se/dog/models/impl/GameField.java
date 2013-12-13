package de.htwg.se.dog.models.impl;

import de.htwg.se.dog.models.FieldInterface;
import de.htwg.se.dog.models.FigureInterface;
import de.htwg.se.dog.models.GameFieldInterface;

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
     *        : field until there is a house
     * 
     * @param houseCount
     *        : number of housefields
     * 
     * @param playerCount
     *        : number of players
     */
    public GameField(int fieldsTillHouse, int playerCount, int houseCount) {
        this.fieldsTillHouse = fieldsTillHouse;
        this.houseCount = houseCount;
        this.playerCount = playerCount;
        this.fieldSize = (houseCount + fieldsTillHouse) * playerCount;
        gamefield = new FieldInterface[fieldSize];
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
    public FieldInterface[] getField() {
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
        FieldInterface[] array = this.getField();

        for (int p = 0; p < playercount; p++) {
            StringBuilder lower = new StringBuilder();
            int startFieldNr = range * p;
            int endFieldNr = startFieldNr + range;
            for (int f = startFieldNr; f < endFieldNr; f++) {
                if (array[f].isHouse()) {
                    upper.append(String.format("( %2d )", f));
                    lower.append(String.format("( %s )", getPlayerOnField(array, f)));
                } else {
                    upper.append(String.format("| %2d |", f));
                    lower.append(String.format("| %s |", getPlayerOnField(array, f)));
                }

            }
            upper.append('\n');
            lower.append('\n');
            upper.append(lower).append('\n');
        }
        return upper.toString();
    }

    private String getPlayerOnField(FieldInterface[] array, int fieldnum) {
        FigureInterface fig = array[fieldnum].getFigure();
        String s;
        if (fig == null) {
            s = "  ";
        } else {
            s = String.format("%2d", fig.getOwnerNr());
        }
        return s;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public FieldInterface getField(int fieldnr) {
        return gamefield[fieldnr];
    }
}
