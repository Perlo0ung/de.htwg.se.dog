package de.htwg.se.dog.controller;

import java.util.HashMap;
import java.util.LinkedList;

import de.htwg.se.dog.models.Card;
import de.htwg.se.dog.models.Field;
import de.htwg.se.dog.models.Figure;
import de.htwg.se.dog.models.GameField;
import de.htwg.se.dog.models.Player;

public class Movement implements MovementStrategy {

    private static final int VALUEOFCARD4 = 4;
    private static final int VALUEOFCARD7 = 7;
    private static final int VALUEOFCARD11 = 11;

    private Movement strategie;
    HashMap<Integer, Movement> strat;

    protected Movement() {
        strat = new HashMap<Integer, Movement>();
        strat.put(VALUEOFCARD4, new MoveFour());
        strat.put(VALUEOFCARD7, new MoveSeven());
        strat.put(VALUEOFCARD11, new MoveSwitchTest());
    }

    @Override
    public boolean move(GameField gamefield, int steps, int fromNr) {
        return strategie.move(gamefield, steps, fromNr);
    }

    @Override
    public boolean validMove(GameField gamefield, int steps, int startfieldnr) {
        return strategie.validMove(gamefield, steps, startfieldnr);

    }

    /**
     * Set move-strategie that is used for a card
     * 
     * @param card
     */
    public void setMoveStrategie(Card card) {
        if ((strategie = strat.get(card.getValue())) == null) {
            strategie = new MoveNormal();
        }
    }

    /**
     * checks if figure is on field
     * 
     * @param field
     *        which should be checked
     * @return true if field is empty, otherwise false;
     */
    protected boolean fieldEmpty(Field field) {
        boolean returnval = false;
        if (field.getFigure() == null) {
            returnval = true;
        }
        return returnval;
    }

    /**
     * Remove Player From fieldID and return it to Player
     * 
     * @param array
     *        gamefieldarray
     * @param fieldID
     *        fieldnumber where figure should be kick from
     */
    protected void kickPlayer(Field[] array, int fieldID) {
        if (!fieldEmpty(array[fieldID])) {
            // get Owner of figure
            Player tempPlayer = array[fieldID].getFigure().getOwner();
            Figure figure = array[fieldID].removeFigure();
            tempPlayer.updateFigurePos(figure.getFignr(), -1);
            // remove figure from field and add it to Playerlist
            tempPlayer.addFigure(figure);
        }
    }

    /**
     * returns the fieldID of the Target field, if not a vaild move, -1 is
     * returned
     * 
     * @param gamefield
     * @param steps
     *        number of steps figure wants to take
     * @param startfieldnr
     *        startfield number from where figure wants to move
     * @return returns number of targetfield
     */
    protected int getTargetfield(GameField gamefield, int steps, int startfieldnr) {
        Field[] array = gamefield.getGamefield();
        int currentfieldID = -1;
        if (!fieldEmpty(array[startfieldnr])) {
            int playerNr = array[startfieldnr].getFigureOwnerNr();
            currentfieldID = (startfieldnr + 1) % gamefield.getFieldSize();
            int nextfieldID = currentfieldID;
            // Loop to move steps
            while (steps > 0) {
                currentfieldID = nextfieldID;
                nextfieldID = (currentfieldID + 1) % gamefield.getFieldSize();
                int currentFieldOwner = array[currentfieldID].getOwner();
                // Check if field is Blocked
                if (array[currentfieldID].getBlocked()) {
                    currentfieldID = -1;
                    break;
                }
                // Check if nobody owns next field
                if (currentFieldOwner == 0) {
                    steps--;
                    // Check if next field is own House and current field ist last
                    // in house
                } else if (currentFieldOwner == playerNr && steps > 0 && array[nextfieldID].getOwner() != playerNr) {
                    steps += gamefield.getHouseCount() - 1;
                }
            }
        }
        return currentfieldID;
    }

    /**
     * Checks if the Player p can do a move with the card 7
     * 
     * @param gamefield
     *        the current gamefield played on
     * @param p
     *        the player that wants to move
     * @return true if the player can move with the card
     */
    public boolean possibleSevenMove(GameField gamefield, Player p) {
        int steps = VALUEOFCARD7;
        boolean returnval = true;
        LinkedList<Integer> figures = new LinkedList<Integer>(p.getFigureRegister());
        Integer currentField = figures.pollFirst();
        while (steps > 0) {
            if (!validMove(gamefield, steps, currentField)) {
                steps--;
            } else {
                currentField = figures.pollFirst();
                if (currentField == null) {
                    returnval = false;
                    break;
                }
            }
        }
        return returnval;
    }

}
