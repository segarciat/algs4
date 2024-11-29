# Exercise 3.1.22

*Self-organizing search*. A self-organizing search algorithm is one that
rearranges items to make those that are accessed frequently likely to be found
early in the search. Modify your search implementation for **Exercise 3.1.2**
to perform the following action on every search hit: move the key-value pair
found to the beginning of the list, moving all pairs between the beginning of the
list and the vacated position to the right one position. This procedure is called
the *move-to-front* heuristic.