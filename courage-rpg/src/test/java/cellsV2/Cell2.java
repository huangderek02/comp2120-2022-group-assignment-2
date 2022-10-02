package cellsV2;

import engineV2.Cell;
import model.GameState;
import model.cells.ActionCell;

import java.util.List;

/**
 * Sample cases for Testing
 * A case with two argument, row and column
 * @author Rita Zhou
 */
public class Cell2 extends ActionCell {
    public int x;
    public int y;

    @Override
    public Cell build(List<String> arguments) {
        Cell2 ret = new Cell2();
        ret.x = Integer.parseInt(arguments.get(0));
        ret.y = Integer.parseInt(arguments.get(1));
        return ret;
    }

    @Override
    public List<String> export() {
        return List.of(String.valueOf(x), String.valueOf(y));
    }

    @Override
    public void act(GameState state) {

    }
}
