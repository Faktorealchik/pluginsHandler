package mainFiles.main;

import mainFiles.thread.ViewAllFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


public class Main {

    public final static File OUTPUTFILE = new File("../public_html/out/fileListener.html");
    private final static String PROPERTY = "test.properties";

    public static void main(String[] args) throws Exception {
        File tmpfile = new File("monitoring.txt");
        System.out.println(tmpfile.getAbsolutePath());
        Runtime.getRuntime().addShutdownHook(new Thread(tmpfile::delete));

        Properties properties = new Properties();
        InputStream input = new FileInputStream(PROPERTY);
        properties.load(input);
        input.close();

        String fname = properties.getProperty("fname");
        int period = Integer.parseInt(properties.getProperty("period"));

        System.out.println(fname);
        System.out.println(period);

        File file = new File(fname);

        Thread fileListener = new Thread(new ViewAllFile(file, period, tmpfile));
        fileListener.setPriority(3);
        fileListener.start();
    }

}