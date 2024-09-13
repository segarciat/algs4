# algs4sgt

This directory contains the source code I wrote as solutions to questions in
*Algorithms* (4th Edition) by Robert Sedgewick and Kevin Wayne. I set the directory
as a Gradle project that uses the `application` plugin. For more details related
to Gradle, see `SETUP.md`.

I decided on the following structure to enable me to move quickly:

- All source code is in the `com.segarciat.algs4` package.
- The source code for a given section in a specific subpackage that is specific to the
Chapter and Section. For example, my solution to Exercise 3 from Section 1.1  (that is,
exercise 1.1.3) has path `app/src/main/java/com/segarciat/algs4/ch1/sec1/ex01/Compare3Integers.java`.
- Some classes have corresponding unit tests in `app/src/test/**/*`.