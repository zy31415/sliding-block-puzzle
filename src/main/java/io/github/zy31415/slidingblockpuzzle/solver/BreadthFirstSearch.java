package io.github.zy31415.slidingblockpuzzle.solver;

import java.util.*;

public class BreadthFirstSearch implements Solver {

    private Set<Node> discovered;
    private Queue<Node> frontier;
    private int currentDepth;

    @Override
    public Node search(Node n0) {
        discovered = new HashSet<>();
        frontier = new LinkedList<>();

        discovered.add(n0);
        frontier.add(n0);
        currentDepth = 0;
        printProgress();

        while (!frontier.isEmpty()) {
            Node c = frontier.remove();

            if (currentDepth != c.getDepth()) {
                currentDepth = c.getDepth();
                printProgress();
            }

            if (c.isGoal()){
                return c;
            }

            for (Node n : c.next()) {
                if (!discovered.contains(n)) {
                    discovered.add(n);
                    frontier.add(n);
                }
            }
        }

        return null;
    }

    private void printProgress() {
        System.out.printf("Searching depth: %d (Discovered: %d; Frontier: %d)\n",
                currentDepth, discovered.size(), frontier.size());
    }
}
