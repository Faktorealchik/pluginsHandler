package mainFiles.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Александр on 28.05.2017.
 */
public class WaitInterrupt implements Runnable {
    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (;;){
            String st= null;
            try {
                st = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(st.equals("stop")){
                Thread.currentThread().interrupt();
            }
        }
    }
}
