package de.htwg.se.dog.util;

/**
 * Interface for observables
 * 
 * @author Michael
 * 
 */
public interface IObservable {
    /**
     * adds the observer to the observable list
     * 
     * @param s
     */
    void addObserver(IObserver s);

    /**
     * removes the observer from the observable list
     * 
     * @param s
     */
    void removeObserver(IObserver s);

    /**
     * removes all observers
     */
    void removeAllObservers();

    /**
     * notifies all observers
     */
    void notifyObservers();

    /**
     * notfies all observer and sends the event given in the parameter
     * 
     * @param e
     */
    void notifyObservers(IOEvent e);
}
