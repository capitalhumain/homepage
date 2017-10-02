package core;

//import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Fork(value=2, jvmArgs = {"-Xms4G", "-Xmx4G"})
public class DoublingDemo {
    public int doubleIt(int n) {
        try {
            Thread.sleep(100);
        } catch(InterruptedException e) {
        }
        return n*2;
    }

    @Benchmark
    public int doubleAndSumSequential() {
        return IntStream.of(3, 1, 4, 1, 5, 9)
                .map(this::doubleIt)
                .sum();
    }

    @Benchmark
    public int doubleAndSumParallel() {
        return IntStream.of(3, 1, 4, 1, 5, 9)
                .parallel()
                .map(this::doubleIt)
                .sum();
    }

    public static void main(String[] args) throws Exception {
        DoublingDemo obj = new DoublingDemo();

        System.out.println(obj.doubleAndSumSequential());

        System.out.println(obj.doubleAndSumParallel());

        //Main.main(args); 會跑很多不知道三小的東西
//        Options opt = new OptionsBuilder()
//                .include(DoublingDemo.class.getSimpleName())
//                .forks(1)
//                .build();
//
//        new Runner(opt).run();
    }
}
