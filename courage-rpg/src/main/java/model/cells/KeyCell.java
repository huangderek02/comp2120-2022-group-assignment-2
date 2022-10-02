package model.cells;

import engineV2.Cell;
import model.GameState;
import model.Location;

import java.util.List;

public class KeyCell extends ActionCell {
    @Override
    public Cell build(List<String> arguments) {
        return this;
    }

    @Override
    public List<String> export() {
        return List.of();
    }

    @Override
    public void act(GameState state) {
        state.addItem(GameState.Item.KEY);
        Location loc = state.getCurrentLocation();
        state.getMap(loc.level)[loc.row][loc.col] = new EmptyCell();
    }
}
