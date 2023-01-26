# Binary Search Tree (BST) with Threads
This assignment is an implementation of building a tree, searching for an element and finding the height of the tree using threads in Java. The program uses multi-threading to divide the size of the tree into smaller subtrees and build them simultaneously, which leads to significant reduction in the time taken as compared to single-threaded implementation.
## Prerequisites
Basic knowledge of Java programming<br>
Familiarity with threading concepts in Java<br>
A development environment with the latest version of Java installed
## Functionalities
Building a tree using a single-threaded implementation<br>
Building a tree using a multi-threaded implementation by dividing the tree into smaller subtrees and building them simultaneously<br>
Searching for an element in the tree using a single-threaded implementation<br>
Searching for an element in the tree using a multi-threaded implementation by searching in all the subtrees simultaneously<br>
Finding the height of the tree using a single-threaded implementation<br>
Finding the height of the tree using a multi-threaded implementation by finding the height of each subtree simultaneously and then merging the results<br>
Comparing the time taken to perform each task using the single-threaded and multi-threaded implementations
## Classes
Tree: This class contains methods that build a tree, search for an element in the tree and find the height of the tree using a single-threaded implementation.<br>
thread1: This is the thread class that builds sub trees<br>
thread2: This is the thread class that searches for an element in the sub trees
