package model.tile;

import model.Game;
import model.pathfinding.Pathfinder;
import model.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// A class representing the standard game enemy.
// This enemy moves around within a set boundary and causes the player to die upon interaction.
public class Enemy extends MultipleTile {
    private static final String ENEMY_CHARACTER_SYMBOL = "O";
    public static final String ENEMY_TILE_IMAGE_SOURCE = "./data/graphics/enemyCharacter1.jpg";
    private static final int RADAR_SIZE = 5;

    // EFFECTS: displays the symbol for the enemy character + an optional string
    public String display(String s) {
        return super.display(ENEMY_CHARACTER_SYMBOL,s);
    }

    // MODIFIES: this
    // EFFECTS: moves every enemy that detects the player on its radar towards the player
    public void move(Game game) {
        Pathfinder pathfinder = new Pathfinder();
        Position playerPosition = game.player().getPosition();
        Set<Position> enemyPositionSet = game.enemy().getPositionSet();

        // Set of all obstacles enemy must avoid
        HashSet<Position> obstacles = new HashSet<Position>() {
            {
                addAll(game.wall().getPositionSet());
                addAll(game.spike().getPositionSet());
            }
        };

        // The path to the target node
        List<Position> pathToTargetNode;

        HashSet<Position> tempSet = new HashSet<>(enemyPositionSet);

        // TODO: Work on smoothing enemy movement and collision
        for (Position enemyPosition : tempSet) {
            if (Pathfinder.detectedByRadar(RADAR_SIZE,enemyPosition,playerPosition)) {
                // Get path to target node
                pathToTargetNode = pathfinder.shortestPathFrom(enemyPosition,playerPosition,obstacles);

                if (pathToTargetNode != null && pathToTargetNode.size() > 1) {
                    if (pathToTargetNode.get(1).equals(playerPosition)) {
//                        game.player().getHealthBar().subtract(20);
                    } else {
                        if (enemyPositionSet.contains(pathToTargetNode.get(1))) {
                            // do nothing
                        } else {
                            // Remove current enemy position from the enemy position set
                            enemyPositionSet.remove(enemyPosition);
                            // Add new enemy position in step towards shortest path to the position set
                            enemyPositionSet.add(pathToTargetNode.get(1));
                        }
                    }
                } else {
                    // do nothing
                }
            }
        }
    }
}
