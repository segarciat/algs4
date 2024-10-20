# Exercise 2.2.14

`Bottom-up queue mergesort`. Develop a bottom-up mergesort implementation based
on the following approach: Given *n* items, create *n* queues, each containing one
of the items. Create a queue of the *n* queues. Then, repeatedly apply the merging
operation of Exercise 2.2.14 to the first two queues and reinsert the merged queue
at the end. Repeat until the queue of queues contains only one queue.