package tui;

import org.junit.Before;

import de.htwg.se.dog.controller.GameTableInterface;
import de.htwg.se.dog.controller.impl.GameTable;
import de.htwg.se.dog.models.FieldInterface;
import de.htwg.se.dog.models.GameFieldInterface;
import de.htwg.se.dog.models.PlayerInterface;
import de.htwg.se.dog.tui.TextUserInterface;

public class TuiTest {
    private final static int PLAYERCOUNT = 2;
    GameTableInterface table;
    GameFieldInterface field;
    FieldInterface[] array;
    TextUserInterface tui = new TextUserInterface(table);
    PlayerInterface p1;

    @Before
    public void setUp() {
        table = new GameTable(PLAYERCOUNT);
        table.newRound();
        field = table.getGameField();
        array = field.getField();

    }
}
