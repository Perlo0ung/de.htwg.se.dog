package de.htwg.se.dog.models;

import java.util.Stack;

public interface CardStackInterface {
    /**
     * Returns size of the CardStack
     * 
     * @return StackSize
     */
    int getSize();

    /**
     * Returns the CardStack
     * 
     * @return stack reference
     */
    Stack<CardInterface> getStack();

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
    CardInterface dealCard(int start, int range);
}
