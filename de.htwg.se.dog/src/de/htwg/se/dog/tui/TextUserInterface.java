package de.htwg.se.dog.tui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.se.dog.controller.GameTableInterface;
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

    public boolean processCardInput(String line) {
        boolean continu = true;
        try {
            Integer zahl = Integer.valueOf(line);
            switch (zahl) {
            case (2):
            case (3):
            case (4):
            case (5):
            case (6):
            case (8):
            case (9):
            case (11):
            case (12):
            case (13):
                controller.playCard(zahl);
                break;
            case (10):
                break;
            case (1):
                //1 oder 11 laufen
                controller.playCard()
                break;
            case (7):
                break;
            case (14):
                default:
                
            }
        } catch (NumberFormatException e) {
            if (line.equalsIgnoreCase("q")) {
                continu = false;
            }
        }
        return false;
    }

    public boolean processFigureInput(String line) {
        return false;
    }

    @Override
    public void update(Event e) {
        printTui();
    }
}
