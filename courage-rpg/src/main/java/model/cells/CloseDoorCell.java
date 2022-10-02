package model.cells;


import engineV2.Cell;
import model.GameState;

import java.util.List;

public class CloseDoorCell  extends ActionCell {
    @Override
    public Cell build(List<String> arguments) {
        return new CloseDoorCell();
    }

    @Override
    public List<String> export() {
        return null;
    }

    @Override
    public void act(GameState state) {
        if (state.useItem(GameState.Item.KEY))  {
//            state[state.getCurrentLocation().row][state.getCurrentLocation().col] = state.createCell(OpenDoorCell.class);
        } else  {
            state.moveBack();
        }
    }
}
