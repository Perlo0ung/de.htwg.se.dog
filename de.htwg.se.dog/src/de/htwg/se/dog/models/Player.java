package de.htwg.se.dog.models;

import java.util.LinkedList;
import java.util.List;

public class Player {

    private List<Figure> figure;
    private List<Card> cards;
    private final int playernum;

    public Player(int playerNr, int figcount) {
        playernum = playerNr;
        figure = new LinkedList<Figure>();
        for (int i = 0; i < figcount; i++) {
            figure.add(new Figure(this, i + 1));
        }
        cards = new LinkedList<Card>();

    }

    public List<Card> getCardList() {
        return this.cards;
    }

    public List<Figure> getFigureList() {
        return this.figure;
    }

    public void addCard(Card c) {
        this.cards.add(c);
    }

    public boolean removeCard(Card c) {
        return cards.remove(c);
    }

    public Figure removeFigure() {
        if (figure.isEmpty()) {
            return null;
        }
        return figure.remove(0);
    }

    public void addFigure(Figure f) {
        figure.add(f);
    }

    public int getPlayerID() {
        return playernum;
    }
}
