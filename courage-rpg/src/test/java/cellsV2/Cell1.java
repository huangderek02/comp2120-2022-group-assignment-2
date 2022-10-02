package cellsV2;

import engineV2.Cell;
import model.GameState;
import model.cells.ActionCell;

import java.util.List;

/**
 * Sample cases for Testing
 * No argument cases
 * @author Rita Zhou
 */
public class Cell1 extends ActionCell {
    @Override
    public Cell build(List<String> arguments) {
        return new Cell1();
    }

    @Override
    public List<String> export() {
        return List.of();
    }

    @Override
    public void act(GameState state) {

    }
}
