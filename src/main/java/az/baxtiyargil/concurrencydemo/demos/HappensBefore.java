package az.baxtiyargil.concurrencydemo.demos;

import az.baxtiyargil.concurrencydemo.component.ValueExchangerSync;
import az.baxtiyargil.concurrencydemo.component.ValueExchangerVolatile;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HappensBefore {

    private static final ValueExchangerSync VALUE_EXCHANGER_SYNC = new ValueExchangerSync();
    private static final ValueExchangerVolatile VALUE_EXCHANGER_VOLATILE = new ValueExchangerVolatile();

    public static void main(String[] args) {
        happensBeforeWithSync();
        //happensBeforeWithVolatile();
    }

    private static void happensBeforeWithVolatile() {
        ValueExchangerVolatile.Values values = ValueExchangerVolatile.Values.builder()
                .valA(3)
                .valB(4)
                .valC(5)
                .build();
        ValueExchangerVolatile.Values valuesZero = ValueExchangerVolatile.Values.builder().build();

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        executorService.submit(() -> VALUE_EXCHANGER_VOLATILE.set(values));
        executorService.submit(() -> VALUE_EXCHANGER_VOLATILE.get(valuesZero));
        executorService.shutdown();
    }

    private static void happensBeforeWithSync() {
        ValueExchangerSync.Values values = ValueExchangerSync.Values.builder()
                .valA(3)
                .valB(4)
                .valC(5)
                .build();
        ValueExchangerSync.Values valuesZero = ValueExchangerSync.Values.builder().build();

        new Thread(() -> VALUE_EXCHANGER_SYNC.set(values)).start();
        new Thread(() -> VALUE_EXCHANGER_SYNC.get(valuesZero)).start();
    }

}
