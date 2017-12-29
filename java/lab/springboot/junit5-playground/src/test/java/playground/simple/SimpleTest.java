package playground.simple;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleTest {

    @Test
    public void testAllMatch() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        assertTrue(integers.stream().allMatch(i -> {
            if(i>0) {
                return true;
            } else {
                return false;
            }
        }));

        integers = Arrays.asList(1, 2, -3, 4);
        assertFalse(integers.stream().allMatch(i -> {
            if(i>0) {
                return true;
            } else {
                return false;
            }
        }));
    }
}
