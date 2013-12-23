package de.htwg.se.dog.util;

import java.util.LinkedList;
import java.util.List;

public class Observable implements IObservable {
    private List<IObserver> observers = new LinkedList<IObserver>();

    @Override
    public void addObserver(IObserver s) {
        observers.add(s);
    }

    @Override
    public void removeObserver(IObserver s) {
        observers.remove(s);
    }

    @Override
    public void removeAllObservers() {
        observers.clear();
    }

    @Override
    public void notifyObservers() {
        notifyObservers(null);
    }

    @Override
    public void notifyObservers(IOEvent e) {
        for (IObserver o : observers) {
            o.update(e);
        }
    }
}