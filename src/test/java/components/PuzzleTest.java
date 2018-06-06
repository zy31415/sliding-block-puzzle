package components;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PuzzleTest {

    @Test
    public void test_PuzzleInitializer() {
        int [] arr = new int [] {0, 1, 2, 6, 4, 5, 3, 7, 8};
        Puzzle p = new Puzzle(arr);

        for (int i = 0; i < arr.length; i ++)
            assertEquals(arr[i], p.blockValue(i));

        assertEquals(0, p.blockValue(0));
    }

    @Test
    public void test_PuzzleInitializer_ZeroPosition() {
        int [] arr = new int [] {4, 1, 2, 6, 0, 5, 3, 7, 8};
        Puzzle p = new Puzzle(arr);

        assertEquals(0, p.blockValue(4));
        assertEquals(4, p.p0);
    }

    @Test
    public void test_PuzzleInitializer_randomFactory() {
        Puzzle p = Puzzle.randomFactory();
    }

    @Test
    public void test_PuzzleInitializer_4by4() {
        int [] arr = new int [] {0, 1, 2, 15, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 3};

        Puzzle.setDimension(4, 4);

        Puzzle p = new Puzzle(arr);

        for (int i = 0; i < arr.length; i ++)
            assertEquals(arr[i], p.blockValue(i));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_PuzzleInitializer_wrong_size() {
        int [] arr = new int [] {0, 1, 2, 6, 4, 5, 3, 7, 8, 9};
        Puzzle p = new Puzzle(arr);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_PuzzleInitializer_repetitive_number() {
        int [] arr = new int [] {0, 1, 3, 6, 4, 5, 3, 7, 8};
        Puzzle p = new Puzzle(arr);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_PuzzleInitializer_too_big() {
        int [] arr = new int [] {0, 1, 2, 3, 4, 5, 6, 7, 10};
        Puzzle p = new Puzzle(arr);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_PuzzleInitializer_negative() {
        int [] arr = new int [] {0, 1, 2, 3, 4, 5, 6, -7, 8};
        Puzzle p = new Puzzle(arr);
    }

    @Test
    public void testIsSovled_False() {
        int [] arr = new int [] {0, 1, 2, 6, 4, 5, 3, 7, 8};
        Puzzle p = new Puzzle(arr);

        assertFalse(p.isSolved());
    }

    @Test
    public void testIsSovled_True() {
        int[] arr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
        Puzzle p = new Puzzle(arr);

        assertTrue(p.isSolved());
    }

    @Test
    public void testEquals() {
        int [] arr = new int [] {0, 1, 2, 6, 4, 5, 3, 7, 8};
        Puzzle p1 = new Puzzle(arr);
        Puzzle p2 = new Puzzle(arr);

        assertTrue(p1.equals(p2));
    }
}
