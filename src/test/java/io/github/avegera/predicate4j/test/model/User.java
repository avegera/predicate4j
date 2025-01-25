package io.github.avegera.predicate4j.test.model;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static io.github.avegera.predicate4j.test.util.MapUtils.getMap;
import static io.github.avegera.predicate4j.test.util.StringUtils.toStringOnlyInitialized;

public class User {

    private final Integer id;

    private final String name;

    private final Boolean active;

    private final List<String> roles;

    private final Set<String> permissions;

    private final List<Boolean> flags;

    private final List<Number> balances;

    private final Map<String, Function<User, Object>> initializationMap;

    private User(
            Integer id,
            String name,
            Boolean active,
            List<String> roles,
            Set<String> permissions,
            List<Boolean> flags,
            List<Number> balances,
            Map<String, Function<User, Object>> initializationMap
    ) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.roles = roles;
        this.permissions = permissions;
        this.flags = flags;
        this.balances = balances;
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

    public Set<String> permissions() {
        return permissions;
    }

    public List<Boolean> flags() {
        return flags;
    }

    public List<Number> balances() {
        return balances;
    }

    public static User userWithId(Integer id) {
        return new User(id, null, null, null, null, null, null, getMap(User::id));
    }

    public static User userWithName(String name) {
        return new User(null, name, null, null, null, null, null, getMap(User::name));
    }

    public static User userWithActiveFlag(Boolean active) {
        return new User(null, null, active, null, null, null, null, getMap(User::active));
    }

    public static User userWithRoles(List<String> roles) {
        return new User(null, null, null, roles, null, null, null, getMap(User::roles));
    }

    public static User userWithPermissions(Set<String> permissions) {
        return new User(null, null, null, null, permissions, null, null, getMap(User::permissions));
    }

    public static User userWithRolesAndPermissions(List<String> roles, Set<String> permissions) {
        return new User(null, null, null, roles, permissions, null, null, getMap(User::roles, User::permissions));
    }

    public static User userWithFlags(List<Boolean> flags) {
        return new User(null, null, null, null, null, flags, null, getMap(User::flags));
    }

    public static User userWithBalances(List<Number> balances) {
        return new User(null, null, null, null, null, null, balances, getMap(User::balances));
    }

    @Override
    public String toString() {
        return toStringOnlyInitialized(this, this.initializationMap);
    }
}