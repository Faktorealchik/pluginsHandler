package main;

import thread.CommandStarter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainHelper {
    private Map<String, Thread> map;

    public Map<String, Thread> getMap() {
        if (map == null) {
            map = run();
        }
        return map;
    }

    public MainHelper() {
    }

    /**
     * возвращает карту команду на нить, которая ее запускает
     */
    private Map<String, Thread> run() {
        /* читаем файл start.txt
         *  получаем команды в String[] commands*/
        Map<String, Thread> map = new HashMap<>();
        String[] commands = readTxtFile().split(" ");
        for (int i = 0; i < commands.length; i++) {
            Thread t = new Thread(new CommandStarter(commands[i]));
            t.start();
            map.put(commands[i], t);
        }
        /*к примеру, у нас будет
          fileListener.jar
         SitesMonitoring.jar */

        return map;
        /*запускаем каждый процесс в отдельном потоке.*/

    }

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