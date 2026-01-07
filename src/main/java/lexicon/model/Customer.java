package lexicon.model;

import java.util.Objects;

public class Customer {

    private Integer id;
    private String name;
    private String phoneNumber;
    private String vehiclePlateNumber;

    public Customer(Integer id, String name, String phoneNumber, String vehiclePlateNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.vehiclePlateNumber = vehiclePlateNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Objects.requireNonNull(id, "Id can't be null");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Objects.requireNonNull(name, "Name can't be null");
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        Objects.requireNonNull(phoneNumber, "Phone Number can't be null");
        this.phoneNumber = phoneNumber;
    }

    public String getVehiclePlateNumber() {
        return vehiclePlateNumber;
    }

    public void setVehiclePlateNumber(String vehiclePlateNumber) {
        Objects.requireNonNull(vehiclePlateNumber, "Vehicle Plate Number can't be null");
        this.vehiclePlateNumber = vehiclePlateNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", vehiclePlateNumber='" + vehiclePlateNumber + '\'' +
                '}';
    }
}
