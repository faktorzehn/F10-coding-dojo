# Caveman Duels

Caveman need sharp stick to stab other caveman. Other caveman also try to stab with sharp stick. Caveman can sharpen stick, poke with stick, or block poky sticks.

If caveman poke other caveman with sharp stick, other caveman run away and me victory. But if other caveman smartly blocking when me poking, nothing happen except my stick become blunt and me need to sharpen again.

Caveman lazy. Also, caveman dumb. Caveman no know what to do, so caveman need fancy techno computer program to tell caveman what to do.

## Input
Your program's input will be a history of the events that have happened, where `S` stands for sharpen (i.e. the caveman sharpened his stick), `P` stands for poke, and `B` stands for block. The input will be a history of both sides (you and the opponent), so your and the opponent's moves will be separated with a comma (`,`).

Example input:

`SPB,SBB`

This means that the player sharpened his/her stick, then poked, then blocked, and the opponent sharpened, then blocked, then blocked again.

You will receive no input on turn 1.

## Output
The program must output one of the following letters:

### S: Sharpen
When sharpening, the caveman's stick's sharpness goes up by 1 and the stick gets 1 extra poke. Each poke reduces the stick's sharpness by 1, and if the stick's sharpness is 0, it's too dull to poke with. Sharpness starts at 0. If sharpness gets to 5, the stick is a sword! (See below.)

If the opponent pokes while you are sharpening (and they have a sharpness > 0), the opponent wins!

### P: Poke
When poking, the caveman's stick's sharpness goes down by 1 and you poke your opponent! If your opponent is sharpening, you win! If the opponent is poking, your stick hits your opponent's stick and they both get duller (by 1 "sharpness unit"). If the opponent is blocking, nothing happens except that your stick becomes duller.

If you poke when your stick's sharpness is 5 or greater, your stick becomes a sword and you _always_ win! (Unless your opponent also has a sword and also chose `P`; in that case, they both become duller, and may revert to sticks if their sharpness falls below 5.)

You cannot poke with a sharpness of 0. If you do, nothing will happen.


### B: Block
When you block, nothing happens when your opponent pokes. If your opponent is not poking, block does nothing.

Blocking does not protect against a sword, even if you also have one!

## Scoring

Scoring is easy. Whichever caveman wins gets a point. The caveman with the most points after 3 duels against every other caveman becomes the new caveman leader!

## Additional Rules
The caveman judge is not patient. If the cavemen take more than 100 turns each to decide a winner, the judge gets bored and both cavemen lose.

## Hints
Blocking while your opponent has 0 sharpness (or >= 5) makes little sense.
You should probably sharpen on your first turn, unless you have a really good idea.

Copied from: https://codegolf.stackexchange.com/questions/34968/caveman-duels-or-me-poke-you-with-sharp-stick
