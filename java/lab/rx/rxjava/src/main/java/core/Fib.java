package core;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Fib {
    private Map<Long, BigInteger> cache = new HashMap<>();

    public BigInteger fib(long i) {
        if(i == 0)
            return BigInteger.ZERO;
        if(i == 1)
            return BigInteger.ONE;

        return cache.computeIfAbsent(i, n -> fib(n-2).add(fib(n-1)));
    }
}
