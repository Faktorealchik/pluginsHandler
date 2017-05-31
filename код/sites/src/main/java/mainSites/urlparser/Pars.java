package mainSites.urlparser;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


/**
 * класс, который парсит страницу.
 * */
public class Pars {
    public String parse(String url, String claz){
        Document html;
        StringBuilder build =new StringBuilder();
        try {
            html = Jsoup.connect(url).userAgent("Chrome").timeout(3000).get();
            Elements answer = html.select(claz);
            if(answer.isEmpty()){
                Element elem=html.getElementById(claz);
                if(elem!=null) return elem.toString();
                return "";
            }
            build = new StringBuilder();
            for (Object anAnswer : answer) {
                build.append(anAnswer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return build.toString();
    }
}
