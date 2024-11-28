# Exercise 3.1.8

What is the most frequently used word of ten letters or more in *Tale of Two Cities*?

## Solution

I ran the program in the provided jar from the top-level directory of the repository as follows:

```bash
java -cp ./algs4sgt/app/libs/algs4.jar edu.princeton.cs.algs4.FrequencyCounter 10 < algs4sgt/app/algs4-data/tale.txt
```

The output was:

```text
monseigneur 101
distinct = 2257
words    = 4579
```

Hence, the most frequently-used word is `monseigneur`. 