import java.io.*;
import java.util.ArrayList;

public class FileHandler{
    private String fileName;
    public FileHandler(String fileName) {
        this.fileName = fileName;
        File file = new File(this.fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception error) {
                System.out.println("An error occurred when creating a file");
            }
        }
    }
        public ArrayList<String> readFile() {
            ArrayList<String> lines = new ArrayList<>();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(this.fileName));
                String line = reader.readLine();
                while (line != null) {
                    lines.add(line);
                    line = reader.readLine();
                }
                reader.close();
            } catch (Exception error) {
                System.out.println("An error occurred when reading the file");
            }
            return lines;
        }

    public void updateFile(String line)  {
        try {
            File file=new File(this.fileName);
            FileWriter Writer=new FileWriter(file,true);
            BufferedWriter bufferedWriter=new BufferedWriter(Writer);
            PrintWriter priWriter=new PrintWriter(bufferedWriter);
            priWriter.print("\n"+line);
            bufferedWriter.close();
            Writer.close();

        } catch (Exception error) {
            System.out.println("An error occurred when updating the file");
        }


    }
}

