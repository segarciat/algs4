# Exercise 3.2.13

Give non-recursive implementations of `get()` and `put()` for `BST`. The implementation
of `put()` is more complicated because of the need to save a pointer to the parent node to
link in the new noe at the bottom. Also, you need a separate pass to check whether the key
is already in the table because of the need to update the counts. Since there are many
more searches than inserts in performance-critical implementations, using this code
for `get()` is justified; the corresponding change for `put()` might not be noticed.