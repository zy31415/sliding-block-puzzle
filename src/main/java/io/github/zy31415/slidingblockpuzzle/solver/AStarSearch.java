package io.github.zy31415.slidingblockpuzzle.solver;

import java.util.*;

public class AStarSearch {
    private Set<HPuzzleNode> discovered;
    private Queue<HPuzzleNode> frontier;
    private int currentDepth;
    private HPuzzleNode c;

    public HPuzzleNode search(HPuzzleNode n0) {
        discovered = new HashSet<>();
        frontier = new PriorityQueue<>();

        discovered.add(n0);
        frontier.add(n0);
        currentDepth = 0;
        printProgress();

        while (!frontier.isEmpty()) {
            c = frontier.remove();

            printProgress();

            if (currentDepth != c.getDepth()) {
                currentDepth = c.getDepth();
            }

            if (c.isGoal()){
                return c;
            }

            for (Node _n : c.next()) {
                HPuzzleNode n = (HPuzzleNode) _n;
                if (!discovered.contains(n)) {
                    discovered.add(n);
                    frontier.add(n);
                }
            }
        }

        return null;
    }

    private void printProgress() {
        if (c != null)
            System.out.printf("f: %d; depth: %d; h: %d; Discovered: %d; Frontier: %d\n",
                    c.getEstimatedCost(), c.getDepth(), c.getHeuristic(),
                    discovered.size(), frontier.size());
    }
}
