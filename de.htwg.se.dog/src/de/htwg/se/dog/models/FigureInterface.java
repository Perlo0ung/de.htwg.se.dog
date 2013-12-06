package de.htwg.se.dog.models;

import de.htwg.se.dog.models.impl.Player;

public interface FigureInterface {
    /**
     * returns the owner of this figure
     * 
     * @return reference to the owner of the figure
     */
    public Player getOwner();

    /**
     * returns the PlayerNr of the owner
     * 
     * @return
     */
    public int getOwnerNr();

    /**
     * returns the figurenum
     * 
     * @return returns the Figurenumber
     */
    public int getFignr();
}