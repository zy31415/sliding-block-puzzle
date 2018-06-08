package io.github.zy31415.slidingblockpuzzle.solver;

import io.github.zy31415.slidingblockpuzzle.components.Puzzle;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class HPuzzleNodeTest {
    @Test
    public void test() {
        Puzzle puzzle = new Puzzle(new int[] {3, 4, 5, 0, 1, 2, 6, 7, 8});
        HPuzzleNode node = new HPuzzleNode(puzzle);

        assertEquals(5, node.getHeuristic());
    }

    @Test
    public void test2() {
        Puzzle puzzle = new Puzzle(new int[] {1, 2, 0, 3, 4, 5, 6, 7, 8});
        HPuzzleNode node = new HPuzzleNode(puzzle);

        assertEquals(2, node.getHeuristic());
    }

    @Test
    public void test3() {
        Puzzle puzzle = new Puzzle(new int[] {1, 8, 6, 5, 4, 3, 0, 7, 2});
        HPuzzleNode node = new HPuzzleNode(puzzle);

        assertEquals(14, node.getHeuristic());
    }

    @Test
    public void test_15_puzzle() {
        Puzzle.setDimension(4, 4);
        Puzzle puzzle = new Puzzle(new int[] {1, 8, 6, 9, 5, 4, 3, 10, 0, 7, 2, 11, 14, 13, 15, 12});
        HPuzzleNode node = new HPuzzleNode(puzzle);

        assertEquals(26, node.getHeuristic());
    }
}
