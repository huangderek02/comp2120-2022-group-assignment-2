package model.cells;

import engineV2.Cell;
import model.GameState;

import java.util.List;

public class EmptyCell extends ActionCell {
    @Override
    public Cell build(List<String> arguments) {
        return new EmptyCell();
    }

    @Override
    public List<String> export() {
        return List.of();
    }

    /**
     * Do nothing
     *
     * @author Xianghao Wang
     * */
    @Override
    public void act(GameState state) {

    }
}
