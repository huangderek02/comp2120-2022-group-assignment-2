package model.cells;

import engineV2.Cell;
import model.GameState;
import model.Location;

import java.util.List;

public class NPCCell extends ActionCell {
    public int id;
    public String message;

    @Override
    public Cell build(List<String> arguments) {
        System.out.println(arguments);
        this.id = Integer.parseInt(arguments.get(0));
        this.message = arguments.get(1);
        return this;
    }

    @Override
    public List<String> export() {
        return null;
    }

    @Override
    public void act(GameState state) {
        Location loc = state.getCurrentLocation();
        state.getMap(loc.level)[loc.row][loc.col] = new EmptyCell();
        state.offerDialog(parseMessage(this.message));
    }

    public String parseMessage(String raw) {
        return String.join(" ", raw.split("\\^"));
    }
}
