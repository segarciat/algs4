# Exercise 4.3.16

Given an MST for an edge-weighted graph $G$ and a new edge $e$, write a program
that determines the range of weights for which $e$ is in an MST. 

## Solution

For this exercise, I combined the samples on page 547 for detecting a cycle
in an undirected graph with page 577 for finding a cycle in a directed graph
to find a cycle containing the new edge $e$. Then I found the weight of the
largest edge in the cycle (skipping $e$). This determines the maximum value
for $e$ in order to allow it to be in the MST.