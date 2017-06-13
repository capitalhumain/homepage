package hello;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;

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
    }
}
