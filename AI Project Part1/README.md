### CS4341 Project3 Part1
### Author: Kewen Gu & Zhaochen Ding

##### Introduction:
In this part of project3, we implemented five features for the given 1000 Connect4 board states. The five features we have used are:
  Feature 1: which player has more pieces in center rows
  Feature 2: which player has a piece at the bottom left corner of the board
  Feature 3: which player has more pieces in the center column
  Feature 4: which player has more pieces in the bottom row
  Feature 5: which player has more connections of 2 pieces
The output of all five features are either 1 or 0, 1 mean player1 has more advantage over player, and 0 otherwise. We'll use the generated features in part two of this project.

##### How to run this program:
There're several ways you can run this project.
In order to run this program in eclipse, edit the run configuration of the project, supply it with the main class which is "project3.Main", and the parameters needed which is "trainDataSet.csv <output csv filename>". Then it should be able to run.

In order to run in command line or terminal, change your working directory to the root folder of this java project, then compile the program by entering "javac -classpath bin/project3/ src/project3/\*.java". Then, cd to the bin directory, and enter "java project3.Main trainDataSet <output csv filename>". You should see the output file generated once you check the project folder.
