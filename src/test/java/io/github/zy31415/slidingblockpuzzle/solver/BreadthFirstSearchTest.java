package io.github.zy31415.slidingblockpuzzle.solver;

import io.github.zy31415.slidingblockpuzzle.components.Puzzle;
import org.junit.Test;

public class BreadthFirstSearchTest {

    @Test
    public void test_15_puzzle_shuffle() {

        Puzzle.setDimension(4, 4);

        Puzzle puzzle = (new Puzzle()).shuffle(100);

        puzzle.print();
        Utils.printResult(
                (PuzzleNode) BreadthFirstSearch.search(new PuzzleNode(puzzle))
        );
    }
}
