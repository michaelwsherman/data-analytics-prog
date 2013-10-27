LogProcessor
===================

An assignment from Data Analytics Programming, taught by Adnan Aziz (http://users.ece.utexas.edu/~adnan/)

LogProcessor takes input of webpages and times. It tracks these webpages and times as long as they remain within a certain time window W (specified when instantiating the LogProcessorFast class). You can then get a list of the K most-visited webpages with the getOrderedUrlsInWindow method.

But there's more to this than the functionality. The idea is this code could be used for giant website, like Facebook, with thousands and thousands of hits coming in per second. So the code needs to be *FAST* (this is why the class is called LogProcessorFast, there was a LogProcessorSlow class but it was too slow!). Also, the webpages and times can be added out of time order.

A simple array is not sufficient to track all of this information at a proper speed. So we use a system of data structures (partially explained to us by Professor Aziz). The "queue" of the page times is actually a MultiSet (implemented in Google Guava), this allows for quick popping of the oldest web pages as they become outdated, and orders pages by time if they arrive out of time order. To actually track the webpage counts we have a TreeSet, ordered by count, which lets us quickly generate a list of the most visited webpages. Finally, we have a HashSet keyed by URLs with reference to the TreeSet of the page counts -- this lets us quickly adjust page counts in the TreeSet without taking the time to search the tree. You can get an idea of how the code works by looking at the tests, written by Professor Aziz. 

My code is twice as fast as the time Professor Aziz reported for his code (although he obviously could make his a lot faster if he wanted to!). I think the primary reason for the increased speed is not popping webpages with count=0 off the treeSet, and only worrying about them at the end when the treeSet is re-created to make sure it is in proper order for generating the list of the most visited pages. Maybe this could be done faster with a proper sort() method? There is also the issue of the extra memory these count=0 pages take up in the tree.

Part of the code (PageTime and PageCount classes, the LogProcessor interface, and the declaration of the LogProcessorFast class, including the creation of the data structures) was written by Professor Aziz. The code of the add and getOrderedUrlsInWindow methods are mine.