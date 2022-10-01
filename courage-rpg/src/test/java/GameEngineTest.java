import engine.Cell;
import engine.GameEngine;
import javafx.util.Pair;
import model.cells.EmptyCell;
import org.junit.Test;
import javafx.util.Pair;

import java.net.URISyntaxException;
import java.util.List;

import static engine.GameEngine.parseArgument;
import static org.junit.Assert.*;

public class GameEngineTest {
    @Test
    public void getResourcePathTest() throws URISyntaxException {
    }


    @Test
    public void getJSONObjectTest() {

    }

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

    public class SampleCell extends Cell {
        @Override
        public Cell build(List<String> arguments) {
            return null;
        }
    }
}
