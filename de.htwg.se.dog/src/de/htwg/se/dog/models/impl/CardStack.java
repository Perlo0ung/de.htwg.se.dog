package de.htwg.se.dog.models.impl;

import java.util.Random;
import java.util.Stack;

import de.htwg.se.dog.models.CardStackInterface;

public class CardStack implements CardStackInterface<Card> {

    private Stack<Card> cardstack = null;
    private static final int CARDS = 4;
    private static final int JOKER = 14;
    private static final int ZERO = 0;

    /**
     * Generate a CardStack with specified size and for a number of players
     * 
     * @param size
     * @param players
     */
    public CardStack(int size, int players) {
        cardstack = new Stack<Card>();
        generateStack(size, players);
    }

    /**
     * Returns size of the CardStack
     * 
     * @return StackSize
     */
    @Override
    public int getSize() {
        return cardstack.size();
    }

    /**
     * generate a new CardStack for every 4 players the amount of cards gets
     * doubled
     * 
     * @param size
     * @param players
     */
    private void generateStack(int size, int players) {
        for (int k = 0; k < (int) Math.ceil(((double) players / CARDS)); k++) {
            for (int i = ZERO; i <= (CARDS - 1); i++) {
                for (int j = ZERO; j <= size; j++) {
                    cardstack.push(new Card(j + 1));
                }
            }
            cardstack.push(new Card(JOKER));
            cardstack.push(new Card(JOKER));
        }
    }

    /**
     * Returns the CardStack
     * 
     * @return stack reference
     */
    @Override
    public Stack<Card> getStack() {
        return this.cardstack;
    }

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
    @Override
    public Card dealCard(int start, int range) {
        Random gen = new Random();
        int index = gen.nextInt(range - start) + start;
        return cardstack.remove(index);
    }

}
