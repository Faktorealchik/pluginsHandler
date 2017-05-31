package mainFiles.thread;

import mainFiles.writer.FileHelper;

import java.io.File;

/**
 * Мониторинг путем хранения копии файла локально.
 */
public class ViewAllFile implements Runnable {
    private File file;
    private int period;
    private File tmpfile;
    public ViewAllFile(File file,int period, File tmpfile) {
        this.file = file;
        this.period = period;
        this.tmpfile=tmpfile;
    }

    /**
     * бесконечно проверяет изменился ли файл(с заданным периодом)
     * */
    @Override
    public void run() {
        FileHelper fileHelper = new FileHelper(tmpfile);
        tmpfile = fileHelper.copyFile(file);

        for (;;) {
            String st = fileHelper.findChanges(file, tmpfile);
            if(!st.isEmpty()){
                fileHelper.write(st);
            }
            try {
                Thread.sleep(period);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
