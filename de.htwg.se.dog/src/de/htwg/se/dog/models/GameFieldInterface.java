package de.htwg.se.dog.models;

/**
 * Interface for the Gamefield
 * 
 * @author Michael
 * 
 */
public interface GameFieldInterface extends Cloneable {

    /**
     * returns the owner number of a field
     * 
     * @param fieldNr
     *            number of the field
     * 
     * @return int: ownerNr of the fieldNr, if it has no owner zero is returned
     */
    int getOwner(int fieldNr);

    /**
     * Returns the gamefieldarray
     * 
     * @return Field[]: the complete GameField
     */
    FieldInterface[] getGameArray();

    /**
     * returns the gamefieldsize
     * 
     * @return int: the size of the complete Field
     */
    int getFieldSize();

    /**
     * returns number of housefields
     * 
     * @return int: the numberofHouseFields for each Player
     */
    int getHouseCount();

    /**
     * returns number of fields between two houses
     * 
     * @return
     */
    int getFieldsTillHouse();

    /**
     * returns the number of players playing
     * 
     * @return
     */
    int getPlayerCount();

    /**
     * creates a string representation of the gamefield
     * 
     * @return the gamefield as a string
     */
    @Override
    String toString();

    /**
     * creates a copy of the gamefield
     * 
     * @return the copy
     */
    FieldInterface[] copyField();

    /**
     * Returns the field which is the starfield for a player
     * 
     * @param playerID
     * @return the startfield number
     */
    int calculatePlayerStart(int playerID);
}
