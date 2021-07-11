package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import exceptions.CellAtMaximumOrMinimumException;
import model.Direction;
import model.Game;
import model.tile.Coin;
import model.tile.MultipleTile;
import model.Position;
import model.tile.SingleTile;
import model.tile.*;
import netscape.javascript.JSObject;
import org.json.*;

// Code citation: JsonSerializationDemo (CPSC 210; The University of British Columbia, Vancouver)
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    public String getSource() {
        return this.source;
    }

    // EFFECTS: reads game from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Game read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGame(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    // EFFECTS: parses game from JSON object and returns it
    private Game parseGame(JSONObject jsonObject) throws CellAtMaximumOrMinimumException {
        Direction initialDiggingDirection;
        Position initialDiggingPosition;
        Set<Position> unoccupiedTiles;
        Air air;
        Wall wall;
        EntryPoint entryPoint;
        ExitPoint exitPoint;
        Player player;
        Spike spike;
        Coin coin;
        Enemy enemy;
        SmallHealthPotion smallHealthPotion;

        initialDiggingDirection = deserializeInitialDiggingDirection(jsonObject);
        initialDiggingPosition = deserializeInitialDiggingPosition(jsonObject);
        unoccupiedTiles = deserializeUnoccupiedTiles(jsonObject);
        air = deserializeAir(jsonObject);
        wall = deserializeWall(jsonObject);
        entryPoint = deserializeEntryPoint(jsonObject);
        exitPoint = deserializeExitPoint(jsonObject);
        player = deserializePlayer(jsonObject);
        spike = deserializeSpike(jsonObject);
        coin = deserializeCoin(jsonObject);
        enemy = deserializeEnemy(jsonObject);
        smallHealthPotion = deserializeSmallHealthPotion(jsonObject);

        return new Game(
                initialDiggingDirection,
                initialDiggingPosition,
                unoccupiedTiles,
                air,
                wall,
                entryPoint,
                exitPoint,
                player,
                spike,
                coin,
                enemy,
                smallHealthPotion
        );
    }

    // EFFECTS: parses the initialDiggingDirection from JSON object and returns it as a Direction object
    private Direction deserializeInitialDiggingDirection(JSONObject jsonObject) {
        Direction[] directions = Direction.values();
        return directions[jsonObject.getJSONObject("initialDiggingDirection").getInt("directionIndex")];
    }

    // EFFECTS: parses the initialDiggingPosition from JSON object and returns it as a Position object
    private Position deserializeInitialDiggingPosition(JSONObject jsonObject) {
        JSONObject jsonPositionObject = jsonObject.getJSONObject("initialDiggingPosition");
        return new Position(jsonPositionObject.getInt("x"),jsonPositionObject.getInt("y"));
    }

    // EFFECTS: parses the unoccupiedTiles from JSON object and returns it as a HashSet of positions
    private Set<Position> deserializeUnoccupiedTiles(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("unoccupiedTiles");
        HashSet<Position> temporaryHashSet = new HashSet<>();
        for (Object json : jsonArray) {
            JSONObject jsonPositionObject = (JSONObject) json;
            Position position = new Position(jsonPositionObject.getInt("x"),jsonPositionObject.getInt("y"));
            temporaryHashSet.add(position);
        }
        return temporaryHashSet;
    }

    // EFFECTS: parses airTiles from JSON object and returns it as an air object
    private Air deserializeAir(JSONObject jsonObject) {
        Air air = new Air();
        air.setPositionSet(deserializeMultipleTile(jsonObject,air,"airTile"));
        return air;
    }

    // EFFECTS: parses wallTiles from JSON object and returns it as a wall object
    private Wall deserializeWall(JSONObject jsonObject) {
        Wall wall = new Wall();
        wall.setPositionSet(deserializeMultipleTile(jsonObject,wall,"wallTile"));
        return wall;
    }

    // EFFECTS: parses the entryPointTile from the JSON object and returns it as an entryPoint object
    private EntryPoint deserializeEntryPoint(JSONObject jsonObject) {
        EntryPoint entryPoint = new EntryPoint();
        entryPoint.setPosition(deserializeSingleTile(jsonObject,entryPoint,"entryPointTile"));
        return entryPoint;
    }

    // EFFECTS: parses the exitPointTile from the JSON object and returns it as an exitPoint object
    private ExitPoint deserializeExitPoint(JSONObject jsonObject) {
        ExitPoint exitPoint = new ExitPoint();
        exitPoint.setPosition(deserializeSingleTile(jsonObject,exitPoint,"exitPointTile"));
        return exitPoint;
    }

    // EFFECTS: parses the playerTile from the JSON object and returns it as a player object
    private Player deserializePlayer(JSONObject jsonObject) throws CellAtMaximumOrMinimumException {
        Player player = new Player();
        player.setPosition(deserializeSingleTile(jsonObject,player,"playerTile"));

        JSONObject jsonPlayerObject = jsonObject.getJSONObject("playerTile");

        JSONObject jsonWalletObject = jsonPlayerObject.getJSONObject("wallet");
        player.setWalletBalance(jsonWalletObject.getInt("walletBalance"));

        JSONObject jsonHealthBarObject = jsonPlayerObject.getJSONObject("healthBar");
        player.getHealthBar().set(jsonHealthBarObject.getInt("health"));
        player.getHealthBar().setIsZero(jsonHealthBarObject.getBoolean("isZero"));

        JSONObject jsonInventoryObject = jsonPlayerObject.getJSONObject("inventory");
        player.getInventory().addSmallHealthPotions(jsonInventoryObject.getInt("numberOfSmallHealthPotions"));

        return player;
    }

    // EFFECTS: parses spikeTiles from the JSON object and returns it as a player object
    private Spike deserializeSpike(JSONObject jsonObject) {
        Spike spike = new Spike();
        spike.setPositionSet(deserializeMultipleTile(jsonObject,spike,"spikeTile"));
        return spike;
    }

    // EFFECTS: parses coinTiles from the JSON object and returns it as a player object
    private Coin deserializeCoin(JSONObject jsonObject) {
        Coin coin = new Coin();
        coin.setPositionSet(deserializeMultipleTile(jsonObject,coin,"coinTile"));
        return coin;
    }

    // EFFECTS: parses enemyTiles from the JSON object and returns it as a player object
    private Enemy deserializeEnemy(JSONObject jsonObject) {
        Enemy enemy = new Enemy();
        enemy.setPositionSet(deserializeMultipleTile(jsonObject,enemy,"enemyTile"));
        return enemy;
    }

    // EFFECTS: parses enemyTiles from the JSON object and returns it as a player object
    private SmallHealthPotion deserializeSmallHealthPotion(JSONObject jsonObject) {
        SmallHealthPotion smallHealthPotion = new SmallHealthPotion();
        smallHealthPotion.setPositionSet(deserializeMultipleTile(jsonObject,smallHealthPotion,"smallHealthPotionTile"));
        return smallHealthPotion;
    }

    // EFFECTS: parses a MultipleTile class object from a JSON object and returns its positions as a HashSet
    private HashSet<Position> deserializeMultipleTile(JSONObject jsonObject, MultipleTile tileObject, String tileName) {
        JSONObject jsonTileObject = jsonObject.getJSONObject(tileName);
        JSONArray jsonArray = jsonTileObject.getJSONArray("positions");
        HashSet<Position> temporaryHashSet = new HashSet<>();
        for (Object json : jsonArray) {
            JSONObject jsonPositionObject = (JSONObject) json;
            Position position = new Position(jsonPositionObject.getInt("x"),jsonPositionObject.getInt("y"));
            temporaryHashSet.add(position);
        }
        return temporaryHashSet;
    }

    // EFFECTS: parses a SingleTile class object from a JSON object and returns its position
    private Position deserializeSingleTile(JSONObject jsonObject, SingleTile tileObject, String tileName) {
        JSONObject jsonTileObject = jsonObject.getJSONObject(tileName);
        JSONObject jsonPositionObject = jsonTileObject.getJSONObject("position");
        return new Position(jsonPositionObject.getInt("x"),jsonPositionObject.getInt("y"));
    }
}
