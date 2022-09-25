package engine;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * It represents a cell class and the arguments to construct the cell object
 * @author Xianghao Wang
 * */
public class CellMaker {
    final private Class<Cell> cellClass;
    final private List<String> arguments;

    /**
     * Construct a cell maker
     * @author Xianghao Wang
     * @param cellClass indicates the Class object of a Cell
     * @param arguments gives the cell maker arguments to construct a Cell */
    public CellMaker(Class<Cell> cellClass, List<String> arguments) {
        this.cellClass = cellClass;
        this.arguments = arguments;
    }

    /**
     * Make a cell
     * @author Xianghao Wang
     * @return a cell object made with the arguments */
    public Cell make() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return  cellClass.getConstructor().newInstance().build(arguments);
    }

    /**
     * Return the Cell's class object
     * @author Xianghao Wang
     * @return the Cell's class
     * */
    public Class<Cell> getCellClass() {
        return this.cellClass;
    }
}
