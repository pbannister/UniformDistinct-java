
Pulled Daniel Lemire's example code, refactored to pull out the distinct algorithms.

Added the 'shuffle' algorithm ... which is unsuitable as I missed that Daniel wanted ordered (sorted) integers.

Added the 'cheat' algorithm as an upper limit. The 'cheat' algorithm generates uniform distint ordered sets, but not *all possible* sets. This may or may not matter, depending on use. The code is slightly specific to the test, but is sufficient to prove the outcome - the 'cheat' algorithm is much faster than the present alternatives.

The output is a bit easier to read than the original example. 
