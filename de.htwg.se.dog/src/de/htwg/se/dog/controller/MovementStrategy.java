package de.htwg.se.dog.controller;


public interface MovementStrategy {
    /**
     * <pre>
     * function depends on previous called setMoveStrategie if never called it
     * trys to Moves Figure "steps" forward
     * if setMoveStrategie is called with card 4 it trys to moves Figure "steps"
     * backward
     * if setMoveStrategie is called with card 7 it trys to moves Figure "steps"
     * forward and kicks every figure on its way
     * if setMoveStrategie is called with card 11 / jack it trys to switches
     * Figure with a Figure "steps" fields further if setMoveStrategie is called
     * with any other cards, it trys to behaves like it is never called
     * </pre>
     * 
     * @param gamefield
     * @param steps
     *            number of steps figure wants to take
     * @param startfieldnr
     *            from where figure wants to move
     * @return true if move was successfull, otherwise false
     */
    boolean move(int steps, int startfieldnr);

    /**
     * Returns true if suggested move is valid
     * 
     * @param gamefield
     * @param steps
     *            number of steps figure want to take
     * @param startfieldnr
     *            from where figure wants to move
     * @return true if move is valid, otherwise false
     */
    boolean validMove(int steps, int startfieldnr);
}
