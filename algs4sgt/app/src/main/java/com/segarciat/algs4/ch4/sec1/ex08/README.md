# Exercise 4.1.8

Develop an implementation for the `Search` API on page 528
that uses `UF`, as described in the text.

## Solution

First I created the `WeightedQuickUnion` data type as discussed in Section 1.5,
and then I extended its API with an overloaded `int count(int compId)` that
returns the size of a component of the given identifier. Then I used this
union-find structure in the `Search` implementation. I also copied the book's
`TestSearch` client to test the implementation, and ran it on `tinyG.txt`:

```bash
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch4.sec1.ex08.TestSearch run --args='algs4-data/tinyG.txt 0'
```

Sample output:

```text
0 1 2 3 4 5 6 
not connected
```