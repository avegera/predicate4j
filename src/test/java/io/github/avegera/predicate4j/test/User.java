package io.github.avegera.predicate4j.test;

public class User {

    private final Integer id;

    public User(Integer id) {
        this.id = id;
    }

    public Integer id() {
        return id;
    }
}