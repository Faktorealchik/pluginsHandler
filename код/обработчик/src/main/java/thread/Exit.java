package thread;

import java.util.Map;

/**
 * Created by Александр on 30.05.2017.
 */
public class Exit implements Runnable {
    private final Map<String, Thread> map;

    public Exit(Map<String, Thread> map) {
        this.map = map;
    }

    /**
     * закрывает все открытые плагины
     */
    @Override
    public void run() {
        for (Thread t : map.values()) {
            if (t.isAlive()) {
                t.interrupt();
            }
        }
    }
}
