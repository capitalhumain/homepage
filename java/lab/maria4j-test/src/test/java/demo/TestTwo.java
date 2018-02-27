package demo;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestTwo {
    @Test
    public void happy() throws Exception {
        System.out.println("TestTwo#happy running");
        assertTrue(true);
        try {
            DBTestLock.acquire();
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:9999/test", "root", "");
            assertNotNull(conn);
            conn.close();
            DBTestLock.release();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
