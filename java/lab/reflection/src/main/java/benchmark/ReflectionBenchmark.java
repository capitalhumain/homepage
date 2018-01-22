package benchmark;

import model.Person;
import org.openjdk.jmh.annotations.*;
import subjects.*;

import java.util.concurrent.TimeUnit;


@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 20, time = 1)
@Fork(3)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class ReflectionBenchmark {
    private Person person;

    private PropertyReader myPropertyReader = new MyPropertyReader();

    private PropertyReader myMethodHandlePropertyReader = new MyMethodHandlePropertyReader();

    private PropertyReader myReflectionPropertyReader = new MyReflectionPropertyReader();

    private PropertyReader myGeneratePropertyReader = new MyGeneratePropertyReader();

    private PropertyReader lambdaMetafactoryPropertyReader = new LambdaMetafactoryPropertyReader();

    @Setup
    public void setup() {
        person = new Person("Ann");
    }

    @Benchmark
    public String __000_DirectAccess() {
        return person.getName();
    }

    @Benchmark
    public String __001_MyPropertyReader() {
        return (String) myPropertyReader.executeGetter(person);
    }

    @Benchmark
    public String __002_MethodHandlePropertyReader() {
        return (String) myMethodHandlePropertyReader.executeGetter(person);
    }

    @Benchmark
    public String __003_ReflectionPropertyReader() {
        return (String) myReflectionPropertyReader.executeGetter(person);
    }

    @Benchmark
    public String __004_CompiledPropertyReader() {
        return (String) myGeneratePropertyReader.executeGetter(person);
    }

    @Benchmark
    public String __005_LambdaMetafactoryPropertyReader() {
        return (String) lambdaMetafactoryPropertyReader.executeGetter(person);
    }
}
