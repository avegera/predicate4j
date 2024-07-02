package io.github.avegera.predicate4j.test;

import java.util.List;

public class Address {

    private final String country;
    private final String state;
    private final String street;
    private final String building;
    private final Integer zipCode;
    private final Boolean active;
    private final List<String> tenants;

    public Address(String country, Integer zipCode) {
        this(country, null, null, null, zipCode, null, null);
    }

    public Address(String country, String state, Integer zipCode) {
        this(country, state, null, null, zipCode, null, null);
    }

    public Address(String country, String state, String street, String building, Integer zipCode, List<String> tenants, Boolean active) {
        this.country = country;
        this.street = street;
        this.state = state;
        this.building = building;
        this.zipCode = zipCode;
        this.tenants = tenants;
        this.active = active;
    }

    public String country() {
        return country;
    }

    public String state() {
        return state;
    }

    public String street() {
        return street;
    }

    public String building() {
        return building;
    }

    public Integer zipCode() {
        return zipCode;
    }

    public Boolean active() {
        return active;
    }

    public List<String> tenants() {
        return tenants;
    }

    @Override
    public String toString() {
        return String.format(
                "Address(country=%s, state=%s, street=%s, building=%s, zipCode=%s, active=%s, tenants=%s)",
                getValue(country),
                getValue(state),
                getValue(street),
                getValue(building),
                getValue(zipCode),
                getValue(active),
                getValue(tenants)
        );
    }

    private String getValue(Object object) {
        if (object == null) {
            return null;
        }
        return object instanceof String ? "\"" + object + "\"" : object.toString();
    }
}