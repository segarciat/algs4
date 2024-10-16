# Exercise 2.2.9

Use of a static array like `aux[]` is inadvisable in library software because
multiple clients might use the class concurrently. Give an implementation of
`Merge` that does not use a static array. Do *not* make `aux[]` local to `merge()`
(see the Q & A for this section). *Hint*: Pass the auxiliary array as an argument
to the recursive `sort()`.