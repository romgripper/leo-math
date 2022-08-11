package bin.xing.leomath;

import java.util.Random;

public class MathUtil {

    private static Random random = new Random(System.currentTimeMillis());

    public static int random(int min, int max) {
        return random.nextInt(max - min) + min;
    }
}
