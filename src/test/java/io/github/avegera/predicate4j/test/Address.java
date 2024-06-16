package io.github.avegera.predicate4j.test;

public class Address {

    private final String country;
    private final String value;
    private final Integer zipCode;

    public Address(String country, String value, Integer zipCode) {
        this.country = country;
        this.value = value;
        this.zipCode = zipCode;
    }

    public String country() {
        return country;
    }

    public String value() {
        return value;
    }

    public Integer zipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        return String.format("Address(country=%s, value=%s, zipCode=%s)", getValue(country), getValue(value), getValue(zipCode));
    }

    private String getValue(Object object) {
        if (object == null) {
            return null;
        }
        return object instanceof String ? "\"" + object + "\"" : object.toString();
    }
}
