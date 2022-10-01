import cellsV2.Cell2;
import engineV2.Cell;
import engineV2.GameEngine;
import cellsV2.Cell1;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameEngineV2Test extends ApplicationTest {
    @Test
    public void testPrecompile() {
        Map<String, String> vars = new HashMap<>();
        vars.put("birth_0", "2&5");
        vars.put("cell1", "model.cellsV2.Cell1");
        // TODO: add more literals by put

        {
            String cmd = "add ${cell1}&${birth_0} 5&7";
            assertEquals("add model.cellsV2.Cell1&2&5 5&7",
                    GameEngine.precompile(cmd, vars));
        }

        // TODO: add more tests
    }

    @Test
    public void testCompiles() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Cell[][] map = new Cell[5][5];
        Map<String, String> literals = new HashMap<>();
        literals.put("cell", "cellsV2.Cell1");
        literals.put("loc_cell", "cellsV2.Cell2");
        literals.put("loc", "3&3");
        // TODO: add more literals

        // Test add
        /// TODO: add more
        String cmd1 = GameEngine.precompile("add ${cell} 2&2", literals);
        GameEngine.compile(cmd1, map);
        assertEquals(Cell1.class, map[2][2].getClass());

        // Test fillRow
        // TODO: add more
        String cmd2 = GameEngine.precompile("fillRow ${loc_cell}&${loc} 1", literals);
        GameEngine.compile(cmd2, map);
        assertEquals(Cell2.class, map[1][0].getClass());
        assertEquals(List.of("3", "3"), map[1][0].export());
        assertEquals(Cell2.class, map[1][4].getClass());
        assertEquals(List.of("3", "3"), map[1][4].export());

        // TODO: fillCol

        // TODO: fill
    }
}
