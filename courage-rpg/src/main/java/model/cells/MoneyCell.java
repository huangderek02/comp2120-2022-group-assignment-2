package model.cells;

import engineV2.Cell;
import model.GameState;
import model.Location;

import java.util.List;

public class MoneyCell extends ActionCell {
    @Override
    public Cell build(List<String> arguments) {
        return this;
    }

    @Override
    public List<String> export() {
        return List.of();
    }

    /**
     * Give player some money
     *
     * @author Xianghao Wang
     * */
    @Override
    public void act(GameState state) {
        state.money += 150;
        Location loc = state.getCurrentLocation();
        state.getMap(loc.level)[loc.row][loc.col] = new EmptyCell();
    }
}
