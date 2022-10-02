package cellsV2;

import engineV2.Cell;
import model.GameState;
import model.cells.ActionCell;

import java.util.List;

/**
 * Sample cases for testing
 * A case with other information
 * @author Rita Zhou
 */
public class PipeCell extends ActionCell {
    private List<String> arguments;

    @Override
    public Cell build(List<String> arguments) {
        PipeCell cell = new PipeCell();
        cell.arguments = arguments;
        return cell;
    }

    @Override
    public List<String> export() {
        return this.arguments;
    }

    @Override
    public void act(GameState state) {

    }
}
