package de.htwg.se.dog.controller;

import de.htwg.se.dog.models.*;

public class Movement {

    // Moves Figure "steps" forward, on sucess it returns true
    // otherwise false
    public boolean moveFigure(GameField gamefield, int steps, int startfieldnr) {
        Field[] array = gamefield.getGamefield();
        // check if startfield is not empty
        if (array[startfieldnr].getFigure() != null) {
            // Check if move is valid
            if (!validMove(gamefield, steps, startfieldnr)) {
                return false;
            }
            int targetfield = getTargetfield(gamefield, steps, startfieldnr);
            kickPlayer(array, targetfield);
            // Move Figure from startfield to Targetfield
            array[targetfield].putFigure(array[startfieldnr].removeFigure());
            return true;
        }
        return false;
    }

    // Returns true if suggested move is valid
    public boolean validMove(GameField gamefield, int steps, int startfieldnr) {
        if (getTargetfield(gamefield, steps, startfieldnr) >= 0) {
            return true;
        }
        return false;
    }

    // Remove Player From fieldID and return it to Player
    private void kickPlayer(Field[] array, int fieldID) {
        if (array[fieldID].getFigure() != null) {
            // get Owner of figure
            Player tempPlayer = array[fieldID].getFigure().getOwner();
            // remove figure from field and add it to Playerlist
            tempPlayer.addFigure(array[fieldID].removeFigure());
        }
    }

    // returns the fieldID of the Target field, if not a vaild move, -1 is
    // returned
    private int getTargetfield(GameField gamefield, int stepAns,
            int startfieldnr) {
        Field[] array = gamefield.getGamefield();
        int playerNr = array[startfieldnr].getFigure().getOwner().getPlayerID();
        int currentfieldID = (startfieldnr + 1) % gamefield.getFieldSize();
        int steps = stepAns;
        // Loop to move steps
        while (steps > 0) {
            int nextfieldID = (currentfieldID + 1) % gamefield.getFieldSize();
            int currentFieldOwner = array[currentfieldID].getOwner();
            // Check if field is Blocked
            if (array[currentfieldID].getBlocked()) {
                return -1;
            }
            // Check if nobody owns next field
            if (currentFieldOwner == 0) {
                steps--;
                // Check if next field is own House
            } else if (currentFieldOwner == playerNr) {
                steps--;
                // Check if current field is last in house
                if (steps > 0 && array[nextfieldID].getOwner() != playerNr) {
                    steps += gamefield.getHouseCount();
                }
            }
            if (steps > 0) {
                currentfieldID = nextfieldID;
            }
        }
        return currentfieldID;
    }

}
