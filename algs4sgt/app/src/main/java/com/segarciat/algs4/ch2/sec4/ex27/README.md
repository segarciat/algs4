# Exercise 2.4.27

*Find the minimum*. Add a `min()` method to `MaxPQ`. Your implementation
should use constant time and constant extra space.

## Solution

The change was simple. We maintain an extra field `minKey`. On `insert()`,
we update `minKey` if the key being inserted is smaller than `minKey`;
on `delMax()`, we set `minKey` to `null` if the queue becomes empty.