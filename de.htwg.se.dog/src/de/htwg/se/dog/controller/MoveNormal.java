package de.htwg.se.dog.controller;

import de.htwg.se.dog.models.impl.Field;
import de.htwg.se.dog.models.impl.Figure;
import de.htwg.se.dog.models.impl.GameField;

public class MoveNormal extends Movement {
    /**
     * Trys to Moves Figure "steps" forward, on success it returns true
     * otherwise false
     * 
     * @param gamefield
     * @param steps
     *        number of steps figure wants to take
     * @param startfieldnr
     *        from where figure wants to move
     * @return true if figure could be moved, otherwise false
     */
    @Override
    public boolean move(GameField gamefield, int steps, int startfieldnr) {
        boolean valid = false;
        Field[] array = gamefield.getGamefield();
        // check if startfield is not empty and the move is valid
        if (!fieldEmpty(array[startfieldnr]) && validMove(gamefield, steps, startfieldnr)) {
            int targetfield = getTargetfield(gamefield, steps, startfieldnr);
            Figure temp = array[startfieldnr].removeFigure();
            kickPlayer(array, targetfield);
            // Move Figure from startfield to Targetfield
            array[targetfield].putFigure(temp);
            //array[startfieldnr].removeFigure()
            valid = true;
        }

        return valid;
    }

    /**
     * Returns true if suggested move is valid
     * 
     * @param gamefield
     * @param steps
     *        number of steps figure want to take
     * @param startfieldnr
     *        from where figure wants to move
     * @return true if move is valid, otherwise false
     */
    @Override
    public boolean validMove(GameField gamefield, int steps, int startfieldnr) {
        boolean valid = false;
        if (getTargetfield(gamefield, steps, startfieldnr) >= 0) {
            valid = true;
        }
        return valid;
    }
}
