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
