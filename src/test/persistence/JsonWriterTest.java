package persistence;

import model.Game;
import model.Position;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// TODO: Update JSONWriter test after implementing procedural generation.
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
            Game initialGame = new Game();
            JsonWriter writer = new JsonWriter(JSON_STORE_TEST_JSON_WRITER);
            writer.open();
            writer.write(initialGame);
            writer.close();

            JsonReader reader = new JsonReader(JSON_STORE_TEST_JSON_WRITER);
            Game game = reader.read();

            assertTrue(initialGame.equals(game));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}