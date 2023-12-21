import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class fileWriteExample {
    public static void main(String[] args) throws IOException {
        File l = new File("FirstDemo.txt");
        FileWriter file = new FileWriter("/home/dev1068/Trup/trup@softvan.in/Intern-tasks/FirstDemo.txt");
        file.write("Third Line");

        Scanner scanner = new Scanner(l);
        while (scanner.hasNextLine()){
            String data= scanner.nextLine();
            System.out.println(data);
        }
        scanner.close();

        file.close();
        System.out.println("Done");


    }
}
