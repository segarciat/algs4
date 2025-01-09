# Exercise 3.5.17

*Finite mathematical sets*. Your goal is to develop an implementation of
the following API for processing finite mathematical sets:

```
public class MathSET<Key>
                 MathSet(Key[] universe)        // Create the empty set (using given universe)
            void add(Key key)                   // put key into the set
    MathSET<Key> complement()                   // set of keys int he universe that are not in this set.
            void union(MathSET<Key> a)          // put any keys from a into the set that are not
                                                // already there.
            void intersection(MathSET<Key> a)   // remove any keys from this set that are not in a.
            void delete(Key key)                // remove key from the set
         boolean contains(Key key)              // is key in the set?
         boolean isEmpty()                      // is the set empty?
             int size()                         // number of keys in the set
```