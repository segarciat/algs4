# Exercise 2.1.17

*Animation*. Add code to `Insertion`, `Selection`, and `Shell` to make them draw
the array contents  as vertical bars like the visual traces in this section,
redrawing the bars after each pass, to produce an animated effect, ending
in a ``sorted`` picture where the bars appear in order of their height.
*Hint*: Use a client like the one in the text that generates random `Double`
values, insert calls to `show()` as appropriate in the sort code, and implement
a `show()` method that clears the canvas and draws the bars.