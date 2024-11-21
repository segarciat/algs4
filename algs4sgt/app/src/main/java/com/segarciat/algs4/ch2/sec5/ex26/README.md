# Exercise 2.5.26

*Simple polygon*. Given $N$ points in the plane, draw a simple polygon with the $N$
points as vertices. *Hint*: Find the point *p* with the smallest $y$ coordinate, breaking
ties with the smallest $x$-coordinate. Connect the points in increasing order of the
polar angle they make with $p$.

## Solution

I used my `Point2D` implementation from Exercise 25 in order to leverage the comparator.