package de.htwg.se.dog.models;

import de.htwg.se.dog.models.impl.Field;

public interface GameFieldInterface {

    /**
     * returns the owner number of a field
     * 
     * @param fieldNr
     *            number of the field
     * 
     * @return int: ownerNr of the fieldNr, if it has no owner zero is returned
     */
    public int getOwner(int fieldNr);

    /**
     * Returns the gamefieldarray
     * 
     * @return Field[]: the complete GameField
     */
    public Field[] getGamefield();

    /**
     * returns the gamefieldsize
     * 
     * @return int: the size of the complete Field
     */
    public int getFieldSize();

    /**
     * returns number of housefields
     * 
     * @return int: the numberofHouseFields for each Player
     */
    public int getHouseCount();

}
