package de.htwg.se.dog.models;

public interface FieldInterface {
    /**
     * /** Puts Figure f on this Field
     * 
     * @param f
     */
    void putFigure(FigureInterface f);

    /**
     * removes the figure from this field and returns it
     * 
     * @return return the removed figure
     */
    FigureInterface removeFigure();

    /**
     * returns the reference of the figure attached to this field
     * 
     * @return the attached figure
     */

    FigureInterface getFigure();

    /**
     * returns the PlayerNr of the FigureOwner
     * 
     * @return
     */
    int getFigureOwnerNr();

    /**
     * returns the field owner number
     * 
     * @return the field owner (for house fields)
     */
    int getOwner();

    /**
     * set blocked status of the field
     * 
     * @param a
     *            status
     */
    void setBlocked(boolean a);

    /**
     * returns the blocked state
     * 
     * @return true if field is blocked
     */
    boolean getBlocked();

    /**
     * returns boolean value if field is a house fied
     * 
     * @return true if housefield
     */
    boolean isHouse();
}
