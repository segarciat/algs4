# Exercise 2.4.29)

*Min/max priority queue*. Design a data type that supports the following
operations: *insert*, *delete the maximum*, and *delete the minimum* (all
in logarithmic time); and *find the maximum* and *find the minimum* (both
in constant time). *Hint*: Use two heaps.

## Solution

Using the hint, I realized I would need one max-oriented heap and one min-oriented
heap. My initial mistake was thinking I needed to keep some keys in one and
some keys in the other. Eventually I realized that the way to go was to keep all
items in both queues. I considered simply using `MinPQ` and `MaxPQ`. However,
though I would achieve the intended purpose in terms of the time requirement,
I realized it would mean I would pay the price in space. In particular, if
`delMin()` was called and the item were removed from the min-oriented
`MinPQ`, the item would still remain in the max-oriented priority queue.
This means it would not be eligible for garbage collection.

Therefore, I implemented the heaps just as it is done `MinPQ` and `MaxPQ`. To
ensure that deleting the item from one heap also led to deleting it from the
other, I used a nested class `Node` that held indices to where the key
was in either heap. This way, when either `delMin()` or `delMax()` is called,
the item is removed from both arrays. This solves the garbage collection issue.