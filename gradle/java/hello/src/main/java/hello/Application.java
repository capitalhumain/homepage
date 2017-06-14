package hello;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Application {
    public static void main(String[] args) {
        Bag<String> bag = new HashBag<>();
        bag.add("One");
        bag.add("Two");
        bag.add("Three");
        System.out.println(String.format("Size: %d", bag.size()));
        for(String item:bag) {
            System.out.println(item);
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update("password".getBytes(StandardCharsets.UTF_8));
            byte[] hash = digest.digest();
            StringBuffer hexString = new StringBuffer();
            for(int i=0; i<hash.length; i++) {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
            System.out.println(hexString.toString());
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace(System.out);
        }
    }
}
