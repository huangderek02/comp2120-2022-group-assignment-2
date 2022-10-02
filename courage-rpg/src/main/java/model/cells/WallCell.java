package model.cells;

import engineV2.Cell;
import model.GameState;

import java.util.List;

public class WallCell extends ActionCell {
    @Override
    public Cell build(List<String> arguments) {
        return new WallCell();
    }

    @Override
    public List<String> export() {
        return List.of();
    }


    /**
     * Prevent entering
     *
     * @author Xianghao Wang
     * */
    @Override
    public void act(GameState state) {
        state.moveBack();
    }
}
