package engineV2;

import java.util.List;


/**
 * This represents a cell
 *
 * @author Xianghao Wang
 * */
public abstract class Cell {

    /**
     * Build the cell with a list of arguments
     *
     * @author Xianghao Wang
     *
     * @param arguments is a list of arguments
     * @return the built cell
     * */
    public abstract Cell build(List<String> arguments);

    /**
     * Export the cell's arguments
     *
     * @author Xianghao Wang
     *
     * @return the argument list
     * */
    public abstract List<String> export();
}
