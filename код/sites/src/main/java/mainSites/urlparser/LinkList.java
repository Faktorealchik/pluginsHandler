package mainSites.urlparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LinkList {
    private List<Link> list;

    /**
     * Создаем файл по пути
     * создаем новый список ссылок
     * получаем в него все ссылки
     * */
    public LinkList(String path) {
        File file = new File(path);
        list = new ArrayList<>();
        readFile(file);
    }

    //собираем все параметры
    public void readFile(File file) {
        try {
            BufferedReader r = new BufferedReader(new FileReader(file));

            String shortName = "";
            String url = "";
            String claz = "";
            int period = 0;
            while (r.ready()) {
                String st = r.readLine();
                String split;
                if (st.startsWith("url")) {
                    split = st.substring(4, st.length());
                    url = split;
                } else if (st.startsWith("name")) {
                    split = st.substring(5, st.length());
                    shortName = split;
                } else if (st.startsWith("period")) {
                    split = st.substring(7, st.length());
                    period = Integer.parseInt(split);
                } else if (st.startsWith("class")) {
                    split = st.substring(6, st.length());
                    claz = split;
                } else {
                    continue;
                }
                System.out.println(split);
                if (!url.isEmpty() && !shortName.isEmpty() && !claz.isEmpty()) {
                    Link l;

                    if (period == 0) {
                        l = new Link(shortName, url, claz);
                    } else {
                        l = new Link(shortName, url, claz, period);
                    }
                    if (l.getValue().isEmpty()) {
                        l.setStatus(2);
                    } else l.setStatus(1);

                    list.add(l);
                    url = "";
                    shortName = "";
                    claz = "";
                    period=0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //возвращает список ссылок
    public List<Link> getAll() {
        return list;
    }
}
