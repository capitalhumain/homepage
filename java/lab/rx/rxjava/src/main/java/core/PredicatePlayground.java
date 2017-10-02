package core;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicatePlayground {
    public static String getNamesOfLength(int length, String... names) {
        return Arrays.stream(names)
                .filter(s -> s.length() == length)
                .collect(Collectors.joining(", "));
    }

    public static String getNamesOf(Predicate<String> predicate, String... names) {
        return Arrays.stream(names)
                .filter(predicate)
                .collect(Collectors.joining(", "));
    }

    public static void main(String[] args) {
        System.out.println(getNamesOfLength(5, "Alpha", "Beta", "Gamma", "Delta", "Epsilon"));

        final Predicate<String> LENGTH_5 = s -> s.length() == 5;
        System.out.println(getNamesOf(LENGTH_5, "Alpha", "Beta", "Gamma", "Delta", "Epsilon"));

        final Predicate<String> STARTS_WITH_E = s -> s.startsWith("E");
        System.out.println(getNamesOf(STARTS_WITH_E, "Alpha", "Beta", "Gamma", "Delta", "Epsilon"));
    }
}
