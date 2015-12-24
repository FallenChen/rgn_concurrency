/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rgn_rxjava01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.ConnectableObservable;

/**
 *
 * @author npadilha
 */
public class ReactiveSumExample {

    public static void main(String[] args) {
        System.out.println("Reacitve Sum. Type 'a: <number>' and 'b: <number>' to try it.");
        ConnectableObservable<String> input = from(System.in);

        Observable<Double> a = varStream("a", input);
        Observable<Double> b = varStream("b", input);

        input.connect();

    }

    private static ConnectableObservable<String> from(final InputStream in) {
        return from(new BufferedReader(new InputStreamReader(in)));

    }

    private static ConnectableObservable<String> from(final BufferedReader reader) {

        return Observable.create(new OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                try {
                    String line;
                    while (!subscriber.isUnsubscribed()
                        && (line = reader.readLine()) != null) {
                        subscriber.onNext(line);

                    }

                } catch (IOException e) {
                    subscriber.onError(e);
                }
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            }
        }).publish();

    }

    private static Observable<Double> varStream(String varName, Observable<String> input) {

        final Pattern pattern = Pattern.compile("\\^s*" + varName + "\\s*[:|=]\\s*(-?\\d+\\.?\\d*)$");

        return input.map(new Func1<String, Matcher>() {

            @Override
            public Matcher call(String str) {
                return pattern.matcher(str);
            }
        }).filter(new Func1<Matcher, Boolean>() {

            @Override
            public Boolean call(Matcher matcher) {
                return matcher.matches() && matcher.group(1) != null;
            }
        }
        ).map(new Func1<Matcher, Double>() {

            @Override
            public Double call(Matcher matcher) {
                return Double.parseDouble(matcher.group(1));
            }
        }
        );
    }

    public static final class ReactiveSumObserver implements Observer<Double> {

        private double sum;

        public ReactiveSumObserver(Observable<Double> a, Observable<Double> b) {
            this.sum = 0;
            Observable.combineLatest(a, b, new Func2<Double, Double, Double>() {

                @Override
                public Double call(Double a, Double b) {
                    return a + b;
                }
            })
                .subscribe(this);
        }

        @Override
        public void onCompleted() {
            System.out.println("Exiting last sum was: " + this.sum);
        }

        @Override
        public void onError(Throwable thrwbl) {
            System.err.println("Error! ");
            thrwbl.printStackTrace();
        }

        @Override
        public void onNext(Double sum) {
            this.sum = sum;
            System.out.println("update : a + b = " + sum);
        }

    }
}
