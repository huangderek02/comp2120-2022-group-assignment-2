package engineV2;

import com.alibaba.fastjson.JSONObject;
import javafx.scene.image.Image;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is the game engine V2;
 * It supports more flexible customizing maps and storing dynamic states
 *
 * @author Xianghao Wang
 * */
public class GameEngine {
    /**
     * Get resource's path by giving its name
     *
     * @author Xianghao Wang
     *
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
     *
     * @author Xianghao Wang
     *
     * @param path is the JSON file's path
     * @return the JSON object
     * */
    public static JSONObject getJSONObjection(String path) throws IOException {
        String content = Files.readString(Paths.get(path));
        return JSONObject.parseObject(content);
    }


    /**
     * Load the game object from a game file
     *
     * @author Xianghao Wang
     *
     * @param headerName is the header of the game file
     * @return a game object
     * */
    public static GameObject loadGameObject(String headerName) throws URISyntaxException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        JSONObject headerJSON = getJSONObjection(getResourcePath(headerName).toString());

        Map<String, String> literals = loadLiterals(headerJSON.getJSONObject("literals"));
        Map<String, String> states = loadStates(headerJSON.getJSONObject("states"));
        Map<String, Image> imageDomain = loadImageDomain(headerJSON.getJSONObject("image-domain"));
        List<Cell[][]> maps = new ArrayList<>();

        // Iterate on each scene and create scene
        for (Object sceneNameObj : headerJSON.getJSONArray("scenes")) {
            String sceneName = sceneNameObj.toString();
            JSONObject sceneJSON = getJSONObjection(getResourcePath(sceneName).toString());
            Map<String, String> sceneLiterals = loadLiterals(sceneJSON.getJSONObject("literals"));
            maps.add(compileScene(sceneJSON, overrideLiterals(sceneLiterals, literals)));
        }

        return new GameObject(imageDomain, states, maps);
    }


    /**
     * This load literals from JSON object
     *
     * @author Xianghao Wang
     *
     * @param json is the JSON object
     * @return the literal maps - literal alias : literal content
     * */
    public static Map<String, String> loadLiterals(JSONObject json) {
        Map<String, String> literals = new HashMap<>();
        for (String literalKey : json.keySet()) {
            literals.put(literalKey, json.getString(literalKey));
        }
        return literals;
    }

    /**
     * This load all of defined images
     *
     * @author Xianghao Wang
     *
     * @param json is the JSON object
     * @return the image maps - image name : image
     * */
    public static Map<String, Image> loadImageDomain(JSONObject json) throws URISyntaxException {
        Map<String, Image> images = new HashMap<>();
        for (String imageAlias : json.keySet()) {
            String imageName = json.getString(imageAlias);
            String imagePath = getResourcePath(imageName).toString();
            String imageUrl = new File(imagePath).toURI().toString();
            images.put(imageAlias, new Image(imageUrl));
        }
        return images;
    }

    /**
     * This load states from JSON object
     *
     * @author Xianghao Wang
     *
     * @param json is the JSON objecct
     * @return the states map - stata name : state
     * */
    public static Map<String, String> loadStates(JSONObject json) {
        Map<String, String> states = new HashMap<>();
        for (String stateKey : json.keySet()) {
            states.put(stateKey, json.getString(stateKey));
        }
        return states;
    }

    /**
     * This load dimension from JSON object
     *
     * @author Xianghao Wang
     *
     * @param json is the JSON object
     * @return the dimension (rows, cols)
     * */
    public static Pair<Integer, Integer> loadDimension(JSONObject json) {
        int rows = Integer.parseInt(json.getString("rows"));
        int cols = Integer.parseInt(json.getString("cols"));
        return new Pair<>(rows, cols);

    }

    /**
     * This compiles a scene from its JSON object
     *
     * @author Xianghao Wang
     *
     * @param json is the scene JSON object
     * @param literals defines some literals can replace ${literal} in the commands
     * @return the compiled map
     * */
    public static Cell[][] compileScene(JSONObject json, Map<String, String> literals) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Pair<Integer, Integer> dimension = loadDimension(json.getJSONObject("dimension"));
        Cell[][] map = new Cell[dimension.getKey()][dimension.getValue()];

        for (Object cmdObj : json.getJSONArray("build-script")) {
            String cmd = precompile(cmdObj.toString(), literals);
            compile(cmd, map);
        }

        return map;
    }

    /**
     * Pre-compile a command with defined literals by replacing ${literal-key} with literal-value
     *
     * @author Xianghao Wang
     *
     * @param cmd is to be pre-compiled
     * @param literals contains some defined literals
     * */
    public static String precompile(String cmd, Map<String, String> literals) {
        StringBuilder cmdBuilder = new StringBuilder();

        Pattern pattern = Pattern.compile("\\$\\{([-\\w]+)\\}");
        Matcher matcher = pattern.matcher(cmd);
        while (matcher.find()) {
            matcher.appendReplacement(cmdBuilder, literals.get(matcher.group(1)));
        }
        matcher.appendTail(cmdBuilder);

        return cmdBuilder.toString();
    }


    /**
     * This compiles a command
     *
     * @author Xianghao Wang
     *
     * @param cmd is the command
     * @param map stores the result of compiling
     * */
    public static void compile(String cmd, Cell[][] map) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String[] tokens = cmd.split(" ");
        String op = tokens[0];
        if (op.equals("add")) {
            Pair<Class, List<String>> cellMaking = parseArgument(tokens[1]);
            Pair<Integer, Integer> loc = parseLocation(tokens[2]);
            map[loc.getKey()][loc.getValue()] = ((Cell) cellMaking.getKey().getConstructor().newInstance()).build(cellMaking.getValue());
        } else if (op.equals("fillRow")) {
            Pair<Class, List<String>> cellMaking = parseArgument(tokens[1]);
            int row = Integer.parseInt(tokens[2]);
            for (int col = 0; col < map[row].length; col ++) {
                map[row][col] = createCell(cellMaking.getKey(), cellMaking.getValue());
            }
        } else if (op.equals("fillCol")) {
            Pair<Class, List<String>> cellMaking = parseArgument(tokens[1]);
            int col = Integer.parseInt(tokens[2]);
            for (int row = 0; row < map.length; row ++) {
                map[row][col] = createCell(cellMaking.getKey(), cellMaking.getValue());
            }
        } else if (op.equals("fill")) {
            Pair<Class, List<String>> cellMaking = parseArgument(tokens[1]);
            for (int row = 0; row < map.length; row ++) {
                for (int col = 0; col < map[row].length; col ++) {
                    map[row][col] = createCell(cellMaking.getKey(), cellMaking.getValue());
                }
            }
        }
    }

    /**
     * This uses scene literals to override the default game literals
     *
     * @author Xianghao Wang
     *
     * @param sceneLiterals contains the scene literals
     * @param gameLiterals contains the game literals
     * @return the new literals after overriding game literals with the scene literals
     * */
    public static Map<String, String> overrideLiterals(Map<String, String> sceneLiterals, Map<String, String> gameLiterals) {
        Map<String, String> literals = new HashMap<>(gameLiterals);
        literals.putAll(sceneLiterals);
        return literals;
    }

    /**
     * This will parse the argument for a symbol
     *
     * @author Xianghao Wang
     *
     * @param str is the raw argument string
     * @return a pair involving the class object and corresponding argument list
     * */
    public static Pair<Class, List<String>> parseArgument(String str) throws ClassNotFoundException {
        String[] tokens = str.split("&");

        // Add all arguments
        List<String> arguments = new ArrayList<>(Arrays.asList(tokens).subList(1, tokens.length));

        // Get class object
        Class classObj = (Class) Class.forName(tokens[0]);

        return new Pair<>(classObj, arguments);
    }

    /**
     * This parses a location string row&col to (row, col)
     *
     * @author Xianghao Wang
     *
     * @param str is the string to be parsed
     * @return the parsed row and column
     * */
    public static Pair<Integer, Integer> parseLocation(String str) {
        String[] tokens = str.split("&");
        return new Pair<>(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
    }


    /**
     * This creates a cell instance with its class object and a list of arguments
     *
     * @author Xianghao Wang
     *
     * @param cellClass is the class object
     * @param arguments is a list of arguments
     *
     * @return a built cell
     * */
    public static Cell createCell(Class cellClass, List<String> arguments) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Cell cell = (Cell) cellClass.getConstructor().newInstance();
        return cell.build(arguments);
    }
}
