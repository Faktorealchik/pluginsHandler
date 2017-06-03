package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * закрывает все плагины
 */
public class ExitServlet extends HttpServlet {
    private final Map<String, Thread> map;

    public ExitServlet(Map<String, Thread> map) {
        this.map = map;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            Thread exit = new Thread(() -> {
                for (Map.Entry<String, Thread> m : map.entrySet()) {
                    Thread t = m.getValue();
                    if (t.isAlive()) {
                        t.interrupt();
                        try {
                            t.join();
                        } catch (InterruptedException ignored) {
                        }
                    }

                }
            });

            exit.start();
            exit.join();

            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
