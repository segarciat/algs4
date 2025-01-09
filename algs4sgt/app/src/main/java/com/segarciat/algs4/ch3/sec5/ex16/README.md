# Exercise 3.5.16

Add a method `sum()` to `SparseVector` that takes a `SparseVector` as argument
and returns a `SparseVector` that is the term-by-term sum of this vector and
the argument vector. *Note*: You need `delete()` (and special attention to
precision) to handle the case when an entry becomes 0.