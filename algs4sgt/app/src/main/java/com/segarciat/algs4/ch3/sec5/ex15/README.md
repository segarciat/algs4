# Exercise 3.5.15

Write a program that takes a string on standard input and an integer $k$
as command-line argument and puts on standard output a sorted list of the
$k$-grams (substrings of length $k$) found in the string, each followed by
its index in the string.

## Solution

This is an application of the `substring()` method, and the use of an ordered
symbol table. To run the program, issue the command:

```bash
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch3.sec5.ex15.KGrams run --args="tacocat 4"
```

Sample output:

```text
acoc: 1
coca: 2
taco: 0
```