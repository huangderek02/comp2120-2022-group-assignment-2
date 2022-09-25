package engine;

import javafx.scene.image.Image;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This represents a game
 *
 * @author Avani Dhaliwal
 * */
public class GameObject {
    final private List<SceneObject> sceneObjects;

    final private Map<Class<Cell>, Image> cellRendering;
    final private Map<String, String> properties;
    final private Map<Class<Cell>, List<String>> arguments;

    /**
     * Construct a game object
     * @Author Xianghao Wang
     * */
    public GameObject() {
        this.sceneObjects = new ArrayList<>();
        this.cellRendering = new HashMap<>();
        this.properties = new HashMap<>();
        this.arguments = new HashMap<>();
    }

    /**
     * Add a scene object
     * @author Avani Dhaliwal
     * @param sceneObject is the scene to be added
     * */
    public void addSceneObject(SceneObject sceneObject) {
        sceneObjects.add(sceneObject);
    }

    /**
     * Add an image for a cell class
     * @author Avani Dhaliwal
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
        arguments.put(classObj, argument);
    }

    /**
     * Return the scene object with given index
     *
     * @author Avani Dhaliwal
     *
     * @param idx is the given index
     * @return the scene object; if not found, return null
     * */
    public SceneObject getSceneObject(int idx) {
        return sceneObjects.get(idx);
    }

    /**
     * Return all scene objects
     *
     * @author Xianghao Wang
     *
     * @return a list of all scene objects
     * */
    public List<SceneObject> getSceneObjectList() {
        return this.sceneObjects;
    }

    /**
     * Return the image for rendering the given cell
     *
     * @author Avani Dhaliwal
     *
     * @param classObj is the given cell's class object
     * @return the image
     * */
    public Image getImage(Class<Cell> classObj) {
        return cellRendering.get(classObj);
    }

    /**
     * Get property
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
     *
     * @author Avani Dhaliwal
     *
     * @param classObj is the given Class object
     * @return the arguments for building Cell with the given class
     * */
    public List<String> getBuildArguments(Class<Cell> classObj) {
        return arguments.get(classObj);
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
