package engine;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import javafx.scene.image.Image;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * This is the game engine loading game object
 *
 * @author Xianghao Wang
 * */
public class GameEngine {

    /**
     * Get resource's path by giving its name
     * @author Xianghao Wang
     * @param fileName is the resource name
     * @return a Path representing the resource
     * */
    public static Path getResourcePath(String fileName) throws URISyntaxException {
         URL resourceURL = GameEngine.class.getClassLoader().getResource(fileName);
         return Paths.get(resourceURL.toURI());
    }


    /**
     * Get JSON object by giving the path
     * @author Xianghao Wang
     * @param path is the JSON file's path
     * @return the JSON object
     * */
    public static JSONObject getJSONObjection(String path) throws IOException {
        String content = Files.readString(Paths.get(path));
        return JSONObject.parseObject(content);
    }

    /**
     * Load and return the game object
     * @author Xianghao Wang
     * @param headerPath is the header of the game object
     * @return the GameObject
     * */
    public static GameObject loadGame(String headerPath) throws IOException, URISyntaxException, ClassNotFoundException {
        GameObject gameObject = new GameObject();

        /* Load header */
        JSONObject headerJSON = getJSONObjection(getResourcePath(headerPath).toString());
        // Load all scene file paths
        List<String> scenePaths = new ArrayList<>();
        for (Object obj : headerJSON.getJSONArray("scenes")) {
            scenePaths.add(obj.toString());
        }
        // Load all properties
        JSONObject propertiesJSON = headerJSON.getJSONObject("properties");
        for (String key : propertiesJSON.keySet()) {
            gameObject.addProperty(key, propertiesJSON.getString(key));
        }
        // Load all symbols and arguments
        Map<String, Class<Cell>> symbols = new HashMap<>();
        JSONObject argumentsJSON = headerJSON.getJSONObject("symbols");
        for (String symbol : argumentsJSON.keySet()) {
            Pair<Class<Cell>, List<String>> parsed = parseArgument(argumentsJSON.getString(symbol));
            symbols.put(symbol, parsed.getKey());
            gameObject.addArgument(parsed.getKey(), parsed.getValue());
        }
        // Load all images
        JSONObject renderingJSON = headerJSON.getJSONObject("rendering");
        for (String symbol : renderingJSON.keySet()) {
            String imagePath = renderingJSON.getString(symbol);
            System.out.println(getResourcePath(imagePath).toString());
            String p = new File(getResourcePath(imagePath).toString()).toURI().toString();
            gameObject.addImage(symbols.get(symbol), new Image(p));
        }

        /* Load all scenes */
        for (String scenePath : scenePaths) {
            gameObject.addSceneObject(loadScene(gameObject, symbols, scenePath));
        }

        return gameObject;
    }


    /**
     * Load and return the scene object
     * @author Xianghao Wang
     * @param gameObject is its parent game object
     * @param symbols defines a list of symbols and corresponding class object
     * @param scenePath is the JSON file path of the scene object
     * @return the SceneObject
     * */
    public static SceneObject loadScene(GameObject gameObject, Map<String, Class<Cell>> symbols, String scenePath) throws URISyntaxException, IOException, ClassNotFoundException {
        SceneObject sceneObject = new SceneObject(gameObject);
        Map<String, Class<Cell>> sceneSymbols = new HashMap<>(symbols);

        // Get JSON object
        JSONObject sceneJSON = getJSONObjection(getResourcePath(scenePath).toString());

        // Read properties
        JSONObject propertiesJSON = sceneJSON.getJSONObject("properties");
        for (String key : propertiesJSON.keySet()) {
            sceneObject.addProperty(key, propertiesJSON.getString(key));
        }

        // Read all symbols
        JSONObject symbolsJSON = sceneJSON.getJSONObject("symbols");
        for (String symbol : symbolsJSON.keySet()) {
            Pair<Class<Cell>, List<String>> parsed = parseArgument(symbolsJSON.getString(symbol));
            sceneSymbols.put(symbol, parsed.getKey());
            sceneObject.addArgument(parsed.getKey(), parsed.getValue());
        }

        // Read cells
        JSONArray cellJSONArray = sceneJSON.getJSONArray("cells");
        for (Object rowObj : cellJSONArray) {
            String rowStr = (String) rowObj;
            List<Class<Cell>> row = new ArrayList<>();
            sceneObject.addCellClassRow(row);
            for (char c : rowStr.toCharArray()) {
                Class<Cell> cellClass = sceneSymbols.get(c + "");
                row.add(cellClass);
            }
        }

        // Read all images
        JSONObject renderingJSON = sceneJSON.getJSONObject("rendering");
        for (String symbol : renderingJSON.keySet()) {
            String imagePath = getResourcePath(renderingJSON.getString(symbol)).toString();
            sceneObject.addImage(sceneSymbols.get(symbol), new Image(imagePath));
        }

        return sceneObject;
    }

    /**
     * This will parse the argument for a symbol
     * @author Xianghao Wangg
     * @param str is the raw argument string
     * @return a pair involving the class object and corresponding argument list
     * */
    public static Pair<Class<Cell>, List<String>> parseArgument(String str) throws ClassNotFoundException {
        String[] tokens = str.split("&");

        // Add all arguments
        List<String> arguments = new ArrayList<>(Arrays.asList(tokens).subList(1, tokens.length));

        // Get class object
        Class<Cell> classObj = (Class<Cell>) Class.forName(tokens[0]);

        return new Pair<>(classObj, arguments);
    }

}
