package de.htwg.se.dog.controller;

import de.htwg.se.dog.models.GameField;

public interface MovementStrategy {
    public boolean move(GameField gamefield, int steps, int startfieldnr);

    public boolean validMove(GameField gamefield, int steps, int startfieldnr);
}
