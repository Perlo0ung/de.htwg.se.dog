package de.htwg.se.dog.tui;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.se.dog.models.CardInterface;
import de.htwg.se.dog.models.FieldInterface;
import de.htwg.se.dog.models.FigureInterface;
import de.htwg.se.dog.models.PlayerInterface;
import de.htwg.se.dog.models.impl.GameField;

public class TextUserInterface {
    private static final Logger LOG = LogManager.getLogger("UI");

    public void printCardsOnHand(PlayerInterface p) {
        StringBuilder sb = new StringBuilder();
        List<CardInterface> cards = p.getCardList();
        Collections.sort(cards);
        for (CardInterface c : p.getCardList()) {
            sb.append(String.format("%s ", c.toString()));
        }
        out(String.format("Player %d:  %s", p.getPlayerID(),sb.toString()));
    }

    public void printGameField(GameField game) {
        StringBuilder upper = new StringBuilder();
        int houseFields = game.getHouseCount();
        int normalFields = game.getFieldsTillHouse();
        int playercount = game.getPlayerCount();
        int range = houseFields + normalFields;
        FieldInterface[] array = game.getField();

        for (int p = 0; p < playercount; p++) {
            StringBuilder lower = new StringBuilder();
            int startFieldNr = range * p;
            int endFieldNr = startFieldNr + range;
            for (int f = startFieldNr; f < endFieldNr; f++) {
                if (array[f].isHouse()) {
                    upper.append(String.format("( %2d )", f));
                    lower.append(String.format("( %s )", getPlayerOnField(array, f)));
                } else {
                    upper.append(String.format("| %2d |", f));
                    lower.append(String.format("| %s |", getPlayerOnField(array, f)));
                }

            }
            upper.append('\n');
            lower.append('\n');
            upper.append(lower).append('\n');
        }
        out(upper.toString());
    }

    public String getPlayerOnField(FieldInterface[] array, int fieldnum) {
        FigureInterface fig = array[fieldnum].getFigure();
        String s;
        if (fig == null) {
            s = "  ";
        } else {
            s = String.format("%2d", fig.getOwnerNr());
        }
        return s;
    }

    private void out(String str) {
        LOG.info(str);
    }
}
