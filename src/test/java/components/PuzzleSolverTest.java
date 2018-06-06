package components;

import org.junit.Test;

public class PuzzleSolverTest {

    @Test
    public void test_one_step_left() {

        PuzzleSolver solver = new PuzzleSolver();

        Puzzle puzzle = new Puzzle(new int [] {1, 0, 2, 3, 4, 5, 6, 7, 8});

        solver.search(puzzle);
        solver.printResult();
    }

    @Test
    public void test_one_step_up() {

        PuzzleSolver solver = new PuzzleSolver();

        Puzzle puzzle = new Puzzle(new int [] {3, 1, 2, 0, 4, 5, 6, 7, 8});

        solver.search(puzzle);
        solver.printResult();
    }

    @Test
    public void test_one_random() {

        PuzzleSolver solver = new PuzzleSolver();

        Puzzle puzzle = Puzzle.randomFactory();

        puzzle.print();

        solver.search(puzzle);
        solver.printResult();
    }

    @Test
    public void test_one_random_15_puzzle() {

        PuzzleSolver solver = new PuzzleSolver();

        Puzzle.setDimension(4,4);

        Puzzle puzzle = Puzzle.randomFactory();
        puzzle.print();

        solver.search(puzzle);
        solver.printResult();
    }
}
