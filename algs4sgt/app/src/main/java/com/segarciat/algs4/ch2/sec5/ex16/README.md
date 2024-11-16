# Exercise 2.5.16

*Unbiased election*. In order to thwart bias against candidates whose names appear toward the end of the alphabet,
California sorted the candidates appearing on its 2003 gubernational ballot by using the following order of
characters:

```text
R W Q O J M V A H B S G Z X N T C I E K U P D Y F L
```
Create a data type where this is the natural order and write a client `California` with a
single static method `main()` that sorts strings according to this  ordering. Assume that each
string is composed solely of uppercase letters.

## Solution

Admittedly my approach was more complicated than necessary. The book site provides a solution
in which they place the 26 letters in a string, and then use the `indexOf()` method of `String`
objects. Nevertheless, my implementation achieves the goal.