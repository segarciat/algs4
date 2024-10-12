# Exercise 1.5.22

*Doubling test for Erdos-Renyi model*.  Develop a performance-testing client that takes
an`int`  value `T` from the command line and performs `T` trials of the following experiment:
Use your client from Exercise 1.5.17 to generate random connections, using `UF` to determine
connectivity as in our development client, looping until all sites are connected. For each
`n`, print the value of `n`, the average number of connections processed, and the ratio
of the running time to the previous. Use your program to validate the hypothesis in the
text that the running times for quick-find and quick-union are quadratic and weighted
quick-union is near-linear.