package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Game;
import model.Position;
import model.tiles.*;
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
    private Game parseGame(JSONObject jsonObject) {
        Air air;
        Wall wall;
        EntryPoint entryPoint;
        ExitPoint exitPoint;
        Player player;
        Spike spike;
        Coin coin;

        air = deserializeAir(jsonObject);
        wall = deserializeWall(jsonObject);
        entryPoint = deserializeEntryPoint(jsonObject);
        exitPoint = deserializeExitPoint(jsonObject);
        player = deserializePlayer(jsonObject);
        spike = deserializeSpike(jsonObject);
        coin = deserializeCoin(jsonObject);

        Game game = new Game(air,wall,entryPoint,exitPoint,player,spike,coin);
        return game;
    }

    // EFFECTS: parses airTiles from JSON object and returns it as an air object
    private Air deserializeAir(JSONObject jsonObject) {
        JSONObject jsonTileObject = jsonObject.getJSONObject("airTile");
        JSONArray jsonArray = jsonTileObject.getJSONArray("positions");
        Air air = new Air();
        for (Object json : jsonArray) {
            JSONObject jsonPositionObject = (JSONObject) json;
            Position position = new Position(jsonPositionObject.getInt("x"),jsonPositionObject.getInt("y"));
            air.addPosition(position);
        }
        return air;
    }

    // EFFECTS: parses wallTiles from JSON object and returns it as a wall object
    private Wall deserializeWall(JSONObject jsonObject) {
        JSONObject jsonTileObject = jsonObject.getJSONObject("wallTile");
        JSONArray jsonArray = jsonTileObject.getJSONArray("positions");
        Wall wall = new Wall();
        for (Object json : jsonArray) {
            JSONObject jsonPositionObject = (JSONObject) json;
            Position position = new Position(jsonPositionObject.getInt("x"),jsonPositionObject.getInt("y"));
            wall.addPosition(position);
        }
        return wall;
    }

    // EFFECTS: parses the entryPointTile from the JSON object and returns it as an entryPoint object
    private EntryPoint deserializeEntryPoint(JSONObject jsonObject) {
        EntryPoint entryPoint = new EntryPoint();
        JSONObject jsonTileObject = jsonObject.getJSONObject("entryPointTile");
        JSONObject jsonPositionObject = jsonTileObject.getJSONObject("position");
        Position position = new Position(jsonPositionObject.getInt("x"),jsonPositionObject.getInt("y"));
        entryPoint.setPosition(position);
        return entryPoint;
    }

    // EFFECTS: parses the exitPointTile from the JSON object and returns it as an exitPoint object
    private ExitPoint deserializeExitPoint(JSONObject jsonObject) {
        ExitPoint exitPoint = new ExitPoint();
        JSONObject jsonTileObject = jsonObject.getJSONObject("exitPointTile");
        JSONObject jsonPositionObject = jsonTileObject.getJSONObject("position");
        Position position = new Position(jsonPositionObject.getInt("x"),jsonPositionObject.getInt("y"));
        exitPoint.setPosition(position);
        return exitPoint;
    }

    // EFFECTS: parses the playerTile from the JSON object and returns it as a player object
    private Player deserializePlayer(JSONObject jsonObject) {
        Player player = new Player();
        JSONObject jsonPlayerObject = jsonObject.getJSONObject("playerTile");

        JSONObject jsonWalletObject = jsonPlayerObject.getJSONObject("wallet");
        JSONObject jsonPositionObject = jsonPlayerObject.getJSONObject("position");

        player.setWalletBalance(jsonWalletObject.getInt("walletBalance"));
        Position position = new Position(jsonPositionObject.getInt("x"),jsonPositionObject.getInt("y"));
        player.setPosition(position);

        return player;
    }

    // EFFECTS: parses spikeTiles from the JSON object and returns it as a player object
    private Spike deserializeSpike(JSONObject jsonObject) {
        JSONObject jsonTileObject = jsonObject.getJSONObject("spikeTile");
        JSONArray jsonArray = jsonTileObject.getJSONArray("positions");
        Spike spike = new Spike();
        for (Object json : jsonArray) {
            JSONObject jsonPositionObject = (JSONObject) json;
            Position position = new Position(jsonPositionObject.getInt("x"),jsonPositionObject.getInt("y"));
            spike.addPosition(position);
        }
        return spike;
    }

    // EFFECTS: parses coinTiles from the JSON object and returns it as a player object
    private Coin deserializeCoin(JSONObject jsonObject) {
        JSONObject jsonTileObject = jsonObject.getJSONObject("coinTile");
        JSONArray jsonArray = jsonTileObject.getJSONArray("positions");
        Coin coin = new Coin();
        for (Object json : jsonArray) {
            JSONObject jsonPositionObject = (JSONObject) json;
            Position position = new Position(jsonPositionObject.getInt("x"),jsonPositionObject.getInt("y"));
            coin.addPosition(position);
        }
        return coin;
    }
}