package az.baxtiyargil.concurrencydemo.demos;

import az.baxtiyargil.concurrencydemo.component.ThreadSafety;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Concurrency {

    private static final ThreadSafety threadSafetyObj = new ThreadSafety();
    private static final Runnable task = () -> System.out.println("Hit by: " + Thread.currentThread().getName());

    public static void main(String[] args) {

        /*
        THREAD_SAFETY_OBJ.getInstance();
        THREAD_SAFETY_OBJ.getInstance();
        THREAD_SAFETY_OBJ.getInstance();
        */

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        executorService.submit(threadSafetyObj::getInstance);
        executorService.submit(threadSafetyObj::getInstance);
        executorService.submit(threadSafetyObj::getInstance);

        executorService.submit(threadSafetyObj::getSafeInstanceSync);
        executorService.submit(threadSafetyObj::getSafeInstanceSync);
        executorService.submit(threadSafetyObj::getSafeInstanceSync);


        /*
         * With and without shutdown
         */
        executorService.shutdown();


//        run by another new thread
//        Thread th = new Thread(task);
//        th.start();

//        run by main thread
//        task.run();
    }

}
