package de.htwg.se.dog.models;

import java.util.List;

public interface PlayerInterface {
	/**
	 * returns list of Cards player has
	 * 
	 * @return List of cards the player has on his hand 
	 */
	List<CardInterface> getCardList();

	/**
	 * returns list of figures player has
	 * 
	 * @return List of figures player has
	 */
	List<FigureInterface> getFigureList();

	/**
	 * adds a card to players cardlist
	 * 
	 * @param c
	 *            card which should be added
	 */
	void addCard(CardInterface c);

	/**
	 * removes card from players cardlist
	 * 
	 * @param c
	 *            card which should be removed
	 * @return boolean: true if remove was succsessfull, otherwise false
	 */
	boolean removeCard(CardInterface c);

	/**
	 * removes figure from players figurelist
	 * 
	 * @return Figure: returns figure which was removed from players figurelist
	 */
	FigureInterface removeFigure();

	/**
	 * adds figure to players figurelist
	 * 
	 * @param f
	 *            : Figure which should be added to players figurelist
	 */
	void addFigure(FigureInterface f);

	/**
	 * returnes player ID
	 * 
	 * @return int: returns playernumber
	 */
	int getPlayerID();

	/**
	 * Update the position where the figure assosiated with fignum it currently
	 * at. If fieldId is -1 the figure will be removed from the register
	 * 
	 * @param fieldId
	 *            the FieldNumber where the is at
	 * @param fignum
	 *            the internal figurenumber for this figure
	 */
	void updateFigurePos(int fignum, int fieldId);

	/**
	 * Returns all the places where figures are
	 * 
	 * @return List with the figure fieldnumbers
	 */
	List<Integer> getFigureRegister();

	/**
	 * Returns a String with all cards the player has output is sorted y the
	 * cards value
	 * 
	 * @return String repreentation of the players cards
	 */
	String printCardsOnHand();

	/**
	 * removes all cards from specified players hand
	 */
	void clearCardList();

	/**
	 * returns the card for a given number
	 * 
	 * @param cardNr
	 * @return the card if player has it on his hand, otherwise returns null
	 */
	CardInterface getCardfromCardNr(int cardNr);

	/**
	 * returns the starfield of this player
	 * @return the startfield of this player
	 */
	int getStartFieldNr();
}
