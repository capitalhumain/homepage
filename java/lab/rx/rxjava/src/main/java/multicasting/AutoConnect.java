package multicasting;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

import static multicasting.MulticastingWithOperator.randomInt;
import static utils.Utils.sleep;

public class AutoConnect {
    public static void main(String[] args) {
        Observable<Integer> threeRandoms = Observable.range(1, 3)
                .map(i -> randomInt())
                .publish()
                .autoConnect(2);

        threeRandoms.subscribe(i -> System.out.println("Observer 1: " + i));
        threeRandoms.reduce(0, (total, next) -> total + next)
                .subscribe(i -> System.out.println("Observer 2: " + i));

        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS)
                .publish()
                .autoConnect();
        seconds.subscribe(i -> System.out.println("Observer 1: " + i));
        sleep(3000);
        seconds.subscribe(i -> System.out.println("Observer 2: " + i));
        sleep(3000);
    }
}
