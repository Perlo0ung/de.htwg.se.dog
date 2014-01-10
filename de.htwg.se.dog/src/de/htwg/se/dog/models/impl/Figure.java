package de.htwg.se.dog.models.impl;

import de.htwg.se.dog.models.FigureInterface;
import de.htwg.se.dog.models.PlayerInterface;

/**
 * Implementation iof Figureinterface
 * 
 * @author Michael
 * 
 */
public class Figure implements FigureInterface {
    private final int fignr;
    private final PlayerInterface owner;

    /**
     * create a new figure with number and owner
     * 
     * @param owner
     *            the number of its owner
     * @param fignr
     *            the figuresnumber
     */
    public Figure(PlayerInterface owner, int fignr) {
        this.owner = owner;
        this.fignr = fignr;

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
