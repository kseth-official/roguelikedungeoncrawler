# ***A Procedurally Generated Rogue-like Dungeon***

**This application is designed for anyone interested 
in playing something similar to a rogue-like 
dungeon game and looking for an adequate challenge.**

## *Game Specification*

The application will generate a *map*, 
with a single entry and exit point, containing 
several rooms, obstacles (spikes), and enemies that the 
player will have to circumvent to progress to each 
next level. The level of difficulty will increase 
with each level completion. 

The player will have a single life and access to 
a menu where the player may utilize up to 3 
types of hints to complete a level, including a hint 
indicating the path from the entry point to the exit.
The player will be unable to use the same hint again 
for the rest of the game once the hint has been 
utilized.

The game will end once the player dies through contact with an obstacle or
an enemy or the player successfully completes 5 levels.

## *Inspiration*

Throughout my life, I have played several Rogue-like 
dungeon games:

- *The Pokemon Mystery Dungeon Games*
- *The Enchanted Cave 2*
- *Hades*

I’ve always wondered about how the games were made. 

This project will allow me to explore this curiosity 
of mine, while simultaneously implementing a pathfinding
algorithm in a game-like context and also a simple 
procedural generation algorithm like The Drunkard 
Walk Algorithm.

## *User Stories*
As part of Phase 1 of this project, the following user stories have been implemented:

- As a user, I want to be able to navigate a room using the arrow keys.
- As a user, I want to be able to interact with the exit point to complete the level. 
- As a user, I want to be able to be able die if I walk into an obstacle.  
- As a user, I want to be able to add coins to my wallet as a count of my score.

The pathfinding and procedural generation will be added in later phases of this project.


