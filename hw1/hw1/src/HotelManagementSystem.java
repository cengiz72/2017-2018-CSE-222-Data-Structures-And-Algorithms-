import java.io.IOException;
import java.util.InputMismatchException;
public class HotelManagementSystem extends AbstractUser{


    private  final String COMMA  = ",";
    private AbstractUser rec = new Receptionist();
    private AbstractUser _guest = new Guest();
    private AbstractUser manager = new Manager();
    /**
     * Helper function for entering the hotel system.
     * @param choice for determining user type(guest,receptionist or manager).
     */
    public void logIn(int choice) {
        String name  ="";
        String password;
        boolean flag = true;
        try {
            takeTheDataFromFile("guests");
            takeTheDataFromFile("receptionists");
        }
        catch (IOException param){
            System.out.println("An error occurred during file operation");
            param.printStackTrace();
            System.exit(-1);
        }
        System.out.print("Name:");
        name = INPUT.next();
        System.out.println(name);
        System.out.print("Password:");
        password = INPUT.next();
        System.out.println(password);

        if (choice == 0) {
            _guest.setName(name);
            _guest.setPassword(password);
            _guest.showMenu();
        }
        else if (choice == 1) {
            for (int i = 0; i < receptionist.size() && flag; ++i) {
                if (receptionist.get(i).split(COMMA)[0].equals(name) &&
                        receptionist.get(i).split(COMMA)[1]
                                .replace("\n","").equals(password)) {
                    rec.setName(name);
                    rec.setPassword(password);
                    rec.showMenu();
                    flag = false;
                }
            }
            if (flag)
                System.out.println("You are not a receptionist!");
        }
        else if (choice == 2) {
            if (manager.getName().equals(name) && manager.getPassword().equals(password))
                manager.showMenu();
            else
                System.out.println("You are not manager!");
        }
    }

    /**
     * Helper function for deciding exit the system.
     * @param choice for determining return value.
     * @return if value of param is 3 return true ,if not ,return false.
     */
    public boolean exitTheSystem(int choice) {
        if (choice == 3) return true;
        return false;
    }

    /**
     * Helper function for taking preference of user.
     * @return return user choice as integer number
     */
    public int takechoice() {
        boolean flag = true;
        int choice = 0;
        try {
            do {
                showMenu();
                System.out.print("choice:");
                choice = INPUT.nextInt();
                System.out.println(choice);
                if (choice < 0 || choice > 3)
                    System.out.println("Please just select above options");
                else{
                    flag = false;
                }

            } while (flag);
        }catch (InputMismatchException param) {
            System.out.println("Please just only enter integer");
            param.printStackTrace();
            System.exit(-1);
        }
        return  choice;
    }
    public void hotel() {
        int choice;
        boolean exitSystem = false;
        System.out.println("Welcome to Hotel dear users");
        do {
            choice = takechoice();
            if (exitTheSystem(choice)) exitSystem = true;
            else logIn(choice);
        }while (!exitSystem);
    }
    public HotelManagementSystem() {
         super();
    }
}
