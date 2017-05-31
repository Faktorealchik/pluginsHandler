package mainSites.urlparser;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Александр on 28.05.2017.
 */
public class ParsTest {


    @Test
    public void test(){
        Pars p = new Pars();
        String st=p.parse("https://www.yandex.ru","ol[class=list.b-news-list]");
//        String ans = p.parse(st,"tv-list");
        System.out.println(st);
        Assert.assertEquals(st.length()>0,st.length());
    }

}