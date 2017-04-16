PickCoins
===================

An assignment from Data Analytics Programming, taught by Adnan Aziz (http://users.ece.utexas.edu/~adnan/)

PickCoins is a solution to this problem: http://leetcode.com/2011/02/coins-in-line.html

In this specific implementation, there is no restriction on the coins being odd or even in number, and the value we are seeking is what player 1's max score is. 

PickCoins only takes an array of coins as an input, and outputs player 1's best. You can see examples of how to use it by looking at the tests (written by Professor Aziz). 

At the heart of PickCoins is a recursive algorithm that uses dynamic programming. While there are better ways to solve this problem, the assignment was to use recursion and a cache. 

I am not especially proud of this solution, but it works. However, there are aspects of the solution very much optimized to the unit tests, most notably my use of some ugly math to make the key of the HashMap cache unique. I originally was using a String as a key for the HashMap (made by concatenating some of the current state information), but this was too slow to pass one of the tests, so String had to go. The current implementation using long is about 200% faster, although it is obviously much less robust. 

The reason there are two separate caches is that this made the program run faster.

There is a better way to do the caching than a HashMap, certainly.

If I had some time, I'd clean this code up a bit. I like my solution, especially because it tells you exactly how both players do, but this code could use a polish
