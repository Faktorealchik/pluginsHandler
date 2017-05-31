package servlets;

import helpers.ServletHelper;
import thread.CommandStarter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class ListenServlet extends HttpServlet {
    private final Map<String, Thread> help;
    private final ServletHelper sh;

    public ListenServlet(Map<String, Thread> help) {
        this.help = help;
        sh = new ServletHelper(help);
    }


    /**
     * создаем новую страничку
     * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_ACCEPTED);
        sh.makeNewPage(resp.getWriter());
    }

    /**
     * для каждого плагина получаем boolean-значение, должен ли он работать
     * в соответсвии с этим запускаем или останавливаем нить с этим плагином.
     * Затем формируем новую страничку с новыми значениями плагинов(работает или нет)
     * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        for (Map.Entry<String, Thread> mapEntry : help.entrySet()) {
            //работает или нет?
            String answer = req.getParameter(mapEntry.getKey()); //null or on
            Thread t = mapEntry.getValue();
            //запускаем или останавливаем
            if (answer == null) {
                if (t.isAlive()) {
                    t.interrupt();
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                if (!t.isAlive()) {
                    t = new Thread(new CommandStarter(mapEntry.getKey()));
                    t.start();
                }
            }
            help.replace(mapEntry.getKey(), t);
        }
        //формируем страничку
        sh.makeNewPage(resp.getWriter());
    }
}
