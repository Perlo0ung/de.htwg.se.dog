package de.htwg.se.dog.models;

import de.htwg.se.dog.models.impl.Figure;

public interface FieldInterface {
    /**
     * /** Puts Figure f on this Field
     * 
     * @param f
     */
    public void putFigure(Figure f);

    /**
     * removes the figure from this field and returns it
     * 
     * @return return the removed figure
     */
    public Figure removeFigure();

    /**
     * returns the reference of the figure attached to this field
     * 
     * @return the attached figure
     */

    public Figure getFigure();

    /**
     * returns the PlayerNr of the FigureOwner
     * 
     * @return
     */
    public int getFigureOwnerNr();

    /**
     * returns the field owner number
     * 
     * @return the field owner (for house fields)
     */
    public int getOwner();

    /**
     * set blocked status of the field
     * 
     * @param a
     *            status
     */
    public void setBlocked(boolean a);

    /**
     * returns the blocked state
     * 
     * @return true if field is blocked
     */
    public boolean getBlocked();

    /**
     * returns boolean value if field is a house fied
     * 
     * @return true if housefield
     */
    public boolean isHouse();
}
