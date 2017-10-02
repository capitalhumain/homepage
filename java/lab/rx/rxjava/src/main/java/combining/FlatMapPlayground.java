package combining;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

import static utils.Utils.sleep;

public class FlatMapPlayground {
    public static void main(String[] args) {
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        source1.flatMap(s -> Observable.fromArray(s.split("")))
                .subscribe(System.out::println);

        Observable<String> source2 = Observable.just("521934/2342/FOXTROT", "21962/12112/78886/TANGO","283242/4542/WHISKEY/2348562");
        source2.flatMap(s -> Observable.fromArray(s.split("/")))
                .filter(s -> s.matches("[0-9]+"))
                .map(Integer::valueOf)
                .subscribe(System.out::println);

        Observable<Integer> intervalArguments = Observable.just(2, 3, 10, 7);

        intervalArguments.flatMap(i -> Observable.interval(i, TimeUnit.SECONDS)
                                                .map(i2 -> i + "s interval: " + ((i+1)*i) + " seconds elapsed")
                                ).subscribe(System.out::println);
        sleep(12000);
    }
}
