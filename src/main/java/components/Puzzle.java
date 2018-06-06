package components;

import java.math.BigInteger;

public class Puzzle {

    public static class Dimension {
        private final static int DEFAULT_SIZE = 3;

        private int width;
        private int height;
        private int numBlocks;

        Dimension() throws IllegalArgumentException {
            this(DEFAULT_SIZE, DEFAULT_SIZE);
        }

        Dimension(int width, int height) throws IllegalArgumentException {

            if ( width <= 0 || height <= 0) {
                throw new IllegalArgumentException();
            }

            this.width = width;
            this.height = height;
            numBlocks = width * height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public int getNumBlocks() {
            return numBlocks;
        }
    }

    static Dimension dimension = new Dimension();

    // precalculated powers of base
    private static BigInteger [] bases;
    private static BigInteger C0; // final state

    static {
        initBases();
        initC0();
    }

    public static void setDimension(int width, int height) {
        dimension = new Dimension(width, height);
        initBases();
        initC0();
    }

    private BigInteger c;    // configuration
    int p0 = -1;             // pointer of zero (blank) blockValue

    public enum Action {
        Right, Down, Left, Up
    }

    private static void initBases() {
        int numBlocks = dimension.getNumBlocks();
        bases = new BigInteger [numBlocks + 1];
        bases[0] = BigInteger.valueOf(1);

        for (int i = 1; i <= numBlocks; i++) {
            bases[i] = bases[i-1].multiply(BigInteger.valueOf(numBlocks));
        }
    }

    private static void initC0() {
        int numBlocks = dimension.getNumBlocks();
        C0 = BigInteger.valueOf(0);
        for (int i = 0; i < numBlocks; i++) {
            C0 = C0.add(bases[i].multiply(BigInteger.valueOf(i)));
        }
    }

    public Puzzle() {
        this(C0, 0);
    }

    public Puzzle(BigInteger c) {
        this(c, getZeroBlock(c));
    }

    public Puzzle(int [] conf) throws IllegalArgumentException {
        int numBlocks = dimension.getNumBlocks();
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
        int numBlocks = dimension.getNumBlocks();
        int [] inputs = new int [numBlocks];
        for (int i = 0; i < numBlocks; i++)
            inputs[i] = i;
        Utils.fisherYatesShuffle(inputs);
        return new Puzzle(inputs);
    }

    public void shuffle(int n) {

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

    static int blockValue(BigInteger conf, int n) {
        return conf.mod(bases[n+1]).divide(bases[n]).intValue();
    }

    int blockValue(int n) {
        return blockValue(c, n);
    }

    static int getZeroBlock(BigInteger conf) {
        int numBlocks = dimension.getNumBlocks();
        for(int i = 0; i < numBlocks; i++){
            if (blockValue(conf, i) == 0)
                return i;
        }
        return -1;
    }

    public boolean down() {
        int numBlocks = dimension.getNumBlocks();
        int width = dimension.getWidth();
        int p1 = p0 + width;
        if (p1 >= numBlocks)
            return false;
        move(p1);
        return true;
    }

    public boolean up() {
        int width = dimension.getWidth();
        int p1 = p0 - width;
        if (p1 < 0)
            return false;
        move(p1);
        return true;
    }

    public boolean right() {
        int width = dimension.getWidth();
        int p1 = p0 + 1;
        if (p0 % width == width - 1)
            return false;
        move(p1);
        return true;
    }

    public boolean left() {
        int width = dimension.getWidth();
        int p1 = p0 - 1;
        if (p0 % width == 0)
            return false;
        move(p1);
        return true;
    }

    private void move(int p1) {
        BigInteger d = BigInteger.valueOf(blockValue(p1));
        c = c.subtract(bases[p1].multiply(d)).add(bases[p0].multiply(d));
        p0 = p1;
    }

    public boolean move(Action action) {
        switch (action) {
            case Up: return up();
            case Down: return down();
            case Left: return left();
            case Right: return right();
        }
        return false;
    }

    public void print() {
        int width = dimension.getWidth();
        int height = dimension.getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.printf("%4d  ", blockValue(i * width + j));
            }
            System.out.print("\n");
        }
    }
}
