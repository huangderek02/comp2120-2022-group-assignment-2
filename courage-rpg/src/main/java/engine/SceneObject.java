package engine;

import javafx.scene.image.Image;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This represents a scene
 *
 * @author Avani Dhaliwal
 * */
public class SceneObject {
    final private GameObject gameObject;
    final private Map<Class<Cell>, Image> cellRendering;
    final private Map<String, String> properties;
    final private Map<Class<Cell>, List<String>> buildingArguments;

    /**
     * Construct a scene object
     *
     * @author XiangHao Wang
     *
     * @param gameObject the game object containing the scene
     * */
    public SceneObject(GameObject gameObject) {
        this.gameObject = gameObject;
        this.cellRendering = new HashMap<>();
        this.properties = new HashMap<>();
        this.buildingArguments = new HashMap<>();
    }

    /**
     * Add an image for a cell class
     *
     * @author Avani Dhaliwal
     *
     * @param classObj is the cell's class Object
     * @param image is the image to be added
     * */
    public void addImage(Class<Cell> classObj, Image image) {
        cellRendering.put(classObj, image);
    }

    /**
     * Add a property
     *
     * @author Avani Dhaliwal
     *
     * @param key is the key
     * @param value is the value
     * */
    public void addProperty(String key, String value) {
        properties.put(key, value);
    }

    /**
     * Add an argument list for a cell class
     *
     * @author Avani Dhaliwal
     *
     * @param classObj is the cell's class object
     * @param argument is a list of arguments
     * */
    public void addArgument(Class<Cell> classObj, List<String> argument) {
        buildingArguments.put(classObj, argument);
    }


    /**
     * Get image of a cell;
     * This will search from its cellRendering firstly.
     * If not found in scene object, then search from the game object.
     *
     * @author Avani Dhaliwal
     *
     * @param classObj represents the Cell class
     * @return an Image to render the Cell with given class; If not found, return null
     * */
    public Image getImage(Class<Cell> classObj) {
        Image image = cellRendering.get(classObj);
        if(image == null){
            image = gameObject.getImage(classObj);
        }
        return image;
    }

    /**
     * Get property
     * Note: this will not search for game object
     *
     * @author Avani Dhaliwal
     *
     * @param key is the given key
     * @return the value corresponding the key; if not found, return null
     * */
    public String getProperty(String key) {
        return properties.get(key);
    }

    /**
     * Get arguments for building a cell
     * This will search from its arguments firstly.
     * If not found in scene object, then search from the game object.
     *
     * @author Avani Dhaliwal
     *
     * @param classObj is the given Class object
     * @return the arguments for building Cell with the given class
     * */
    public List<String> getBuildArguments(Class<Cell> classObj) {
        List<String> argument = buildingArguments.get(classObj);
        if(argument == null){
            argument = gameObject.getBuildArguments(classObj);
        }
        return argument;
    }


    /**
     * Build a cell
     *
     * @author Avani Dhaliwal
     *
     * @param classObj is the Class object of the cell to be built
     * @return a Cell constructed by arguments
     * */
    public Cell build(Class<Cell> classObj) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return (Cell) classObj.getConstructor().newInstance().build(getBuildArguments(classObj));
    }

}
