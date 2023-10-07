package az.baxtiyargil.concurrencydemo.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Race condition example: Check-Then-Act (Cta)
 */

@WebServlet(
        name = "raceConditionCta",
        description = "Example Servlet Race Condition Cta",
        urlPatterns = {"/race-condition-cta"}
)
public class RaceConditionCta extends HttpServlet {

    private final Map<String, String> sharedMap = new HashMap<>();

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (sharedMap.containsKey("key")) {
            String val = sharedMap.remove("key");
            if (val == null) {
                System.out.println("Value for 'key' was null");
            }
        } else {
            sharedMap.put("key", "value");
        }
    }

    public void destroy() {
    }
}