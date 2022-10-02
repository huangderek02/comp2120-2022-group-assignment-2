package model.cells;

import engineV2.Cell;
import model.GameState;
import model.Location;

import java.util.List;


//AKA stairs
public class PortalCell extends ActionCell {
    private Location destination;
    public boolean isUp;

    @Override
    public Cell build(List<String> arguments) {
        this.isUp = arguments.get(0).equals("up");
        this.destination = new Location(
                Integer.parseInt(arguments.get(1)),
                Integer.parseInt(arguments.get(2)),
                Integer.parseInt(arguments.get(3))
        );
        return this;
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
