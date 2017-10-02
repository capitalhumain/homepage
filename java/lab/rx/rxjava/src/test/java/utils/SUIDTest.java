package utils;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SUIDTest {
    @Test
    public void test_ten_id() {
        for(int i=0; i<10; i++) {
            long id = SUID.nextId();
            System.out.println(id);
//            byte[] bytes = ByteBuffer.allocate(4).putLong(id).array();
//            for(byte b : bytes) {
//                System.out.format("0x%x", b);
//            }
        }
        // 8,388,607
        //16777215
        byte[] bytes = ByteBuffer.allocate(4).putInt(16777215).array();
        for(byte b: bytes) {
            System.out.format("0x%x", b);
        }
        System.out.println();
        byte[] bs = new byte[3];
        bs[0] = bytes[1];
        bs[1] = bytes[2];
        bs[2] = bytes[3];

        System.out.println(Base64.getEncoder().encodeToString(bs));

        Pattern ptn = Pattern.compile("^[A-Z]{1}_[0-9]{5}(_[0-9]{5})?$");
        System.out.println(ptn.matcher("A_00991").matches());
        System.out.println(ptn.matcher("A_00991_00000").matches());
        System.out.println(ptn.matcher("C_00991_").matches());
        System.out.println(ptn.matcher("P_0000").matches());
    }

    @Test
    public void testNumberLong() {
        String victim = "{$numberLong=12321312312}";
        Pattern pattern = Pattern.compile(".*numberLong=([0-9]*).*");
        Matcher m = pattern.matcher(victim);
        System.out.println(m.matches());
        System.out.println(m.group(1));
    }
}
