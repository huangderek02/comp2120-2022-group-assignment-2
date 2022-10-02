package model.cells;

import engine.Cell;
import model.GameState;

import java.util.List;

public class DoorCell extends ActionCell {
    @Override
    public Cell build(List<String> arguments) {
        return new DoorCell();
    }

    @Override
    public void act(GameState state) {
//        state.getMap(state.getCurrentLocation().level)
//                [state.getCurrentLocation().row][state.getCurrentLocation().col] = state.createCell(EmptyCell.class);
    }
}
