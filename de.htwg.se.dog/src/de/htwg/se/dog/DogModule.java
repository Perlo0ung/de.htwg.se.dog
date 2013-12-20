package de.htwg.se.dog;

import com.google.inject.AbstractModule;

import de.htwg.se.dog.controller.CardDealerInterface;
import de.htwg.se.dog.models.CardStackInterface;
import de.htwg.se.dog.models.GameFieldInterface;
import de.htwg.se.dog.models.PlayerInterface;

public class DogModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(PlayerInterface.class).to(de.htwg.se.dog.models.impl.Player.class);
		bind(CardDealerInterface.class).to(de.htwg.se.dog.controller.impl.CardDealer.class);
		bind(CardStackInterface.class).to(de.htwg.se.dog.models.impl.CardStack.class);
		bind(GameFieldInterface.class).to(de.htwg.se.dog.models.impl.GameField.class);
	}

}
