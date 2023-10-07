package az.baxtiyargil.concurrencydemo.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Race condition example: Read-Modify-Write (Rmw)
 */

@WebServlet(
        name = "raceConditionRmw",
        description = "Example Servlet Race Condition Rmw",
        urlPatterns = {"/race-condition-rmw"}
)
public class RaceConditionRmw extends HttpServlet {

    private long count = 0;

    public long getCount() {
        return count;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        count++;
        System.out.println(Thread.currentThread().getName() + " " + getCount());
    }

    public void destroy() {
    }

}