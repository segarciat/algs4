# Exercise 1.5.18

*Random grid generator*. Write a program `RandomGrid` that takes an `int` value
`n` from the command line, generates all the connections in an `n`-by-`n` grid, puts
them in random order, randomly orients them (so that `p` and `q` and `q` and `p` are
equally likely to occur), and prints the result to standard output. To randomly order
the connections, use a `RandomBag` (see Exercise 1.3.34 on page 167). To encapsulate
`p` and `q` in a single object, use the `Connection` nested class shown below.
Package your program as two static methods: `generate()`, which takes `n` as argument
and returns an array of connections, and `main()`, which takes `n` from the command
line, calls `generate()`, and iterates through the returned array to print the
connections.