# Exercise 4.4.7

Develop a version of `DijkstraSP` that supports a client method that returns
a *second* shortest path from `s` to `t` in an edge-weighted digraph (and returns
`null` if there is only one shortest path from `s` to `t`).

## Solution

To implement this, I maintain a second array `secondEdgeTo`. It's possible that
it contains the same paths as the `edgeTo`. If it does not, however, then we have
an alternate path. One difficulty I encountered is round-off error. I created
a file that should have had multiple paths, but because of floating-point comparison
with equals (i.e. round-of errors), it was not being reported as such. I decided to
use a threshold `EPSILON` to address this.

I created a modified version of `tinyEWD.txt` from the text named `tinyEWD2.txt`
where edge `7->5` now has a weight of `0.13`. This was to have an alternate path
`0 to 5`, `0->4->5` and `0->2->7->5`, both of weight `0.73` (subject to rounding error).

To see this, run:

```bash
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch4.sec4.ex07.DijkstraSP run --args='src/main/java/com/segarciat/algs4/ch4/sec4/ex07/tinyEWD2.txt 0'
```

The observed output was:

```text
0 to 0 (0.00): 
0 to 1 (1.05): 0->4  0.38   4->5  0.35   5->1  0.32   
0 to 1 (1.05): 0->2  0.26   2->7  0.34   7->5  0.13   5->1  0.32   
0 to 2 (0.26): 0->2  0.26   
0 to 3 (0.99): 0->2  0.26   2->7  0.34   7->3  0.39   
0 to 4 (0.38): 0->4  0.38   
0 to 5 (0.73): 0->4  0.38   4->5  0.35   
0 to 5 (0.73): 0->2  0.26   2->7  0.34   7->5  0.13   
0 to 6 (1.51): 0->2  0.26   2->7  0.34   7->3  0.39   3->6  0.52   
0 to 7 (0.60): 0->2  0.26   2->7  0.34 
```