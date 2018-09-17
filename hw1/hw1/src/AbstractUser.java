import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Cengiz Toprak
 */
public abstract class AbstractUser implements User {

    public ArrayList<String> rooms = new ArrayList<>();
    public ArrayList<String> guest = new ArrayList<>();
    public ArrayList<String> receptionist = new ArrayList<>();
    private String name;
    public  static Scanner INPUT = new Scanner(System.in);
    private String password;
    public  AbstractUser() {
        name = "";
        password ="";
    }
    public String getName() {return name;}

    public String getPassword() {return password;}

    public void setName(String name) { this.name = name;}

    public void setPassword(String password) { this.password = password; }

    private final String NEW_LINE = "\n";
    @Override
    public void takeTheDataFromFile(String filename) throws IOException {
        BufferedReader file;
        String line;
        file = new BufferedReader(new FileReader(filename));
        if (filename.equalsIgnoreCase("rooms")) {
            while((line=file.readLine())!= null)
                rooms.add(line + NEW_LINE);

            file.close();
        }
        else if (filename.equalsIgnoreCase("guests")) {
            while((line=file.readLine())!= null)
                if (!(guest.contains(line + NEW_LINE)))
                    guest.add(line + NEW_LINE);
            file.close();
        }
        else if (filename.equalsIgnoreCase("receptionists")) {
            while((line=file.readLine())!= null)
                if (!(receptionist.contains(line + NEW_LINE)))
                receptionist.add(line + NEW_LINE);
            file.close();
        }


    }

    @Override
    public void writeDataToFile(String filename) throws IOException {
        FileWriter file;
        file = new FileWriter(filename);
        if (filename.equalsIgnoreCase("rooms")) {

            for (int i = 0; i < 16; ++i)
                file.write(rooms.get(i));
            file.close();
        }
        else if (filename.equalsIgnoreCase("guests")) {

            for (int i = 0; i < guest.size(); ++i)
                file.append(guest.get(i));
            file.close();
        }
        else if (filename.equalsIgnoreCase("receptionists")) {

            for (int i = 0; i < receptionist.size(); ++i)
                file.append(receptionist.get(i));
            file.close();
        }
    }

    public void showMenu() {
        System.out.println("0 --> for  log in as guest");
        System.out.println("1 --> for  log in as receptionist");
        System.out.println("2 --> for  log in as manager");
        System.out.println("3 --> for  log out from system");
    }
}
