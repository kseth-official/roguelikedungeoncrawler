package persistence;

import model.Game;
import model.Position;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Code citation: JsonSerializationDemo (CPSC 210; The University of British Columbia, Vancouver)
class JsonWriterTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.
    private static final String JSON_STORE_TEST_JSON_WRITER = "./data/testJsonWriter.json";

    @Test
    public void testWriterInvalidFile() {
        try {
            Game game = new Game();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testConstructor() {
        JsonWriter jsonWriter = new JsonWriter(JSON_STORE_TEST_JSON_WRITER);
        assertTrue(jsonWriter.getDestination().equals(JSON_STORE_TEST_JSON_WRITER));
    }

    @Test
    public void testWriter() {
        try {
            Game game = new Game();
            JsonWriter writer = new JsonWriter(JSON_STORE_TEST_JSON_WRITER);
            writer.open();
            writer.write(game);
            writer.close();

            JsonReader reader = new JsonReader(JSON_STORE_TEST_JSON_WRITER);
            game = reader.read();
            testAirTileReadCorrectly(game);
            testCoinTileReadCorrectly(game);
            testEntryPointReadCorrectly(game);
            testExitPointReadCorrectly(game);
            testPlayerReadCorrectly(game);
            testSpikeTileReadCorrectly(game);
            testWallTileReadCorrectly(game);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    void testAirTileReadCorrectly(Game game) {
        assertTrue(game.air().getPositionSet().contains(new Position(2,6)));
    }

    void testCoinTileReadCorrectly(Game game) {
        assertTrue(game.coin().getPositionSet().contains(new Position(4,3)));
        assertTrue(game.coin().getPositionSet().contains(new Position(7,6)));
        assertTrue(game.coin().getPositionSet().contains(new Position(4,4)));
        assertTrue(game.coin().getPositionSet().contains(new Position(4,5)));
        assertTrue(game.coin().getPositionSet().contains(new Position(4,6)));
        assertTrue(game.coin().getPositionSet().contains(new Position(3,6)));
        assertTrue(game.coin().getPositionSet().contains(new Position(2,6)));
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
        assertEquals(game.player().getWalletBalance(),0);
        assertEquals(game.player().getPosition(),new Position(1,6));
    }


    void testSpikeTileReadCorrectly(Game game) {
        assertTrue(game.spike().getPositionSet().contains(new Position(9,8)));
        assertTrue(game.spike().getPositionSet().contains(new Position(9,9)));
        assertTrue(game.spike().getPositionSet().contains(new Position(9,3)));
        assertTrue(game.spike().getPositionSet().contains(new Position(9,4)));
    }

    void testWallTileReadCorrectly(Game game) {
        assertTrue(game.wall().getPositionSet().contains(new Position(0,0)));
        assertTrue(game.wall().getPositionSet().contains(new Position(12,0)));
        assertTrue(game.wall().getPositionSet().contains(new Position(0,12)));
        assertTrue(game.wall().getPositionSet().contains(new Position(12,12)));

        for (int i = 1; i < 12; ++i) {
            assertTrue(game.wall().getPositionSet().contains(new Position(i,0)));
            assertTrue(game.wall().getPositionSet().contains(new Position(i,12)));
            assertTrue(game.wall().getPositionSet().contains(new Position(0,i)));
            assertTrue(game.wall().getPositionSet().contains(new Position(12,i)));
        }
    }
}