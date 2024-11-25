# Exercise 2.5.29

*Sort files by size and date of last modification*. Write comparators for the type `File`
to order by increasing/decreasing order of the file size, ascending/descending order of
file name, and ascending/descending order of last modification date. Use these comparators
in a program `LS` that takes a command-line argument and lists the file in the current
directory according to specified order, e.g., `"-t` to sort by timestamp. Support multiple
flags to break ties. Be sure to use a stable sort.