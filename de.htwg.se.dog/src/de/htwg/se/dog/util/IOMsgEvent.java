package de.htwg.se.dog.util;
/**
 * Abstract calls for ObserverEvents
 * @author Michael
 *
 */
public class IOMsgEvent implements IOEvent{

	private String message;

	public IOMsgEvent(String msg) {
		this.message = msg;
	}

	public String getMessage() {
		return message;
	}

}
