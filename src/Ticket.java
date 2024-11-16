import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// Ticket class representing a ticket object
public class Ticket {

    // Attributes of a ticket
    private String row;
    private int seat;
    private double price;
    private Person person;

    // Constructor to initialize ticket attributes
    public Ticket(String row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    // Getter methods for accessing ticket information
    public String getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }

    public Person getPerson() {
        return person;
    }

    //Print ticket information
    public void printInfo() {
        System.out.println("Ticket Information:");
        System.out.println("  Row: " + row);
        System.out.println("  Seat: " + seat);
        System.out.println("  Price: " + price);
        System.out.println("  Person:" + person);
    }

    // Method to save ticket information to a text file
    public void Save_Ticket(){
        // Generating file name based on row and seat number
        String filename = row + String.valueOf(seat) + ".txt";
        try{
            // Creating a folder to store tickets if it doesn't exist
            File folder = new File("Ticket Folder");
            if (!folder.exists()){
                folder.mkdirs();
            }

            // Creating a file object
            File fol = new File(folder,filename);

            // Creating a FileWriter object for writing to the file
            FileWriter file = new FileWriter(fol);
            file.write("Ticket Information \n");
            file.write("Row: " + row + "\n");
            file.write("Seat: " + seat + "\n");
            file.write("Price: " + price + "\n");
            file.write("Person Information \n");
            file.write("Name: " + person.getName() + "\n");
            file.write("Surname: " + person.getSurname() + "\n");
            file.write("Email: " + person.getEmail() + "\n");
            file.close();

        }

        // Handling IO exceptions
        catch (IOException e) {
            System.out.println("An error occurred while saving the ticket information.");
            e.printStackTrace();
        }

    }
}
