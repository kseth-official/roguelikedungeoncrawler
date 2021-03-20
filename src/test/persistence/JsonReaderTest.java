package persistence;
import model.Game;
import model.Position;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

// Code citation: JsonSerializationDemo (CPSC 210; The University of British Columbia, Vancouver)
class JsonReaderTest {
    // update testJsonReader every time a new tile is added to the map
    private static final String JSON_STORE_TEST_JSON_READER = "./data/testJsonReader.json";

    @Test
    public void testConstructor() {
        JsonReader jsonReader = new JsonReader(JSON_STORE_TEST_JSON_READER);
        assertTrue(jsonReader.getSource().equals(JSON_STORE_TEST_JSON_READER));
    }

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Game game = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    public void testReader() {
        JsonReader reader = new JsonReader(JSON_STORE_TEST_JSON_READER);
        try {
            Game game = reader.read();
            testAirTileReadCorrectly(game);
            testCoinTileReadCorrectly(game);
            testEntryPointReadCorrectly(game);
            testExitPointReadCorrectly(game);
            testPlayerReadCorrectly(game);
            testSpikeTileReadCorrectly(game);
            testWallTileReadCorrectly(game);
            testEnemyTileReadCorrectly(game);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    void testAirTileReadCorrectly(Game game) {
        assertTrue(game.air().getPositionSet().isEmpty());
    }

    void testCoinTileReadCorrectly(Game game) {
        assertTrue(game.coin().getPositionSet().contains(new Position(4,3)));
        assertTrue(game.coin().getPositionSet().contains(new Position(7,6)));
        assertTrue(game.coin().getPositionSet().contains(new Position(4,4)));
        assertTrue(game.coin().getPositionSet().contains(new Position(4,5)));
        assertTrue(game.coin().getPositionSet().contains(new Position(7,1)));
        assertTrue(game.coin().getPositionSet().contains(new Position(6,1)));
        assertTrue(game.coin().getPositionSet().contains(new Position(7,2)));
        assertTrue(game.coin().getPositionSet().contains(new Position(5,1)));
        assertTrue(game.coin().getPositionSet().contains(new Position(7,3)));
        assertTrue(game.coin().getPositionSet().contains(new Position(10,6)));
        assertTrue(game.coin().getPositionSet().contains(new Position(4,1)));
        assertTrue(game.coin().getPositionSet().contains(new Position(7,4)));
        assertTrue(game.coin().getPositionSet().contains(new Position(9,6)));
        assertTrue(game.coin().getPositionSet().contains(new Position(4,2)));
        assertTrue(game.coin().getPositionSet().contains(new Position(7,5)));
        assertTrue(game.coin().getPositionSet().contains(new Position(8,6)));
    }

    void testEntryPointReadCorrectly(Game game) {
        assertEquals(game.entryPoint().getPosition(),new Position(1,6));
    }

    void testExitPointReadCorrectly(Game game) {
        assertEquals(game.exitPoint().getPosition(),new Position(11,6));
    }

    void testPlayerReadCorrectly(Game game) {
        assertEquals(game.player().getWalletBalance(),3);
        assertEquals(game.player().getPosition(),new Position(5,9));
    }

    void testSpikeTileReadCorrectly(Game game) {
        assertTrue(game.spike().getPositionSet().contains(new Position(9,8)));
        assertTrue(game.spike().getPositionSet().contains(new Position(9,9)));
        assertTrue(game.spike().getPositionSet().contains(new Position(9,3)));
        assertTrue(game.spike().getPositionSet().contains(new Position(9,4)));
    }

    void testWallTileReadCorrectly(Game game) {
        // read boundaries
        assertTrue(game.wall().getPositionSet().contains(new Position(0,0)));
        assertTrue(game.wall().getPositionSet().contains(new Position(12,0)));
        assertTrue(game.wall().getPositionSet().contains(new Position(0,12)));
        assertTrue(game.wall().getPositionSet().contains(new Position(12,12)));

        // read walls
        for (int i = 1; i < 12; ++i) {
            assertTrue(game.wall().getPositionSet().contains(new Position(i,0)));
            assertTrue(game.wall().getPositionSet().contains(new Position(i,12)));
            assertTrue(game.wall().getPositionSet().contains(new Position(0,i)));
            assertTrue(game.wall().getPositionSet().contains(new Position(12,i)));
        }
    }

    void testEnemyTileReadCorrectly(Game game) {
        assertTrue(game.enemy().getPositionSet().contains(new Position(1,11)));
    }
}