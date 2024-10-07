# Exercise 1.4.43

*Resizing array versus linked lists*. Run experiments to validate the hypothesis
that resizing arrays are faster than linked lists for stacks (see Exercise 1.4.35
and Exercise 1.4.36). Do so by developing a version of `DoublingRatio`
that computes the ratio of the running times of the two programs.

## Solution

Here's a sample run of the ratios I observed:

```text
    250 Infinity
    500     NaN
   1000     NaN
   2000 Infinity
   4000     0.0
   8000 Infinity
  16000     2.0
  32000     0.5
  64000     1.0
 128000     1.3
 256000     3.7
 512000     0.4
1024000     1.2
2048000     0.5
4096000     3.7
8192000     3.5
16384000     3.0
32768000     2.3
65536000     3.0
```

The resizing implementation tends to run about 3 times
as fast as the linked list implementation.