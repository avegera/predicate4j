package io.github.avegera.predicate4j.test;

import java.util.List;

public class Customer {

    private final List<String> roles;

    private final List<Product> products;

    public Customer(List<String> roles, List<Product> products) {
        this.roles = roles;
        this.products = products;
    }

    public List<String> roles() {
        return roles;
    }

    public List<Product> products() {
        return products;
    }
}