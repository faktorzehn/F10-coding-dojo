# Robot Roulette

Everyone starts with 100 HP. Each round, 2 players are chosen at random from the pool of contestants who have not yet competed in that round. Both players pick a number between 0 and their current HP, and reveal those numbers at the same time. The player who chose the lower number immediately loses, while the other player's health is reduced by his chosen number.

## Task

Create a program (any language) that takes command line parameters as input and prints the amount of HP to bet.
The input parameters are *own HP*, *enemy HP*, and the *enemy bid history*.

Example: `robot.exe 63 54 26,20` You have 63 HP and the enemy has 54 HP. The enemy has bid 26 and 20 in the past rounds.

If it is the first round, *enemy bid history* will be an empty string. If you choose the same number as your opponent, both player's health is reduced by 5 and the match is repeated.

Doing any of this results in a loss:
- Choosing a number lower than that of the opponent
- HP dropping to or below zero
- Taking longer than 1 second to terminate
- Non-zero exit code (errors/exceptions in code)
- Printing anything else other than a single number

**You may create as many different programs as you wish.**

---

Based on https://codegolf.stackexchange.com/questions/173209/robot-roulette-high-stakes-robot-gambling