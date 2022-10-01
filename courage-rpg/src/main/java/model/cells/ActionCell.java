package model.cells;

import engine.Cell;
import model.GameState;

public abstract class ActionCell extends Cell {
    public abstract void act(GameState state);
}
