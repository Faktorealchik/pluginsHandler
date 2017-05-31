package helpers;

import java.io.PrintWriter;
import java.util.Map;

public class ServletHelper {
    private final Map<String, Thread> help;

    public ServletHelper(Map<String, Thread> help) {
        this.help = help;
    }

    /**
     * Создаем страничку, в ней для каждого плагина даем его ответ(ссылку)
     * */
    public void makeNewPage(PrintWriter resp) {
        resp.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Monitoring</title>\n" +
                "</head>\n" +
                "<body><div>");
        mkTab(resp);
        resp.println("</div></body>\n" +
                "</html>");

    }


    /**
     * создаем ответ каждого плагина
     * */
    private void mkTab(PrintWriter resp) {
        resp.println("<form method=\"post\" action=\"/again\">\n" +
                "   <p><b>What should work? </b></p>\n"
        );

        for (Map.Entry<String, Thread> map : help.entrySet()) {
            resp.println("<p><input type=\"checkbox\" name=\"" + map.getKey() + "\" checked>");
            resp.println("<a href=\"./out/" + map.getKey().replace(".jar", ".html") + "\">" + map.getKey() + " Am I working? "
                    + map.getValue().isAlive() + "</a><br>\n");
        }
        resp.println("</p><p><input type=\"submit\" value=\"Submit\"></p>\n" +
                "  </form>");
    }
}
