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
Continue moving the objects until you get all the rabbits in a hole. Press the undo button
if you wish to undo a move. This move can be redone by pressing the redo button. For a hint 
press the hint button.


Changes:
We added an undo and a redo option to the game, and also a solver. This allows for moves to be 
undone and then redone, and also for the game to give the user hints of what move to do.
This required the creation of the UndoRedo class, and the test cases for this class. Changes were
also made to existing classes to include the UndoRedo logic and the solver.
