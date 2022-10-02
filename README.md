# Product introduction

Our project consists of the RPG game **Courage** and a customizable game engine **DungeonMaker**.

For more information on our product, please refer to our Confluence file: https://avanidhaliwal.atlassian.net/l/cp/0Wa1vV1W.

#### Courage

***Courage*** is a dungeon exploration game where the user can go through various mazes, defeat enemies encountered in the mazes and also obtain items. The main objective of the game is to rescue the Princess who has been kidnapped by the enemies. (For more details on the game story, see here: https://avanidhaliwal.atlassian.net/wiki/spaces/CGA2/pages/1146943 ) The player aka Soldier has to go through various sets of mazes/levels and defeat enemies on their way to get to the princess. On the way, they meet friendly NPCs that they can talk to and sometimes even obtain items to help them get to the princess. 

#### DungeonMaker

**FOR** game designers **WHO** are looking to make exploration and dungeon-like 8-bit games using JSON. The **DungeonMaker** is a simple Game Engine **THAT** allows its users to create various maps, enemies, NPCs, and an engaging plot story by assigning dialogues between NPCs and the player. **UNLIKE** other advanced and complex Game Engines such as Unity and Unreal Engine where the user needs to create a game from scratch, **OUR PRODUCT** does not require any coding experience. Furthermore, **DungeonMaker** already provides features such as a character-leveling up system, a shop, and a battle system, thus, making it easy and quick for the game designer to create their game. 

# Features in the game/game engine

**Movement**: The player can use the keys WASD to move their character on the map. But they cannot go through walls. 

**Maze/Map/Level:** The cells/tiles that are included in the map are read from a JSON file. This way the game designer can customize their maps by editing the JSON files. 

**Enemies**: The user can interact with them by engaging in a battle when they are in the same cell.

**Inventory System:** Items obtained from NPCs or picked up from the map are stored here. 

**Save and load the game:** When the user saves, a unique save id is created and displayed on the dialogue. The user can then use that save id to load a game. 

**GUI**: The player can see their character, map, inventory, dialogue, as well as a panel containing the buttons:

1. ‘New’: Starts a new game from the start
2. ‘Exit’: Closes the game
3. ‘Save’: Saves the user’s progress
4. ‘Load’: Loads the game from a previous save file. 

**Leveling up:** When the player levels up, their health and attack points increase. The player levels up by gaining experience through defeating enemies.

**Shop:** The player obtains random buffs/debuffs when interacting with NPCs. 

**Battle:** When the player encounters an enemy, if the enemy’s HP is less than that of the player, the enemy is defeated. But, the player also loses health equal to that of the enemy’s health. If the enemy’s health is higher, the player is not able to go through the enemy. 

**NPC:** The NPCs may or may not help the player by giving them either buffs/debuffs. 

# A short demo of the game

Link to demo video: https://drive.google.com/file/d/1HFyZZe2p8uxww-dGqIjW3GE1_MdYsAWi/view?usp=sharing

# How to get help

For runnning the game in IntelliJ and setting up the IntelliJ for JavaFx game, please refer to Confluence file [Installation and Setup](https://avanidhaliwal.atlassian.net/l/cp/dFTAXZgH).

# The screenshots of the game

![](https://imgur.com/TuUKa5D.png)

**Fig 1: Level 1 Start**

![](https://imgur.com/alJy8Az.png)

**Fig 2: Level 1 Mid**

![](https://imgur.com/HgZxDBi.png)

**Fig 3: Level 1 End**

![](https://imgur.com/NtMtlhX.png)

**Fig 4: Level 2**

![](https://imgur.com/Ag7y3wQ.png)

**Fig 5: Game saved**

![](https://imgur.com/YBGaKI1.png)

**Fig 6: Game Load**

![](https://imgur.com/gR3iUKW.png)

**Fig 7: Level 3 NPC** 
