package lexicon;

import lexicon.model.Customer;
import lexicon.model.ParkingSpot;
import lexicon.model.Reservation;
import lexicon.model.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParkingAppUI {

    private final Scanner scanner;
    private final List<Customer> customerList;
    private final List<ParkingSpot> parkingSpots;
    private final List<Reservation> reservationList;

    public ParkingAppUI() {
        this.scanner = new Scanner(System.in);
        this.customerList = new ArrayList<>();
        this.parkingSpots = new ArrayList<>();
        this.reservationList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            parkingSpots.add(new ParkingSpot(i, 101));
        }
    }

    public void start() {

        boolean running = true;
        while (running) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerCustomer();
                    break;
                case 2:
                    displayAvailableParkingSlots();
                    break;
                case 3:
                    createReservation();
                    break;
                case 4:
                    completeReservation();
                    break;
                case 0:
                    System.out.println("Thanks for using the Parking App!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, try again!");
            }
        }
    }

    public void printMenu() {
        System.out.println("----- Parking App -----");
        System.out.println("1. Register Customer");
        System.out.println("2. View available parking slots");
        System.out.println("3. Create Reservation");
        System.out.println("4. Complete Reservation");
        System.out.println("0. Exit");
        System.out.print("Choose option: ");
    }

    public void registerCustomer() {

        System.out.print("Enter Customer ID: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter Vehicle Plate Number: ");
        String vehicleNumber = scanner.nextLine();

        Customer customer = new Customer(id, name, phoneNumber, vehicleNumber);
        customerList.add(customer);
        System.out.println("Customer registered successfully.");
        System.out.println();
    }

    public void displayAvailableParkingSlots() {

        // Display all spots in Area 101 like a real parking place with lines
        IO.println("\n=== Parking Lot - Area 101 ===");
        IO.println("┌───────┬───────┬───────┬───────┬───────┐");
        for (int i = 0; i < parkingSpots.size(); i++) {
            ParkingSpot spot = parkingSpots.get(i);
            String status = spot.isOccupied() ? "[ X ]" : String.format("[%2d ]", spot.getId());
            IO.print("│ " + status + " ");
            if ((i + 1) % 5 == 0) {
                IO.println("│");
                if (i < parkingSpots.size() - 1) {
                    IO.println("├───────┼───────┼───────┼───────┼───────┤");
                }
            }
        }
        IO.println("└───────┴───────┴───────┴───────┴───────┘");
        System.out.println();
    }

    public void createReservation() {

        System.out.print("Select Parking slot: ");
        int slotId = scanner.nextInt();
        scanner.nextLine();

        ParkingSpot selectedSpot = null;
        for (ParkingSpot spot : parkingSpots) {
            if (spot.getId() == slotId && !spot.isOccupied()) {
                selectedSpot = spot;
                break;
            }
        }
        if (selectedSpot == null) {
            System.out.println("Invalid or occupied parking slot.");
            return;
        }

        System.out.print("Enter Customer Id: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        Customer customer = null;
        for (Customer c : customerList) {
            if (c.getId() == customerId) {
                customer = c;
                break;
            }
        }
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.print("Enter duration: ");
        int duration = scanner.nextInt();
        scanner.nextLine();

        Reservation reservation = new Reservation(selectedSpot, customer, duration);
        reservationList.add(reservation);
        System.out.println("Reservation created: " + reservation);
    }

    public void completeReservation() {

        if (reservationList.isEmpty()) {
            System.out.println("No active reservation found.");
            return;
        }

        System.out.println("Active Reservations:");
        for (int i = 0; i < reservationList.size(); i++) {
            Reservation res = reservationList.get(i);
            if (res.getStatus() == Status.ACTIVE) {
                System.out.printf("%d. %s - Spot ID: %d, Customer: %s%n",
                        i + 1, res.getReservationId(), res.getParkingSpot().getId(), res.getCustomer().getName());
            }
        }

        System.out.print("Select reservation to complete (number): ");
        int resChoice = scanner.nextInt();
        scanner.nextLine();

        if (resChoice < 1 || resChoice > reservationList.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Reservation selectedReservation = reservationList.get(resChoice - 1);
        if (selectedReservation.getStatus().equals(Status.COMPLETED)) {
            System.out.println("Reservation is already completed.");
            return;
        }
        selectedReservation.complete();
        System.out.println("Reservation completed successfully!");
    }
}
