package bin.xing.leomath;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathUtilTest {

    @Test
    void random() {
        for (int i = 0; i < 100; i++) {
            int num = MathUtil.random(1, 9);
            assertTrue(num >= 1);
            assertTrue(num <= 9);
        }
    }
}