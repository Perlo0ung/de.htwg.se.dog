package de.htwg.se.dog.models.impl;

import de.htwg.se.dog.models.FieldInterface;

public class Field implements FieldInterface<Figure> {

    private final int owner;
    private boolean house;
    private boolean blocked;
    private Figure figure;

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
    public void putFigure(Figure f) {
        this.figure = f;
    }

    @Override
    public Figure removeFigure() {
        Figure tmp = this.figure;
        this.figure = null;
        return tmp;
    }

    @Override
    public Figure getFigure() {
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
