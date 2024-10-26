# Exercise 2.3.6

Write a program to compute the exact value of $C_n$, and compare the exact value with
the approximation $2n\ln n$, for $n=100$, $1,000$, and $10,000$.

## Solution

Here's a sample run:

```text
n=100, actual=780, averageExact=747, averageExactToActualRatio=0.96, averageApprox=921, averageApproxToActualRatio=1.18
n=1000, actual=12129, averageExact=11985, averageExactToActualRatio=0.99, averageApprox=13816, averageApproxToActualRatio=1.14
n=10000, actual=160679, averageExact=165771, averageExactToActualRatio=1.03, averageApprox=184207, averageApproxToActualRatio=1.15
```

Notice the ratios are close to 1, suggesting the approximation is fairly accurate for the
run and these values of $n$.