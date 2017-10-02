package core;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class GamePageLinksSupplierTest {
    @Test
    public void testHappy() {
        GamePageLinksSupplier supplier = new GamePageLinksSupplier(LocalDate.of(2017, 6, 5), 1);
        List<String> gids = supplier.get();
        assertNotNull(gids);
        gids.forEach(System.out::println);
    }
}
