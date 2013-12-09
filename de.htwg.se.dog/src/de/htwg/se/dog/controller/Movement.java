package de.htwg.se.dog.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import de.htwg.se.dog.models.CardInterface;
import de.htwg.se.dog.models.FieldInterface;
import de.htwg.se.dog.models.FigureInterface;
import de.htwg.se.dog.models.GameFieldInterface;
import de.htwg.se.dog.models.PlayerInterface;

public class Movement implements MovementStrategy {

    private static final int VALUEOFCARD7 = 7;
    private static final int VALUEOFCARD11 = 11;
    private static final int EMPTYFIELD = -5;
    private static final int BLOCKEDFIELD = -6;

    private Movement strategie;
    private static Map<Integer, Movement> strat;
    static {
        strat = new HashMap<Integer, Movement>();
        strat.put(VALUEOFCARD11, new MoveSwitch());
    }

    protected Movement() {}

    @Override
    public boolean move(GameFieldInterface gamefield, int steps, int fromNr) {
        return strategie.move(gamefield, steps, fromNr);
    }

    @Override
    public boolean validMove(GameFieldInterface gamefield, int steps, int startfieldnr) {
        return strategie.validMove(gamefield, steps, startfieldnr);

    }

    /**
     * Set move-strategie that is used for a card
     * 
     * @param card
     */
    public void setMoveStrategie(CardInterface card) {
        strategie = strat.get(card.getValue());
        if (strategie == null) {
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
    protected boolean fieldEmpty(FieldInterface field) {
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
    protected void kickPlayer(FieldInterface[] array, int fieldID) {
        if (!fieldEmpty(array[fieldID])) {
            // get Owner of figure
            PlayerInterface tempPlayer = array[fieldID].getFigure().getOwner();
            FigureInterface figure = array[fieldID].removeFigure();
            tempPlayer.updateFigurePos(figure.getFignr(), -1);
            // remove figure from field and add it to Playerlist
            tempPlayer.addFigure(figure);
        }
    }

    /**
     * returns the fieldID of the Target field, if not a vaild move, -1 is
     * returned.
     * 
     * @param gamefield
     * @param steps
     *        number of steps figure wants to take
     * @param startfieldnr
     *        startfield number from where figure wants to move
     * @return returns number of targetfield, if startfield is empty it returns
     *         -5 or if field is blocked it returns -6
     */
    protected int getTargetfield(GameFieldInterface gamefield, int steps, int startfieldnr) {
        int absSteps = Math.abs(steps);
        FieldInterface[] array = gamefield.getGamefield();
        int currentfieldID = EMPTYFIELD;
        if (!fieldEmpty(array[startfieldnr])) {
            int playerNr = array[startfieldnr].getFigureOwnerNr();
            currentfieldID = nextField(gamefield.getFieldSize(), startfieldnr, steps);
            int nextfieldID = currentfieldID;
            // Loop to move steps
            while (absSteps > 0) {
                currentfieldID = nextfieldID;
                nextfieldID = nextField(gamefield.getFieldSize(), currentfieldID, steps);
                int currentFieldOwner = array[currentfieldID].getOwner();
                // Check if field is Blocked
                if (array[currentfieldID].getBlocked()) {
                    currentfieldID = BLOCKEDFIELD;
                    absSteps = 0;
                }
                // Check if nobody owns next field
                absSteps = adjustSteps(gamefield, steps, absSteps, array, playerNr, nextfieldID, currentFieldOwner);
            }
        }
        return currentfieldID;
    }

    private int adjustSteps(GameFieldInterface gamefield, int steps, int psteps, FieldInterface[] array, int playerNr, int nextfieldID, int currentFieldOwner) {
        int absSteps = psteps;
        if (currentFieldOwner == 0 || currentFieldOwner == playerNr && steps > 0) {
            absSteps--;
            // Check if next field is own House and current field ist last
            // in house
        }
        if (currentFieldOwner == playerNr && absSteps > 0 && array[nextfieldID].getOwner() != playerNr && steps > 0) {
            absSteps += gamefield.getHouseCount();
        }
        return absSteps;
    }

    /**
     * returns the next field, if direction is >= 0 it returns the next fieldNr
     * otherwise the previous fieldNr
     * 
     * @param fieldSize
     *        Size of the Gamefield
     * @param currentfieldID
     * @param direction
     * @return
     */
    protected int nextField(int fieldSize, int currentfieldID, int direction) {
        int field;
        if (direction >= 0) {
            field = (currentfieldID + 1) % fieldSize;
        } else {
            field = ((currentfieldID - 1) + fieldSize) % fieldSize;
        }
        return field;
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
    public boolean possibleSevenMove(GameFieldInterface gamefield, PlayerInterface p) {
        int steps = VALUEOFCARD7;
        int remaining = 0;
        boolean returnval = true;
        LinkedList<Integer> figures = new LinkedList<Integer>(p.getFigureRegister());
        if (figures.isEmpty()) {
            steps = 0;
            returnval = false;
        }
        Integer currentField = figures.pollFirst();
        while (steps > 0) {
            if (!validMove(gamefield, steps, currentField)) {
                steps--;
                remaining++;
            } else {
                if (remaining == 0) {
                    break;
                }
                currentField = figures.pollFirst();
                steps = remaining;
                if (currentField == null) {
                    returnval = false;
                    break;
                }
            }
        }
        return returnval;
    }

}
