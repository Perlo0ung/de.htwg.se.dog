package de.htwg.se.dog.controller;

import de.htwg.se.dog.models.*;

public class Movement {

    // Moves Figure "steps" forward, on sucess it returns true
    // otherwise false
    public boolean moveFigure(GameField gamefield, int steps, int startfieldnr) {
        boolean valid = false;
        Field[] array = gamefield.getGamefield();
        // check if startfield is not empty and the move is valid
        if (array[startfieldnr].getFigure() != null && validMove(gamefield, steps, startfieldnr)) {
                int targetfield = getTargetfield(gamefield, steps, startfieldnr);
                kickPlayer(array, targetfield);
                // Move Figure from startfield to Targetfield
                array[targetfield].putFigure(array[startfieldnr].removeFigure());
                valid = true;
        }
        return valid;
    }

    // Returns true if suggested move is valid
    public boolean validMove(GameField gamefield, int steps, int startfieldnr) {
        boolean valid = false;
        if (getTargetfield(gamefield, steps, startfieldnr) >= 0) {
            valid = true;
        }
        return valid;
    }

    /**
     * Tauscht 2 figuren aus, falls nicht auf beiden feldern eine Figur steht,
     * macht sie nichts
     * @param gamefield Spielfeld auf dem die Figuren sind
     * @param fromNr Feldnummer von Figur 1 
     * @param toNr Feldnummer von Figur 2
     * @return true falls tausch erfolgreich,
     * andernfalls false
     */
    public boolean moveSwitch(GameField gamefield, int fromNr, int toNr){
        boolean valid = false;
        Field[] array = gamefield.getGamefield();
        if(array[fromNr].getFigure() != null && array[toNr].getFigure() != null){
            Figure tmp = array[fromNr].removeFigure();
            array[fromNr].putFigure(array[toNr].removeFigure());
            array[toNr].putFigure(tmp);
            valid = true;
        }
        return valid;
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
                // Check if next field is own House and current field ist last in house
            } else if (currentFieldOwner == playerNr && steps > 0 && array[nextfieldID].getOwner() != playerNr) {
                    steps += gamefield.getHouseCount()-1;
            }
            if (steps > 0) {
                currentfieldID = nextfieldID;
            }
        }
        return currentfieldID;
    }

}
