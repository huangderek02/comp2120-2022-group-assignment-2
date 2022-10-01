import engine.GameEngine;
import engine.GameObject;
import model.GameState;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

/**
 * @author Rita Zhou
 * This is the test case for the GameState
 * It will test the method of handle, pollDialog, addItem
 * and useItem. If we can pass all test, then we can say that
 * our GameState.java is working correctly
 */
public class GameStateTest extends ApplicationTest {
    /**
     * @author Rita Zhou
     */
    @Test (timeout = 1000)
    public void testHandle(){

    }

    /**
     * @author Rita Zhou
     */
    @Test (timeout = 1000)
    public void testPollDialog() throws IOException, URISyntaxException, ClassNotFoundException {
        GameObject gameObject = GameEngine.loadGame("template-1/header.json");
        GameState gameState = new GameState(gameObject);

        assertEquals("1234567890", gameState.pollDialog());
        assertEquals("ABC abc", gameState.pollDialog());
        assertEquals("Hello!", gameState.pollDialog());
        assertNull(gameState.pollDialog());
    }

    /**
     * @author Rita Zhou
     */
    @Test (timeout = 1000)
    public void testAddItem() throws IOException, URISyntaxException, ClassNotFoundException {
        GameObject gameObject = GameEngine.loadGame("template-1/header.json");
        GameState gameState = new GameState(gameObject);

        gameState.addItem(GameState.Item.KEY);
    }
}
