# Exercise 4.4.9

The table below, from an old published road map, purports to give the length
of the shortest routes connecting the cities. It contains an error. Correct
the table. Also, add a table that shows how to achieve the shortest routes.

```text
     Providence:       -      53      54      48 
       Westerly:      53       -      18     101 
      NewLondon:      54      18       -      12 
        Norwich:      48     101      12       -
```

## Solution

I created a `DijkstraSP` client to find the shortest paths from
the given information. I ran it as follows:

It produced the following output:;

```text
     Providence:       -      53      54      48 
       Westerly:      53       -      18      30 
      NewLondon:      54      18       -      12 
        Norwich:      48      30      12       -
```

I concluded that the entry claiming the distance from `Norwich`
to `Westerly` is incorrect.