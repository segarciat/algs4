# Exercise 3.1.25

*Software caching*. Since the default implementation of `contains()` calls
`get()`, the inner loop of `FrequencyCounter`

```
if (!st.contains(word)) st.put(word, 1);
else                    st.put(word, st.get(word) + 1);
```

leads to two or three searches for the same key. To enable clear client code like
this without sacrificing performance, we can use a technique known as *software caching*,
where we save the location of the most recently accessed key in an instance variable.
Modify `SequentialSearchST` and `BinarySearchST` to take advantage of this idea.

## Solution

In the case of `SequentialSearchST`, I added a field `mostRecent` which `Node` initialized
to `null`. When a key is searched with `get()`, `mostRecent` is set the found `Node` value,
if any. To support the correctness and effectiveness of this scheme, I also updated
the `delete()` method. If the key of the node deleted matches the key of the cached
node, then `mostRecent` is set to `null`.

In `BinarySearchST`, I added a field `mostRecent` which is an index of type `int`
initialized to `-1`. Whenever there is a search hit in `get()`, `mostRecent`
is updated to the index of the key-value pair. In this implementation, I had to
also modify `put()` and `delete()`.

- In the case of `delete()`, if they key deleted
matches cached key, the `mostRecent` is set to `-1` to indicate that the
cached key is no longer present. Otherwise, if the cached key is to the right
of the deleted key in the array (meaning its rank is larger), then its value
is shifted down by 1.
- For `put()`, whenever a new key is inserted with a rank less than or equal
to the cached key, the cached key moves up, so I update `mostRecent` by
adding 1 to its value.

Together, these invariants ensure that when `mostRecent` is `-1`, the cached
key is not in the symbol-table, and when it is non-negative, it points to a valid
item in the symbol-table (which could be stale but is valid nonetheless).