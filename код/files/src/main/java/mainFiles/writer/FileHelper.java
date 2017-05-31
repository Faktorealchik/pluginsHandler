package mainFiles.writer;

import mainFiles.main.Main;

import java.io.*;

public class FileHelper {
    private File fileout;
    private PrintWriter pw;
    private File tmpfile;

    public FileHelper(File tmpfile) {
        this.fileout = Main.OUTPUTFILE;
        try {
            pw = new PrintWriter(fileout);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.tmpfile = tmpfile;
    }

    /**
     * выводит на экран
     */

    public void write(String str) {
        try {
            pw = new PrintWriter(fileout);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        printHeader(str);
    }

    /**
     * копирует файл во временный файл
     */
    public File copyFile(File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(tmpfile));
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (reader.ready()) {
                writer.write(reader.readLine() + "\n");
            }
            write("путь временного файла: " + tmpfile.getAbsolutePath());
            writer.flush();
            writer.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            tmpfile = null;
        }
        return tmpfile;
    }

    /**
     * находит различия в файлах
     */

    public String findChanges(File file, File tmpfile) {

        try {
            BufferedReader readerFile = new BufferedReader(new FileReader(file));
            BufferedReader readerTmpFile = new BufferedReader(new FileReader(tmpfile));
            StringBuilder answer = new StringBuilder();
            answer.append("путь временного файла: ").append(tmpfile.getAbsolutePath());
            while (readerFile.ready()) {
                String st = readerFile.readLine();
                String st1 = readerTmpFile.readLine();
                if (st.equals(st1)) {
                    //ok
                } else {
                    answer.append("<br>Some changes there: <br>was: ").append(st1).append(" <br>now: ").append(st);
                }
            }
            while (readerTmpFile.ready()) {
                String st1 = readerTmpFile.readLine();
                answer.append("<br>Some changes there: <br>was: ").append(st1).append(" <br>now:").append("null");
            }
            readerFile.close();
            readerTmpFile.close();

            if (answer.toString().isEmpty()) {
                return "";
            }
            return answer.append("<br>-----------------------<br>").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private void printHeader(String value) {
        pw.print("<html>\n<head>\n<title>Link Parser</title>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" >\n" +
                "</head>\n<body>\n");
        pw.print("<p>to stop write \"stop\"</p><div>" + value + "</div>");
        pw.print("</body>\n</html>\n");
        pw.flush();
        pw.close();
    }
}
