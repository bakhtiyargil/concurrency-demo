package az.baxtiyargil.concurrencydemo.component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Sample class for test 'ThreadLocal' variables.
 */
public class ThreadLocalSample {

    private static final ThreadLocal<Connection> connectionHolder = ThreadLocal.withInitial(() -> {
        try {
            return DriverManager.getConnection("DB_URL");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    });

    /**
     * Method for getting new 'Connection' object for each thread call.
     */
    public static Connection getConnection() {
        return connectionHolder.get();
    }

}
