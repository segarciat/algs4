# Exercise 4.2.17

How many strong components are there in the digraph on page 591?

## Solution

Run:

```bash
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch4.sec2.ex17.ClientKosarajuSharirSCC run --args='algs4-data/mediumDG.txt'
```

Output:

```text
10 strong connected components
```