# Exercise 3.4.18

Add a constructor to `SeparateChainingHashST` that gives the client the ability
to specify the average number of probes to be tolerated for searches. Use array resizing
to keep the average list size less than the specified value, and use the technique described
on page 478 to ensure that the modulus for `hash()` is prime.

## Solution

The exercise refers to page 478, which is the Q & A section of this chapter, but there
is nothing related to prime modulus there. The website has a Q & A that mentions Mersenne
primes. When resizing the array, I decided to use the smallest Mersenne prime that is
larger than the current table size. All Mersenne primes have the form $2^p-1$ where $p$
is some prime (though not all numbers of that form are prime). Since the largest integer
table size in Java would be `Integer.MAX_VALUE`, which has value $2^{31}-1$ (guaranteed
by the fact that Java represents signed integers using 2's complement), I decided to
simply store the list of possible sizes in an array.