# Honest Rock Paper Scissors

Santa Claus has arrived in town, but he has only brought a single present!
"You will have to fight for it", he laughs sadistacally. 
The game is Rock Paper Scissors with a twist: Both players announce simultaneously which move they are going to play. Then they choose the move they are actually going to play. You may play a different move than announced, but Santa rewards honesty.

## Implementation
Clone this repository and open the honest-rock-paper-scissors maven project.
You must implement the `Bot` Interface.
A new instance of your class is created for every match (one match beeing 10000 rounds between two bots). 
At the start of a round, the `announce` method is called. Then the `play` method is called with the move your opponent has announced. You must keep track of previous rounds on your own, if you wish to do so. 

## Scoring
Each bot plays against every other bot 10000 times and scores are summed up.
The bot with the most points wins.

|   |Honest   | Dishonest  |
| ------------ | ------------ | ------------ |
| Win  |3   |2   |
|Draw   |2   |1   |
|Loss   |1   | 0  |
Throwing exeptions, returning null, or performing very slow calculations puts you on Santa's naughty list.

------------

Adapted from https://codegolf.stackexchange.com/questions/145175/honest-rock-paper-scissors by [Gryphon](https://codegolf.stackexchange.com/users/69331/gryphon)