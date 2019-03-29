## The Water Filling Algorithm

The input to the algorithm is an arbitrary sequence of integers, e.g.

[2 5 1 2 3 4 7 7 6]

Each integer represents the height of the terrain (viewed from the side) at that position.

| | | | | | | | | |
|-|-|-|-|-|-|-|-|-|
| | | | | | |#|#| |
| | | | | | |#|#|#|
| |#| | | | |#|#|#|
| |#| | | |#|#|#|#|
| |#| | |#|#|#|#|#|
|#|#| |#|#|#|#|#|#|
|#|#|#|#|#|#|#|#|#|

When rain falls it accumulates in puddles.

| | | | | | | | | |
|-|-|-|-|-|-|-|-|-|
| | | | | | |#|#| |
| | | | | | |#|#|#|
| |#|O|O|O|O|#|#|#|
| |#|O|O|O|#|#|#|#|
| |#|O|O|#|#|#|#|#|
|#|#|O|#|#|#|#|#|#|
|#|#|#|#|#|#|#|#|#|

Calculate the maximum volume of accumulated water (number of "O"s).

This particular example should yield 10 as a result.

[2 5 1 2 3 4 7 7 6]
=> 10

### More Examples

[5 3 1 7 1 8 2 3 4] => 15

| | | | | | | | | |
|-|-|-|-|-|-|-|-|-|
| | | | | |#| | | |
| | | |#|O|#| | | |
| | | |#|O|#| | | |
|#|O|O|#|O|#| | | |
|#|O|O|#|O|#|O|O|#|
|#|#|O|#|O|#|O|#|#|
|#|#|O|#|O|#|#|#|#|
|#|#|#|#|#|#|#|#|#|

----------------------------------------------

[5 3 1 3 -1 4 2 3 6] => 20

| | | | | | | | | |
|-|-|-|-|-|-|-|-|-|
| | | | | | | | | |
| | | | | | | | | |
| | | | | | | | |#|
|#|O|O|O|O|O|O|O|#|
|#|O|O|O|O|#|O|O|#|
|#|#|O|#|O|#|O|#|#|
|#|#|O|#|O|#|#|#|#|
|#|#|#|#|O|#|#|#|#|
|#|#|#|#|O|#|#|#|#|
|#|#|#|#|#|#|#|#|#|

Hint: Reversed input should yield the same result