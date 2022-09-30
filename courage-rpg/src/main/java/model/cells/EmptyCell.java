package model.cells;

import engine.Cell;

import java.util.List;

public class EmptyCell extends Cell {
    @Override
    public Cell build(List<String> arguments) {
        return null;
    }
}
