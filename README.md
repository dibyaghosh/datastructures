# Java Data Structures

Here are a list of data structures implemented in Java for practice


## Union Find

File: DisjointSet.java

This implementation of Union Find implements path compression as detailed in http://www.cs.cornell.edu/Courses/cs6110/2014sp/Handouts/UnionFind.pdf

## Binary Search Tree

File: BinarySearchTree.java

This is a naive (unbalanced) version of a Binary Search Tree

## Left Leaning Red Black Trees

File: LLRedBlackTree.java

This is an implementation of a LLRBT as defined in https://www.cs.princeton.edu/~rs/talks/LLRB/LLRB.pdf . Simpler to implement than a traditional red-black tree, this tree is used to incorporate 2-3 trees using BSTs. Through rotation, this tree maintains balance, and maintains a maximum height of 2log(n)

## Priority Queues with Heaps

File: PriorityQueue.java

This is an implementation of a Priority Queue with an internal heap datastructure (https://en.wikipedia.org/wiki/Heap_(data_structure)). Internally, this uses an Arraylist for the heap, maintaining fullness when possible

## Hash Set

File: HashSet.java

This is an implementation of a HashSet as per http://algs4.cs.princeton.edu/34hash/. This particular version incorporates resizable tables (for less collisions) which allows for a configurable memory/time tradeoff.
