package model.cells;

import engineV2.Cell;
import model.GameState;
import model.Location;
import model.cells.ActionCell;
import model.cells.EmptyCell;

import java.util.List;

public class WaterCell extends ActionCell {
    @Override
    public Cell build(List<String> arguments) {
        return this;
    }

    @Override
    public List<String> export() {
        return List.of();
    }

    /**
     * Give player an HP recovery water
     *
     * @author Xianghao Wang
     * */
    @Override
    public void act(GameState state) {
        state.addItem(GameState.Item.HP_RECOVERY);
        Location loc = state.getCurrentLocation();
        state.getMap(loc.level)[loc.row][loc.col] = new EmptyCell();
    }
}
