package engineV2;

import com.alibaba.fastjson.JSONObject;
import javafx.util.Pair;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class GameEngine {
    /**
     * Get resource's path by giving its name
     * @author Xianghao Wang
     * @param fileName is the resource name
     * @return a Path representing the resource
     * */
    public static Path getResourcePath(String fileName) throws URISyntaxException {
        URL resourceURL = engine.GameEngine.class.getClassLoader().getResource(fileName);

        assert resourceURL != null;

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

    public static GameObject loadGameObject(String headerName) throws URISyntaxException, IOException {
        JSONObject headerJSON = getJSONObjection(getResourcePath(headerName).toString());

        Map<String, String> literals = loadLiterals(headerJSON.getJSONObject("literals"));
        Map<String, String> states = loadStates(headerJSON.getJSONObject("states"));


        // Iterate on each scene and create scene


        return null;
    }

    public static Map<String, String> loadLiterals(JSONObject json) {
        return null;
    }

    public static Map<String, Image> loadImageDomain(JSONObject json) {
        return null;
    }

    public static Map<String, String> loadStates(JSONObject json) {
        return null;
    }

    public static String preCompile(String cmd, Map<String, String> literals) {
        return null;
    }

    public static Pair<Integer, Integer> loadDimension(JSONObject json) {
        return null;
    }

    public static Cell[][] compileScene(JSONObject json, Map<String, String> literals) {
        return null;
    }

    public static Map<String, String> overrideLiterals(Map<String, String> sceneLiterals, Map<String, String> gameLiterals) {
        return null;
    }
}
