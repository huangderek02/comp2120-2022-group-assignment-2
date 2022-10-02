package model.cells;

import engine.Cell;
import model.GameState;
import model.Location;

import java.util.List;


//AKA stairs
public class PortalCell extends ActionCell {
    private Location destination;

    @Override
    public Cell build(List<String> arguments) {
        PortalCell cell = new PortalCell();
        cell.destination = new Location(
                //Level
                Integer.parseInt(arguments.get(0)),
                //Row
                Integer.parseInt(arguments.get(1)),
                //Column
                Integer.parseInt(arguments.get(2))
        );
        return cell;
    }

    @Override
    public void act(GameState state) {
        state.moveTo(this.destination);
    }
}
