package helpers;

import thread.CommandStarter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainHelper {
    private Map<String, Thread> map;

    /**
     * возвращает карту, если была создана
     * или создает новую, если ее нет.
     */
    public Map<String, Thread> getMap() {
        if (map == null) {
            map = startPlugins();
        }
        return map;
    }

    public MainHelper() {
    }

    /**
     * возвращает карту - команду на нить, которая ее запускает
     */
    private Map<String, Thread> startPlugins() {
        /* читаем файл start.txt
         *  получаем команды в String[] commands*/
        Map<String, Thread> map = new HashMap<>();
        String[] commands = readTxtFile().split(" ");
        for (String command : commands) {
            Thread t = new Thread(new CommandStarter(command));
            t.start();
            map.put(command, t);
        }
        /*к примеру, у нас будет
          fileListener.jar
         SitesMonitoring.jar */

        return map;
        /*запускаем каждый процесс в отдельном потоке.*/

    }


    /**
     * Читаем .txt файл и собираем название каждого плагина
     */
    private String readTxtFile() {
        StringBuilder answer = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("start.txt"))) {
            while (reader.ready()) {
                answer.append(reader.readLine()).append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer.toString().trim();
    }
}