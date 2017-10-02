package utils;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class StringUtilsTest {
    @Test
    public void asciiToHex_theCorrect() {
        String victim = "test";
        String result = StringUtils.asciiToHex(victim);
        assertNotNull(result);
        System.out.printf("%s=%s\n", victim, result);
    }
}
