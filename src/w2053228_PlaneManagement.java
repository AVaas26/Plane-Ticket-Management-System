import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;

public class w2053228_PlaneManagement {
    public static Scanner scanner = new Scanner(System.in);
    public static int numSeatsPerRow = 14;

    public static Ticket[] tickets = new Ticket[52];

    // Main method
    public static void main(String[] args) {
        int option;
        System.out.println("Welcome to the Plane Management application");

        // Initialize the seating arrangement
        int[][] seats = new int[4][];
        seats[0] = new int[14];
        seats[1] = new int[12];
        seats[2] = new int[12];
        seats[3] = new int[14];


        //Display menu option
        do {
            System.out.println("*******************************************");
            System.out.println("***            Menu Option              ***");
            System.out.println("*******************************************");
            System.out.println("    1) Buy a seat ");
            System.out.println("    2) Cancel a seat ");
            System.out.println("    3) Find first available seat ");
            System.out.println("    4) Show sealing plan ");
            System.out.println("    5) Print tickets information and total sales ");
            System.out.println("    6) Search ticket ");
            System.out.println("    0) Quit ");
            System.out.println("******************************************* \n");
            System.out.println("Please select an option:");

            // Validate user input
            do {
                try {
                    option = scanner.nextInt();
                    if (option>=0 && option<7){
                        break;
                    }
                    else {
                        System.out.println("Please enter a number between (0-7) ");
                    }
                }
                catch (Exception e) {
                    System.out.println("Please enter a valid option ");
                    scanner.nextLine();
                }
            } while (true);

            //Perform actions based on user input
            switch (option) {
                case 1: {
                    System.out.println("Buy a seat....");
                    buy_seat(seats, scanner);
                    break;
                }
                case 2: {
                    System.out.println("Cancel a seat....");
                    cancel_seat(seats, scanner);
                    break;
                }
                case 3: {
                    System.out.println("Find first available seat....");
                    Find_FirstAvailable_Seat(seats);
                    break;
                }
                case 4: {
                    System.out.println("Show sealing plan....");
                    show_seating_plan(seats);
                    break;
                }
                case 5: {
                    System.out.println("Print tickets information and total sales....");
                    print_tickets_info();
                    break;
                }
                case 6: {
                    System.out.println("Search ticket....");
                    search_ticket(scanner);
                    break;
                }
            }
        }while (option!=0);
        System.out.println("Goodbye....!");
        System.exit(0);
    }

    // Method to buy a seat
    private static void buy_seat(int[][] seats, Scanner scanner) {

        int row_num = 0;
        int seatNum;
        String row;
        double price;

        // Prompt user to enter row letter
        while (true) {
            System.out.print("Please enter the row letter (A-D): ");
            row = scanner.next();
            row = row.toUpperCase();
            if (!(row.equals("A") || row.equals("B") || row.equals("C") || row.equals("D"))) {
                System.out.println("Please enter a valid row letter!");
            } else {
                break;
            }
        }

        // Convert row letter to row number
        switch (row) {
            case "A":
                break;
            case "B":
                row_num = 1;
                break;
            case "C":
                row_num = 2;
                break;
            case "D":
                row_num = 3;
        }


        // Prompt user to enter seat number
        while (true) {
            System.out.print("Please enter the seat number (1-14): ");
            try {
                seatNum = scanner.nextInt();
                if (seatNum < 1 || seatNum > numSeatsPerRow) {
                    System.out.println("Invalid seat number, please enter again.");
                }
                else if ((row_num ==1 || row_num == 2) && (seatNum == 13 || seatNum == 14)) {
                    System.out.println("Invalid Seat number.please enter number between (1-12)");

                } else{
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid seat number.Please enter a number between 1 and 14.");
                scanner.next();
            }
        }

        // Check if seat is already sold
        if (seats[row_num][seatNum - 1] == 1) {
            System.out.println("This seat has been sold, please select another seat.");
        }
        // Assign seat, calculate price, and collect customer information
        else {
            seats[row_num][seatNum - 1] = 1;
            if (seatNum < 6) {
                price = 200;
            } else if (seatNum < 10) {
                price = 150;
            } else {
                price = 180;
            }


            System.out.println("Please give your name :");
            String name = scanner.next();

            System.out.println("Please give your surname :");
            String surname = scanner.next();

            System.out.println("Please give your email :");
            String email = scanner.next();

            Person info = new Person(name, surname, email);
            Ticket ticket = new Ticket(row, seatNum, price, info);
            for(int i=0; i<tickets.length; i++){
                if(tickets[i] == null){
                    tickets[i] = ticket;
                    break;
                }
            }
            System.out.println("Your Seat Number " + row + seatNum + " has been purchased.");
            ticket.Save_Ticket();
        }
    }

    private static void cancel_seat(int[][] seats, Scanner scanner) {
        int row_num = 0;
        int seatNum;
        String row;

        while (true) {
            System.out.print("Please enter the row letter (A-D): ");
            row = scanner.next();
            row = row.toUpperCase();
            if (!(row.equals("A") || row.equals("B") || row.equals("C") || row.equals("D"))) {
                System.out.println("Please enter a valid input!");
            } else {
                break;
            }
        }

        switch (row) {
            case "1":
                break;
            case "2":
                row_num = 1;
                break;
            case "3":
                row_num = 2;
                break;
            case "D":
                row_num = 3;
        }

        while (true) {
            System.out.print("Please enter the seat number (1-14): ");
            try {
                seatNum = scanner.nextInt();
                // Check if seat number is valid
                if (seatNum < 1 || seatNum > numSeatsPerRow) {
                    System.out.println("Invalid row or seat number, please enter again.");
                }else{
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid seat number. Please enter a number between 1 and 14.");
                scanner.next();
            }
        }

        // Check if the seat is already unsold
        if (seats[row_num][seatNum - 1] == 0) {
            System.out.println("This seat has been unsold!");
        }else{
            // Mark the seat as unsold, delete corresponding ticket file, and remove ticket from the list
            seats[row_num][seatNum - 1] = 0;

            // Delete the ticket file for the cancelled ticket
            File file = new File("Ticket Folder" ,row + seatNum +".txt");
            if (file.exists()){
                file.delete();
                System.out.println("Seat Number " + row + seatNum + " has been cancelled.");
            }

            // Remove the ticket from the list of tickets
            for(int i=0; i<tickets.length; i++){
                if(tickets[i] != null){
                    if(tickets[i].getRow().equals(row) && tickets[i].getSeat() == seatNum){
                        tickets[i] = null;
                        break;
                    }
                }
            }
        }
    }

    private static void Find_FirstAvailable_Seat(int[][] seats) {
        // Loop through all the seats in the 2D array
        String row = null;
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 0) {
                    // If a seat is unsold, print the row and seat number
                    if (i==0){
                        row = "A";
                    } else if (i==1) {
                        row = "B";
                    } else if (i ==2) {
                        row = "C";
                    } else if (i ==3) {
                        row = "D";
                    }
                    System.out.println("The first available seat is: Row  " + row + ", Seat " + (j + 1));
                    return;
                }
            }
        }
        System.out.println("There are no available seats at the moment.");
    }

    private static void print_tickets_info() {
        // Initialize the total sales variable
        double totalSales = 0;

        // Loop through all the tickets in the list of tickets
        for (Ticket ticket : tickets) {
            if(ticket != null) {
                System.out.println(ticket.getPerson().getName());
                System.out.println(ticket.getPerson().getSurname());
                System.out.println(ticket.getPerson().getEmail());
                System.out.println(ticket.getRow() + ticket.getSeat());
                totalSales += ticket.getPrice();
            }

        }
        System.out.println("Total Sales: " + totalSales);
    }

    //Method to Search ticket
    private static void search_ticket(Scanner scanner) {
        int seatNum;
        String row;

        // Prompt user to enter the row letter
        while (true) {
            System.out.print("Please enter the row letter (A-D): ");
            row = scanner.next();
            row = row.toUpperCase();
            if (!(row.equals("A") || row.equals("B") || row.equals("C") || row.equals("D"))) {
                System.out.println("Please enter a valid input!");
            } else {
                break;
            }
        }

        // Prompt user to enter the seat number
        while (true) {
            System.out.print("Please enter the seat number (1-14): ");
            try {
                seatNum = scanner.nextInt();
                if (seatNum < 1 || seatNum > numSeatsPerRow) {
                    System.out.println("Invalid row or seat number, please enter again.");
                }else{
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid seat number. Please enter a number between 1 and 14.");
                scanner.next();
            }
        }

        // Iterate through tickets to find a match based on row and seat number
        for (Ticket ticket : tickets) {
            if(ticket != null) {
                if (row.equals(ticket.getRow()) && seatNum == ticket.getSeat()) {
                    // Display ticket information if found
                    System.out.println("Ticket Information:\n" +
                            "Name: " + ticket.getPerson().getName() + "\n" +
                            "Surname: " + ticket.getPerson().getSurname() + "\n" +
                            "Email: " + ticket.getPerson().getEmail());
                    return;
                }
            }
        }
        // If no ticket found for the entered seat
        System.out.println("No ticket found for this seat.");
    }

    // Method to display the seating plan
    public static void show_seating_plan(int[][] seats){
        for (int[] seat : seats) {
            for (int i : seat) {
                if(i==0){
                    System.out.print("O ");
                }
                else{
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }
}
