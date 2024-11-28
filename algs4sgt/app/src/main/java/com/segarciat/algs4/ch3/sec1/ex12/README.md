# Exercise 3.1.12

Modify `BinarySearchST` to maintain one array of `Item` objects that contain keys
and values, rather than two parallel arrays. Add a constructor that takes an array
of `Item` values as argument and uses mergesort to sort the array.

## Solution

I purposely did not use `Merge.sort()` in the constructor because I would either
need to detect duplicate or null keys in the client's input (before or after).
I found this was unnecessarily tedious, so I instead used `put()` to add the
client's keys to the symbol table using the client's initializer array.