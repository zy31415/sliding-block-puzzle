package components;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

public class Puzzle {
    private BigInteger c;    // configuration
    private static BigInteger C0; // final state

    int p0 = -1;             // pointer of zero (blank) block

    private static final int DEFAULT_SIZE = 3;

    private static int width = DEFAULT_SIZE;
    private static int height = DEFAULT_SIZE;
    private static int numBlocks = DEFAULT_SIZE * DEFAULT_SIZE;

    // precalculated powers of base
    private static BigInteger [] bases;

    private Puzzle parent = null;

    public enum Action {
        Right, Down, Left, Up
    }

    private Action action = null;

    static {
        init();
    }

    private static void init() {
        initNumBlocks();
        initBases();
        initC0();
    }

    private static void initNumBlocks() {
        numBlocks = width * height;
    }

    private static void initBases() {
        bases = new BigInteger [numBlocks + 1];
        bases[0] = BigInteger.valueOf(1);

        for (int i = 1; i <= numBlocks; i++) {
            bases[i] = bases[i-1].multiply(BigInteger.valueOf(numBlocks));
        }
    }

    private static void initC0() {
        C0 = BigInteger.valueOf(0);

        for (int i = 0; i < numBlocks; i++) {
            C0 = C0.add(bases[i].multiply(BigInteger.valueOf(i)));
        }
    }

    public static void setDimension(int width, int height) throws IllegalArgumentException {

        if ( width <= 0 || height <= 0) {
            throw new IllegalArgumentException();
        }

        Puzzle.width = width;
        Puzzle.height = height;

        init();
    }

    public Puzzle() {
        this(C0, 0);
    }

    public Puzzle(int [] conf) throws IllegalArgumentException {

        if (conf.length != numBlocks)
            throw new IllegalArgumentException("Configuration size is wrong.");

        // Use markers to make sure that each number is only used once.
        boolean [] markers = new boolean[numBlocks];

        c = BigInteger.valueOf(0);

        for (int i = 0; i < conf.length; i++) {
            int n = conf[i];
            if (n < 0 || n >= numBlocks || markers[n])
                throw new IllegalArgumentException("Illegal input array.");
            markers[n] = true;
            c = c.add(bases[i].multiply(BigInteger.valueOf(n)));

            if (n == 0) p0 = i;
        }
    }

    private Puzzle(BigInteger c, int p0) {
        this.c = c;
        this.p0 = p0;
    }

    public static Puzzle randomFactory() {
        int [] inputs = new int [numBlocks];
        for (int i = 0; i < numBlocks; i++)
            inputs[i] = i;
        fisherYatesShuffle(inputs);
        return new Puzzle(inputs);
    }

    public void shuffle(int n) {

    }

    private static void fisherYatesShuffle(int [] inputs) {
        int n = inputs.length;
        for (int i = 0; i < n; i++) {
            int j = ThreadLocalRandom.current().nextInt(i, n);

            // swap inputs[i] and inputs[j]
            int tmp = inputs[i];
            inputs[i] = inputs[j];
            inputs[j] = tmp;
        }
    }

    public boolean isSolved() {
        return c.equals(C0);
    }

    @Override
    public int hashCode() {
        return c.intValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Puzzle) {
            Puzzle p = (Puzzle) obj;
            return p.c.equals(c);

        }
        return false;
    }

    int block(int n) {
        return c.mod(bases[n+1]).divide(bases[n]).intValue();
    }

    public Puzzle down() {
        int p1 = p0 + width;
        if (p1 >= numBlocks)
            return null;
        Puzzle p = move(p1);
        p.action = Action.Down;
        return p;
    }

    public Puzzle up() {
        int p1 = p0 - width;
        if (p1 < 0)
            return null;
        Puzzle p = move(p1);
        p.action = Action.Up;
        return p;
    }

    public Puzzle right() {
        int p1 = p0 + 1;
        if (p0 % width == width - 1)
            return null;
        Puzzle p = move(p1);
        p.action = Action.Right;
        return p;
    }

    public Puzzle left() {
        int p1 = p0 - 1;
        if (p0 % width == 0)
            return null;
        Puzzle p = move(p1);
        p.action = Action.Left;
        return p;
    }

    private Puzzle move(int p1) {
        BigInteger d = BigInteger.valueOf(block(p1));
        BigInteger c1 = c.subtract(bases[p1].multiply(d)).add(bases[p0].multiply(d));
        Puzzle p = new Puzzle(c1, p1);
        p.parent = this;
        return p;
    }

    public Puzzle move(Action action) {
        switch (action) {
            case Up: return up();
            case Down: return down();
            case Left: return left();
            case Right: return right();
        }
        return null;
    }

    public Puzzle getParent() {
        return parent;
    }

    public Action getAction() {
        return action;
    }

    public void print() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.printf("%4d  ", block(i * width + j));
            }
            System.out.print("\n");
        }
    }
}
