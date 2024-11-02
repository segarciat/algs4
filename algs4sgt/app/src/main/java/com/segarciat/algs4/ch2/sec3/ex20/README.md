# Exercise 2.3.20

*Non-recursive quicksort*. Implement a non-recursive version of quicksort based on
a main loop where a sub-array is popped from a stack to be partitioned, and the
resulting sub-arrays are pushed onto the stack. *Note*: Push the larger of the
sub-arrays onto the stack first, which guarantees that the stack will have at most
$\lg n$ entries.

## Solution

A sub-array is determined by its lowest and highest indices. Therefore, I used
a stack of integers where I pushed two integers each time. I thought of a stack
of 2-element arrays or even a custom class encapsulating the integer range,
but I decided to do away with it in order to avoid the cost of using `new`.