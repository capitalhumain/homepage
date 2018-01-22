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

    private PropertyAccessor myAccessor = new MyAccessor();

    private PropertyAccessor myMethodHandleAccessor = new MyMethodHandleAccessor();

    private PropertyAccessor myReflectionAccessor = new MyReflectionAccessor();

    private PropertyAccessor myGenerateAccessor = new MyGenerateAccessor();

    private PropertyAccessor lambdaMetafactoryAccessor = new LambdaMetafactoryAccessor();

    @Setup
    public void setup() {
        person = new Person("Ann");
    }

    @Benchmark
    public String __000_DirectAccess() {
        return person.getName();
    }

    @Benchmark
    public String __001_AccessorClass() {
        return (String) myAccessor.executeGetter(person);
    }

    @Benchmark
    public String __002_MethodHandle() {
        return (String) myMethodHandleAccessor.executeGetter(person);
    }

    @Benchmark
    public String __003_Reflection() {
        return (String) myReflectionAccessor.executeGetter(person);
    }

    @Benchmark
    public String __004_CompiledAccessor() {
        return (String) myGenerateAccessor.executeGetter(person);
    }

    @Benchmark
    public String __005_LambdaAccessor() {
        return (String) lambdaMetafactoryAccessor.executeGetter(person);
    }
}
