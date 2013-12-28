package de.htwg.se.dog.util;
/**
 * Sends String message to observers
 * @author Michael
 *
 */
public class IOMsgEvent implements IOEvent{

	private String message;
	/**
	 * initialises the message that will be send
	 * @param msg the message
	 */
	public IOMsgEvent(String msg) {
		this.message = msg;
	}

	/**
	 * get the string from message event
	 * @return MsgEvent as String
	 */
	public String getMessage() {
		return message;
	}

}
