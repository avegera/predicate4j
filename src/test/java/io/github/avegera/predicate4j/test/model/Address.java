package io.github.avegera.predicate4j.test.model;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static io.github.avegera.predicate4j.test.util.MapUtils.getMap;
import static io.github.avegera.predicate4j.test.util.StringUtils.toStringOnlyInitialized;

public class Address {

    private final String country;
    private final String state;
    private final String street;
    private final String building;
    private final Integer zipCode;
    private final Boolean active;
    private final List<String> tenants;
    private final Map<String, Function<Address, Object>> initializationMap;

    public Address(String country, Integer zipCode) {
        this(country, null, null, null, zipCode, null, null, getMap(Address::country, Address::zipCode));
    }

    public Address(String country, String state, Integer zipCode) {
        this(country, state, null, null, zipCode, null, null, getMap(Address::country, Address::state, Address::zipCode));
    }

    public Address(String country, String state, String street, String building, Integer zipCode, List<String> tenants, Boolean active) {
        this(country, state, street, building, zipCode, tenants, active, getMap(
                Address::country,
                Address::state,
                Address::building,
                Address::zipCode,
                Address::tenants,
                Address::active)
        );
    }

    public Address(String country, String state, String street, String building, Integer zipCode, List<String> tenants, Boolean active, Map<String, Function<Address, Object>> initializationMap) {
        this.country = country;
        this.street = street;
        this.state = state;
        this.building = building;
        this.zipCode = zipCode;
        this.tenants = tenants;
        this.active = active;
        this.initializationMap = initializationMap;
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
        return toStringOnlyInitialized(this, this.initializationMap);
    }
}