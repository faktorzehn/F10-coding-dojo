# Potter Kata

A bookstore has the first five Harry Potter books for sale.

One copy of any of the five books costs 8 EUR. If, however, you buy two different books from the series, you get a 5% discount on those two books. If you buy 3 different books, you get a 10% discount. With 4 different books, you get a 20% discount. If you go the whole hog, and buy all 5, you get a huge 25% discount.

|distinct books| discount (%)|
|-|-|
|2|5|
|3|10|
|4|20|
|5|25|

Note that if you buy, say, four books, of which 3 are different titles, you get a 10% discount on the 3 that form part of a set, but the fourth book still costs 8 EUR.

## Input & Goal

Given a list of bought books, calculate the minimum price (apply the highest possible discount).

`[1 2]` -> 15.2

`[1 1 2]` -> 23.2

`[1 2 4 5]` -> 25.6

`[1 2 3 4 5]` -> 30

`[1 2 2 3 3 3]` -> 44.8

`[1 1 1 2 2 2 3 3 4 4 5 5]` -> 75.2

Critical:

`[1 1 2 2 3 3 4 5]` -> 51.2

Reworded from http://codingdojo.org/kata/Potter/
