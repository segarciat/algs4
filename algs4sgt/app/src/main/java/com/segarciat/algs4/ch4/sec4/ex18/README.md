# Exercise 4.4.18

Write a `CPM` client that prints all critical paths.

## Solution

I extended the `main` method in the `CPM` class from the book to
display the critical paths. The critical path length is precisely
the length of the path from the source to the sink. Since each
job has a start and end time, I searched for the job whose end time
matches the path length to the sink from the source. I also noticed
the `jobsPC.txt` file in the text is different from the one in the
source code distribution; in the latter, an extra field follows
the duration specifying the number of precedence constraints. I just
had to change `j` to start at `2` in the loop that built the graph
to skip that field.

Running the program as follows:

```bash
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch4.sec4.ex18.CPM run < app/algs4-data/jobsPC.txt
```

yields

```text
   0:   0.0
   1:  41.0
   2: 123.0
   3:  91.0
   4:  70.0
   5:   0.0
   6:  70.0
   7:  41.0
   8:  91.0
   9:  41.0
Finish time: 173.0
Critical paths: 
0 (41.0) -> 9 (29.0) -> 6 (21.0) -> 8 (32.0) -> 2 (50.0) -> 
```