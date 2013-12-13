package de.htwg.se.dog.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Offers neccessary methods which can be used by the view to display game
 * status.
 * 
 * @author Michael
 */
public abstract class UIObservable {
    private List<IObserver> observers = new ArrayList<IObserver>();

    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    public void removeAllObservers() {
        observers.clear();
    }

    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (IObserver o : observers) {
            o.update(this);
        }
    }
}