# ***A Procedurally Generated Rogue-like Dungeon***

**This application is designed for anyone interested 
in playing a rogue-like dungeon crawler that 
provides an adequate challenge.**

## *Game Specification*
### *Map*
The game procedurally generate a *map* 
, with a single entry and exit point, containing 
several rooms, obstacles (spikes), enemies, and coins.
 
### *Objective*
The player starts at the entry point and must circumvent 
or eliminate all obstacles and enemies to reach the exit point 
and progress to the next level. 
The game consists of 5 levels, and the player wins
if they successfully complete all five.

### *Mechanics*
- The player has a single life.
- The player dies when the player's health 
bar reaches zero.
- The level of difficulty increases 
with each level completion. 
- The player has a wallet for coin storage.
- The player may collect coins by walking on to coin tiles or 
by defeating enemies.
- The player has access to a shop where the
player may purchase items using coins.
- The player may buy additional power-ups in the shop that may
enhance gameplay.

#### *Power-ups*
##### *Map-level power-ups*
|Number    |Name    |Usage    |Description    |
|----------|--------|---------|---------------|
|1. |Find exit-point power-up |One-time |Displays the path from the entry-point to the exit-point.|

##### *Player-level power-ups*
|Number    |Name    |Usage    |Description    |
|----------|--------|---------|---------------|
|1. |||||

*More power-ups will be added in the future.*

## *Inspiration*

I've played several Rogue-like dungeon games before:

- *The Pokemon Mystery Dungeon Games*
- *The Enchanted Cave 2*
- *Hades*

I’ve always wondered about how the games were made. 

This project will allow me to explore this curiosity 
of mine, while simultaneously implementing a pathfinding
algorithm in a game-like context and also a procedural generation 
algorithm like The Drunkard Walk Algorithm.

## *User Stories*
### *Phase 1*`
As part of *Phase 1* of this project, the following user stories have been implemented:

- As a user, I want to be able to navigate a room using the arrow keys.
- As a user, I want to be able to interact with the exit point to complete the level. 
- As a user, I want to be able to be able to die (lose all health) if I walk into an obstacle.  
- As a user, I want to be able to add coins to my wallet as a count of my score.

The pathfinding and procedural generation will be added in later phases of this project.

### *Phase 2*

The following user stories have been implemented as part of phase 2.
- As a user, I want to be able to access a menu on starting the application.
- As a user, I want to be able to start a new game or load a saved game from a save file.
- As a user, I want to be able to save the current game to a save file.
- As a user, I want to have access to at least 3 save files.

### *Post Phase 2*
The following user stories will be implemented post phase 2 before beginning phase 3:

- As a user, I want to be able to see my current health.
- As a user, I want to be able to access the shop menu.
- As a user, I want to be able to die instantly (lose all health) upon interaction with an enemy.
- As a user, I want to be able to get detected by an enemy's radar.
- As a user, I want to be followed by an enemy once detected on the radar.
