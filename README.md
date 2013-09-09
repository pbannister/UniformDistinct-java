
Pulled Daniel Lemire's example code, refactored to pull out the distinct algorithms.

Added the 'shuffle' algorithm ... which is unsuitable as I missed that Daniel wanted ordered (sorted) integers.

Added the 'cheat' algorithm as an upper limit. The 'cheat' algorithm generates uniform distint ordered sets, but not *all possible* sets. This may or may not matter, depending on use. The code is slightly specific to the test, but is sufficient to prove the outcome - the 'cheat' algorithm is much faster than the present alternatives.

Added the 'divide0' algorithm. This is fully general (any limit, any count) uses only the space for the final output, and log2(N) stack space for recursion. Slightly non-uniform when the limit is not a power of two - due to rounding - but not significant for large numbers. (If your range is small, use a different algorithm.)

There is a faster variant of the 'divide' algorithm. When I get time...

The output is a bit easier to read than the original example. 
