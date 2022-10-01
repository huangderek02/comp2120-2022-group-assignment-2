package model.cells;

import engine.Cell;
import model.GameState;

/**
 * This represents a cell that can act on game state i.e. change game state
 *
 * @author Xianghao Wang
 * */
public abstract class ActionCell extends Cell {

    /**
     * Act on game state when moving to this cell
     *
     * @author Xianghao Wang
     *
     * @param state is the game state
     * */
    public abstract void act(GameState state);
}
