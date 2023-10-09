package exercise.model;

import exercise.annotation.Inspect;

import java.lang.reflect.Method;

public class Address {

    private String city;
    private int postalCode;

    public Address(String city, int postalCode) {
        this.city = city;
        this.postalCode = postalCode;
    }

    // BEGIN
    @Inspect
    // END
    public String getCity() {
        return city;
    }

    // BEGIN
    @Inspect
    // END
    public int getPostalCode() {
        return postalCode;
    }

    public String getFullAddress() {
        return city + " " + postalCode;
    }
}
