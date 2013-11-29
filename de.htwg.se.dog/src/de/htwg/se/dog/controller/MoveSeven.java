package de.htwg.se.dog.controller;

import java.util.Map;
import java.util.Map.Entry;

import de.htwg.se.dog.models.GameField;

public class MoveSeven extends Movement{

    /**
     * 
     * @param gamefield
     * @param test
     */
    @Override
    public boolean move(GameField gamefield, Map<Integer, Integer> test) {
        for (Entry<Integer, Integer> entry : test.entrySet()) {
            if (!validMove(gamefield, entry.getValue(), entry.getKey())) {
                return false;
            }
        }
        for (Entry<Integer, Integer> entry : test.entrySet()) {
            move(gamefield, entry.getValue(), entry.getKey());
        }
        return true;
    }

    @Override
    public boolean validMove(GameField gamefield, int steps, int startfieldnr) {
        // TODO Auto-generated method stub
        return false;
    }

}
