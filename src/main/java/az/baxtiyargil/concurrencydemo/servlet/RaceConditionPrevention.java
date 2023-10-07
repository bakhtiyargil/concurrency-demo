package az.baxtiyargil.concurrencydemo.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

@WebServlet(
        name = "raceConditionPrevention",
        description = "Example Servlet Race Condition Prevention",
        urlPatterns = {"/race-condition-prv"}
)
public class RaceConditionPrevention extends HttpServlet {

    private final AtomicLong atomicCount = new AtomicLong(0);
    private long count;

    private AtomicLong getAtomicCount() {
        return atomicCount;
    }

    private long getCount() {
        return count;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        withAtomicLong();
        withSynchronized();
    }

    /**
     * Preventing race condition with 'AtomicLong'
     */
    private void withAtomicLong() {
        atomicCount.getAndIncrement();
        System.out.println(Thread.currentThread().getName() + " " + getAtomicCount());
    }

    /**
     * Preventing race condition with 'synchronized' block
     */
    private void withSynchronized() {
        synchronized (this) {
            count++;
            System.out.println(Thread.currentThread().getName() + " " + getCount());
        }
    }

}