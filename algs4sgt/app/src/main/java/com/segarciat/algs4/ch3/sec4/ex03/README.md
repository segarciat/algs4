# Exercise 3.4.3

Modify your implementation of the previous exercise to include an integer field
for each key-value pair that is set to the number of entries in the table at
the time that pair is inserted. Then implement a method that deletes all keys
(and associated values) for which the field is greater than a given integer
`k`. *Note*: This extra functionality is useful in implementing the symbol table
for a compiler.