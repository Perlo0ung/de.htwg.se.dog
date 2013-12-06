package de.htwg.se.dog.models;

import java.util.Stack;

import de.htwg.se.dog.models.impl.Card;

public interface CardStackInterface {
    /**
     * Returns size of the CardStack
     * 
     * @return StackSize
     */
    public int getSize();

    /**
     * Returns the CardStack
     * 
     * @return stack reference
     */
    public Stack<Card> getStack();

    /**
     * Gets Card from stack between start inc. and range excl. ands removes it
     * from the Stack
     * 
     * @param start
     *            : int startpoint
     * @param range
     *            : int range for random generator
     * @return Card: returns card from stack
     */
    public Card dealCard(int start, int range);
}
