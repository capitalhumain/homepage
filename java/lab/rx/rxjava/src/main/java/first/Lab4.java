package first;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

public class Lab4 {
    public static void main(String[] args) {
        // just()
        // Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        // fromIterable()
        List<String> items = Arrays.asList("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable<String> source = Observable.fromIterable(items);

        source.map(String::length)
                .filter(i -> i>=5 )
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
