# ***A Procedurally Generated Rogue-like Dungeon***

**This application is designed for anyone interested 
in playing a rogue-like dungeon crawler that 
provides an adequate challenge.**

**Currently the procedural generation and enemy AI have not been implemented, and will
be done after Phase 4 of this project.**

## *Game Specification*
### *Map*
The game generates a *map* with walls, a single entry point, a single exit point, obstacles (spikes), an enemy,
small health potions, and coins.
 
### *Objective*
The player starts at the entry point and must circumvent all obstacles and the enemy to reach the exit point 
and descend to the next level. 

### *Mechanics*
- The player has a single life.
- The player dies when the player's health 
bar reaches zero.
- The player has a wallet for coin storage.
- The player may collect coins by walking on to coin tiles.
- The player may collect small health potions.
- The player may walk around the map using WASD.

## *Inspiration*

I've played several Rogue-like dungeon games before:

- *The Pokemon Mystery Dungeon Games*
- *The Enchanted Cave 2*
- *Hades*

I’ve always wondered about how the games were made. 

This project will allow me to explore this curiosity 
of mine, while simultaneously implementing a pathfinding
algorithm in a game-like context and also a procedural generation algorithm.

## *User Stories*
### *Phase 1*`
The following user stories have been implemented in phase 1.

- As a user, I want to be able to navigate a room using the arrow keys.
- As a user, I want to be able to interact with the exit point to complete the level. 
- As a user, I want to be able to be able to die (lose all health) if I walk into an obstacle.  
- As a user, I want to be able to add coins to my wallet as a count of my score.

### *Phase 2*

The following user stories have been implemented in phase 2.
- As a user, I want to be able to access a menu on starting the application.
- As a user, I want to be able to start a new game or load a saved game from a save file.
- As a user, I want to be able to save the current game to a save file.
- As a user, I want to have access to at least 3 save files.

### *Post Phase 2*
The following user stories have been implemented post phase 2 before beginning phase 3:

- As a user, I want to be able to see an enemy on the map. (check)
- As a user, I want to be able to pause the game and access a menu with options to
  return back to the game, return back to the main menu without saving, save the
  game, and exit the game. (check)
- As a user, I want to be able to see my current health. (check)
- As a user, I want to be able to lose 20 health upon walking into an enemy, 
  and not be able to walk through them. (check)
- As a user, I want to be able to add Small Health Potions to my inventory. (check)
- As a user, I want to be able to use Small Health Potions in my inventory to increase my health by 25. (check)

### *Phase 3*
The following user stories will be implemented in phase 3.
GUI Implemetation:
- As a user, I want to be able to see a panel where all the Coins that have been
   added to the Player's Wallet are displayed. (check)
- As a user, I want to be able to click a button to reduce 10 coins (Xs) in my wallet (Y) and buy a Small Health Potion.
- As a user, I want to be able to click a button to dispose a coin, i.e., drop one coin (X) from my wallet (Y).
- As a user, I want to be able to click on a Use Small Health Potion Button to use a SmallHealthPotion and 
   add health to my HealthBar. (check)
- As a user, I want to be able to click on a Descend Button to make the Player interact
   with the ExitPoint to complete a level. (check)
- As a user, I want to be able to access menus with menu items that allow me
   to load and save the game. (check)
- As a user, I want to be able to be able to hear a sound when I die or complete the level. (check)

### *Post Phase 3*
- As a user, I want to be able to see graphical images to represent the game sprites. 

### *Phase 4*
#### *Phase 4: Task 2*
I have chosen to implement a class in my model package that is robust. It contains 
three methods that are tested for a checked exception, both when the exception is 
thrown and not thrown.

Class Name: Inventory

Method Names: addSmallHealthPotions(), addOneSmallHealthPotion(), addOneSmallHealthPotion()

#### *Phase 4: Task 3*
I would refactor the MainMenu, LoadGame, SaveGame, and PauseMenu classes so that they all extend
an abstract menu class that implements the ActionListener interface. I would then override
the actionPerformed() method belonging to that interface so that these subclasses would only have
the required additional functionality, and a large portion of reptitive code would be removed. 

In future improvements of this project, I hope to use a pathfinding algorithm to allow enemies to follow the player, and
a procedural generation algorithm for the level so that it has high replayability.




	
