package model.tile;

// A class representing the Spike tile on the Game Map, which will cause the player to die upon interaction.
public class Spike extends MultipleTile {
    private static final String SPIKE_TILE_SYMBOL = "░";

    // EFFECTS: displays the symbol for the spike tile + an optional string
    public String display(String s) {
        return super.display(SPIKE_TILE_SYMBOL,s);
    }
}
