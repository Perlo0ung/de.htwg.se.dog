package de.htwg.se.dog.models.impl;

import de.htwg.se.dog.models.FigureInterface;
import de.htwg.se.dog.models.PlayerInterface;

public class Figure implements FigureInterface {
    private static final int ZERO = 0;
    private static final int FOUR = 4;
    private final int fignr;
    private final PlayerInterface owner;

    public Figure(PlayerInterface owner, int fignr) {
        this.owner = owner;
        if (fignr > ZERO && fignr <= FOUR) {
            this.fignr = fignr;
        } else {
            throw new IllegalArgumentException("Failed to create Figrue with Fignr:" + fignr);
        }

    }

    @Override
    public PlayerInterface getOwner() {
        return this.owner;
    }

    @Override
    public int getOwnerNr() {
        return owner.getPlayerID();
    }

    @Override
    public int getFignr() {
        return this.fignr;
    }

}
