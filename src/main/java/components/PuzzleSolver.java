package components;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class PuzzleSolver {

    private PuzzleNode solved;

    public void search(Puzzle p) {

        PuzzleNode n0 = new PuzzleNode(p);

        Set<PuzzleNode> discovered = new HashSet<>();
        Stack<PuzzleNode> frontier = new Stack<>();

        discovered.add(n0);
        frontier.push(n0);

        while (!frontier.empty()) {
            PuzzleNode c = frontier.pop();

            if (c.isSolved()){
                solved = c;
                System.out.printf("Found final state.\n");
                return;
            }

            for (Puzzle.Action action : Puzzle.Action.values()) {
                PuzzleNode c1 = c.move(action);
                if (c1 != null && !discovered.contains(c1)) {
                    discovered.add(c1);
                    frontier.add(c1);
                }
            }
        }

    }

    public void printResult() {
        Stack<PuzzleNode> stack = new Stack<>();

        PuzzleNode p = solved;

        while (p != null) {
            stack.push(p);
            p = p.getParent();
        }

        while (!stack.empty()) {
            Puzzle.Action action = stack.pop().getPreviousAction();

            if (action != null)
                System.out.printf("%c", action.name().charAt(0));
        }

        System.out.printf("\n");
    }

}
