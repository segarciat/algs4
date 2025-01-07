# Exercise 3.4.30

*Chi-square statistic*. Add a method to `SeparateChainingHashST` to compute the
$\chi^2$ statistic for the hash table. With $n$ keys and table size $m$, this
number is defined by the equation:

$$
\chi^2 = (m/n) \cdot ((f_0 - n/m)^2 + (f_1 - n/m)^2 + \cdots + (f_{m-1} - n/m)^2)
$$

where $f_i$ is the number of keys with has value $i$. This statistic is one way of
checking our assumption that the hash function produces random values. If so, this
statistic obeys a $\chi^2$ distribution with $m-1$ degrees of freedom, so its mean
is $m-1$, and its variance is $2\cdot (m-1)$.