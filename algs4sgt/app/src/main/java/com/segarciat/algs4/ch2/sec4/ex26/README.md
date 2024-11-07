# Exercise 2.4.26

*Heap without exchanges*. Because the `exchange()` primitive is used in the `sink()`
and `swim()` operations, the items are loaded and stored twice as often as necessary.
Give more efficient implementations that avoid this inefficiency, a la insertion sort.
(see Exercise 2.1.25).

## Solution

In `swim()`, we bring smaller parents down; in `sink()`, we bring larger
children up. In both, we save the element that is sinking or swimming
before the loop begins, and set it after the loop ends.