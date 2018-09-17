import java.io.*;


/**
 * @author Cengiz Toprak
 */
public class Main {

    public static void main(String[] args){
        /*
        ** Before run main ,firstly read the 6.part of report.
        **
         */

        try {
            System.setIn(new FileInputStream(new File("input1.txt")));
        } catch (IOException param) {
            param.printStackTrace();
            System.exit(-1);
        }
        try {
            System.setOut(new PrintStream(new FileOutputStream("output.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-3);
        }
        HotelManagementSystem system = new HotelManagementSystem();
        system.hotel();
    }
}
