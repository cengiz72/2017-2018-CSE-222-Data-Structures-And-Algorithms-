import java.io.IOException;
import java.util.InputMismatchException;

/**
 * @author Cengiz Toprak
 */
public class Guest extends  AbstractUser{
    //Macros
    private final String COMMA = ",";
    private final String RESERVED = "RESERVED";
    private final String EMPTY = "EMPTY";
    @Override
    public void showMenu() {
        System.out.println("0 --> for booking a room");
        System.out.println("1 --> for canceling a reservation");
        performOperation();
    }
    /**
     *  helper function for booking a room.
    * */
    public void bookAnRoom() {
        String guestInfo;
        String roomInfo;
        boolean flag = true;
        int roomNo = 0;
        try
        {
            do {
                showRooms();
                System.out.print("Select the room:");
                roomNo = INPUT.nextInt();
                System.out.println(roomNo);
                if (roomNo < 1 || roomNo > 16)
                    System.out.println("Please select the between 1 and 16 roomNo!");
                else if(!(rooms.get(roomNo-1).split(COMMA)[1].equals(EMPTY)))
                    System.out.println("You can just book an empty room!");
                else {
                    flag = false;
                    guestInfo = getName() + COMMA + getPassword() + COMMA
                            + roomNo + COMMA + RESERVED+ COMMA +
                            rooms.get(roomNo - 1).split(COMMA)[2];
                    guest.add(guestInfo);
                    roomInfo = rooms.get(roomNo-1).split(COMMA)[0] + COMMA + RESERVED
                            + COMMA + rooms.get(roomNo - 1).split(COMMA)[2];
                    rooms.set(roomNo-1,roomInfo);
                    System.out.printf("You have booked %d. room\n",roomNo);
                    //update files
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
            System.out.println("Selection must be just positive integer!");
            System.exit(-1);
        }
    }

    /**
     * helper function for canceling a reservation.
    * */
    public void cancelingReservation() {
        String roomInfo;
        boolean flag = true;
        int roomNo;
        int index = 0;
        try
        {
            do {
                showRooms();
                System.out.print("Select the room:");
                roomNo = INPUT.nextInt();
                System.out.println(roomNo);
                if (roomNo < 1 || roomNo > 16)
                    System.out.println("Please select the between 1 and 16 roomNo!");
                else if((rooms.get(roomNo-1).split(COMMA)[1].equals(RESERVED)) &&
                        getName().equals(nameWithRespectToRoomNo(roomNo))) {
                    flag = false;
                    for (int i = 0; i < guest.size(); ++i) {
                        if (roomNo == Integer.parseInt(((guest.get(i).split(COMMA))[2])))
                            index=i;
                    }

                    guest.remove(index);
                    System.out.printf("%s's reservation is canceled\n",getName());
                    roomInfo = rooms.get(roomNo-1).split(COMMA)[0] + COMMA + EMPTY
                            + COMMA + rooms.get(roomNo-1).split(COMMA)[2];
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
                else {
                    System.out.println("You can just cancel own reservation!");
                }
            }while(flag);
        }
        catch (InputMismatchException param) {
            System.out.println("Selection must be just positive integer!");
            System.exit(-1);
        }
    }

    /**
     *
     * @param no for number of room
     * @return  return name if given room no is reserved or full,if not ,retur null.
     */
    private String nameWithRespectToRoomNo(int no) {
        for (int i = 0; i < guest.size(); ++i)
            if (no == Integer.parseInt(guest.get(i).split(COMMA)[2]))
                //name of guest
                return guest.get(i).split(COMMA)[0];
        return "null";
    }
    /**
     * function whose it books an room and canceling reservation.
     * */
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
                if ( chooise != 0 && chooise != 1){
                    System.out.println("Please chooice the one of them above options.");
                }
                else flag=false;
            }while(flag);
        }
        catch (InputMismatchException param) {
            System.out.println("Selection must be just positive integer!");
            System.exit(-1);
        }
        //book an room
        if (chooise == 0) bookAnRoom();
            //canceled reservation
        else cancelingReservation();
    }
    /**
     * helper function for showing situation of rooms.
     * */
    public void showRooms() {
        String a = "Room NO        Room Type      Room Situation";
        System.out.printf("%s%50s\n",a,a);
        for (int i = 0; i < 16; i += 2) {

            System.out.printf("%5s %17s %15s %14s %17s %16s\n",
                    rooms.get(i).split(COMMA)[0],
                    rooms.get(i).split(COMMA)[2].replace("\n",""),
                    rooms.get(i).split(COMMA)[1],
                    rooms.get(i+1).split(COMMA)[0],
                    rooms.get(i+1).split(COMMA)[2].replace("\n",""),
                    rooms.get(i+1).split(COMMA)[1]);
        }
    }

    public Guest(){
        super();
    }
}