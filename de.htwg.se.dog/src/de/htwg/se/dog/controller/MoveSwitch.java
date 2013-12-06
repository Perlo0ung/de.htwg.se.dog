package de.htwg.se.dog.controller;

import de.htwg.se.dog.models.Field;
import de.htwg.se.dog.models.Figure;
import de.htwg.se.dog.models.GameField;
import de.htwg.se.dog.models.Player;

public class MoveSwitch extends Movement {

    /**
     * switches 2 figures, if targetfig is on a housefield or not on both fields
     * is a figure, it does nothing
     * 
     * @param gamefield
     * @param steps
     *        number of steps to field of Figure 2
     * @param fromNr
     *        fieldnumber of Figure 1
     * @return true if switch was successful, otherwise false
     */
    @Override
    public boolean move(GameField gamefield, int steps, int fromNr) {
        int toNr = steps;
        boolean valid = false;
        Field[] array = gamefield.getGamefield();
        if (!fieldEmpty(array[fromNr]) && !array[fromNr].isHouse() && !fieldEmpty(array[toNr]) && !array[toNr].isHouse()) {
            /* switch Figures */
            Figure f1 = array[fromNr].removeFigure();
            Figure f2 = array[toNr].removeFigure();
            array[fromNr].putFigure(f2);
            array[toNr].putFigure(f1);
            /* update FirgurePositions */
            Player ownerF1 = f1.getOwner();
            Player ownerF2 = f2.getOwner();
            ownerF2.updateFigurePos(f2.getFignr(), fromNr);
            ownerF1.updateFigurePos(f1.getFignr(), toNr);
            valid = true;
        }
        return valid;
    }

    /**
     * checks if it is possible to switch 2 figures
     */
    @Override
    public boolean validMove(GameField gamefield, int steps, int fromNr) {
        int toNr = super.getTargetfield(gamefield, steps, fromNr);
        Field[] array = gamefield.getGamefield();
        if (!super.fieldEmpty(array[fromNr]) && !array[fromNr].isHouse() && !super.fieldEmpty(array[toNr]) && !array[toNr].isHouse())
            return true;
        return false;
    }
}
