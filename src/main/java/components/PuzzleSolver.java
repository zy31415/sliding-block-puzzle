package components;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class PuzzleSolver {
/*
    private Puzzle solved;

    public void search(Puzzle p) {
        Set<Puzzle> discovered = new HashSet<>();
        Stack<Puzzle>frontier = new Stack<>();

        discovered.add(p);
        frontier.push(p);

        while (!frontier.empty()) {
            Puzzle c = frontier.pop();

            if (c.isSolved()){
                solved = c;
                System.out.printf("Found final state.\n");
                return;
            }

            for (Puzzle.Action action : Puzzle.Action.values()) {
                Puzzle c1 = c.move(action);
                if (c1 != null && !discovered.contains(c1)) {
                    discovered.add(c1);
                    frontier.add(c1);
                }
            }
        }
    }

    public void printResult() {
        Stack<Puzzle> stack = new Stack<>();

        Puzzle p = solved;

        while (p != null) {
            stack.push(p);
            p = p.getParent();
        }

        while (!stack.empty()) {
            Puzzle.Action action = stack.pop().getAction();

            if (action != null)
                System.out.printf("%s\n", action.name());
        }

        System.out.printf("\n");
    }
*/
}
