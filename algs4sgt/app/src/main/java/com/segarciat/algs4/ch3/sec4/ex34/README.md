# Exercise 3.4.34

*Hash cost*. Determine empirically the ratio of the time required for `hash()`
to the time required for `compareTo()`, for as many commonly-used types of
keys for which you can get meaningful results.

## Solution

The types are chose were `Integer`, `Double`, `String` and `Instant`. I arranged
for a random value to be hashed or compared. In the case of `String`, I used a
fixed string, and I use string concatenation to produce the second one so that
it would produce one with a different identity (so they wouldn't refer to the
same string in the string pool). I ran each of `hashcode()` and `compareTo()`
roughly 1 million times to get a significant spread and to smoothen out
differences due uncontrollable runtime conditions.

Here are the results for a sample run:

```text
Integer: 1.675676
Double: 1.142857
String: 0.739130
Instant: 0.756757
```

From these values, the cost of the two operations is within a constant factor.