# Exercise 3.3.23

*2-3 trees without balance restriction*. Develop an implementation of the basic
symbol-table API that uses 2-3 trees that are not necessarily balanced as the underlying
data structure. Allow 3-nodes to lean either way. Hook the new node onto the bottom with
a *black* link when inserting into a 3-node at the bottom. Run experiments to develop
a hypothesis estimating the average path length in a tree built from $n$ random insertions.

## Solution

To implement the idea that a black link should be used when inserting into a 3-node at the
bottom, I modified `put()` to take an argument Boolean argument `parentColor` that represents 
whether the parent is a 2-node or 3-node. Based on this color I decided whether to make the
new link red or black.

Since 2-3 trees correspond to red-black trees, the depth of any node corresponds to the
black depth of that node. That is, the number of black link encountered on a path
to it from the root. To implement this, I introduced a new field `blackDepth` in each node.
Since I have chosen to not implement `delete()` (given that  it is not necessary for this
experiment), and there are no rotations, the depth of all nodes is determined at insertion,
and it never changes after that.

Below are results from a sample run:

```text
n=128, averagePathLength=3.0, log_2(n)=7.0, log_3(n)=4.4
n=256, averagePathLength=3.5, log_2(n)=8.0, log_3(n)=5.0
n=512, averagePathLength=4.7, log_2(n)=9.0, log_3(n)=5.7
n=1024, averagePathLength=5.0, log_2(n)=10.0, log_3(n)=6.3
n=2048, averagePathLength=6.0, log_2(n)=11.0, log_3(n)=6.9
n=4096, averagePathLength=6.5, log_2(n)=12.0, log_3(n)=7.6
n=8192, averagePathLength=7.4, log_2(n)=13.0, log_3(n)=8.2
n=16384, averagePathLength=7.9, log_2(n)=14.0, log_3(n)=8.8
n=32768, averagePathLength=9.0, log_2(n)=15.0, log_3(n)=9.5
n=65536, averagePathLength=9.3, log_2(n)=16.0, log_3(n)=10.1
n=131072, averagePathLength=9.7, log_2(n)=17.0, log_3(n)=10.7
n=262144, averagePathLength=10.8, log_2(n)=18.0, log_3(n)=11.4
n=524288, averagePathLength=11.3, log_2(n)=19.0, log_3(n)=12.0
n=1048576, averagePathLength=12.2, log_2(n)=20.0, log_3(n)=12.6
n=2097152, averagePathLength=12.7, log_2(n)=21.0, log_3(n)=13.2
n=4194304, averagePathLength=14.2, log_2(n)=22.0, log_3(n)=13.9
n=8388608, averagePathLength=14.1, log_2(n)=23.0, log_3(n)=14.5
```

I hypothesize from these values that the average path length is about
$\log_3(n)$.