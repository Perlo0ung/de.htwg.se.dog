package de.htwg.se.dog.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.se.dog.controller.GameTableInterface;
import de.htwg.se.dog.models.FieldInterface;
import de.htwg.se.dog.models.impl.Card;
import de.htwg.se.dog.util.IOEvent;
import de.htwg.se.dog.util.IObserver;

public class TextUserInterface implements IObserver {

    private static final int CARD4 = 4;
    private static final int CARD7 = 7;
    private static final int CARD11 = 11;
    private static final int CARD14 = 14;
    private static final int CARD13 = 13;
    private static final int NOTINITIALIZED = -99;
    private static final int QUIT = -1;
    private static final int SKIP = -2;
    private static final int RETRY = -3;
    private static final Logger LOG = LogManager.getLogger("UI");
    private final GameTableInterface controller;

    public TextUserInterface(GameTableInterface controller) {
        this.controller = controller;
        controller.addObserver(this);
    }

    private synchronized void out(String str) {
        LOG.info(str);
    }

    public synchronized void printTui() {
        out(controller.getGameFieldString());
        out(controller.getPlayerHandString());
    }

    public boolean processTurn(Scanner scanner) {
        while (controller.playerQueueIsEmpty()) {
            out("Kein Spieler hat mehr Karten, beginne neue Runde.");
            controller.newRound();
        }
        controller.nextPlayer();
        controller.notifyObservers();
        int fieldnr = -1;
        int steps = 0;
        Map<Integer, Integer> moves = null;
        int card = NOTINITIALIZED;
        while (true) {
            out("Mögliche Sonderbefehle: retry(Zugauswahl von vorne), quit(beendet das Spiel)\n");
            card = processCardInput(scanner);
            moves = new HashMap<Integer, Integer>();
            if (card == QUIT)
                return false;
            // spieler setzt aus
            if (card == SKIP)
                return true;
            if (card == RETRY) {
                continue;
            }
            if ((card == CARD13 || card == 1 || card == CARD14) && !controller.isPlayerStartfieldBlocked()) {
                out("Möchtest du eine neue Figure aufs Spielfeld setzten?(J/N):");
                char input = scanner.next().charAt(0);
                if ((input == 'J' || input == 'j') && controller.moveFigureToStart(card)) {
                    out("Moving Figure to Start-Field");
                    return true;
                } else {
                    out("Mache normalen Zug.");
                }
            }
            //Joker auswahl
            if (card == CARD14) {
                String input;
                try {
                    out("Bitte KartenNummer der neuen Karte eingeben:");
                    input = scanner.next();
                    int targetFieldNr = Integer.valueOf(input);
                    if (targetFieldNr <= 0 || targetFieldNr > 14)
                        throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    out("Bitte nur Zahlen im Bereich [1:13] eingeben.");
                }
                continue;
            }
            fieldnr = processFigureInput(scanner);
            if (fieldnr == QUIT)
                return false;
            if (card == SKIP)
                return true;
            if (card == RETRY) {
                continue;
            }
            steps = processSteps(scanner, card);
            moves.put(fieldnr, steps);
            // check if valid move, if not, redo turn decision
            if (!controller.isValidMove(card, moves)) {
                out("Das ist kein gültiger Zug, wiederhole Zugauswahl.");
                continue;
            }
            break;
        }
        out("mache Zug :)\n\n\n\n\n\n");
        controller.playCard(card, moves);
        if (controller.currentPlayerHaswon()) {
            out(String.format(
                    "%n%n%n%n%n%n%n%n%n%n%n%nSpieler %d hat Gewonnen!",
                    controller.getCurrentPlayerID()));
            return false;
        }
        return true;
    }

    private int processSteps(Scanner scanner, int cardNr) {
        int steps = 0;
        boolean wertOkay = false;
        switch (cardNr) {
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
        case CARD4:
            while (!wertOkay) {
                out("Wollen sie Vorwärts(V) oder Rückwärts(R) laufen?");
                String tmp = scanner.next();
                if (tmp.equalsIgnoreCase("V")) {
                    steps = CARD4;
                    wertOkay = true;
                } else if (tmp.equalsIgnoreCase("R")) {
                    steps = -CARD4;
                    wertOkay = true;
                } else {
                    out("Bitte nur R oder V eingeben.");
                }
            }
            break;
        case CARD7:
            steps = CARD7;
            break;
        case CARD11:
            int targetFieldNr = -1;
            while (true) {
                String input;
                try {
                    out("Bitte Feldnummer der mit ihrer zu tauschenden Figur eingeben:");
                    input = scanner.next();
                    targetFieldNr = Integer.valueOf(input);
                } catch (NumberFormatException e) {
                    out("Bitte nur Zahlen eingeben.");
                }
                // check if targetfield is on gamefield
                if (targetFieldNr < 0 && targetFieldNr >= controller.getGameField().getFieldSize()) {
                    out("Eingegebenes Feld gibt es nicht auf dem Spielbrett.");
                    continue;
                }
                // check if on targetfield is a switchable figure
                FieldInterface targetField = controller.getGameField().getGameArray()[targetFieldNr];
                if (targetField.getFigure() != null && !targetField.isBlocked()) {
                    steps = targetFieldNr;
                    break;
                } else {
                    out("Auf dem Feld steht keine Figur oder sie ist blocked");
                }

            }
            break;
        default:
            steps = cardNr;
            break;
        }
        return steps;
    }

    private int processCardInput(Scanner scanner) {
        int card = NOTINITIALIZED;
        while (true) {
            out("Bitte zu spielende Kartennummer auswählen:");
            String input = scanner.next();
            try {
                Integer zahl = Integer.valueOf(input);
                if (!controller.playerHasCard(zahl)) {
                    out(String.format("Spieler %d hat keine solche Karte!", controller.getCurrentPlayerID()));
                    continue;
                }
                if (!controller.possibleCards(controller.getCurrentPlayer()).contains(new Card(zahl))) {
                    out(String.format("Spieler %d kann diese Karte nicht benutzen!", controller.getCurrentPlayerID()));
                    continue;
                }
                card = zahl;
            } catch (NumberFormatException e) {
                card = stringEingabe(input);
            }
            if (card > NOTINITIALIZED) {
                break;
            }

        }
        return card;
    }

    private int stringEingabe(String input) {
        int retval = NOTINITIALIZED;
        if (input.equalsIgnoreCase("quit")) {
            out("Spiel Beendet!");
            retval = QUIT;
        }
        if (input.equalsIgnoreCase("skip")) {
            out(String.format("Spieler %d wirft seine Karten weg und setzt bis zur nächsten Runde aus.", controller.getCurrentPlayerID()));
            controller.getCurrentPlayer().clearCardList();
            retval = SKIP;
        }
        if (input.equals("retry")) {

            retval = RETRY;
        }
        return retval;
    }

    private int processFigureInput(Scanner scanner) {
        int fieldnr = NOTINITIALIZED;
        while (true) {
            String input = null;
            try {
                out("Bitte Feldnummer der zu laufenden Figur auswählen: ");
                input = scanner.next();
                Integer zahl = Integer.valueOf(input);
                if (controller.fieldIsEmpty(zahl)) {
                    out(String.format("Feld %d ist leer!", zahl));
                    continue;
                }
                if (controller.getFigureOwnerID(zahl) != controller.getCurrentPlayerID()) {
                    out(String.format("Die Figure auf dem Feld %d gehört Spieler %d", zahl, controller.getFigureOwnerID(zahl)));
                    continue;
                }
                fieldnr = zahl;
            } catch (NumberFormatException e) {
                fieldnr = stringEingabe(input);
            }
            if (fieldnr > NOTINITIALIZED) {
                break;
            }
        }
        return fieldnr;
    }

    @Override
    public void update(IOEvent e) {
        if (e == null) {
            printTui();
        }
    }

}
