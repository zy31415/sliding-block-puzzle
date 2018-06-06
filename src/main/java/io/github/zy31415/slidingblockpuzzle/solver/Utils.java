package io.github.zy31415.slidingblockpuzzle.solver;

import io.github.zy31415.slidingblockpuzzle.components.Puzzle;

import java.util.Stack;

public class Utils {
    public static void printResult(PuzzleNode goal) {

        Stack<PuzzleNode> stack = new Stack<>();
        PuzzleNode p = goal;

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
