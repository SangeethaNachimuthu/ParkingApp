package lexicon.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

public class Reservation {

    private String reservationId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status;
    private Customer customer;
    private ParkingSpot parkingSpot;

    public Reservation(ParkingSpot parkingSpot, Customer customer, int hours) {
        this.reservationId = "RES-" + (new Random().nextInt(9000000) + 10000000);
        this.startTime = LocalDateTime.now();
        setDurationInHours(hours);
        this.status = Status.ACTIVE;
        this.customer = Objects.requireNonNull(customer, "Customer can't be null");
        this.parkingSpot = Objects.requireNonNull(parkingSpot, "Parking spot can't be null");
        this.parkingSpot.occupy();
    }

    public String getReservationId() {
        return reservationId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Status getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void complete() {
        this.status = Status.COMPLETED;
        this.parkingSpot.vacate();
    }

    public void setDurationInHours(int hours) {
        if (hours <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }
        this.endTime = startTime.plusHours(hours);
    }

    @Override
    public String toString() {
        return """
                Reservation Details
                --------------------------
                Reservation ID: %s
                Start Time: %s
                End Time: %s
                Customer: %s
                Parking spot: %s
                --------------------------
                """.formatted(reservationId, startTime, endTime, customer, parkingSpot);
    }
}
