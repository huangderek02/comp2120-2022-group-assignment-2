package model.cells;

import engineV2.Cell;
import model.GameState;

import java.util.List;

public class DoorCell extends ActionCell {

    @Override
    public Cell build(List<String> arguments) {
        return this;
    }

    @Override
    public List<String> export() {
        return null;
    }

    @Override
    public void act(GameState state) {
        if (state.useItem(GameState.Item.KEY)) {

        } else {

        }
    }
}
