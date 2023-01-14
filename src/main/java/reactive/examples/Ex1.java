package reactive.examples;

import io.reactivex.rxjava3.core.Observable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Ex1 {
    public static void main(String[] args) throws IOException {
        Observable<Long> red   = Observable.interval(1, TimeUnit.SECONDS);
        Observable<Long> green = Observable.interval(2, TimeUnit.SECONDS);

        Observable.zip(
                red.timestamp(),
                green.timestamp(),
                (r, g) -> r.time() - g.time()
        ).forEach(System.out::println);

        System.in.read();

    }
}
