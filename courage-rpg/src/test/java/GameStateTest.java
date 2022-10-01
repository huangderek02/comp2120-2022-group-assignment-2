import engine.GameEngine;
import engine.GameObject;
import model.GameState;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class GameStateTest extends ApplicationTest {
    @Test (timeout = 1000)
    public void testHandle(){

    }

    @Test (timeout = 1000)
    public void testPollDialog() throws IOException, URISyntaxException, ClassNotFoundException {
        GameObject gameObject = GameEngine.loadGame("template-1/header.json");
        GameState gameState = new GameState(gameObject);

        assertEquals("1234567890", gameState.pollDialog());
        assertEquals("ABC abc", gameState.pollDialog());
        assertEquals("Hello!", gameState.pollDialog());
        assertNull(gameState.pollDialog());
    }

    @Test (timeout = 1000)
    public void testAddItem() throws IOException, URISyntaxException, ClassNotFoundException {
        GameObject gameObject = GameEngine.loadGame("template-1/header.json");
        GameState gameState = new GameState(gameObject);

        gameState.addItem(GameState.Item.KEY);
    }
}
