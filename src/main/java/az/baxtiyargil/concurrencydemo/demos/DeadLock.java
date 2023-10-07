package az.baxtiyargil.concurrencydemo.demos;

import az.baxtiyargil.concurrencydemo.DeadLockExample;
import az.baxtiyargil.concurrencydemo.LeftRightDeadlock;
import lombok.SneakyThrows;

import java.time.Instant;
import java.util.concurrent.CountDownLatch;

public class DeadLock {
    private static final CountDownLatch LATCH = new CountDownLatch(1);

    @SneakyThrows
    public static void main(String[] args) {
        LeftRightDeadlock leftRightDeadlock = new LeftRightDeadlock();
        new Thread(leftRightDeadlock::leftRight).start();
        new Thread(leftRightDeadlock::rightLeft).start();
    }

    private static void testDeadlock() throws InterruptedException {
        DeadLockExample deadLockExampleParent = new DeadLockExample();
        DeadLockExample deadLockExampleChild = new DeadLockExample();

        new Thread(() -> {
            try {
                System.out.printf("[ %s ] created, blocked by the latch...\n", Thread.currentThread().getName());
                LATCH.await();
                System.out.printf("[ %s ] starts at: %s\n", Thread.currentThread().getName(), Instant.now());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            deadLockExampleParent.addChild(deadLockExampleChild);
        }).start();
        new Thread(() -> {
            try {
                System.out.printf("[ %s ] created, blocked by the latch...\n", Thread.currentThread().getName());
                LATCH.await();
                System.out.printf("[ %s ] starts at: %s\n", Thread.currentThread().getName(), Instant.now());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            deadLockExampleChild.setParent(deadLockExampleParent);
        }).start();

        Thread.sleep(2000);
        LATCH.countDown();

        System.out.println("Finished?");
    }
}
