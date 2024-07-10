package io.github.avegera.predicate4j.test;

public class Product {

    private final String name;

    private final boolean active;

    public Product(String name) {
        this.name = name;
        this.active = false;
    }

    public Product(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    public String name() {
        return name;
    }

    public boolean active() {
        return active;
    }
}
