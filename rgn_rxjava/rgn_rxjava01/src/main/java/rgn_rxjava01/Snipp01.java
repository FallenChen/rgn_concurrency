/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rgn_rxjava01;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 *
 * @author npadilha
 */
public class Snipp01 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("One", "Two", "Three", "Four", "Five");

        Observable<String> observable = Observable.from(list);

        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String element) {
                System.out.println(element);
            }
        },
            new Action1<Throwable>() {
                @Override
                public void call(Throwable t) {
                    System.err.println(t);
                }
            }, new Action0() {
                @Override
                public void call() {
                    System.out.println("We're finished");
                }
            });

        //iteratorSample();
    }

    private static void iteratorSample() {
        List<String> list = Arrays.asList("One", "Two", "Three", "Four", "Five");
        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
