package io.github.zy31415.slidingblockpuzzle.solver;

import io.github.zy31415.slidingblockpuzzle.components.Puzzle;
import org.junit.Ignore;
import org.junit.Test;

public class DepthFirstSearchTest {

    @Test
    public void test_one_step_left() {

        Puzzle puzzle = new Puzzle(new int [] {1, 0, 2, 3, 4, 5, 6, 7, 8});
        puzzle.print();

        Utils.printResult(
                (PuzzleNode) new DepthFirstSearch().search(new PuzzleNode(puzzle))
        );
    }

    @Test
    public void test_one_step_up() {

        Puzzle puzzle = new Puzzle(new int [] {3, 1, 2, 0, 4, 5, 6, 7, 8});

        Utils.printResult(
                (PuzzleNode) new DepthFirstSearch().search(new PuzzleNode(puzzle))
        );
    }

    @Test
    public void test_one_random() {

        Puzzle puzzle = Puzzle.randomFactory();

        puzzle.print();

        Utils.printResult(
                (PuzzleNode) new DepthFirstSearch().search(new PuzzleNode(puzzle))
        );
    }

    @Ignore
    @Test
    public void test_one_random_15_puzzle() {

        Puzzle.setDimension(4,4);

        Puzzle puzzle = Puzzle.randomFactory();
        puzzle.print();

        Utils.printResult(
            (PuzzleNode) new DepthFirstSearch().search(new PuzzleNode(puzzle))
        );
    }

    @Ignore
    @Test
    public void test_15_puzzle_shuffle() {

        Puzzle.setDimension(4, 4);

        Puzzle puzzle = (new Puzzle()).shuffle(3);

        puzzle.print();
        Utils.printResult(
            (PuzzleNode) new DepthFirstSearch().search(new PuzzleNode(puzzle))
        );
    }
}
