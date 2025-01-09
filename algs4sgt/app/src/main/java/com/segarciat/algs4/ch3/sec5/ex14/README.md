# Exercise 3.5.14

Develop and test a static method `invert()` that takes as argument an
`ST<String, Bag<String>>` and produces as return value the inverse
of the given symbol table (a symbol table of the same type).

## Solution

I included a test client main associates with each number a set of
properties. The symbol table therefore lists the numbers that satisfy
that given property.

Here is a sample output:

```text
Original symbol table:
-1.5: real rational negative
1: real rational integer positive
pi: real irrational positive

Inverted Table
integer: 1
irrational: pi
negative: -1.5
positive: pi 1
rational: 1 -1.5
real: pi 1 -1.5
```

