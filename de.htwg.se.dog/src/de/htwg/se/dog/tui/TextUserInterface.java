package de.htwg.se.dog.tui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.se.dog.controller.GameTableInterface;
import de.htwg.se.dog.models.impl.Card;
import de.htwg.se.dog.util.Event;
import de.htwg.se.dog.util.IObserver;

public class TextUserInterface implements IObserver {

    private static final Logger LOG = LogManager.getLogger("UI");
    private final GameTableInterface controller;

    public TextUserInterface(GameTableInterface controller) {
        this.controller = controller;
        controller.addObserver(this);
    }

    private void out(String str) {
        LOG.info(str);
    }

    public void printTui() {
        out(controller.getGameFieldString());
        out(controller.getPlayerHandString());
    }

    public boolean processTurn(Scanner scanner) {
        while (controller.playerQueueIsEmpty()) {
            controller.newRound();
        }
        controller.nextPlayer();
        int card = processCardInput(scanner);
        if (card == -1)
            return false;
        if (card == 13 || card == 1 || card == 14) {
            controller.moveFigureToStart();
            printTui();
        } else {
            int fieldnr = processFigureInput(scanner);
            if (fieldnr == -1)
                return false;
        }
        //TODO sonderfälle (sieben, rauskommen, tauschen)
        //TODO zug ausführen
        System.out.println("mache Zug :)\n\n\n\n\n\n");
        return true;
    }

    private int processCardInput(Scanner scanner) {
        int card = 0;
        printTui();
        while (true) {
            out("Bitte zu spielende Kartennummer auswählen:");
            String input = scanner.next();
            try {
                Integer zahl = Integer.valueOf(input);
                if (!controller.playerHasCard(zahl)) {
                    out(String.format("Spieler %d hat keine solche Karte!", controller.getCurrentPlayer().getPlayerID()));
                    continue;
                }
                if (!controller.possibleCards(controller.getCurrentPlayer()).contains(new Card(zahl))) {
                    out(String.format("Spieler %d kann diese Karte nicht benutzen!", controller.getCurrentPlayer().getPlayerID()));
                    continue;
                }
                card = zahl;
            } catch (NumberFormatException e) {
                if (input.equalsIgnoreCase("q")) {
                    card = -1;
                }
            }

            break;
        }
        return card;
    }

    private int processFigureInput(Scanner scanner) {
        int fieldnr = 0;
        while (true) {
            out("Bitte Feldnummer der zu laufenden Figur auswählen: ");
            String input = scanner.next();
            try {
                Integer zahl = Integer.valueOf(input);
                if (!controller.fieldIsEmpty(zahl)) {
                    out(String.format("Feld %d ist leer!", zahl));
                    continue;
                }
                if (controller.getFigureOwnerID(zahl) != controller.getCurrentPlayer().getPlayerID()) {
                    out(String.format("Die Figure auf dem Feld %d gehört Spieler %d", zahl, controller.getFigureOwnerID(zahl)));
                    continue;
                }
                fieldnr = zahl;
            } catch (NumberFormatException e) {
                if (input.equalsIgnoreCase("q")) {
                    fieldnr = -1;
                }
            }
            break;
        }
        return fieldnr;
    }

    @Override
    public void update(Event e) {
        printTui();
    }
}
