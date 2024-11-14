# Exercise 2.5.9

Develop a data type that allows you to write a client that can sort a file such as
the one shown below:

```text
# input (DJIA volumes for each day)
 1-Oct-28	3500000
 2-Oct-28	3850000
 3-Oct-28	4060000
 4-Oct-28	4330000
 5-Oct-28	4360000
...
30-Dec-99	554680000
31-Dec-99	374049984
 3-Jan-00	931800000
 4-Jan-00	1009000000
 5-Jan-00	1085500032
 
# output
  19-Aug-40 130000
  26-AUg-40	160000
  24-Jul-40	200000
  10-Aug-42 210000
  23-Jun-42 210000
  ...
  23-Jul-02 2441019904
  17-Jul-02 2566500096
  15-Jul-02 2574799872
  19-Jul-02 2654099968
  24-Jul-02 2775555936
```

## Solution

The data appears to be sorted by DJIA volume and hten by date, so I implemented
a `compareTo()` method that would lead to such a sort.