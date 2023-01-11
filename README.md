# ***Deep Dungeon - a roguelike dungeon crawler***

**This project is a Java Desktop application with frontend implemented using Java Swing with over 29 user stories and 171 unit tests written in JUnit. The game provides a fun challenge and consists of 3 levels with random map generation and A\* search for enemy AI**

## *Inspiration*

I've played several Rogue-like dungeon games before:

- *Hades (2018)*
- *The Enchanted Cave 2 (2015)*
- *Pokemon Mystery Dungeon: Blue Rescue Team (2005)*

I’ve always wondered how these games were made. 

This project allowed me to explore this curiosity 
of mine, while simultaneously implementing an A* pathfinding algorithm for enemy AI and
a Random Walker Algorithm for procedural map generation, while practicing Unit Testing for game frontend.

## *Game Specification*
### *Map*
The game generates a *map* with walls, a single entry point, a single exit point, spikes, enemies,
small health potions, and coins.

![](GameMapImage.png)

### *Enemies*

The enemies follow you around regardless of how many there are.

![](EnemiesFollowYouAround.gif)

### *Random Map Generation*

The level changes each time you descend through the stairs.

![](LevelChangesEachTime.gif)
 
### *Objective*
The player starts at the entry point and must circumvent all spikes and enemies to reach the exit point 
and descend to the next level. Upon descending through 3 levels the game is over.  

### *Mechanics*
- The player has a single life.
- The player dies when the player's health 
bar reaches zero.
- The player has a wallet for coin storage.
- The player may collect coins by walking on to coin tiles.
- The player loses 25 health upon walking into an enemy.
- The player loses 100 health upon walking into a spike.
- The player may collect small health potions.
- The player may buy a small health potion for 10 coins.
- The player may use the small health potion to gain 25 health.
- The player may dispose a coin.
- The player may walk around the map using WASD.

## *User Stories*
### *Phase 1*
The following user stories were implemented in phase 1:

- As a user, I want to be able to navigate a room using WASD. (check)
- As a user, I want to be able to interact with the exit point to complete the level. (check) 
- As a user, I want to be able to be able to die (lose all health) if I walk into a spike. (check)  
- As a user, I want to be able to add coins to my wallet as a count of my score. (check)

### *Phase 2*
The following user stories were implemented in phase 2:

- As a user, I want to be able to access a menu on starting the application. (check)
- As a user, I want to be able to start a new game or load a saved game from a save file. (check)
- As a user, I want to be able to save the current game to a save file. (check)
- As a user, I want to have access to at least 3 save files. (check)

### *Post Phase 2*
The following user stories were implemented post phase 2 before beginning phase 3:

- As a user, I want to be able to see an enemy on the map. (check)
- As a user, I want to be able to pause the game and access a menu with options to
  return to the game, return to the main menu without saving, save the
  game, and exit the game. (check)
- As a user, I want to be able to see my current health. (check)
- As a user, I want to be able to lose 20 health upon walking into an enemy 
  and not be able to walk through them. (check)
- As a user, I want to be able to add Small Health Potions to my inventory. (check)
- As a user, I want to be able to use Small Health Potions in my inventory to increase my health by 25. (check)

### *Phase 3*
The following user stories were implemented in phase 3:
#### GUI Implemetation:
- As a user, I want to be able to see a panel where all the Coins that have been
   added to the Player's Wallet are displayed. (check)
- As a user, I want to be able to click a button to reduce 10 coins (Xs) in my wallet (Y) and buy a Small Health Potion. 
   (check)
- As a user, I want to be able to click a button to dispose a coin, i.e., drop one coin (X) from my wallet (Y). (check)
- As a user, I want to be able to click on a Use Small Health Potion Button to use a SmallHealthPotion and 
   add health to my HealthBar. (check)
- As a user, I want to be able to click on a Descend Button to make the Player interact
   with the ExitPoint to complete a level. (check)
- As a user, I want to be able to access menus with menu items that allow me
   to load and save the game. (check)
- As a user, I want to be able to be able to hear a sound when I die or complete the game. (check)

### *Post Phase 3*
The following user stories were implemented post phase 3 before beginning phase 4:
- As a user, I want to be able to see graphical images to represent the game sprites. 

### *Phase 4*
This phase involved increasing code robustness and refactoring to implement design patterns.

#### *Phase 4: Task 2*
I chose to implement a robust class named Inventory in my model package. It contains 
three methods that are tested for a checked exception, both when the exception is thrown and not thrown.

Class Name: Inventory

Method Names: addSmallHealthPotions(), addOneSmallHealthPotion(), addOneSmallHealthPotion()

#### *Phase 4: Task 3*
This is a discussion of future refactoring possibilities. 

I would refactor the MainMenu, LoadGame, SaveGame, and PauseMenu classes so that they all extend
an abstract menu class that implements the ActionListener interface. I would then override
the actionPerformed() method belonging to that interface so that these subclasses would only have
the required additional functionality, and a large portion of repetitive code would be removed consequently. 

In future improvements of this project, I hope to use a pathfinding algorithm to allow enemies to follow the player, and
a procedural generation algorithm for the level so that it has high replayability.

### *Phase 5*
The following user stories were implemented in phase 5:
#### **Pathfinding Implementation**
- As a user, I want to have an enemy follow me around as I move.
- As a user, I want to have multiple enemies follow me around as I move.
- As a user, I want to be able to be trapped by enemies so that I'm forced to either quit or die to lose the game.

### *Phase 6*
The following user stories were implemented in phase 6:
#### **Procedural Generation Implementation Using A Random Walker Algorithm**
- As a user, I want to have the level change completely every time I create a new game.
- As a user, I want to have the level change every time I descend through the stairs.
- As a user, I want to be able to descend through 3 dungeon levels before descending again to finish the game.

### *Extras*
- As a user, I want to be able to hear a sound every time I complete a level.
