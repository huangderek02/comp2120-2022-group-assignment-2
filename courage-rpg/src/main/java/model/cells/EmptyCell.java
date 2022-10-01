package model.cells;

import engine.Cell;
import model.GameState;

import java.util.List;

public class EmptyCell extends ActionCell {
    @Override
    public Cell build(List<String> arguments) {
        return new EmptyCell();
    }

    @Override
    public void act(GameState state) {

    }
}
