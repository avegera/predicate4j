package io.github.avegera.predicate4j.test.model;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static io.github.avegera.predicate4j.test.util.MapUtils.getMap;
import static io.github.avegera.predicate4j.test.util.StringUtils.toStringOnlyInitialized;

public class User {

    private final Integer id;

    private final String name;

    private final Boolean active;

    private final List<String> roles;

    private final Map<String, Function<User, Object>> initializationMap;

    public User(Integer id, String name, Boolean active, List<String> roles, Map<String, Function<User, Object>> initializationMap) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.roles = roles;
        this.initializationMap = initializationMap;
    }

    public Integer id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Boolean active() {
        return active;
    }

    public List<String> roles() {
        return roles;
    }

    public static User userWithId(Integer id) {
        return new User(id, null, null, null, getMap(User::id));
    }

    public static User userWithName(String name) {
        return new User(null, name, null, null, getMap(User::name));
    }

    public static User userWithActiveFlag(Boolean active) {
        return new User(null, null, active, null, getMap(User::active));
    }

    public static User userWithRoles(List<String> roles) {
        return new User(null, null, null, roles, getMap(User::roles));
    }

    @Override
    public String toString() {
        return toStringOnlyInitialized(this, this.initializationMap);
    }
}