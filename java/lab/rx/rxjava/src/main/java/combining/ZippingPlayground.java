package combining;

import io.reactivex.Observable;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import static utils.Utils.sleep;

public class ZippingPlayground {
    public static void main(String[] args) {
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable<Integer> source2 = Observable.range(1, 6);

        source1.zipWith(source2, (s, i) -> s + "-" + i)
                .subscribe(System.out::println);

        Observable.zip(source1, source2, (s, i) -> s + "-" + i)
                .subscribe(System.out::println);

        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);
        Observable.zip(source1, seconds, (s, l) -> s)
                .subscribe(s -> System.out.println("Received: " + s + " at " + LocalTime.now()));

        sleep(6000);
    }
}
