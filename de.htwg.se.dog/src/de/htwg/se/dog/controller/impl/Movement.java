package de.htwg.se.dog.controller.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import de.htwg.se.dog.controller.MovementStrategy;
import de.htwg.se.dog.models.FieldInterface;
import de.htwg.se.dog.models.FigureInterface;
import de.htwg.se.dog.models.GameFieldInterface;
import de.htwg.se.dog.models.PlayerInterface;

public class Movement implements MovementStrategy {

    private static final int VALUEOFCARD11 = 11;
    private static final int VALUEOFCARD7 = 7;
    private static final int EMPTYFIELD = -5;
    private static final int BLOCKEDFIELD = -6;

    private Movement strategie;
    protected GameFieldInterface gameField;

    public Movement() {}

    public Movement(GameFieldInterface gameField) {
        this.gameField = gameField;
    }

    @Override
    public boolean move(int steps, int fromNr) {
        return strategie.move(gameField, steps, fromNr);
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
    public void setMoveStrategie(int StrategieNr) {
        if (StrategieNr != 11) {
            strategie = new MoveNormal(gameField);
        } else {
            strategie = new MoveSwitch(gameField);
        }
    }

    /**
     * checks if figure is on field
     * 
     * @param field
     *            which should be checked
     * @return true if field is empty, otherwise false;
     */
    public boolean fieldEmpty(FieldInterface field) {
        boolean returnval = false;
        if (field.getFigure() == null) {
            returnval = true;
        }
        return returnval;
    }

    /**
     * Remove Player From fieldID and return it to Player if field at fieldID is
     * empty, it does nothing
     * 
     * @param array
     *            gamefieldarray
     * @param fieldID
     *            fieldnumber where figure should be kick from
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
     *            number of steps figure wants to take
     * @param startfieldnr
     *            startfield number from where figure wants to move
     * @return returns number of targetfield, if startfield is empty it returns
     *         -5 or if field is blocked it returns -6
     */
    protected int getTargetfield(GameFieldInterface gamefield, int steps, int startfieldnr) {
        int absSteps = Math.abs(steps);
        FieldInterface[] array = gamefield.getField();
        int currentfieldID = EMPTYFIELD;
        //TODO Monitor whether if-Statment is necessary or not
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
     *            Size of the Gamefield
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

    public int getPlayerStart(GameFieldInterface gamefield, PlayerInterface p) {
        return (gamefield.getHouseCount() + gamefield.getFieldsTillHouse()) * (p.getPlayerID() - 1);
    }

    public boolean moveStart(GameFieldInterface gamefield, PlayerInterface player) {
        boolean retval = false;
        int startFieldNr = getPlayerStart(gamefield, player);
        FieldInterface[] array = gamefield.getField();
        if (possibleMoveStart(array, startFieldNr, player)) {
            kickPlayer(array, startFieldNr);
            array[startFieldNr].putFigure(player.removeFigure(), startFieldNr);
            array[startFieldNr].setBlocked(true);
            retval = true;
        }
        return retval;
    }

    public boolean possibleMoveStart(FieldInterface[] array, int startFieldNr, PlayerInterface player) {
        return !array[startFieldNr].getBlocked() && !player.getFigureList().isEmpty();
    }

    /* ------------------------------------------------------------------------- */
    /* Move Seven Methodes */
    /* ------------------------------------------------------------------------- */

    /**
     * Move-Method which executes every move given in "moves" or non if one move
     * is not possible
     * 
     * @param gamefield
     * @param moves
     *            Map of moves you want to execute, while the key is the
     *            startfieldnr and the value is the number of steps from this
     *            startfieldnr
     * @return true if all moves could be executed, otherwise false
     */
    public boolean move(GameFieldInterface gamefield, Map<Integer, Integer> moves) {
        boolean retval = true;
        this.setMoveStrategie(VALUEOFCARD7);
        /* Check if all moves are possible */
        for (Entry<Integer, Integer> field : moves.entrySet()) {
            if (!this.move(gamefield, field.getValue(), field.getKey())) {
                retval = false;
                break;
            }
        }
        /* Move --------------*/
        for (Entry<Integer, Integer> field : moves.entrySet()) {
            for (int i = 0; i <= field.getValue(); i++) {
                this.move(gamefield, 1, field.getKey());
            }
        }
        return retval;
    }

    /**
     * Checks if the Player p can do a move with the card 7
     * 
     * @param gamefield
     *            the current gamefield played on
     * @param p
     *            the player that wants to move
     * @return true if the player can move with the card
     */
    public boolean AnyValidMove12312(GameFieldInterface gamefield, PlayerInterface p) {
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
            this.setMoveStrategie(VALUEOFCARD7);
            if (!this.validMove(gamefield, steps, currentField)) {
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

    public boolean AnyValidMove(GameFieldInterface gamefield, PlayerInterface p) throws CloneNotSupportedException {
        GameFieldInterface copy = (GameFieldInterface) gamefield.clone();
        LinkedList<Integer> figures = new LinkedList<Integer>(p.getFigureRegister());
        Collections.sort(figures, Collections.reverseOrder());
        FieldInterface array[] = copy.getField();
        int steps = VALUEOFCARD7;
        int remaining = 0;
        boolean returnval = true;

        if (figures.isEmpty()) {
            steps = 0;
            returnval = false;
        }
        Integer currentField = figures.pollFirst();
        while (steps > 0) {
            this.setMoveStrategie(VALUEOFCARD7);
            if (!this.validMove(copy, steps, currentField)) {
                steps--;
                remaining++;
            } else {
                int target = getTargetfield(copy, remaining, currentField);
                array[target].putFigure(array[currentField].removeFigure());
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
