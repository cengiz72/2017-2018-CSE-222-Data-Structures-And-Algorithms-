import java.io.IOException;
import java.util.InputMismatchException;

/**
 * @author Cengiz Toprak.
 */
public class Manager extends Receptionist {
    //admins name and password.
    private  final String name = "Halil";
    private  final String password = "2222";
    private  final String COMMA = ",";
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * This function provides to add new receptionist by admin.
     */
    public void addReceptionist() {
        String name;
        String receptionistInfo;
        String password;
        System.out.println("Available receptionists.");
        for (int j = 0; j < receptionist.size(); ++j)
            System.out.printf("---> %s\n",receptionist.get(j).split(COMMA)[0]);
        System.out.println("Please enter name and password for adding new receptionist");
        System.out.print("Name:");
        name = INPUT.next();
        System.out.println(name);
        System.out.print("Password:");
        password = INPUT.next();
        System.out.println(password);
        receptionistInfo = name + COMMA + password + "\n";
        receptionist.add(receptionistInfo);
        System.out.printf("You add new receptionist named %s to hotel system.\n",name);
        //update file
        try {
            writeDataToFile("receptionists");
        } catch (IOException param){
            System.out.println("An error occurred during file operation");
            param.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * This function provides to delete a receptionist by admin.
     */
    public void deleteReceptionist() {
        String name;
        boolean flag = true;
        int index = 0;
        if (receptionist.size() == 0)
            System.out.println("There are no receptionist!");
        else {
            System.out.println("Available receptionists.");
            for (int j = 0; j < receptionist.size(); ++j)
                System.out.printf("---> %s\n",receptionist.get(j).split(COMMA)[0]);
            System.out.println("Please enter name for deleting a receptionist");
            System.out.print("Name:");
            name = INPUT.next();
            System.out.println(name);
            for (int i = 0; i < receptionist.size() && flag; ++i) {
                if ((receptionist.get(i).split(COMMA)[0]).equals(name)) {
                    flag = false;
                    index = i;
                }

            }
            if (!flag) {
                receptionist.remove(index);
                System.out.printf("You delete a receptionist named %s from hotel system.\n",name);
                //update file
                try {
                    writeDataToFile("receptionists");
                } catch (IOException param){
                    System.out.println("An error occurred during file operation");
                    param.printStackTrace();
                    System.exit(-1);
                }
            }
            else {
                System.out.println("there is no a such named  receptionist!");
            }
        }
    }

    @Override
    public void showMenu() {
        System.out.println("0 --> for booking a room");
        System.out.println("1 --> for checking in  a guest");
        System.out.println("2 --> for checking out a guest");
        System.out.println("3 --> for canceling a  reservation");
        System.out.println("4 --> for adding  a new receptionist");
        System.out.println("5 --> for deleting a receptionist");
        performOperation();
    }

    @Override
    public void performOperation() {
        boolean flag=true;
        int chooise = -3;
        try {
            takeTheDataFromFile("guests");
            takeTheDataFromFile("rooms");
            takeTheDataFromFile("receptionists");
        }
        catch (IOException param){
            System.out.println("An error occurred during file operation");
            param.printStackTrace();
            System.exit(-1);
        }
        try {
            do {
                System.out.print("Enter your chooice:");
                chooise = INPUT.nextInt();
                System.out.println(chooise);
                if ( chooise < 0 || chooise > 5){
                    System.out.println("Please chooice the one of them above options.");
                }
                else flag=false;
            }while(flag);
        }
        catch (InputMismatchException param) {
            System.out.println("Selection must be just positive integer!");
            System.exit(-1);
        }
        switch (chooise) {
            case 0: bookAnRoom();
                break;
            case 1: checkingInGuest();
                break;
            case 2: checkingOutGuest();
                break;
            case 3: cancelReservation();
                break;
            case 4: addReceptionist();
                break;
            case 5: deleteReceptionist();
                break;
        }
    }

    public  Manager() {
        super();
    }
}
