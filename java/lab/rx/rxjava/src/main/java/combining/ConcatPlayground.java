package combining;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

import static utils.Utils.sleep;

public class ConcatPlayground {
    public static void main(String[] args) {
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable<String> source2 = Observable.just("Zeta", "Eta", "Theta");

        Observable.concat(source1, source2)
                .subscribe(System.out::println);

        Observable<String> source3 = Observable.interval(1, TimeUnit.SECONDS)
                .take(2)
                .map(l -> l + 1)
                .map(l -> "Source3: " + l + " seconds");
        Observable<String> source4 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> (l+1)*300)
                .map(l -> "source4: " + l + " seconds");
        Observable.concat(source3, source4)
                .subscribe(i -> System.out.println("Received: " + i));
        sleep(5000);
    }
}
