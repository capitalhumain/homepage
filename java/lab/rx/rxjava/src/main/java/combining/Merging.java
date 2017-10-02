package combining;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static utils.Utils.sleep;

public class Merging {
    private static void merge1() {
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable<String> source2 = Observable.just("Zeta", "Eta", "Theta");
        Observable.merge(source1, source2)
                .subscribe(s -> System.out.println("Receive: " + s));
    }

    private static void merge2() {
        Observable<String> source1 = Observable.just("Alpha", "Beta");
        Observable<String> source2 = Observable.just("Gamma", "Delta");
        Observable<String> source3 = Observable.just("Epsilon", "Zeta");
        Observable<String> source4 = Observable.just("Eta", "Theta");
        Observable<String> source5 = Observable.just("Iota", "Kappa");

        Observable.mergeArray(source1, source2, source3, source4, source5)
                .subscribe(s -> System.out.println("Receive: " + s));
    }

    private static void merge3() {
        Observable<String> source1 = Observable.just("Alpha", "Beta");
        Observable<String> source2 = Observable.just("Gamma", "Delta");
        Observable<String> source3 = Observable.just("Epsilon", "Zeta");
        Observable<String> source4 = Observable.just("Eta", "Theta");
        Observable<String> source5 = Observable.just("Iota", "Kappa");

        List<Observable<String>> sources = Arrays.asList(source1, source2, source3, source4, source5);

        Observable.merge(sources)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }

    public static void mergeInterval() {
        Observable<String> source1 = Observable.interval(1, TimeUnit.SECONDS)
                .map(l -> l + 1)
                .map(l -> "Source1: " + l + " seconds");
        Observable<String> source2 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> l+1)
                .map(l -> "Source2: " + (l*300) + " milliseconds");
        Observable.merge(source1, source2)
                .subscribe(System.out::println);
    }

    public static void main(String[] args) {
        merge1();
        merge2();
        merge3();

        mergeInterval();
        sleep(3000);
    }
}
