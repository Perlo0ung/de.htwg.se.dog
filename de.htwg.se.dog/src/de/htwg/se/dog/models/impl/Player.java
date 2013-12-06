package de.htwg.se.dog.models.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.htwg.se.dog.models.PlayerInterface;

public class Player implements PlayerInterface<Figure, Card> {

    private final List<Figure> figure;
    private final List<Card> cards;
    private final Map<Integer, Integer> figureRegister;
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

    @Override
    public List<Card> getCardList() {
        return this.cards;
    }

    @Override
    public List<Figure> getFigureList() {
        return this.figure;
    }

    @Override
    public void addCard(Card c) {
        this.cards.add(c);
    }

    @Override
    public boolean removeCard(Card c) {
        return cards.remove(c);
    }

    @Override
    public Figure removeFigure() {
        if (figure.isEmpty()) {
            return null;
        }
        return figure.remove(0);
    }

    @Override
    public void addFigure(Figure f) {
        figure.add(f);
    }

    @Override
    public int getPlayerID() {
        return playernum;
    }

    @Override
    public void updateFigurePos(int fignum, int fieldId) {
        if (fieldId == -1) {
            figureRegister.remove(fignum);
        } else {
            figureRegister.put(fignum, fieldId);
        }
    }

    @Override
    public List<Integer> getFigureRegister() {
        return new LinkedList<Integer>(figureRegister.values());
    }

    @Override
    public String toString() {
        return String.format("PlayerId: %s", this.getPlayerID());
    }
}
