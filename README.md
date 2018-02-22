# Algorithms, Part 1

This repository contains the notes, exercises, and assignments, for the "Algorithms - Part 1" course on Coursera.

The course is taught by [Prof. Robert Sedgewick](http://www.cs.princeton.edu/~rs/) and [Prof. Kevin Wayne](http://www.cs.princeton.edu/~wayne/contact/), of [Princeton University](https://www.princeton.edu/).

Links to the course:  
- [Coursera](https://www.coursera.org/learn/algorithms-part1)  
- [YouTube](https://www.youtube.com/playlist?list=PLLQ1cuT9LqRVvLaktVZzEGMp4o9Wnmdtr)

## Course Notes

### Table of contents

* [Week 1](#week-1)
  * [Union-Find](#union-find)


### Week 1

#### Union-Find  

- for solving the Dynamic Connectivity problem
- Introduces Union-Find data type
- Explores several implementations
  - Quick Find
  - Quick Union
  - Weighted Quick Union
  - Weighted Quick Union with Path Compression

##### Dynamic Connectivity Problem  

Given a set of N objects, connect 2 objects, or check if 2 are connected directly or indirectly.

The problem is to implement 2 commands:

- `union(p, q) // => Connect object 'p' with object 'q'`  
- `connected(p, q) // => is object 'p' connected with object 'q'`  


*Connection properties:*

We assume that "is connected to" is an [equivalence relation](https://en.wikipedia.org/wiki/Equivalence_relation).  

An equivalence relation is a relationship between two objects that is [Reflexive](https://en.wikipedia.org/wiki/Reflexive_relation), [Symmetric](https://en.wikipedia.org/wiki/Symmetric_relation), and [Transitive](https://en.wikipedia.org/wiki/Transitive_relation). 

- Reflexive: An object is always connected to itself (`a ~ a`)
- Symmetric: If obj1 is connected to obj2, then obj2 is also connected to obj1 (`a ~ b` is the same as `b ~ a`)
- Transitive: If obj1 is connected to obj2, and obj2 is connected to obj3, then obj1 is connected to obj3 (if `a ~ b` and `b ~ c`, then `a ~ c`)

*Connected Components:*

When we have an equivalence relation, a set of objects and connections divide into "connected components". A connected component is a maximal set of objects that are mutually connected.

For example: For a set of 8 objects named: 0 to 7, if they are connected in this fashion:  
`1 ~ 4` `4 ~ 5`  
`2 ~ 3` `2 ~ 6` `3 ~ 6` `3 ~ 7`  
then there are 3 connected components: `{ { 0 } { 1, 4, 5 } { 2, 3, 6, 7 } }`  

Any two objects in a connected component are connected to each other, and there is no object outside that is connected to those objects.

Now, if a new connection is made `0 ~ 4`, then the connected components containing objects `0` and `4` will be merged to create a "union" of the 2 connected components: `{ { 0, 1, 4, 5 } { 2, 3, 6, 7 } }`

*Applications:*

In the Dynamic Connectivity problem, the objects can be of any type. For example:

* Pixels in a digital photo
* Computers in a network
* People in a social network
* etc...

  
##### Quick Find

Lecture: [Coursera](https://www.coursera.org/learn/algorithms-part1/lecture/EcF3P/quick-find)  
Code: [QuickFind.java](src/week1/unionfind/QuickFind.java)  
Test: [QuickFindTest.java](tests/week1/unionfind/QuickFindTest.java)

Quick-Find is an [eager algorithm](https://en.wikipedia.org/wiki/Eager_evaluation) for solving the dynamic connectivity problem. It uses an integer array as the underlying data structure. The interpretation is that each index/key of the array represents a single object, and the value/entry stored against that key represents a unique id assigned to each connected component.

For example:  

In a set of 10 objects (0 to 9), which are connected in this fashion:  
`0 ~ 5` `5 ~ 6`  
`1 ~ 2` `2 ~ 7`  
`3 ~ 8` `3 ~ 4` `4 ~ 9`

```
 0   1 - 2   3 - 4   
 |       |   |   |
 5 - 6   7   8   9 
```

The array will contain the following values:

| key       | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |
|-----------|---|---|---|---|---|---|---|---|---|---|
| arr [key] | 0 | 1 | 1 | 8 | 8 | 0 | 0 | 1 | 8 | 8 |


This represents these connected components: `{ { 0, 5, 6 } { 1, 2, 7 } { 3, 4, 8, 9 } }`

Here, 2 objects `p` and `q` are said to be connected if and only if `arr[p]` and `arr[q]` are equal.

*How it works:*

* To start with `N` objects, create an array of `N` elements, such that each value is the same as it's index. For `N = 6` : `[ 0, 1, 2, 3, 4, 5 ]`
* This implies that no object is connected, and each object stands as an independent connected component: `{ { 0 } { 1 } { 2 } { 3 } { 4 } { 5 } }`
* Now, execute `union(1, 2)` to connect objects `1` and `2`
* The array gets changed to: `[ 0, 1, 1, 3, 4, 5 ]`
* This implies that objects `1` and `2` are connected, and are a part of the same connected component. The other objects are still their own independent components: `{ { 0 } { 1, 2 } { 3 } { 4 } { 5 } }`
* Now, execute `union(4, 5)` to connect objects `1` and `2`
* The array gets changed to: `[ 0, 1, 1, 3, 4, 4 ]`
* This implies that objects `4` and `5` are connected, and are a part of the same connected component.
* There are now 4 connected components: `{ { 0 } { 1, 2 } { 3 } { 4, 5 } }`


*Issues with Quick-Find algorithm:*

The Quick-Find algorithm has a quadratic time-complexity `O(n^2)`, hence it's not scalable for large number of nodes.






