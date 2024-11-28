# Exercise 3.1.9

Add code to `FrequencyCounter` to keep track of the *last* call to `put()`.
Print the last word inserted and the number of words that were processed in
the input stream prior to this insertion. Run your program for `tale.txt` with
length cutoffs of `1`, `8`, and `10`.

## Solution

Run from the `algs4sgt` directory as follows:

```bash
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch3.sec1.ex09.FrequencyCounter run --args='1' < app/algs4-data/tale.txt
```