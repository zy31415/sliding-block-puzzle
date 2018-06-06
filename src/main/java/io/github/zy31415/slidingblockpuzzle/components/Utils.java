package io.github.zy31415.slidingblockpuzzle.components;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {
    public static void fisherYatesShuffle(int [] inputs) {
        int n = inputs.length;
        for (int i = 0; i < n; i++) {
            int j = ThreadLocalRandom.current().nextInt(i, n);

            // swap inputs[i] and inputs[j]
            int tmp = inputs[i];
            inputs[i] = inputs[j];
            inputs[j] = tmp;
        }
    }
}
