package de.htwg.se.dog.models.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;

import de.htwg.se.dog.models.CardInterface;
import de.htwg.se.dog.models.FigureInterface;
import de.htwg.se.dog.models.PlayerInterface;

/**
 * Implementation of PlayerInterface
 * 
 * @author Michael, Christian
 * 
 */
public class Player implements PlayerInterface {

    private final List<FigureInterface> figure;
    private final List<CardInterface> cards;
    private final Map<Integer, Integer> figureRegister;
    private final int playernum;
    private final int startFieldNr;

    //TODO: Remove housefields-Parameter
    /**
     * Constructor to create new Player-OBJ
     * 
     * @param playerNr
     *            number of player
     * @param houseFields
     *            creates number of houseFields many figures
     * @param startFieldNr
     *            saves this number as players startFieldNr
     */
    @Inject
    public Player(int playerNr, int houseFields, int startFieldNr) {
        playernum = playerNr;
        figure = new LinkedList<FigureInterface>();
        for (int i = 0; i < houseFields; i++) {
            figure.add(new Figure(this, i + 1));
        }
        this.startFieldNr = startFieldNr;
        cards = new LinkedList<CardInterface>();
        figureRegister = new HashMap<Integer, Integer>();
    }

    @Override
    public List<CardInterface> getCardList() {

        return this.cards;
    }

    @Override
    public List<FigureInterface> getFigureList() {
        return this.figure;
    }

    @Override
    public void addCard(CardInterface c) {
        this.cards.add(c);
        Collections.sort(this.cards);
    }

    @Override
    public boolean removeCard(CardInterface c) {
        return cards.remove(c);
    }

    @Override
    public CardInterface getCardfromCardNr(int cardNr) {
        CardInterface card = null;
        for (CardInterface entry : cards) {
            if (entry.getValue() == cardNr) {
                card = entry;
                break;
            }
        }
        return card;
    }

    @Override
    public FigureInterface removeFigure() {
        if (figure.isEmpty()) {
            return null;
        }
        return figure.remove(0);
    }

    @Override
    public void addFigure(FigureInterface f) {
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

    @Override
    public String printCardsOnHand() {
        StringBuilder sb = new StringBuilder();
        for (CardInterface c : this.getCardList()) {
            sb.append(String.format("%s ", c.toString()));
        }
        return String.format("%s\nCards: %s", this.toString(), sb.toString());
    }

    @Override
    public void clearCardList() {
        this.cards.clear();
    }

    @Override
    public int getStartFieldNr() {
        return startFieldNr;
    }
}
