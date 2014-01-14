package de.htwg.se.dog.view;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.dog.controller.GameTableInterface;
import de.htwg.se.dog.controller.impl.GameTable;
import de.htwg.se.dog.models.FieldInterface;
import de.htwg.se.dog.models.GameFieldInterface;
import de.htwg.se.dog.models.PlayerInterface;
import de.htwg.se.dog.view.TextUserInterface;

public class TuiTest {
    private final static int PLAYERCOUNT = 4;
    GameTableInterface table;
    GameFieldInterface field;
    FieldInterface[] array;
    TextUserInterface tui;
    PlayerInterface p1;

    @Before
    public void setUp() {
        table = new GameTable(PLAYERCOUNT);
        tui = new TextUserInterface(table);
        table.newRound();
        table.nextPlayer();
        field = table.getGameField();
        array = field.getGameArray();
    }

    @Test
    public void testPrintTui() {
        tui.update(null);
    }
}
