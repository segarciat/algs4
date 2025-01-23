# Exercise 5.1.7

Develop an implementation of key-indexed counting that makes use of an
array of `Queue` objects.

## Solution

One challenge was deciding how big to make the array. I presumed that
all keys in a given queue would be the same, and that keys in different
queues would be distinct. Then I would sort the queues according to
the key they contain, and dequeue until all queues are exhausted.
However, a challenge would be to efficiently find a queue corresponding
to a given key.

It was not clear to me whether the keys should be `String`, `int`, or
of any given type. Therefore, I assumed they were meant to be `int`,
and had the client specify the value of `R`, indicating that values
in the array are in the interval `[0, R-1]`. This may not be what the
authors had in mind, but I can't ascertain what the intention is.