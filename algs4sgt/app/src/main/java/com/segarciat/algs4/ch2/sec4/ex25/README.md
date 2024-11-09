# Exercise 2.4.25

*Computational number theory*. Write a program that prints out all integers of
the form $a^3+b^3$ where $a$ and $b$ are integers between $0$ and $n$ in sorted
order, without using excessive space. That is, instead of computing an array
of the $n^2$ sums and sorting them, build a minimum-oriented priority queue,
initially containing $(0^3, 0, 0), (1^3, 1, 0), (2^3,2,0),\ldots,(n^3,n,n)$.
Then, while the priority queue is nonempty, *remove the smallest item*
$(i^3+j^3, i, j)$, print it, and then, if $j < n$, *insert* the item
$(i^3 + (j+1)^3, i, j+1)$. Use this program to find all distinct integer
$a$, $b$, $c$, and $d$ between $0$ and $10^6$ such that $a^3+b^3=c^3+d^3$.

## Solution

To solve this problem, I defined a simple class `CubedSumPair` containing
$i$, $j$, and $i^3+j^3$. In it, I implemented a `compareTo()` method
such that any two objects are compared by the value of $i^3+j^3$.
In addition to the condition that $j < n$ for adding the next pair, I also
imposed that $i < j$, so that the pairs printed always satisfy
$(i^3+j^3, i, j)$ with $i\leq j$. This ensures we do not have duplicates
of the form $(a,b)$ and $(c, d)$ with $a=c$ and $b=d$ (meaning the same
two numbers but in different order).