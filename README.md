# JumpInGame

Authors: Kush Gopeechund, Aashna Narang, Krishan Easparan, and Nick Anderson

Welcome to Jump In! This is a puzzle game where the player
has to figure out how to get all the rabbits into a hole to win. The player 
has to to maneuver the rabbits and foxes to find the solution.


Rules: 
Rabbits can only jump over obstacles to move, which can either be Fox or a mushroom.
The user can move rabbits up, down, left or right. The Fox on the other hand
can only be moved forwards or backwards. Two rabbits can not go in the same hole.
To win the game, the user has to strategically move the pieces to get all the rabbits
into a rabbit hole.


How to play:
Click on the animal you would like to move. The board will highlight the possible moves.
Continue moving the objects until you get all the rabbits in a hole.


Changes:
We added a GUI component to the game to replace the text-based version, so instead of typing
in where you want to move a game piece, you click the piece and move it to a highlighted tile.
This required us to create several new clasess including, Board, GameButton, JumpInController,
JumpInEvent, JumpInView, MainMenu, MainMenuButton, Resources, Utility, WinEvent and WinListener. 
We also added all the test cases for all the logical components of the game(not the GUI).

Issues:
We didn't figure out how to implement the win listener, so the user never gets notified when
the game is done and is able to continue the game.

