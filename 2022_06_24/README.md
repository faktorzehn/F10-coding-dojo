# Can the 🐸 visit all the lily pads?

In this challenge you will be simulating a frog jumping from lily pad to lily pad in a pond. A frog's jump distance is uniquely determined by the size of the lily pad it jumps from. So for example there are lily pads that let a frog jump 1 unit, lily pads that let a frog jump 2 units etc. A frog can never jump more or less than the allowed amount, nor can it jump out of the pond, but it can jump in either direction.

So we will represent a lily pad by the number of units it allows a frog to jump. This number is always positive. We will then represent a pond as a list of lily pads.

Our question is then: <b>If a frog starts on the first lily pad, can they visit every lily pad in the pond by following the jumping rules?</b>

## Examples

If we have the following pond, the answer is yes:
<pre>
[2, 3, 1, 4, 1]
 🐸

[2, 3, 1, 4, 1]
       🐸

[2, 3, 1, 4, 1]
    🐸

[2, 3, 1, 4, 1]
             🐸

[2, 3, 1, 4, 1]
          🐸
</pre>

However for the following pond the answer is no:
<pre>
[3,2,1,2,1,2]
</pre>
The frog can never reach any lily pad labeled with a 1.

The frog is allowed to visit the same lily pad more than once. The following example requires it:
<pre>
[2, 1, 1, 1]
 🐸

[2, 1, 1, 1]
       🐸

[2, 1, 1, 1]
    🐸

[2, 1, 1, 1]
       🐸

[2, 1, 1, 1]
          🐸
</pre>
Some lily pads are dead ends and need to be visited last for example:
<pre>
[2,3,1,1]
</pre>
Here there is nowhere to go from 3, so that has to be the final pad.

## Test cases
Possible
<pre>
[10]
[2,1,1,1]
[3,1,4,2,2,1]
[6,1,1,1,1,1,3]
[2,3,1,1]
[2,2,1,2]
[8,9,1,5,2,5,1,7,4]
</pre>
Impossible
<pre>
[2,1]
[3,2,1,2,1,2]
[3,2,2,2,2,2]
[3,4,1,2,1,1]
[2,9,1,9]
[3,3,3,1,3,3]
</pre>

---

Source: https://codegolf.stackexchange.com/questions/248577/can-the-visit-all-the by [Wheat Wizard](https://codegolf.stackexchange.com/users/56656/wheat-wizard)
