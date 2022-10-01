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

import static org.junit.Assert.*;


public class GameEngineTest extends ApplicationTest {
    @Override
    public void start(Stage stage) throws Exception {

    }

    @Test
    public void getResourcePathTest() throws URISyntaxException {
    }


    @Test
    public void getJSONObjectTest() {

    }

    /**
     * @author Rita Zhou
     * @throws ClassNotFoundException
     */
    @Test
    public void parseArgumentTest() throws ClassNotFoundException {
        {
            // Test non-parameter cell
            String str = "GameEngineTest$SampleCell";
            Pair<Class<Cell>, List<String>> actual = GameEngine.parseArgument(str);
            assertEquals(SampleCell.class, actual.getKey());
            assertEquals(List.of(), actual.getValue());
        }
        {
            // Test parameter cell 1
            String str = "GameEngineTest$SampleCell&ABC&123";
            Pair<Class<Cell>, List<String>> actual = GameEngine.parseArgument(str);
            assertEquals(SampleCell.class, actual.getKey());
            assertEquals(List.of("ABC", "123"), actual.getValue());
        }
        {
            // Test parameter cell 2
            String str = "GameEngineTest$SampleCell&ABC&abc";
            Pair<Class<Cell>, List<String>> actual = GameEngine.parseArgument(str);
            assertEquals(SampleCell.class, actual.getKey());
            assertEquals(List.of("ABC", "abc"), actual.getValue());
        }
        {
            // Test parameter cell 3
            String str = "GameEngineTest$SampleCell&X and Y";
            Pair<Class<Cell>, List<String>> actual = GameEngine.parseArgument(str);
            assertEquals(SampleCell.class, actual.getKey());
            assertEquals(List.of("X and Y"), actual.getValue());
        }
        {
            // Test parameter cell 4
            String str = "GameEngineTest$SampleCell&.&,&?&!&()&~";
            Pair<Class<Cell>, List<String>> actual = GameEngine.parseArgument(str);
            assertEquals(SampleCell.class, actual.getKey());
            assertEquals(List.of(".", ",", "?", "!","()", "~"), actual.getValue());
        }
        {
            // Test parameter cell 5
            String str = "GameEngineTest$SampleCell&123&789&1&5";
            Pair<Class<Cell>, List<String>> actual = GameEngine.parseArgument(str);
            assertEquals(SampleCell.class, actual.getKey());
            assertEquals(List.of("123", "789", "1", "5"), actual.getValue());
        }
        {
            // Test for cell in a module with non-parameter
            String str = "sampleCells.SampleCell";
            Pair<Class<Cell>, List<String>> actual = GameEngine.parseArgument(str);
            assertEquals(sampleCells.SampleCell.class, actual.getKey());
            assertEquals(List.of(), actual.getValue());
        }
        {
            // Test for cell in a module with parameter
            String str = "sampleCells.SampleCell&ABC&123";
            Pair<Class<Cell>, List<String>> actual = GameEngine.parseArgument(str);
            assertEquals(sampleCells.SampleCell.class, actual.getKey());
            assertEquals(List.of("ABC", "123"), actual.getValue());
        }

    }

    /**
     * @author Rita Zhou
     * @throws URISyntaxException
     * @throws IOException
     * @throws ClassNotFoundException
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
     * @throws IOException
     * @throws URISyntaxException
     * @throws ClassNotFoundException
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


    public class SampleCell extends Cell {
        @Override
        public Cell build(List<String> arguments) {
            return null;
        }
    }
}
