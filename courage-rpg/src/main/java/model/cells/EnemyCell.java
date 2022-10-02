package model.cells;

import engineV2.Cell;
import model.GameState;
import model.Location;
import model.cells.ActionCell;
import model.cells.EmptyCell;

import java.util.List;

public class EnemyCell extends ActionCell {
    public int hp;

    @Override
    public Cell build(List<String> arguments) {
        hp = Integer.parseInt(arguments.get(0));
        return this;
    }

    @Override
    public List<String> export() {
        return List.of(String.valueOf(hp));
    }

    @Override
    public void act(GameState state) {
        if (state.hp > hp) {
            state.hp -= hp;
            Location loc = state.getCurrentLocation();
            state.getMap(loc.level)[loc.row][loc.col] = new EmptyCell();
        } else {
            state.moveBack();
        }
    }
}
