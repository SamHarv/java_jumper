import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;

public class FileIO {
    public static final String FILE_NAME = "buildings.txt";
    private String fileName;

    public FileIO() {
        fileName = "unknown.txt";
    }

    public FileIO(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public String readFile() {
        String output = "";
        try {
            Scanner console = new Scanner(System.in);
            FileReader reader = new FileReader(FILE_NAME);
            try {
                Scanner fileInput = new Scanner(reader);
                while (fileInput.hasNextLine()) {
                    output += fileInput.nextLine() + "\n";
                }
                fileInput.close();
            } finally {
                try {
                    reader.close();
                    // console.close();
                } catch (Exception e) {
                    System.out.println("Error in closing reader! Exiting...");
                }
            }
        } catch (Exception e) {
            System.out.println("Error in writing to file! Exiting...");
        }
        return output;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void writeFile(String input) {
        try {
            FileWriter writer = new FileWriter("outcome.txt");
            try {
                writer.write(input);
            } finally {
                try {
                    writer.close();
                } catch (Exception e) {
                    System.out.println("Error in closing writer! Exiting...");
                }
            }
        } catch (Exception e) {
            System.out.println("Error in writing to file! Exiting...");
        }
    }

}
