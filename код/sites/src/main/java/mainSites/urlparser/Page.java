package mainSites.urlparser;

import mainSites.main.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


//печать странички
public class Page {

    private static volatile Page instance;

    public static Page getInstance() throws FileNotFoundException {
        Page local = instance;
        if (local == null) {
            synchronized (Page.class) {
                instance = local = new Page();
            }
        }
        return local;
    }

    private PrintWriter pw=new PrintWriter(Main.FILE_OUT);

    private Page() throws FileNotFoundException {

    }

    public void printAll(List<Link> links) {
        File file = new File(Main.FILE_OUT);
        try {
            pw = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sd = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z");
        printHeader();
        for (Link link : links) {
            printRow(link, sd);
        }
        printFooter();
    }

    private void printHeader() {
        pw.print("<html>\n<head>\n<title>Link Parser</title>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" >\n" +
                "</head>\n<body>\n");
        pw.print("<table border=\"1\" cellpadding=\"7\" cellspacing=\"1\">\n");
        pw.print("<tr>\n");
        pw.print("<td><b>название</b></td>\n" +
                //"<td></td>\n"+
                "<td><b>значение</b></td>\n" +
                "<td><b>статус</b></td>\n");
        pw.print("</tr>\n");
    }

    private void printRow(Link link, SimpleDateFormat sd) {
        pw.print("<tr valign=\"top\" align=\"left\">\n");
        pw.print("<td><a href=\"" + link.getUrl() + "\">" + link.getShortName() + "</a></td>\n");
        pw.print("<td class=\"value" + link.getShortName() + "\">\n" + link.getValue() + "\n</td>");
        pw.print("<td class=\"stop" + link.getShortName() + "\">");
        Date date = new Date();
        switch (link.getStatus()) {
            case Status.STATUS_OK:
                pw.print("обновлено " + sd.format(date));
                break;
            case Status.STATUS_WARN:
                pw.print("проблемы " + sd.format(date));
                break;
            case Status.STATUS_CHECKED:
                pw.print("проверено "+sd.format(date));
                break;
        }
        pw.print("<div style=\"display:none\">" + date.getTime() + "</div>");
        pw.print("</td></tr>\n");
    }


    private void printFooter() {
        pw.print("</table>\n" +
                "<h1><a href=\"index.html\">back</a></h1></h1></body>\n</html>\n");
        pw.flush();
        pw.close();
    }
}
