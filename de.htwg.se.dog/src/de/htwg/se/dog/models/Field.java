package de.htwg.se.dog.models;

public class Field {

    private final int owner;
    private boolean house;
    private boolean blocked;
    private Figure figure;

    public Field(int owner) {
        this.blocked = false;
        if (owner == 0) {
            this.owner = 0;
            this.house = false;
        } else {
            this.owner = owner;
            this.house = true;
        }
    }

    /*
     * Puts Figure f on this Field
     * 
     * @param f
     */
    public void putFigure(Figure f) {
        this.figure = f;
    }

    /*
     * removes the figure from this field and returns it
     * 
     * @return return the removed figure
     */
    public Figure removeFigure() {
        Figure tmp = this.figure;
        this.figure = null;
        return tmp;
    }

    /*
     * returns the reference of the figure attached to this field
     * 
     * @return the attached figure
     */

    public Figure getFigure() {
        return this.figure;
    }

    /*
     * @return the field owner (for house fields)
     */
    public int getOwner() {
        return this.owner;
    }

    /* block this field for any movement operations */
    public void setBlocked(boolean a) {
        this.blocked = a;
    }

    /*
     * @return true if field is blocked
     */
    public boolean getBlocked() {
        return this.blocked;
    }

    /*
     * @return true if housefield
     */
    public boolean isHouse() {
        return this.house;
    }

}
