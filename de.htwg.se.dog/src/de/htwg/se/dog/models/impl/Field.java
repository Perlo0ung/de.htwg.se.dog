package de.htwg.se.dog.models.impl;

import de.htwg.se.dog.models.FieldInterface;
import de.htwg.se.dog.models.FigureInterface;

public class Field implements FieldInterface {

    private final int owner;
    private boolean house;
    private boolean blocked;
    private FigureInterface figure;

    public Field(int owner) {
        this.blocked = false;
        if (owner == 0) {
            this.owner = 0;
            this.house = false;
        } else {
            this.owner = owner;
            this.house = true;
        }
    }

    @Override
    public void putFigure(FigureInterface f) {
        this.figure = f;
    }

    @Override
    public FigureInterface removeFigure() {
        FigureInterface tmp = this.figure;
        this.figure = null;
        return tmp;
    }

    @Override
    public FigureInterface getFigure() {
        return this.figure;
    }

    @Override
    public int getFigureOwnerNr() {
        return figure.getOwnerNr();
    }

    @Override
    public int getOwner() {
        return this.owner;
    }

    @Override
    public void setBlocked(boolean a) {
        this.blocked = a;
    }

    @Override
    public boolean getBlocked() {
        return this.blocked;
    }

    @Override
    public boolean isHouse() {
        return this.house;
    }

}
