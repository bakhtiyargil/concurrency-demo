package az.baxtiyargil.concurrencydemo;

public class LeftRightDeadlock {

    private final Object left = new Object();
    private final Object right = new Object();

    public void leftRight() {
        synchronized (left) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (right) {
                System.out.println("Hit by: " + Thread.currentThread().getName());
            }
        }
    }

    public void rightLeft() {
        synchronized (right) {
            synchronized (left) {
                System.out.println("Hit by: " + Thread.currentThread().getName());
            }
        }
    }

}
