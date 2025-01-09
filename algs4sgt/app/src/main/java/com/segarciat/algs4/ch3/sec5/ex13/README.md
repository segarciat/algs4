## Solution

Since the `ST` does not provide the two-argument `keys()` method, I changed
the symbol table to `RedBlackBST`, which does support this operation directly.

We can run the program as follows:

```bash
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch3.sec5.ex13.LookupCSV run --args="./algs4-data/amino.csv 0 3"
```

Here's a sample interaction (the lines starting with `#` were not actually inputted
during the interaction):


```text
# Input
TTA TTM 
# Output
TTA, Leucine
TTC, Phenylalanine
TTG, Leucine
# Input
TTM TTA
# No output
```