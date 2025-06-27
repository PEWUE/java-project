package model;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.UUID;

/**
 * Represents a customer in the shop system.
 * <p>
 * Stores personal and contact information required for order processing and delivery.
 */
public class Customer implements Serializable {
    private final UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String street;
    private String city;
    private String postalCode;
    private String country;
    private ZoneId timeZone;

    /**
     * Creates a new customer with the specified personal and contact details.
     *
     * @param firstName   the first name of the customer
     * @param lastName    the last name of the customer
     * @param email       the email address of the customer
     * @param phoneNumber the phone number of the customer
     * @param street      the street address of the customer
     * @param city        the city of the customer
     * @param postalCode  the postal code of the customer's address
     * @param country     the country of the customer
     */
    public Customer(String firstName, String lastName, String email, String phoneNumber, String street, String city, String postalCode, String country) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.timeZone = ZoneId.systemDefault();
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ZoneId getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(ZoneId timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + email + ")";
    }
}
