package mainFiles.thread;

import java.io.File;

/**
 * мониторинг по последнему изменению файла
 * Не используется(хотя тоже имеет право на жизнь)
 */

@Deprecated
public class Monitoring implements Runnable {
    private File file;
    private long lastUpdate;
    private int period;

    public Monitoring(File file, long lastUpdate, int period) {
        this.file = file;
        this.lastUpdate = lastUpdate;
        this.period = period;
    }

    @Override
    public void run() {
        for (; ; ) {
            long modified = file.lastModified();
            if (modified == lastUpdate) {
                System.out.println("Nothing to change");
                System.out.println(modified);
            } else {
                System.out.println("FILE was changed");
                lastUpdate = modified;
                System.out.println(modified);
            }
            try {
                Thread.sleep(period);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
