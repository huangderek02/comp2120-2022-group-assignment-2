package engine;

import java.util.List;


/**
 * Cell represents a cell in a scene
 * @author Xianghao Wang
 * */
public abstract class Cell {

    /**
     * Build cell with a list of arguments configured in JSON file
     * @author Xianghao Wang
     * @param arguments: represents a list of arguments
     * @return a cell built with these arguments
     * */
    public abstract Cell build(List<String> arguments);
}
