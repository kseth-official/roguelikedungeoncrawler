package persistence;

import org.json.JSONObject;

// Code citation: JsonSerializationDemo (CPSC 210; The University of British Columbia, Vancouver)
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
