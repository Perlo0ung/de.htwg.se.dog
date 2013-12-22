package de.htwg.se.dog.tui;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.se.dog.controller.GameTableInterface;
import de.htwg.se.dog.models.FieldInterface;
import de.htwg.se.dog.models.PlayerInterface;
import de.htwg.se.dog.models.impl.Card;
import de.htwg.se.dog.util.Event;
import de.htwg.se.dog.util.IObserver;

public class TextUserInterface implements IObserver {

    private static final int NOTINITIALIZED = -99;
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
        Map<Integer, Integer> moves = null;
        int card = NOTINITIALIZED;
        while (true) {
            card = processCardInput(scanner);
            moves = new HashMap<Integer, Integer>();
            if (card == -1)
                return false;
            //spieler setzt aus
            if (card == -2)
                return true;
            if ((card == 13 || card == 1 || card == 14) && !controller.isPlayerStartfieldBlocked(controller.getCurrentPlayer())) {
                out("M�chtest du eine neue Figure aufs Spielfeld setzten?(J/N):");
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
            //check if valid move, if not, redo turn decision
            if (!controller.isValidMove(card, moves)) {
                out("Das ist kein g�ltiger Zug, wiederhole Zugauswahl.");
                continue;
            }
            break;
        }
        System.out.println("mache Zug :)\n\n\n\n\n\n");
        controller.playCard(card, moves);
        if (controller.currentPlayerHaswon()) {
            out(String.format("/n/n/n/n/n/n/n/n/n/n/n/nSpieler %d hat Gewonnen!", controller.getCurrentPlayer().getPlayerID()));
            return false;
        }
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
                out("Wollen sie Vorw�rts(V) oder R�ckw�rts(R) laufen?");
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
            out("7 ist noch nicht implementiert!");
            break;
        case 11:
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
                //check if targetfield is on gamefield
                if (targetFieldNr < 0 && targetFieldNr >= controller.getGameField().getFieldSize()) {
                    out("Eingegebenes Feld gibt es nicht auf dem Spielbrett.");
                    continue;
                }
                //check if on targetfield is a switchable figure
                //TODO remove possibility to switch with own figure
                FieldInterface targetField = controller.getGameField().getGameArray()[targetFieldNr];
                if (targetField.getFigure() != null && !targetField.isBlocked()) {
                    steps = targetFieldNr;
                    break;
                } else {
                    out("Auf dem Feld steht keine Figur oder sie ist blocked");
                }

            }
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
        int card = NOTINITIALIZED;
        while (true) {
            out("Bitte zu spielende Kartennummer ausw�hlen:");
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
        if (input.equalsIgnoreCase("q")) {
            out("Spiel Beendet!");
            retval = -1;
        }
        if (input.equalsIgnoreCase("skip")) {
            out(String.format("Spieler %d wirft seine Karten weg und setzt bis zu n�chsten Runde aus.", controller.getCurrentPlayer().getPlayerID()));
            controller.getCurrentPlayer().clearCardList();
            retval = -2;
        }
        return retval;
    }

    private int processFigureInput(Scanner scanner) {
        int fieldnr = NOTINITIALIZED;
        while (true) {
            out("Bitte Feldnummer der zu laufenden Figur ausw�hlen: ");
            String input = scanner.next();
            try {
                Integer zahl = Integer.valueOf(input);
                if (controller.fieldIsEmpty(zahl)) {
                    out(String.format("Feld %d ist leer!", zahl));
                    continue;
                }
                if (controller.getFigureOwnerID(zahl) != controller.getCurrentPlayer().getPlayerID()) {
                    out(String.format("Die Figure auf dem Feld %d geh�rt Spieler %d", zahl, controller.getFigureOwnerID(zahl)));
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
    public void update(Event e) {
        printTui();
    }
}
