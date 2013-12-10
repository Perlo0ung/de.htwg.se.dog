package de.htwg.se.dog.controller;

import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import de.htwg.se.dog.models.GameFieldInterface;
import de.htwg.se.dog.models.PlayerInterface;
import de.htwg.se.dog.models.impl.Card;

public class MoveSeven {
    private static final int VALUEOFCARD7 = 7;

    public boolean move(GameFieldInterface gamefield, Map<Integer, Integer> moves) {
        boolean retval = true;
        Movement move = new Movement();
        move.setMoveStrategie(new Card(7));
        /* Check if all moves are possible */
        for (Entry<Integer, Integer> field : moves.entrySet()) {
            if (!move.move(gamefield, field.getValue(), field.getKey())) {
                retval = false;
                break;
            }
        }
        /* Move --------------*/
        for (Entry<Integer, Integer> field : moves.entrySet()) {
            for (int i = 0; i <= field.getValue(); i++) {
                move.move(gamefield, 1, field.getKey());
            }
        }
        return retval;
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
    public boolean AnyValidMove(GameFieldInterface gamefield, PlayerInterface p) {
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
            Movement move = new Movement();
            move.setMoveStrategie(new Card(3));
            if (!move.validMove(gamefield, steps, currentField)) {
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
