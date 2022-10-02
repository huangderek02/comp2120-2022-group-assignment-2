import engineV2.Cell;
import engineV2.GameEngine;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the Test cases for Game Engine v.2
 * In this file, we are testing precompile and compile
 * If we can pass these tests, then we can say that our
 * game engine are working correctly
 *
 * @author Rita Zhou
 */
public class GameEngineV2Test extends ApplicationTest {
    /**
     * Testing precompile
     * @author Rita Zhou
     */
    @Test (timeout = 1000)
    public void testPrecompile() {
        Map<String, String> vars = new HashMap<>();
        vars.put("birth_0", "2&5");
        vars.put("name", "Var&List& ! ? . ");
        vars.put("cell1", "model.cellsV2.Cell1");
        vars.put("cell2", "model.cellsV2.Cell2&Hello world 5!");

        {
            String cmd = "add ${cell1} 7&4";
            assertEquals("add model.cellsV2.Cell1 7&4",
                    GameEngine.precompile(cmd, vars));

            String cmd1 = "add ${cell1}&123 7&4";
            assertNotEquals("add model.cellsV2.Cell1 7&4",
                    GameEngine.precompile(cmd1, vars));
            assertEquals("add model.cellsV2.Cell1&123 7&4",
                    GameEngine.precompile(cmd1, vars));
        }
        {
            String cmd = "add ${cell1}&${birth_0} 5&7";
            assertEquals("add model.cellsV2.Cell1&2&5 5&7",
                    GameEngine.precompile(cmd, vars));
        }
        {
            String cmd = "add ${cell2}&${name} 7&11";
            assertNotEquals("add model.cellsV2.Cell2&Hello world 5! 7&11",
                    GameEngine.precompile(cmd, vars));
            assertNotEquals("add Var&List& ! ? .  7&11",
                    GameEngine.precompile(cmd, vars));
            assertEquals("add model.cellsV2.Cell2&Hello world 5!&Var&List& ! ? .  7&11",
                    GameEngine.precompile(cmd, vars));
        }
        {
            String cmd = "fillRow ${cell1} 2";
            assertEquals("fillRow model.cellsV2.Cell1 2",
                    GameEngine.precompile(cmd, vars));

            String cmd1 = "fillRow ${cell2} 2";
            assertNotEquals("fillRow model.cellsV2.Cell1 2",
                    GameEngine.precompile(cmd1, vars));
            assertEquals("fillRow model.cellsV2.Cell2&Hello world 5! 2",
                    GameEngine.precompile(cmd1, vars));
        }
        {
            String cmd = "fillCol ${cell1} 5";
            assertEquals("fillCol model.cellsV2.Cell1 5",
                    GameEngine.precompile(cmd, vars));

            String cmd1 = "fillCol ${cell2} 5";
            assertNotEquals("fillCol model.cellsV2.Cell1 5",
                    GameEngine.precompile(cmd1, vars));
            assertEquals("fillCol model.cellsV2.Cell2&Hello world 5! 5",
                    GameEngine.precompile(cmd1, vars));
        }
        {
            String cmd = "fill ${cell1}";
            assertEquals("fill model.cellsV2.Cell1",
                    GameEngine.precompile(cmd, vars));

            String cmd1 = "fill ${cell2}";
            assertNotEquals("fill model.cellsV2.Cell1",
                    GameEngine.precompile(cmd1, vars));
            assertEquals("fill model.cellsV2.Cell2&Hello world 5!",
                    GameEngine.precompile(cmd1, vars));
        }

    }

    /**
     * Testing compiles
     * @author Rita Zhou
     */
    @Test (timeout = 1000, expected = ArrayIndexOutOfBoundsException.class)
    public void testCompiles() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        // Test add
        {
            Cell[][] map = new Cell[5][5];
            Map<String, String> literals = new HashMap<>();
            literals.put("cell", "cellsV2.Cell1");

            String cmd1 = GameEngine.precompile("add ${cell} 2&2", literals);
            GameEngine.compile(cmd1, map);
            assertEquals(cellsV2.Cell1.class, map[2][2].getClass());
            assertNull(map[2][1]);
            assertNull(map[2][3]);
            assertNull(map[1][2]);
            assertNull(map[3][2]);
        }
        {
            Cell[][] map = new Cell[1][1];
            Map<String, String> literals = new HashMap<>();
            literals.put("cell", "cellsV2.Cell1");

            String cmd1 = GameEngine.precompile("add ${cell}", literals);
            GameEngine.compile(cmd1, map);
            assertNull(map[0][0]);
        }
        {
            Cell[][] map = new Cell[5][5];
            Map<String, String> literals = new HashMap<>();
            literals.put("cell", "cellsV2.PipeCell");
            literals.put("name", "Bob");

            String cmd1 = GameEngine.precompile("add ${cell}&${name} 0&0", literals);
            GameEngine.compile(cmd1, map);
            assertEquals(cellsV2.PipeCell.class, map[0][0].getClass());
            assertEquals(List.of("Bob"), map[0][0].export());
            assertNull(map[1][0]);
            assertNull(map[0][1]);
        }
        {
            Cell[][] map = new Cell[5][5];
            Map<String, String> literals = new HashMap<>();
            literals.put("cell", "cellsV2.PipeCell");

            String cmd1 = GameEngine.precompile("add ${cell}&123 4&4", literals);
            GameEngine.compile(cmd1, map);
            assertEquals(cellsV2.PipeCell.class, map[4][4].getClass());
            assertEquals(List.of("123"), map[4][4].export());
            assertNull(map[3][4]);
            assertNull(map[4][3]);
        }
        // Test fillRow
        {
            Cell[][] map = new Cell[5][5];
            Map<String, String> literals = new HashMap<>();
            literals.put("loc_cell", "cellsV2.Cell1");

            String cmd2 = GameEngine.precompile("fillRow ${loc_cell} 3", literals);
            GameEngine.compile(cmd2, map);
            assertEquals(cellsV2.Cell1.class, map[3][0].getClass());
            assertEquals(cellsV2.Cell1.class, map[3][4].getClass());

            assertNull(map[0][0]);
            assertNull(map[1][4]);
            assertNull(map[2][3]);
            assertNull(map[4][2]);
        }
        {
            Cell[][] map = new Cell[1][1];
            Map<String, String> literals = new HashMap<>();
            literals.put("loc_cell", "cellsV2.Cell1");

            String cmd2 = GameEngine.precompile("fillRow ${loc_cell}", literals);
            GameEngine.compile(cmd2, map);
            assertNull(map[0][0]);
        }
        {
            Cell[][] map = new Cell[5][5];
            Map<String, String> literals = new HashMap<>();
            literals.put("loc_cell", "cellsV2.Cell2");
            literals.put("loc", "3&3");

            String cmd2 = GameEngine.precompile("fillRow ${loc_cell}&${loc} 1", literals);
            GameEngine.compile(cmd2, map);
            assertEquals(cellsV2.Cell2.class, map[1][0].getClass());
            assertEquals(List.of("3", "3"), map[1][0].export());
            assertEquals(cellsV2.Cell2.class, map[1][4].getClass());
            assertEquals(List.of("3", "3"), map[1][4].export());

            assertNull(map[0][0]);
            assertNull(map[2][4]);
            assertNull(map[3][3]);
            assertNull(map[4][2]);
        }
        // Testing FillCol
        {
            Cell[][] map = new Cell[5][5];
            Map<String, String> literals = new HashMap<>();
            literals.put("loc_cell", "cellsV2.PipeCell");

            String cmd2 = GameEngine.precompile("fillCol ${loc_cell} 4", literals);
            GameEngine.compile(cmd2, map);
            assertEquals(cellsV2.PipeCell.class, map[3][4].getClass());
            assertEquals(cellsV2.PipeCell.class, map[1][4].getClass());

            assertNull(map[0][0]);
            assertNull(map[2][1]);
            assertNull(map[3][3]);
            assertNull(map[4][2]);
        }
        {
            Cell[][] map = new Cell[1][1];
            Map<String, String> literals = new HashMap<>();
            literals.put("loc_cell", "cellsV2.Cell1");

            String cmd2 = GameEngine.precompile("fillCol ${loc_cell}", literals);
            GameEngine.compile(cmd2, map);
            assertNull(map[0][0]);
        }
        {
            Cell[][] map = new Cell[5][5];
            Map<String, String> literals = new HashMap<>();
            literals.put("loc_cell", "cellsV2.PipeCell");

            String cmd2 = GameEngine.precompile("fillCol ${loc_cell}&7&a&!&o 0", literals);
            GameEngine.compile(cmd2, map);
            assertEquals(cellsV2.PipeCell.class, map[2][0].getClass());
            assertEquals(List.of("7", "a", "!", "o"), map[2][0].export());
            assertEquals(cellsV2.PipeCell.class, map[0][0].getClass());
            assertEquals(List.of("7", "a", "!", "o"), map[0][0].export());


            assertNull(map[0][4]);
            assertNull(map[2][1]);
            assertNull(map[3][3]);
            assertNull(map[4][2]);
        }

        // Testing Fill
        {
            Cell[][] map = new Cell[5][5];
            Map<String, String> literals = new HashMap<>();
            literals.put("loc_cell", "cellsV2.Cell1");

            String cmd = GameEngine.precompile("fill ${loc_cell}", literals);
            GameEngine.compile(cmd, map);
            assertEquals(cellsV2.Cell1.class, map[2][0].getClass());
            assertEquals(cellsV2.Cell1.class, map[0][0].getClass());
        }
        {
            Cell[][] map = new Cell[1][1];
            Map<String, String> literals = new HashMap<>();
            literals.put("loc_cell", "cellsV2.Cell1");

            String cmd2 = GameEngine.precompile("fill ${loc_cell}", literals);
            GameEngine.compile(cmd2, map);
            assertEquals(cellsV2.Cell1.class, map[0][0].getClass());
        }
    }
}
