package io.github.avegera.predicate4j.test;

public class Organization {

    private final Boolean active;

    public Organization(Boolean active) {
        this.active = active;
    }

    public Boolean active() {
        return active;
    }
}