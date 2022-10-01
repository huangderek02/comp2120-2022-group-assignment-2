import engine.Cell;
import engine.GameEngine;
import engine.GameObject;
import engine.SceneObject;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;


import org.testfx.framework.junit.ApplicationTest;
import sampleCells.EmptyCell;
import sampleCells.NewCell;
import sampleCells.WallCell;

import static org.junit.Assert.*;

/**
 * @author Rita Zhou
 * This is the test for the GameEngine
 * We are mainly test three method in the GameEngine: parseArgument,
 * getProperty, and getBuildArguments, getCellClassObj.
 * As long as we can run these methods, then we can say that we
 * build the game engine correctly. Because these methods used
 * all functions in the GameEngine and return the current arguments
 * we required.
 */
public class GameEngineTest extends ApplicationTest {
    /**
     * boot javafx
     * @author Rita
     * @param stage
     */
    @Override
    public void start(Stage stage) throws Exception {

    }


    /**
     * @author Rita Zhou
     */
    @Test (timeout = 1000)
    public void parseArgumentTest() throws ClassNotFoundException {
        {
            // Test non-parameter cell
            String str = "GameEngineTest$SampleCell";
            Pair<Class, List<String>> actual = GameEngine.parseArgument(str);
            assertEquals(SampleCell.class, actual.getKey());
            assertEquals(List.of(), actual.getValue());
        }
        {
            // Test parameter cell 1
            String str = "GameEngineTest$SampleCell&ABC&123";
            Pair<Class, List<String>> actual = GameEngine.parseArgument(str);
            assertEquals(SampleCell.class, actual.getKey());
            assertEquals(List.of("ABC", "123"), actual.getValue());
        }
        {
            // Test parameter cell 2
            String str = "GameEngineTest$SampleCell&ABC&abc";
            Pair<Class, List<String>> actual = GameEngine.parseArgument(str);
            assertEquals(SampleCell.class, actual.getKey());
            assertEquals(List.of("ABC", "abc"), actual.getValue());
        }
        {
            // Test parameter cell 3
            String str = "GameEngineTest$SampleCell&X and Y";
            Pair<Class, List<String>> actual = GameEngine.parseArgument(str);
            assertEquals(SampleCell.class, actual.getKey());
            assertEquals(List.of("X and Y"), actual.getValue());
        }
        {
            // Test parameter cell 4
            String str = "GameEngineTest$SampleCell&.&,&?&!&()&~";
            Pair<Class, List<String>> actual = GameEngine.parseArgument(str);
            assertEquals(SampleCell.class, actual.getKey());
            assertEquals(List.of(".", ",", "?", "!","()", "~"), actual.getValue());
        }
        {
            // Test parameter cell 5
            String str = "GameEngineTest$SampleCell&123&789&1&5";
            Pair<Class, List<String>> actual = GameEngine.parseArgument(str);
            assertEquals(SampleCell.class, actual.getKey());
            assertEquals(List.of("123", "789", "1", "5"), actual.getValue());
        }
        {
            // Test for cell in a module with non-parameter
            String str = "sampleCells.SampleCell";
            Pair<Class, List<String>> actual = GameEngine.parseArgument(str);
            assertEquals(sampleCells.SampleCell.class, actual.getKey());
            assertEquals(List.of(), actual.getValue());
        }
        {
            // Test for cell in a module with parameter
            String str = "sampleCells.SampleCell&ABC&123";
            Pair<Class, List<String>> actual = GameEngine.parseArgument(str);
            assertEquals(sampleCells.SampleCell.class, actual.getKey());
            assertEquals(List.of("ABC", "123"), actual.getValue());
        }

    }

    /**
     * @author Rita Zhou
     */
    @Test (timeout=1000)
    public void testGameObjectPropertyAccessing() throws URISyntaxException, IOException, ClassNotFoundException {
        GameObject gameObject = GameEngine.loadGame("template-0/header.json");
        assertNotNull(gameObject.getProperty("title"));
        assertNotNull(gameObject.getProperty("camera"));
        assertEquals("Courage", gameObject.getProperty("title"));
        assertEquals("0:(1,1)", gameObject.getProperty("camera"));
        assertNull(gameObject.getProperty("123"));
    }

    /**
     * @author Rita Zhou
     */
    @Test (timeout = 1000)
    public void testSceneObjectPropertyAccessing() throws IOException, URISyntaxException, ClassNotFoundException {
        GameObject gameObject = GameEngine.loadGame("template-0/header.json");
        SceneObject sceneObject = gameObject.getSceneObject(0);
        SceneObject sceneObject1 = gameObject.getSceneObject(1);

        assertNotNull(sceneObject.getProperty("title"));
        assertNotNull(sceneObject.getProperty("birth-point"));
        assertEquals("level 1", sceneObject.getProperty("title"));
        assertEquals("(2,2)", sceneObject.getProperty("birth-point"));
        assertNull(sceneObject.getProperty("123"));

        assertNotNull(sceneObject1.getProperty("title"));
        assertNotNull(sceneObject1.getProperty("birth-point"));
        assertEquals("level 2", sceneObject1.getProperty("title"));
        assertEquals("(2,2)", sceneObject1.getProperty("birth-point"));
        assertNull(sceneObject1.getProperty("We"));
    }

    /**
     * @author Rita Zhou
     */
    @Test (timeout = 1000)
    public void testGameObjectArgumentsAccessing() throws IOException, URISyntaxException, ClassNotFoundException {
        GameObject gameObject = GameEngine.loadGame("template-0/header.json");

        assertEquals(List.of(), gameObject.getBuildArguments(EmptyCell.class));
        assertEquals(List.of("123", "9", "1"), gameObject.getBuildArguments(WallCell.class));
        assertEquals(List.of("ABC", "def"), gameObject.getBuildArguments(sampleCells.SampleCell.class));
        assertEquals(List.of("!", "?", ".", ",", "()", "~"), gameObject.getBuildArguments(NewCell.class));

        assertNull(gameObject.getBuildArguments(SampleCell.class));
    }

    /**
     * @author Rita Zhou
     */
    @Test (timeout = 1000)
    public void testSceneObjectArgumentAccessing() throws IOException, URISyntaxException, ClassNotFoundException {
        GameObject gameObject = GameEngine.loadGame("template-0/header.json");

        SceneObject sceneObject1 = gameObject.getSceneObject(0);
        SceneObject sceneObject2 = gameObject.getSceneObject(1);

        assertNotNull(sceneObject1.getBuildArguments(EmptyCell.class));
        assertNotNull(sceneObject1.getBuildArguments(WallCell.class));
        assertNotNull(sceneObject1.getBuildArguments(sampleCells.SampleCell.class));
        assertNotNull(sceneObject1.getBuildArguments(NewCell.class));

        assertEquals(List.of("1", "23", "B", "AC"), sceneObject1.getBuildArguments(EmptyCell.class));
        assertEquals(List.of(), sceneObject1.getBuildArguments(WallCell.class));
        assertEquals(List.of("ABC", "def"), sceneObject1.getBuildArguments(sampleCells.SampleCell.class));
        assertEquals(List.of("!", "?", ".", ",", "()", "~"), sceneObject1.getBuildArguments(NewCell.class));

        assertNotNull(sceneObject2.getBuildArguments(EmptyCell.class));
        assertNotNull(sceneObject2.getBuildArguments(WallCell.class));
        assertNotNull(sceneObject2.getBuildArguments(sampleCells.SampleCell.class));
        assertNotNull(sceneObject2.getBuildArguments(NewCell.class));

        assertEquals(List.of("a", "bc", ".", "?"), sceneObject2.getBuildArguments(EmptyCell.class));
        assertEquals(List.of(), sceneObject2.getBuildArguments(WallCell.class));
        assertEquals(List.of("ABC", "def"), sceneObject2.getBuildArguments(sampleCells.SampleCell.class));
        assertEquals(List.of("!", "?", ".", ",", "()", "~"), sceneObject2.getBuildArguments(NewCell.class));

        assertNull(sceneObject1.getBuildArguments(SampleCell.class));
        assertNull(sceneObject2.getBuildArguments(SampleCell.class));
    }

    /**
     * @author Rita Zhou
     */
    @Test (timeout = 1000)
    public void testGetCellClassObj() throws IOException, URISyntaxException, ClassNotFoundException {
        GameObject gameObject = GameEngine.loadGame("template-0/header.json");

        SceneObject sceneObject1 = gameObject.getSceneObject(0);
        SceneObject sceneObject2 = gameObject.getSceneObject(1);

        assertEquals(EmptyCell.class, sceneObject1.getCellClassObj(2, 1));
        assertEquals(WallCell.class, sceneObject1.getCellClassObj(4, 2));
        assertEquals(EmptyCell.class, sceneObject1.getCellClassObj(3, 3));
        assertEquals(WallCell.class, sceneObject1.getCellClassObj(0, 0));
        assertEquals(sampleCells.SampleCell.class, sceneObject1.getCellClassObj(4, 3));
        assertEquals(NewCell.class, sceneObject1.getCellClassObj(0, 1));

        assertEquals(EmptyCell.class, sceneObject2.getCellClassObj(1, 3));
        assertEquals(WallCell.class, sceneObject2.getCellClassObj(0, 4));
        assertEquals(EmptyCell.class, sceneObject2.getCellClassObj(0, 0));
        assertEquals(WallCell.class, sceneObject2.getCellClassObj(4, 4));
        assertEquals(sampleCells.SampleCell.class, sceneObject2.getCellClassObj(3, 2));
        assertEquals(NewCell.class, sceneObject2.getCellClassObj(2, 1));
    }

    public static class SampleCell extends Cell {
        @Override
        public Cell build(List<String> arguments) {
            return null;
        }
    }
}
