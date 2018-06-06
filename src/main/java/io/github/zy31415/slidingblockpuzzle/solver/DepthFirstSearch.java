package io.github.zy31415.slidingblockpuzzle.solver;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class DepthFirstSearch {

    public static Node search(Node n0) {

        Set<Node> discovered = new HashSet<>();
        Stack<Node> frontier = new Stack<>();

        discovered.add(n0);
        frontier.push(n0);

        while (!frontier.empty()) {
            Node c = frontier.pop();

            if (c.isGoal()){
                System.out.printf("Found final state.\n");
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
}
