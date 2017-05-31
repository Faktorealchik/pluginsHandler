package mainSites.thread;

import mainSites.main.Main;
import mainSites.urlparser.Link;
import mainSites.urlparser.Page;
import mainSites.urlparser.Pars;
import mainSites.urlparser.Status;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Класс-нить, отвечаающий за просмотр изменений.
 */
public class Update2 implements Runnable {

    private Link link;
    private final Page page;
    private int id;

    /**
     * в page получаем актуальную ссылку
     * */
    public Update2(Link link, int id) throws FileNotFoundException {
        this.link = link;
        page=Page.getInstance();
        this.id=id;
    }

    /**
     * бесконечно сравниваем актуальное значение со значением в файле
     * если что-то поменялось выводим, что-то поменялось и сразу пишем в файл
     * */
    @Override
    public void run() {
        Pars p = new Pars();
        for (;;) {
            String actual = p.parse(link.getUrl(), link.getClaz());
            String was = getFromFile(link.getShortName());

            if (actual.equals(was)) {
                System.out.println("Nothing changed");
                link.setStatus(Status.STATUS_CHECKED);
                //ok, continue;
            } else {//если что-то изменилось, то меняемч
                System.out.println("something was changed");
                link.setValue(actual);
                Main.linkList.set(id,link);
                page.printAll(Main.linkList);
            }
            try {
                Thread.sleep(link.getPeriod());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * получаем значения с файла
     * получаем Link с файла.
     */
    private String getFromFile(String name) {
        StringBuilder answer= new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(Main.FILE_OUT))) {
            while(reader.ready()){
                String st = reader.readLine();

                if(st.contains("class=\"value"+name+"\"")){//начинается нужный блок
                    while(true){
                        st=reader.readLine();
                        if(st.contains("class=\"stop"+name+"\"")){ //если в строке есть конец строки, закончить
                            reader.close();
                            return answer.toString().trim();
                        }
                        else{ //иначе добавить
                            answer.append(st).append("\n");
                        }
                    }
                }
            }
        } catch (IOException e) {
            answer.append("");
            e.printStackTrace();
        }
        return answer.toString();
    }
}
