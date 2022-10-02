package model.cells;

import engine.Cell;
import model.GameState;
import model.Location;

import java.util.List;

public class CloseDoorCell  extends ActionCell {

    public Location location = null;

    @Override
    public Cell build(List<String> arguments) {
        return new CloseDoorCell();
    }



    @Override
    public void act(GameState state) {
        if (state.useItem(GameState.Item.KEY))  {
//            state[state.getCurrentLocation().row][state.getCurrentLocation().col] = state.createCell(OpenDoorCell.class);

        //Cell becomes empty when door is closed
        ActionCell actionCell = state.createCell(EmptyCell.class);
        //getting the current location of the state
        location = state.getCurrentLocation();
        //A new map is created based level of the current location
        ActionCell[][] maps = state.getMap(location.level);
        //The action cell is placed on the corresponding location on the map
        maps[location.row][location.col] = actionCell;
        } else  {
            state.moveBack();
        }
    }
}
