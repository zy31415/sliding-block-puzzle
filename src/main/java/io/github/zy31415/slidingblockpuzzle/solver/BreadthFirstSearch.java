package io.github.zy31415.slidingblockpuzzle.solver;

import java.util.*;

public class BreadthFirstSearch{

    public static Node search(Node n0) {
        Set<Node> discovered = new HashSet<>();
        Queue<Node> frontier = new LinkedList<>();

        discovered.add(n0);
        frontier.add(n0);

        while (!frontier.isEmpty()) {
            Node c = frontier.remove();

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
