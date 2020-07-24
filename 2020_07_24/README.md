# Oversized Pancake Flipper

There are N pancakes in a row, each with a plain(-) and a chocolate(+) side. Customers would like to receive their pancakes with the chocolate side up. To increase efficency, management has given you a utensil that flips K adjacent pancakes at the same time. Due to its large size, it cannot flip less than K pancakes (even at the edge of the row).

## Input & Goal
The state of the pancakes (represented by + and -), and the size of the flipper. If possible, calculate the minimum amount of flips needed for all pancakes to have the chocolate side up. Example:

`---+-++- 3` -> 3

`+++++ 4` -> 0

`-+-+- 4` -> IMPOSSIBLE


Reworded from https://codingcompetitions.withgoogle.com/codejam/round/00000000002017f7/0000000000201847
