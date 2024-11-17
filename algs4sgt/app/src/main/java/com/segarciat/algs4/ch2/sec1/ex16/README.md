# Exercise 2.1.16

*Certification*. Write a `check()` method that calls `sort()` for a given array and
returns `true` if `sorted()` puts the array in order *and* leaves the same set of
objects in the array as were there initially, `false` otherwise. Do not assume that
`sort()` is restricted to move data only with `exchange()`. You may use `Arrays.sort()`
and assume that it is correct.

## Solution

I concluded that if there was a different set of objects in the sorted array
after the sort, then at least one of the objects in the sorted array would
not match an object in the original array. Therefore, I created a copy to
maintain the original array, and did this check using `==` for object identity
rather than `.equals()`.