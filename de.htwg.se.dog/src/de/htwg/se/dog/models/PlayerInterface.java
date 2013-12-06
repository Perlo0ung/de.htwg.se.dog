package de.htwg.se.dog.models;

import java.util.List;

import de.htwg.se.dog.models.impl.Card;
import de.htwg.se.dog.models.impl.Figure;

public interface PlayerInterface {
    /**
     * returns list of Cards player has
     * 
     * @return List<Card>: of playercards returned
     */
    List<Card> getCardList();

    /**
     * returns list of figures player has
     * 
     * @return List<Figure>: of figures player returned
     */
    List<Figure> getFigureList();

    /**
     * adds a card to players cardlist
     * 
     * @param c
     *            card which should be added
     */
    void addCard(Card c);

    /**
     * removes card from players cardlist
     * 
     * @param c
     *            card which should be removed
     * @return boolean: true if remove was succsessfull, otherwise false
     */
    boolean removeCard(Card c);

    /**
     * removes figure from players figurelist
     * 
     * @return Figure: returns figure which was removed from players figurelist
     */
    Figure removeFigure();

    /**
     * adds figure to players figurelist
     * 
     * @param f
     *            : Figure which should be added to players figurelist
     */
    void addFigure(Figure f);

    /**
     * returnes player ID
     * 
     * @return int: returns playernumber
     */
    int getPlayerID();

    /**
     * Update the position where the figure assosiated with fignum it currently
     * at. If fieldId is -1 the figure will be removed from the register
     * 
     * @param fieldId
     *            the FieldNumber where the is at
     * @param fignum
     *            the internal figurenumber for this figure
     */
    void updateFigurePos(int fignum, int fieldId);

    /**
     * Returns all the places where figures are
     * 
     * @return List with the figure fieldnumbers
     */
    List<Integer> getFigureRegister();

}
