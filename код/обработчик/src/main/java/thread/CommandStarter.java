package thread;

import java.io.File;
import java.io.IOException;

/**
 * Created by Александр on 29.05.2017.
 */
public class CommandStarter implements Runnable {

    private final String command;

    public CommandStarter(String command) {
        this.command = command;
    }

    @Override
    public void run() {
        Process proc = null;
        try {
            ProcessBuilder pb = new ProcessBuilder("java", "-jar", command);
            pb.directory(new File("./jar"));
            proc = pb.start();
            System.out.println("Job running");
            proc.waitFor(); // wait until jar is finished
            System.out.println("Job finished");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Job interrupted");
        } finally {
            if (proc != null) {
                proc.destroy();
            }
        }

    }
}
