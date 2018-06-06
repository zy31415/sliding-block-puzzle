package components;

public class PuzzleNode extends Puzzle {

    private final PuzzleNode parent;
    private final Puzzle.Action previousAction;

    PuzzleNode(Puzzle puzzle, PuzzleNode parent, Puzzle.Action previousAction) {
        super(puzzle.c, puzzle.p0);
        this.parent = parent;
        this.previousAction = previousAction;
    }

    PuzzleNode(Puzzle puzzle) {
        this(puzzle, null, null);
    }

    @Override
    public PuzzleNode move(Action action) {
        Puzzle p = super.move(action);

        if (p != null)
            return new PuzzleNode(p, this, action);

        return null;
    }

    public PuzzleNode getParent() {
        return parent;
    }

    public Action getPreviousAction() {
        return previousAction;
    }
}
