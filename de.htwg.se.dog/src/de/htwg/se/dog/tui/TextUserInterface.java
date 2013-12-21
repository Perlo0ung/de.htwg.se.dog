package de.htwg.se.dog.tui;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.se.dog.controller.GameTableInterface;
import de.htwg.se.dog.models.PlayerInterface;
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
            out("Kein Spieler hat mehr Karten, beginne neue Runde.");
            controller.newRound();
        }
        controller.nextPlayer();
        printTui();
        int fieldnr = -1;
        int steps = 0;
        int card = processCardInput(scanner);
        Map<Integer, Integer> moves = new HashMap<Integer, Integer>();
        if (card == -1)
            return false;
        //TODO Check && if startfield blocked
        if ((card == 13 || card == 1 || card == 14) && controller.isPlayerStartfieldBlocked(controller.getCurrentPlayer())) {
            out("Möchtest du eine neue Figure aufs Spielfeld setzten?(J/N):");
            char input = scanner.next().charAt(0);
            if ((input == 'J' || input == 'j') && controller.moveFigureToStart()) {
                out("Moving Figure to Start-Field");
                PlayerInterface currentPlayer = controller.getCurrentPlayer();
                currentPlayer.removeCard(currentPlayer.getCardfromCardNr(card));
                return true;
            } else {
                out("Mache normalen Zug.");
            }
        }
        fieldnr = processFigureInput(scanner);
        if (fieldnr == -1)
            return false;
        steps = processSteps(scanner, card);
        moves.put(fieldnr, steps);
        System.out.println("mache Zug :)\n\n\n\n\n\n");
        controller.playCard(card, moves);
        return true;
    }

    private int processSteps(Scanner scanner, int CardNr) {
        int steps = 0;
        boolean wertOkay = false;
        switch (CardNr) {
        case 1:
            while (!wertOkay) {
                out("Wollen Sie 11 oder 1 laufen? Bitte Zahl eingeben:");
                String tmp = scanner.next();
                if (tmp.equalsIgnoreCase("1") || tmp.equalsIgnoreCase("11")) {
                    steps = Integer.valueOf(tmp);
                    wertOkay = true;
                } else {
                    out("Bitte nur 1 oder 11 eingeben.");
                }
            }
            break;
        case 4:
            while (!wertOkay) {
                out("Wollen sie Vorwärts(V) oder Rückwärts(R) laufen?");
                String tmp = scanner.next();
                if (tmp.equalsIgnoreCase("V")) {
                    steps = 4;
                    wertOkay = true;
                } else if (tmp.equalsIgnoreCase("R")) {
                    steps = -4;
                    wertOkay = true;
                } else {
                    out("Bitte nur R oder V eingeben.");
                }
            }
            break;
        case 7:
            //TODO 7 aufteilen
            System.out.println("7 ist noch nicht implementiert!");
            break;
        case 11:
            //TODO switch einbauen
            System.out.println("tauschen ist noch nicht implementiert.");
            break;
        case 2:
        case 3:
        case 5:
        case 6:
        case 8:
        case 9:
        case 10:
        case 12:
        case 13:
            steps = CardNr;
            break;
        }
        return steps;
    }

    private int processCardInput(Scanner scanner) {
        int card = 0;
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
                    out("Spiel Beendet!");
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
                if (controller.fieldIsEmpty(zahl)) {
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
                    out("Spiel Beendet!");
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
