# Exercise 1.5.17

*Random connections*. Develop a `UF` client `ErdosRenyi` that
takes an  integer value `n` from the command line, generates random pairs of
integers between `0` and `n-1`, calling `connected()` to
determine if they are connected and then `union()` if not (as in our
development client), looping until all sites are connected, and printing the number
of connections generated. Package your program as a static method `count()`
that takes `n` as an argument and returns the number of connections and
a `main()` that takes `n` from the command line, calls `count`,
and prints the returned value.