package utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * https://gist.github.com/formix/d9521fd49cbeee305e2a
 */
public class SUID {
    private static final int SHORT_MAX = 65536;
    private static int counter = -1;

    private SUID() {
    }

    public static synchronized long nextId() {
        if(counter == -1) {
            Random rnd = new SecureRandom();
            counter = rnd.nextInt(SHORT_MAX);
        }
        long now = System.currentTimeMillis();
        long id = (now << 16) | counter;
        counter = (counter + 1) % SHORT_MAX;
        return id;
    }
}
