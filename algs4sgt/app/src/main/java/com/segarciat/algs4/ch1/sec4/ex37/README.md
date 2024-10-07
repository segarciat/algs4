## Solution

The results for large $n$ that I observed were:

```text
    250     NaN
    500     NaN
   1000     NaN
   2000     NaN
   4000     NaN
   8000 Infinity
  16000 Infinity
  32000     1.0
  64000     2.0
 128000     3.0
 256000 Infinity
 512000 Infinity
1024000    17.0
2048000    42.0
4096000    40.0
8192000    31.4
16384000    55.5
32768000    47.2
65536000    31.8
131072000    61.3
```

Thus, the penalty for autoboxing is approximately upwards of 45 times
as long on my machine, for large $n$.