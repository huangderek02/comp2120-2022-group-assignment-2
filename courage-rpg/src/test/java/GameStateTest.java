import engineV2.GameEngine;
import engineV2.GameObject;
import model.GameState;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

/**
 * @author Rita Zhou
 * This is the test case for the GameState
 * It will test the method of pollDialog, addItem
 * and useItem. If we can pass all test, then we can say that
 * our GameState.java is working correctly
 */
public class GameStateTest extends ApplicationTest {

    /**
     * @author Rita Zhou
     * Test pollDialog
     */
    @Test (timeout = 1000)
    public void testPollDialog() throws IOException, URISyntaxException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        GameObject gameObject = GameEngine.loadGameObject("template-2/header.json");
        GameState gameState = new GameState(gameObject);

        assertEquals("[System] Game Started", gameState.pollDialog());
        assertNull(gameState.pollDialog());
    }

    /**
     * @author Rita Zhou
     * Test addItem and useItem
     */
    @Test (timeout = 1000)
    public void testItem() throws IOException, URISyntaxException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        GameObject gameObject = GameEngine.loadGameObject("template-2/header.json");
        GameState gameState = new GameState(gameObject);

        gameState.addItem(GameState.Item.KEY);
        assertEquals(1, gameState.get(GameState.Item.KEY));

        gameState.addItem(GameState.Item.KEY);
        gameState.addItem(GameState.Item.KEY);
        assertEquals(3, gameState.get(GameState.Item.KEY));

        gameState.addItem(GameState.Item.KEY);
        gameState.useItem(GameState.Item.KEY);
        assertEquals(3, gameState.get(GameState.Item.KEY));

        gameState.useItem(GameState.Item.KEY);
        gameState.useItem(GameState.Item.KEY);
        assertEquals(1, gameState.get(GameState.Item.KEY));

        gameState.useItem(GameState.Item.KEY);
        assertEquals(-1, gameState.get(GameState.Item.KEY));

        assertFalse(gameState.useItem(GameState.Item.KEY));
    }
}
