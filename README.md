# Algorithms 4th Edition

This repository contains my solutions to exercises from the book *Algorithms* (4th edition)
by Robert Sedgewick and Kevin Wayne; [click here to visit the book website](https://algs4.cs.princeton.edu/home/).

My first pass through this book was in 2020 when I purchased it to self-study data structures and
algorithms, but I did not finish Chapters 5 and 6. I decided to give it another pass with the following
goals in mind:

1. Refresh my knowledge on fundamental data structures and algorithms.
2. Practice Java.
3. Practice basic unit testing.
4. Prepare to self-study operating systems.

The time was ripe because I returned to college to pursue a B.S. in computer science,
and I wanted a good reason to practice Java to supplement my Java course.

## Repo Structure

- The `pdf` folder consists of files typeset with LaTeX and the compiled PDFs.
These are my solutions to exercises from the book that do not require writing
a computer programming. This innermost directories (shall I call them "leaf directories")
contain the `.tex` and `.pdf` files for each section.
- The `algs4sgt` directory is a Gradle project containing all of the source code
that I wrote to complete exercises from the book.

## Companion Site

The [book companion site](https://algs4.cs.princeton.edu/home/) has resources used
throughout the book exposition and sometimes necessary for exercises:

- `algs4.jar`: Library of static methods and data-type definitions for methods, data structures,
and other programs developed throughout the book. The library is available for
[download from the book site](https://algs4.cs.princeton.edu/code/).
- `algs-data`: Test data files used by the authors. [Download from the book site](https://algs4.cs.princeton.edu/code/).
This folder is about 1.1G, so I have added to my `.gitignore`.
