package de.htwg.se.dog.util;

/**
 * Interface for observer events
 * 
 * @author Michael
 * 
 */
public interface IObserver {
    /**
     * Method that sends the update signal to all observers
     * 
     * @param e
     *            the event
     */
    void update(IOEvent e);
}
