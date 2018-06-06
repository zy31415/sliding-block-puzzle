# Sliding Block Puzzle

I use this repo to study artificial intelligence algorithms on solving the 
Sliding Block Puzzle.

## Depth first solver (DFS)

DFS can quickly solve 8-puzzles but it turns out that 
it is not an efficient algorithm to solve the larger 15-puzzles.
There two pieces of evidence for this:

1) For a randomly generated 15-puzzle, DFS always ends up with the following error:

        java.lang.OutOfMemoryError: GC overhead limit exceeded
 
2) If I create a 15-puzzle from shuffling a final state puzzle, then the performance of DFS 
deteriorate quickly as the times of shuffling increases. Experiments show that the same 
memory error will surely appear if times of shuffling is greater than 10.

## Breadth first solver (BSF)

## Depth-limited solver

## Iterative deepening depth-first solver

## Bidirectional solver

## Heuristic solver


## Reference

[1] Artificial Intelligence - A Modern Approach by Russel and Norvig
 
