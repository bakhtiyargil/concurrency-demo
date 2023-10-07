package az.baxtiyargil.concurrencydemo.component;

/**
 * Sample class for test thread safety of "Object Member Variables".
 */
public class ThreadSafety {

    private Object instance = null;
    private Object safeInstance = null;
    private static Object safeInstanceStatic = null;


    /**
     * Not thread safe method for initialize Object
     */
    public Object getInstance() {
        if (instance == null) {
            System.out.println("NoThreadSafety: Hit by " + Thread.currentThread().getName());
            instance = new Object();
        }
        return instance;
    }

    /**
     * Thread safe method for initialize Object - "synchronized".
     * <p>
     * One thread in total per instance.
     * <p>
     */
    public synchronized Object getSafeInstanceSync() {
        if (safeInstance == null) {
            System.out.println("ThreadSafetySync: Hit by " + Thread.currentThread().getName());
            safeInstance = new Object();
        }
        return safeInstance;
    }

    /**
     * Thread safe method for initialize Object - "synchronized" block.
     * <p>
     * Only one thread can execute inside a Java code block synchronized on the same monitor object.
     * <p>
     */
    public Object getSafeInstanceSyncBlock() {
        synchronized (this) {
            if (safeInstance == null) {
                System.out.println("ThreadSafetySyncBlock: Hit by " + Thread.currentThread().getName());
                safeInstance = new Object();
            }
            return safeInstance;
        }
    }

    /**
     * Thread safe static method for initialize Object - "synchronized" block.
     * <p>
     * One thread per class regardless of which static synchronized method it calls.
     * <p>
     */
    public static synchronized Object getSafeInstanceSyncStatic() {
        if (safeInstanceStatic == null) {
            System.out.println("ThreadSafetySyncBlock: Hit by " + Thread.currentThread().getName());
            safeInstanceStatic = new Object();
        }
        return safeInstanceStatic;
    }

    /**
     * Thread safe static method for initialize Object - "synchronized".
     * <p>
     * Only one thread can execute inside method at the same time. Same as static sync methods.
     * <p>
     */
    public static synchronized Object getSafeInstanceSyncBlockStatic() {
        synchronized (ThreadSafety.class) {
            if (safeInstanceStatic == null) {
                System.out.println("ThreadSafetySyncBlock: Hit by " + Thread.currentThread().getName());
                safeInstanceStatic = new Object();
            }
            return safeInstanceStatic;
        }
    }

    //Sync in lambda expressions
    //What Objects to Synchronize On
}
