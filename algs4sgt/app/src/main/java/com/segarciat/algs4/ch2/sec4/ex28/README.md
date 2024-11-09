# Exercise 2.4.28

*Selection filter*. Write a program similar to `TopM` that reads points $(x, y, z)$
from standard input, takes a value $m$ from the command line, and prints the $m$
points that are closest to the origin in Euclidean space. Estimate the running time
of your client for $n=10^8$ and $m=10^4$.

## Solution

Similar the `TopM` client in the book, my implementation uses a priority queue
with $m+1$ items. However, since we are interested in the $m$ closest points,
I used a max-oriented queue. This way, each time the queue has $m+1$ items,
the largest is removed, and the remaining items in the queue are the $m$ smallest.
See my proof of the statement in Exercise 2.4.17, which shows why this does
what is expected. The version of `MaxPQ` provided with the distribution of the
book has a version that accepts a `Comparator`, so I leveraged this to impose
the order on the priority queue based on the distance of each point to the origin.
For the points, I implemented simple record class `Point3D`.

Here's a way to run this program:

```text
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch2.sec4.ex28.ClosestMPoints run --args='3'
```