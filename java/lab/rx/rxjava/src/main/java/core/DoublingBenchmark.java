package core;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class DoublingBenchmark {
    public static void main(String[] args) {
        try {
            Options opt = new OptionsBuilder()
                    .include(DoublingDemo.class.getSimpleName())
//                    .forks(1) // override DoublingDemo annotation forks
                    .build();

            new Runner(opt).run();
        } catch(RunnerException e) {
            e.printStackTrace();
        }
    }
}
