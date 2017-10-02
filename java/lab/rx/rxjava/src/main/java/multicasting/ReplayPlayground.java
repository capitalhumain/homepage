package multicasting;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

import static utils.Utils.sleep;

public class ReplayPlayground {
    public static void main(String[] args) {
        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS)
                .replay()
                .autoConnect();

        seconds.subscribe(l -> System.out.println("Observer 1: " + l));
        sleep(3000);
        seconds.subscribe(l -> System.out.println("Observer 2: " + l));
        sleep(3000);

        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .replay(1)
                .autoConnect();

        source.subscribe(s -> System.out.println("Observer 1:" + s));
        source.subscribe(s -> System.out.println("Observer 2:" + s));
    }
}
