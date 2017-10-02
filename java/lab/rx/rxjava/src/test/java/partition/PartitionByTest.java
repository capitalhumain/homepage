package partition;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class PartitionByTest {
    @Test
    public void theCorrect() {
        List<Integer> source = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
        
        System.out.println("==== partitioningBy");
        Map<Boolean, List<Integer>> groups = source.stream().collect(Collectors.partitioningBy(s -> s > 3));

        assertEquals(2, groups.size());
        groups.forEach((k, v) -> System.out.println(k + ": " + v));

        System.out.println("==== groupingBy");
        Map<Integer, List<Integer>> groups2 = source.stream().collect(Collectors.groupingBy(s -> s%3));
        assertEquals(3, groups2.size());
        groups2.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
