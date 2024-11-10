# Exercise 2.4.30

*Dynamic median-finding*. Design a data type that supports *insert*
in logarithmic time, *find the median* in constant time, and *delete
the median* in logarithmic time. *Hint*: Use a min-heap and a max-heap.

## Solution

I solved this by storing the larger items in the min-pq and the smaller
items in the max-pq. There are no duplicate objects among the two queues;
the union of their elements makes up the entire collection. At any given
time, the sizes of min-pq and max-pq are at most 1 apart, which min-pq
potentially being the larger one. Also, min-pq always holds the median
at its root.