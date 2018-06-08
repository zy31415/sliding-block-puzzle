package io.github.zy31415.slidingblockpuzzle.solver;

import io.github.zy31415.slidingblockpuzzle.components.Puzzle;
import org.junit.Ignore;
import org.junit.Test;

public class AStarSearchTest {
    @Ignore
    @Test
    public void test() {
        Puzzle.setDimension(4, 4);

        Puzzle puzzle = (new Puzzle()).shuffle(100);

        puzzle.print();
        Utils.printResult(
                new AStarSearch().search(new HPuzzleNode(puzzle)));
    }
}
