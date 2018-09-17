import java.io.IOException;

public interface User {
    /**
     * show the options to user.
    * */
    void showMenu();
    /**
     * reads data of guests or receptionists or rooms from given file.
     * @param filename name of file to be written.
     * @throws java.io.IOException
     * */
    void takeTheDataFromFile(String filename) throws IOException;
    /**
     * This function writes data of guests or receptionists or rooms
     * to given file.
     * @param filename name of file to be written.
     * @throws java.io.IOException
     * */
    void writeDataToFile(String filename)throws IOException;
}
