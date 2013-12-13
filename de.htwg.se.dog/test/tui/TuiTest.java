package tui;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.dog.controller.GameTable;
import de.htwg.se.dog.models.FieldInterface;
import de.htwg.se.dog.models.PlayerInterface;
import de.htwg.se.dog.models.impl.GameField;
import de.htwg.se.dog.tui.TextUserInterface;

public class TuiTest {
    private final static int PLAYERCOUNT = 2;
    GameTable table;
    GameField field;
    FieldInterface[] array;
    TextUserInterface tui = new TextUserInterface();
    PlayerInterface p1;

    @Before
    public void setUp() {
        table = new GameTable(PLAYERCOUNT);
        table.newRound();
        field = table.getGameField();
        array = field.getField();

    }

    @Test
    public void testGenerateGameField() {

        for (int i = 0; i < PLAYERCOUNT; i++) {
            p1 = table.getNextPlayer();
            for (int j = 0; j < 4; j++) {
                int rand = (int) (Math.random() * field.getFieldSize());
                array[rand].putFigure(p1.removeFigure(), rand);

            }
        }

        tui.printGameField(table.getGameField());
    }

    @Test
    public void testPrintCardsOnhand() {
        for (int i = 0; i < PLAYERCOUNT; i++) {
            p1 = table.getNextPlayer();
            tui.printCardsOnHand(p1);
        }
    }
}
