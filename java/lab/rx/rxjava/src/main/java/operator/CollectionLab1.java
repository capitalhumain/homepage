package operator;

import com.google.common.collect.ImmutableList;
import io.reactivex.Observable;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public class CollectionLab1 {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toList()
                .subscribe(s -> System.out.println("Received: " + s));

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toList(3)
                .subscribe(s -> System.out.println("Received: " + s));

        Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3)
                .toSortedList()
                .subscribe(s -> System.out.println("Receive: " + s));

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toMap(s -> s.charAt(0))
                .subscribe(m -> System.out.println("Receive: " + m));

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toMap(s -> s.charAt(0), String::length)
                .subscribe(s -> System.out.println("Receive: " + s));

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toMap(s -> s.charAt(0), String::length, ConcurrentHashMap::new)
                .subscribe(s -> System.out.println("Receive: " + s));

        // 長度一樣的
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toMap(String::length)
                .subscribe(s -> System.out.println("Receive: " + s));

        // 長度一樣的會變成list
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon", "Gamma")
                .toMultimap(String::length)
                .subscribe(s -> System.out.println("Receive: " + s));

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .collect(HashSet::new, HashSet::add)
                .subscribe(s -> System.out.println("Receive: " + s));

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilom")
                .collect(ImmutableList::builder, ImmutableList.Builder::add)
                .map(ImmutableList.Builder::build)
                .subscribe(s -> System.out.println("Receive: " + s));
    }
}
