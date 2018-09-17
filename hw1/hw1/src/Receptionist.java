import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Cengiz Toprak
 */
public class Receptionist extends AbstractUser {
    private final String COMMA = ",";
    private final String RESERVED = "RESERVED";
    private final String EMPTY = "EMPTY";
    private final String FULL = "FULL";

    /**
     *
     * @param no room number
     * @return name of guest as string if given room number is reserved or full,if not,
     * return null string.
     */
    private String nameAccordingToRoomNo(int no){
        for (int i = 0; i < guest.size(); ++i)
            if (no == Integer.parseInt(guest.get(i).split(COMMA)[2]))
                return (guest.get(i).split(COMMA)[0]);
        return "";
    }
    /**
     * This function shows situation of rooms.
     */
    public void showRooms() {
        String name = "";
        String name1 = "";
        int i = 0;
        String a = "Room NO        Room Type      Room Situation    Guest";
        System.out.printf("%s%57s\n",a,a);
        for (int j = 0; j < 16; j += 2) {
            name = nameAccordingToRoomNo(j+1);
            name1 = nameAccordingToRoomNo(j+2);
            System.out.printf("%5s%17s%18s%14s | %4s%18s%15s%15s\n",
                    rooms.get(j).split(COMMA)[0],
                    rooms.get(j).split(COMMA)[2].replace("\n",""),
                    rooms.get(j).split(COMMA)[1],
                    name,
                    rooms.get(j+1).split(COMMA)[0],
                    rooms.get(j+1).split(COMMA)[2].replace("\n",""),
                    rooms.get(j+1).split(COMMA)[1],
                    name1);
        }

    }

    @Override
    public void showMenu() {
        System.out.println("0 --> for booking a room");
        System.out.println("1 --> for checking in  a guest");
        System.out.println("2 --> for checking out a guest");
        System.out.println("3 --> for canceling a  reservation");
        performOperation();
    }
    /**
     *
     */
    public void performOperation() {
        boolean flag=true;
        int chooise = -3;
        try {
            takeTheDataFromFile("guests");
            takeTheDataFromFile("rooms");
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
                if ( chooise < 0 || chooise > 3){
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
        }
    }
    /**
     *Helper function for checking in a guest.
     */
    public void checkingInGuest() {

        String name = "";
        String password;
        String roomInfo;
        String guestInfo;
        int roomNo = 1;
        ;
        boolean flag = true;
        try {
            System.out.println("Please select an room for checking in a guest.");
            do {
                showRooms();
                System.out.print("Room no:");
                roomNo = INPUT.nextInt();
                System.out.println(roomNo);
                if (roomNo < 1 || roomNo > 16)
                    System.out.println("Please select the between 1 and 16 roomNo!");
                else if ((rooms.get(roomNo-1).split(COMMA))[1].equals(FULL))
                    System.out.println("Please select the a reserved or an empty room!");
                else {
                    flag = false;
                    System.out.print("Name:");
                    name = INPUT.next();
                    System.out.println(name);
                    System.out.print("Password:");
                    password = INPUT.next();
                    System.out.println(password);
                    if (nameAccordingToRoomNo(roomNo).equals(name))
                        name = nameAccordingToRoomNo(roomNo);
                    guestInfo =  name + COMMA + password + COMMA +
                            rooms.get(roomNo - 1).split(COMMA)[0]
                            + COMMA + FULL + COMMA +
                            rooms.get(roomNo - 1).split(COMMA)[2];
                    guest.add(guestInfo);
                    //update room situation.
                    roomInfo = rooms.get(roomNo - 1).split(COMMA)[0] + COMMA + FULL + COMMA +
                            rooms.get(roomNo - 1).split(COMMA)[2];
                    rooms.set(roomNo - 1,roomInfo);
                    try {
                        writeDataToFile("rooms");
                        writeDataToFile("guests");
                    }catch (IOException param){
                        System.out.println("An error occurred during file operation");
                        param.printStackTrace();
                        System.exit(-1);
                    }
                }
            }while(flag);
        }
        catch (InputMismatchException param) {
            System.out.println("Please just only enter integer");
            param.printStackTrace();
            System.exit(-1);
        }
        showRooms();
        System.out.printf("It is checked in %s by %s to %d. room\n"
                ,name,getName(),roomNo);
    }
    /**
     *Helper function for checking out  a guest.
     */
    public void checkingOutGuest() {
        String name = "";
        int roomNo;
        int index = 0;
        String roomInfo;
        boolean flag = true;
        try {
            System.out.println("Please select room no for checking out a guest.");
            do {
                showRooms();
                System.out.print("Room no:");
                roomNo = INPUT.nextInt();
                System.out.println(roomNo);
                if (roomNo < 1 || roomNo > 16)
                    System.out.println("Please select an room no among the 1 and 16.");
                else if (!((rooms.get(roomNo-1).split(COMMA))[1].equals(FULL)))
                    System.out.println("Please select a full room.");
                else {
                    flag = false;
                    //update room situation
                    roomInfo = (rooms.get(roomNo-1).split(COMMA))[0] + COMMA + EMPTY +
                            COMMA + (rooms.get(roomNo-1).split(COMMA))[2];
                    rooms.set(roomNo-1,roomInfo);

                    //remove guest who has no book
                    for (int i = 0; i < guest.size(); ++i) {
                        if (roomNo == Integer.parseInt(((guest.get(i).split(COMMA))[2])))
                            index=i;
                    }
                    name = guest.remove(index).split(COMMA)[0];
                    showRooms();
                    System.out.printf("It is checked out %s by %s from %d. room\n"
                            ,name,getName(),roomNo);
                    try {
                        writeDataToFile("rooms");
                        writeDataToFile("guests");
                    }catch (IOException param){
                        System.out.println("An error occurred during file operation");
                        param.printStackTrace();
                        System.exit(-1);
                    }
                }
            } while(flag);

        } catch (InputMismatchException param) {
            System.out.println("Please enter just integer number!");
            System.exit(-1);
        }
    }
    /**
     *Helper function for  booking a room.
     */
    public void bookAnRoom() {
        String name = "";
        String password;
        String allInfoGuest;
        String roomInfo;
        int roomNo = 1;
        boolean flag = true;
        try {
            System.out.println("Please enter name , password and room no.");
            do {
                System.out.print("Name:");
                name = INPUT.next();
                System.out.println(name);
                System.out.print("Password:");
                password = INPUT.next();
                System.out.println(password);
                if (name.equals("") || password.equals(""))
                    System.out.println("Please enter name or password correctly!");
                else flag = false;

            }while (flag);
            flag = true;
            do {
                showRooms();
                System.out.print("room No:");
                roomNo = INPUT.nextInt();
                System.out.println(roomNo);
                if (roomNo < 1 || roomNo > 16)
                    System.out.println("Please select the between 1 and 16 roomNo!");
                else if (!((rooms.get(roomNo-1).split(COMMA))[1].equals(EMPTY)))
                    System.out.println("You can just book an empty room!");
                else {
                    flag = false;
                    allInfoGuest = name + COMMA + password + COMMA +
                            (rooms.get(roomNo-1).split(COMMA))[0] +
                            COMMA + RESERVED + COMMA +
                            (rooms.get(roomNo-1).split(COMMA))[2];
                    guest.add(allInfoGuest);
                    //update room situation
                    roomInfo = (rooms.get(roomNo-1).split(COMMA))[0] + COMMA +
                            RESERVED + COMMA +
                            (rooms.get(roomNo-1).split(COMMA))[2];
                    rooms.set(roomNo-1,roomInfo);
                    try {
                        writeDataToFile("rooms");
                        writeDataToFile("guests");
                    }catch (IOException param){
                        System.out.println("An error occurred during file operation");
                        param.printStackTrace();
                        System.exit(-1);
                    }
                }
            }while (flag);

        }
        catch (InputMismatchException param) {
            System.out.println("Please enter only integer!");
            System.exit(-1);
        }
        showRooms();
        System.out.printf("%d. room is reserved to %s by %s\n",roomNo,name,getName());
    }
    /**
     *Helper function for canceling a reservation.
     */
    public void cancelReservation() {
        int roomNo;
        int index = 0;
        String roomInfo;
        String name = "";
        boolean flag = true;
        try {
            System.out.println("Please select room no for canceling reservation.");
            do {
                showRooms();
                System.out.print("Room no:");
                roomNo = INPUT.nextInt();
                System.out.println(roomNo);
                if (roomNo < 1 || roomNo > 16)
                    System.out.println("Please select an room no among the 1 and 16.");
                else if (!((rooms.get(roomNo-1).split(COMMA))[1].equals(RESERVED)))
                    System.out.println("Please select a booked room.");
                else {
                    flag = false;
                    //update room situation
                    roomInfo = (rooms.get(roomNo-1).split(COMMA))[0] + COMMA + EMPTY +
                            COMMA + (rooms.get(roomNo-1).split(COMMA))[2];
                    rooms.set(roomNo-1,roomInfo);

                    //remove guest who has no book
                    for (int i = 0; i < guest.size(); ++i) {
                        if (roomNo == Integer.parseInt(((guest.get(i).split(COMMA))[2])))
                            index=i;
                    }
                    name = guest.remove(index).split(COMMA)[0];
                    showRooms();
                    System.out.printf("It is canceled %s's reservation by %s\n",name,getName());
                    try {
                        writeDataToFile("rooms");
                        writeDataToFile("guests");
                    }catch (IOException param){
                        System.out.println("An error occurred during file operation");
                        param.printStackTrace();
                        System.exit(-1);
                    }
                }
            } while(flag);

        } catch (InputMismatchException param) {
            System.out.println("Please enter just integer number!");
            System.exit(-1);
        }
    }
    /**
     * No parameter constructor.
     */
    public Receptionist(){
        super();
    }
}