import java.util.ArrayList;
import java.util.Scanner;

class Movie {
    private String movieName;
    private String showTime;

    public Movie(String movieName, String showTime) {
        this.movieName = movieName;
        this.showTime = showTime;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getShowTime() {
        return showTime;
    }
}

class Seat {
    String type;
    int price;
    boolean isBooked;

    public Seat(String type, int price) {
        this.type = type;
        this.price = price;
        this.isBooked = false;
    }
}

class Booking {
    private Seat[][] seats;
    private int totalCost;
    private ArrayList<String> bookedSeats;

    public Booking(int rows, int cols) {
        seats = new Seat[rows][cols];
        bookedSeats = new ArrayList<>();
        totalCost = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (i < 2) {
                    seats[i][j] = new Seat("Premium", 200);
                } else {
                    seats[i][j] = new Seat("Regular", 100);
                }
            }
        }
    }

    public void displaySeats() {
        System.out.println("\nSeat Layout:");
        System.out.println("O = Available | X = Booked\n");

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j].isBooked) {
                    System.out.print("X ");
                } else {
                    System.out.print("O ");
                }
            }
            System.out.println();
        }
    }

    public void bookSeat(int row, int col) {

        if (row < 0 || row >= seats.length || col < 0 || col >= seats[0].length) {
            System.out.println("Invalid Seat Position!");
            return;
        }

        if (seats[row][col].isBooked) {
            System.out.println("Seat Already Booked!");
        } else {
            seats[row][col].isBooked = true;
            totalCost += seats[row][col].price;

            bookedSeats.add("(" + row + "," + col + ")");

            System.out.println("Booking Successful!");
            System.out.println("Seat Type: " + seats[row][col].type);
            System.out.println("Price: ₹" + seats[row][col].price);
        }
    }

    public void showSummary() {
        System.out.println("\n===== BOOKING SUMMARY =====");

        if (bookedSeats.isEmpty()) {
            System.out.println("No Seats Booked.");
        } else {
            System.out.println("Booked Seats: " + bookedSeats);
            System.out.println("Total Cost: ₹" + totalCost);
        }

        System.out.println("===========================");
    }
}

public class MovieTicketBookingSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Movie movie = new Movie("Pushpa 2", "7:00 PM");

        Booking booking = new Booking(4, 4);

        int choice;

        do {

            System.out.println("\n================================");
            System.out.println(" MOVIE TICKET BOOKING SYSTEM ");
            System.out.println("================================");
            System.out.println("Movie : " + movie.getMovieName());
            System.out.println("Show Time : " + movie.getShowTime());

            System.out.println("\n1. View Seats");
            System.out.println("2. Book Seat");
            System.out.println("3. View Booking Summary");
            System.out.println("4. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    booking.displaySeats();
                    break;

                case 2:
                    booking.displaySeats();

                    System.out.print("Enter Row (0-3): ");
                    int row = sc.nextInt();

                    System.out.print("Enter Column (0-3): ");
                    int col = sc.nextInt();

                    booking.bookSeat(row, col);
                    break;

                case 3:
                    booking.showSummary();
                    break;

                case 4:
                    System.out.println("Thank You For Using Movie Ticket Booking System!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 4);

        sc.close();
    }
}
