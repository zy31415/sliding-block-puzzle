package io.github.zy31415.slidingblockpuzzle.solver;

import io.github.zy31415.slidingblockpuzzle.components.Puzzle;
import org.junit.Ignore;
import org.junit.Test;

public class BreadthFirstSearchTest {

    @Test
    public void test_8_puzzle_random() {

        Puzzle.setDimension(3, 3);
        Puzzle puzzle = Puzzle.randomFactory();
        puzzle.print();

        Utils.printResult(
                (PuzzleNode) new BreadthFirstSearch().search(new PuzzleNode(puzzle))
        );
    }

    @Ignore
    @Test
    public void test_15_puzzle_shuffle() {

        Puzzle.setDimension(4, 4);

        Puzzle puzzle = (new Puzzle()).shuffle(100);

        puzzle.print();
        Utils.printResult(
                (PuzzleNode) new BreadthFirstSearch().search(new PuzzleNode(puzzle))
        );
    }

    @Ignore
    @Test
    public void test_24_puzzle_shuffle() {

        Puzzle.setDimension(5, 5);

        Puzzle puzzle = (new Puzzle()).shuffle(100);

        puzzle.print();
        Utils.printResult(
                (PuzzleNode) new BreadthFirstSearch().search(new PuzzleNode(puzzle))
        );
    }
}
