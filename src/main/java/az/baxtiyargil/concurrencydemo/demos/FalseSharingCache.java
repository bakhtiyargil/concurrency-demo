package az.baxtiyargil.concurrencydemo.demos;

import az.baxtiyargil.concurrencydemo.component.FalseSharingCounter;

public class FalseSharingCache {

    private static final FalseSharingCounter COUNTER_1 = new FalseSharingCounter();
    private static FalseSharingCounter counter2 = COUNTER_1;
    private static final long ITERATIONS = 1_000_000_000;

    private static final Thread THREAD_1 = new Thread(() -> {
        long startTime = System.currentTimeMillis();
        for (long i = 0; i < ITERATIONS; i++) {
            COUNTER_1.count1++;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("total time: " + (endTime - startTime));
    });
    private static final Thread THREAD_2 = new Thread(() -> {
        long startTime = System.currentTimeMillis();
        for (long i = 0; i < ITERATIONS; i++) {
            counter2.count2++;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("total time: " + (endTime - startTime));
    });

    public static void main(String[] args) {
        //withSameCacheLine();
        withDifferentCacheLine();
    }


    /**
     * Counter1 and Counter2 are same objects so in same cache lines
     */
    private static void withSameCacheLine() {
        THREAD_1.start();
        THREAD_2.start();
    }

    /**
     * Counter1 and Counter2 are different objects so in different cache lines
     */
    private static void withDifferentCacheLine() {
        counter2 = new FalseSharingCounter();

        THREAD_1.start();
        THREAD_2.start();
    }

}

