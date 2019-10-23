## Tennis Scoring System

Implement a scoring system for tennis according to the rules below. Given a string consisting of "A" and "B" calculate the current score or whoever wins the match.

### A Game (round)
   * The scores are 0, 15, 30, 40
   * If a player with 40 scores a point, she wins the game
   * However, if both players have 40 (deuce), the scoring player gains advantage (but does not win the game)
     * If a player with advantage scores a point, she wins the game
     * If a player without advantage scores a point, the score reverts back to 40-40 (deuce)

##### Sample inputs

* "ABAAB" 40-30
* "ABABABABB" 40-advantage
* "ABAABA" A wins the game

### A Set
   * The scores are 0-7
   * If a player reaches 6 points and two more points than the opponent, she wins the set
   * However, if a player reaches 6 points but the opponent has 5 or 6 points, a final seventh round (tie-breaker) is played
   * Whoever wins the seventh round (tie-breaker) wins the set

##### Sample inputs

* "ABAABAAABBBBABAABABBBBBABABBABBABABABBBABABABABABABABABB" 2-5
* "ABABABBABABABABABABBBABAAAAAAAAAAAAAAABBBBBBBBBBBBBBBBBBBB" 4-6
* "ABAABAAABBBBABAABABBBBBABABBABBABABABBBABABABABABABABABBAABABAABABAAAAABABAABABAABBBBBBBB" 6-7
* "ABAABABAAABABAAABABAAABAABABABAABAA" 5-0 (30-15)

### A Match
   * Whoever wins 2 sets wins the match (best of three)

##### Sample Inputs

* "ABAABAAAAABBBBAAAAAAAAAAAAAAAABBBBBBBBABAABBAAAABBBBBBBBBBBABBBABABBAABABAAAAAAAAABAABABABAAAAAAAAAA" A wins (6-1, 1-6, 6-0)
* "AAAAAAAAAAAABBBAAABBBBBAAABBBBBAAABBBBBAAABBBBBAAABBBBBAAABBAAAAAAAAAAAAAAAABABABABABA" 3-6 4-0 (40-40)
* "AAAAAAAAAAAAAAAAAAAAAAAABBBBBBBBBBBBBBBBBBBBBBBBAAAAAAAAAAAAAAAAAAAABBBBBBBBBBBBBBBBBBBBAAAABBBBAAABBB" 6-0 0-6 6-6 (40-40), whoever scores two more points than the opponent wins
* "ABAABAAAAABBBBAAAAAAAAAAAAAAAABBBBBBBBABAABBAAAABBBBBBBBBBBABBBABABBAABABAAAAAAAAABAABABABAAAAAAAAAABBBBBBB" ...to be continued

Source: [Tennis Kata](http://codingdojo.org/kata/Tennis/)

