# Exercise 3.4.25

*Hash cache*. Modify `Transaction` on page 462 to maintain an instance
variable `hash`, so that `hashCode()` can save the hash value
the first time it is called for each object, and does not have to recompute it
on subsequent calls. *Note*: This idea works only for immutable types.