package combining;

import io.reactivex.Observable;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static utils.Utils.sleep;

public class CombineLatestPlayground {
    public static void main(String[] args) {
        Observable<Long> source1 = Observable.interval(300, TimeUnit.MILLISECONDS);
        Observable<Long> source2 = Observable.interval(1, TimeUnit.SECONDS);

        System.out.println(LocalDateTime.now());
        Observable.combineLatest(source1, source2, (l1, l2) -> "SOURCE 1:" + l1 + " SOURCE 2: " + l2 + " " + LocalDateTime.now())
                .subscribe(System.out::println);

        sleep(3000);

        /*
        source1前面不會印出來，一秒後source2第一個emit出去，當時source1是emit最新的值是2
        1200millisecond

2017-09-25T13:48:52.023
SOURCE 1:2 SOURCE 2: 0 2017-09-25T13:48:53.074   <---- 1sec
SOURCE 1:3 SOURCE 2: 0 2017-09-25T13:48:53.276   <---- 1200 milliseconds
SOURCE 1:4 SOURCE 2: 0 2017-09-25T13:48:53.576
SOURCE 1:5 SOURCE 2: 0 2017-09-25T13:48:53.875
SOURCE 1:5 SOURCE 2: 1 2017-09-25T13:48:54.073   <---- 2sec
SOURCE 1:6 SOURCE 2: 1 2017-09-25T13:48:54.175
SOURCE 1:7 SOURCE 2: 1 2017-09-25T13:48:54.471
SOURCE 1:8 SOURCE 2: 1 2017-09-25T13:48:54.775
SOURCE 1:9 SOURCE 2: 1 2017-09-25T13:48:55.075
SOURCE 1:9 SOURCE 2: 2 2017-09-25T13:48:55.075   <---- 3sec
         */
    }
}
