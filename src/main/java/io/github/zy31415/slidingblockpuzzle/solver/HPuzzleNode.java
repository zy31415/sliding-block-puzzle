package io.github.zy31415.slidingblockpuzzle.solver;

import io.github.zy31415.slidingblockpuzzle.components.Puzzle;

public class HPuzzleNode extends PuzzleNode implements Comparable <HPuzzleNode> {
    private final int heuristic;
    private final int estimatedCost;

    HPuzzleNode(Puzzle puzzle, PuzzleNode parent, Puzzle.Action previousAction, int depth) {
        super(puzzle, parent, previousAction, depth);
        heuristic = computeHeuristicValue();
        estimatedCost = depth + heuristic;
    }

    public HPuzzleNode(Puzzle puzzle) {
        this(puzzle, null, null, 0);
    }

    private int computeHeuristicValue() {
        int numBlocks = Puzzle.getDimension().getNumBlocks();
        int width = Puzzle.getDimension().getWidth();

        int out = 0;

        for (int v0 = 0; v0 < numBlocks; v0++) {
            int i0 = v0 / width;
            int j0 = v0 % width;

            int v = puzzle.blockValue(v0);

            if (v == 0) // '0' represents empty block;
                continue;

            int i = v / width;
            int j = v % width;

            out += Math.abs(i-i0) + Math.abs(j-j0);
        }

        return out;
    }

    int getEstimatedCost() {
        return estimatedCost;
    }

    int getHeuristic() {
        return heuristic;
    }

    @Override
    HPuzzleNode move(Puzzle.Action action) {
        Puzzle p = puzzle.move(action);

        if (p != null)
            return new HPuzzleNode(p, this, action, depth + 1);

        return null;
    }

    @Override
    public int compareTo(HPuzzleNode o) {
        // Note: this class has a natural ordering that is inconsistent with equals.
        return estimatedCost - o.estimatedCost;
    }
}
