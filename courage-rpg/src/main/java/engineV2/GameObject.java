package engineV2;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the game object for game engine V2
 *
 * @author Xianghao Wang
 * */
public class GameObject {
    final public Map<String, Image> imageDomain;
    final public Map<String, String> states;
    final public List<Cell[][]> maps;

    final public Map<String, String> rawImageDomain;

    /**
     * Construct a game object
     *
     * @author Xianghao Wang
     *
     * @param imageDomain stores the map between image name and image object
     * @param states stores the map between state name and state
     * @param maps stores a list of game maps
     * */
    public GameObject(Map<String, Image> imageDomain, Map<String, String> states, List<Cell[][]> maps) {
        this.imageDomain = imageDomain;
        this.states = states;
        this.maps = maps;
        this.rawImageDomain = new HashMap<>();
    }

    /**
     * Get the image object with its name
     *
     * @author Xianghao Wang
     *
     * @param name is the name of the image
     * @return the image object
     * */
    public Image getImage(String name) {
        return imageDomain.get(name);
    }

    /**
     * Get the state with its name
     *
     * @author Xianghao Wang
     *
     * @param name is the name of the state
     * @return the state
     * */
    public String getState(String name) {
        return states.get(name);
    }

    /**
     * Get the game map from its index
     *
     * @author Xianghao Wang
     *
     * @param idx is the index of a map
     * @return the game map
     * */
    public Cell[][] getMap(int idx) {
        return maps.get(idx);
    }


    /**
     * Get the number of maps
     *
     * @author Xianghao Wang
     *
     * @return the number of game maps
     * */
    public int getMapCount() {
        return maps.size();
    }
}
