# Exercise 3.5.12

Modify `LookupCSV` to associate with each key all values that appear
in key-value pairs with that key in the input (not just the most recent,
as in the associative-array abstraction).

## Solution

Arranging for this was simple; simply use a `Queue` for the value of the symbol
table. While building the symbol table, fetch the queue when the key is encountered,
and enqueue the associated value. While responding to queries, fetch the queue and
display all items in it.

Here's a sample run using `sample.txt`:

```bash
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch3.sec5.ex12.LookupCSV run --args="src/main/java/a/com/segarciat/algs4/ch3/sec5/ex12/sample.txt 0 1"
```

Here's what the interactive session looked like (the lines starting with `#` were not actually
inputted during the interaction):

```text
# input
fish
# output
pescatarian,
# input 
peanut
# output
vegan, allergen,
# input 
milk
# output
calcium, allergen, 
beef
red-mean,
```