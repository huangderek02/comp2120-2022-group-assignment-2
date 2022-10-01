package engine;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * It represents a cell class and the arguments to construct the cell object
 *
 * @author Xianghao Wang
 * @deprecated
 */
public class CellMaker {
    final private Class<Cell> cellClass;
    final private List<String> arguments;

    /**
     * Construct a cell maker
     *
     * @param cellClass indicates the Class object of a Cell
     * @param arguments gives the cell maker arguments to construct a Cell
     * @author Xianghao Wang
     */
    public CellMaker(Class<Cell> cellClass, List<String> arguments) {
        this.cellClass = cellClass;
        this.arguments = arguments;
    }

    /**
     * Make a cell
     *
     * @return a cell object made with the arguments
     * @author Xianghao Wang
     */
    public Cell make() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return cellClass.getConstructor().newInstance().build(arguments);
    }

    /**
     * Return the Cell's class object
     *
     * @return the Cell's class
     * @author Xianghao Wang
     */
    public Class<Cell> getCellClass() {
        return this.cellClass;
    }
}
