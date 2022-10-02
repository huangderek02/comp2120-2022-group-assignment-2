package engineV2;

import javafx.scene.image.Image;

import java.util.List;
import java.util.Map;

public class GameObject {
    final private Map<String, Image> imageDomain;
    final private Map<String, String> states;
    final private List<Cell[][]> maps;
    public GameObject(Map<String, Image> imageDomain, Map<String, String> states, List<Cell[][]> maps) {
        this.imageDomain = imageDomain;
        this.states = states;
        this.maps = maps;
    }

    public Image getImage(String name) {
        return imageDomain.get(name);
    }

    public String getState(String name) {
        return states.get(name);
    }

    public Cell[][] getMap(int idx) {
        return maps.get(idx);
    }

    public int getMapCount() {
        return maps.size();
    }
}
