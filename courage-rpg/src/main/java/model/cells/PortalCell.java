package model.cells;

import engineV2.Cell;
import model.GameState;
import model.Location;

import java.util.List;

public class PortalCell extends ActionCell {
    private Location destination;

    @Override
    public Cell build(List<String> arguments) {
        PortalCell cell = new PortalCell();
        cell.destination = new Location(
                Integer.parseInt(arguments.get(1)),
                Integer.parseInt(arguments.get(2)),
                Integer.parseInt(arguments.get(3))
        );
        return cell;
    }

    @Override
    public List<String> export() {
        return null;
    }

    @Override
    public void act(GameState state) {
        state.moveTo(this.destination);
    }
}
