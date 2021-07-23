package model.tile;

import exceptions.PathNotFoundException;
import model.Game;
import model.pathfinding.Pathfinder;
import model.Position;
import model.pathfinding.Radar;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// A class representing the standard game enemy.
// This enemy moves around within a set boundary and causes the player to lose 25 health upon interaction.
public class Enemy extends MultipleTile {
    private static final String ENEMY_CHARACTER_SYMBOL = "O";
    public static final String ENEMY_TILE_IMAGE_SOURCE = "./data/graphics/enemyCharacter1.jpg";
    private static final int RADAR_SIZE = 5;

    // EFFECTS: displays the symbol for the enemy character + an optional string
    public String display(String s) {
        return super.display(ENEMY_CHARACTER_SYMBOL,s);
    }

    // MODIFIES: this
    // EFFECTS: Moves every enemy that detects the player on its radar towards the player. Does not move the enemy if
    // it is already right next to the player, if it is on the same position as the player, if another enemy exists on
    // the next position on the shortest path to the player, or if there is no path to the player.
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

        // The path to the player
        List<Position> pathToPlayer;

        HashSet<Position> tempSet = new HashSet<>(enemyPositionSet);

        for (Position enemyPosition : tempSet) {
            Radar enemyRadar = new Radar(enemyPosition,RADAR_SIZE);
            if (enemyRadar.hasDetected(playerPosition)) {
                // Get path to target node
                try {
                    pathToPlayer = pathfinder.shortestPathFrom(enemyPosition,playerPosition,obstacles);
                    boolean enemyMovementCondition =
                            // move if you're more than one block away from the player or don't
                            pathToPlayer.size() > 2
                                    && !pathToPlayer.get(1).equals(playerPosition)
                                    // don't move if another enemy exists at the next position on the shortest path to
                                    // the player
                                    && !enemyPositionSet.contains(pathToPlayer.get(1));

                    if (enemyMovementCondition) {
                        // Remove current enemy position from the enemy position set
                        enemyPositionSet.remove(enemyPosition);
                        // Add new enemy position to the enemyPositionSet
                        enemyPositionSet.add(pathToPlayer.get(1));
                    }
                } catch (PathNotFoundException e) {
                    // do not move the enemy
                }
            }
        }
    }
}
