package de.htwg.se.dog.models;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Player {

    private List<Figure> figure;
    private List<Card> cards;
    private Map<Integer, Integer> figureRegister; 
    private final int playernum;

    public Player(int playerNr, int figcount) {
        playernum = playerNr;
        figure = new LinkedList<Figure>();
        for (int i = 0; i < figcount; i++) {
            figure.add(new Figure(this, i + 1));
        }
        cards = new LinkedList<Card>();
        figureRegister = new HashMap<Integer, Integer>();

    }
    /**
     * returns list of Cards player has
     * @return List<Card>: of playercards returned
     */
    public List<Card> getCardList() {
        return this.cards;
    }
    
    /**
     * returns list of figures player has
     * @return List<Figure>: of figures player returned
     */
    public List<Figure> getFigureList() {
        return this.figure;
    }

    /**
     * adds a card to players cardlist
     * @param c card which should be added
     */
    public void addCard(Card c) {
        this.cards.add(c);
    }

    /**
     * removes card from players cardlist
     * @param c card which should be removed
     * @return boolean: true if remove was succsessfull, otherwise false
     */
    public boolean removeCard(Card c) {
        return cards.remove(c);
    }

    /**
     * removes figure from players figurelist
     * @return Figure: returns figure which was removed from players figurelist
     */
    public Figure removeFigure() {
        if (figure.isEmpty()) {
            return null;
        }
        return figure.remove(0);
    }

    /**
     * adds figure to players figurelist
     * @param f: Figure which should be added to players figurelist
     */
    public void addFigure(Figure f) {
        figure.add(f);
    }

    /**
     * returnes player ID
     * @return int: returns playernumber
     */
    public int getPlayerID() {
        return playernum;
    }
    /**
     * Update the position where the figure assosiated with fignum it currently at
     * if fieldId is -1 the figure will be removed from the register
     * @param feldid: the FieldNumber where the is at
     * @param fignum: the internal figurenumber for this figure
     */
    public void updateFigurePos(int fieldId, int fignum) {
        if (fieldId == -1) {
            figureRegister.remove(fignum);
        } else {
            figureRegister.put(fignum, fieldId);
        }
    }
    public Collection<Integer> getFigureRegister() {
        return figureRegister.values();
    }
}
