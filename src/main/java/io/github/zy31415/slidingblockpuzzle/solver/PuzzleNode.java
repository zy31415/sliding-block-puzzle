package io.github.zy31415.slidingblockpuzzle.solver;

import io.github.zy31415.slidingblockpuzzle.components.Puzzle;

import java.util.ArrayList;
import java.util.List;

public class PuzzleNode implements Node {

    private final Puzzle puzzle;

    private final PuzzleNode parent;
    private final Puzzle.Action previousAction;
    private final int depth;

    PuzzleNode(Puzzle puzzle, PuzzleNode parent, Puzzle.Action previousAction, int depth) {
        this.puzzle = puzzle;
        this.parent = parent;
        this.previousAction = previousAction;
        this.depth = depth;
    }

    PuzzleNode(Puzzle puzzle) {
        this(puzzle, null, null, 0);
    }

    @Override
    public List<Node> next() {
        List<Node> out = new ArrayList<>();

        for (Puzzle.Action action : Puzzle.Action.values()) {
            PuzzleNode c1 = move(action);
            if (c1 != null)
                out.add(c1);
        }
        return out;
    }

    private PuzzleNode move(Puzzle.Action action) {
        Puzzle p = puzzle.move(action);

        if (p != null)
            return new PuzzleNode(p, this, action, depth + 1);

        return null;
    }

    public PuzzleNode getParent() {
        return parent;
    }

    public Puzzle.Action getPreviousAction() {
        return previousAction;
    }

    @Override
    public int getDepth() {
        return depth;
    }

    @Override
    public boolean isGoal() {
        return puzzle.isSolved();
    }

    @Override
    public int hashCode() {
        return puzzle.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PuzzleNode) {
            return puzzle.equals(((PuzzleNode) obj).puzzle);
        }
        return false;
    }
}
