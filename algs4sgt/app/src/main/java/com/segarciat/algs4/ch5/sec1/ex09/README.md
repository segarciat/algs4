# Exercise 5.1.9

Develop an implementation of LSD string sort that works for variable-length strings.

## Solution

I eliminated the `w` function parameter that indicated the length of all strings,
and defined a local variable with the same name that is the length of the longest
string. Then I used a custom `charAt()` helper function just like in the `MSD`
implementation given in the book, which returns `-1` when a string index is out
of bounds. The rest of the implementation is a hybrid of `LSD` and `MSD` from
the book.