package mainSites.main;

import mainSites.thread.Update2;
import mainSites.urlparser.Link;
import mainSites.urlparser.LinkList;
import mainSites.urlparser.Page;

import java.util.List;


public class Main {

    public static final String FILE_OUT = "../public_html/out/SitesMonitoring.html";
    private final static String FILE_IN = "links.txt";
    public static volatile List<Link> linkList;


    public static void main(String[] args) throws Exception {
        linkList = new LinkList(FILE_IN).getAll(); // получаем все ссылки в файле

        Page p = Page.getInstance(); //получаем страничку
        p.printAll(linkList);//печатаем имеющееся в файл
        for (int i = 0; i<linkList.size();i++) { //и закускаем нити на слежение
            Thread t = new Thread(new Update2(linkList.get(i),i));
            t.start();
        }
    }
}
