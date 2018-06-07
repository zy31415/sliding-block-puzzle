package io.github.zy31415.slidingblockpuzzle.solver;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class DepthFirstSearch {

    private Set<Node> discovered;
    private Stack<Node> frontier;
    private int currentDepth;

    public Node search(Node n0, int depthLimit) {

        discovered= new HashSet<>();
        frontier = new Stack<>();

        discovered.add(n0);
        frontier.push(n0);
        currentDepth = n0.getDepth();
        printProgress();

        while (!frontier.empty()) {
            Node c = frontier.pop();

            if (currentDepth != c.getDepth()) {
                currentDepth = c.getDepth();
                printProgress();
            }

            if (c.isGoal()){
                System.out.printf("Found final state.\n");
                return c;
            }

            for (Node n : c.next()) {
                if (n.getDepth() <= depthLimit && !discovered.contains(n)) {
                    discovered.add(n);
                    frontier.add(n);
                }
            }
        }

        return null;
    }

    public Node search(Node n0) {
        return search(n0, Integer.MAX_VALUE);
    }

    public Node iterativeDeepeningSearch(Node n0) {
        int depthLimit = 1;

        Node node = null;

        while (node == null) {
            node = search(n0, depthLimit);
            depthLimit++;
        }
        return node;
    }

    private void printProgress() {
        System.out.printf("Searching depth: %d (Discovered: %d; Frontier: %d)\n",
                currentDepth, discovered.size(), frontier.size());
    }
}
