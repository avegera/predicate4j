package io.github.avegera.predicate4j.quantifier.atLeastOne;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.CaseAs;
import com.tngtech.jgiven.annotation.Description;
import io.github.avegera.predicate4j.test.argument.FirstArgument;
import io.github.avegera.predicate4j.test.model.User;
import io.github.avegera.predicate4j.test.predicate.PredicateContext;
import io.github.avegera.predicate4j.test.predicate.PredicateTest;
import io.github.avegera.predicate4j.test.scenario.PredicateScenarioTest;
import io.github.avegera.predicate4j.test.tag.Logical;
import io.github.avegera.predicate4j.test.tag.Quantifier;
import io.github.avegera.predicate4j.test.tag.Type;
import io.github.avegera.predicate4j.test.tag.Where;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static io.github.avegera.predicate4j.Where.where;
import static io.github.avegera.predicate4j.test.model.User.*;
import static io.github.avegera.predicate4j.test.util.ListWithNullableIterator.listWithNullableIterator;
import static java.util.Arrays.asList;
import static java.util.Collections.*;

@Where("where")
@Type("object")
@Quantifier("atLeastOne")
@Description("Test predicates under where().atLeastOne(...)")
public class WhereAtLeastOneTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeastOne(User::roles).isEqualTo($argument1)")
    public void atLeastOneIsEqualTo(PredicateContext<User, String> context, String value) {
        scenario(context, where().atLeastOne(User::roles).isEqualTo(value));
    }

    static Stream<Arguments> atLeastOneIsEqualTo() {
    return PredicateTest.<User, List<String>>builder()
            .predicate("where().atLeastOne(User::roles).isEqualTo($argument1)")
            .withMapper(User::roles)
            .withArgument("Admin")
                .isTrueFor(userWithRoles(asList("Admin", "Admin", "Admin")))
                .isTrueFor(userWithRoles(asList("Admin", "User", "Admin")))
                .isTrueFor(userWithRoles(asList("Admin", "Guest")))
                .isTrueFor(userWithRoles(singletonList("Admin")))
                .isFalseFor(userWithRoles(asList("admin", "Administrator", " Admin ")))
                .isFalseFor(userWithRoles(asList("User", "Guest", "", "   ", null)))
                .isFalseFor(userWithRoles(asList(null, null, null)))
                .isFalseFor(userWithRoles(singletonList("User")))
                .isFalseFor(userWithRoles(emptyList()))
                .isFalseFor(userWithRoles(null))
                .isFalseFor(null)
            .withArgument(null)
                .isTrueFor(userWithRoles(asList("Admin", null, "User")))
                .isTrueFor(userWithRoles(asList(null, "User", null)))
                .isTrueFor(userWithRoles(asList(null, null)))
                .isTrueFor(userWithRoles(singletonList(null)))
                .isFalseFor(userWithRoles(asList("Admin", "User")))
                .isFalseFor(userWithRoles(singletonList("Admin")))
                .isFalseFor(userWithRoles(emptyList()))
                .isFalseFor(userWithRoles(null))
                .isFalseFor(null)
            .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeastOne(User::roles).notEqualTo($argument1)")
    public void atLeastOneNotEqualTo(PredicateContext<User, String> context, String value) {
        scenario(context, where().atLeastOne(User::roles).notEqualTo(value));
    }

    static Stream<Arguments> atLeastOneNotEqualTo() {
        return PredicateTest.<User, List<String>>builder()
            .predicate("where().atLeastOne(User::roles).notEqualTo($argument1)")
            .withMapper(User::roles)
            .withArgument("Admin")
                .isTrueFor(userWithRoles(asList("User", "Admin", "Guest")))
                .isTrueFor(userWithRoles(asList("admin", "Administrator", " Admin ", "Guest")))
                .isTrueFor(userWithRoles(asList("User", "Guest", "", "   ", null)))
                .isTrueFor(userWithRoles(asList("User", "Guest")))
                .isTrueFor(userWithRoles(asList("User", "Admin")))
                .isTrueFor(userWithRoles(asList(null, null)))
                .isFalseFor(userWithRoles(asList("Admin", "Admin", "Admin")))
                .isFalseFor(userWithRoles(singletonList("Admin")))
                .isFalseFor(userWithRoles(emptyList()))
                .isFalseFor(userWithRoles(null))
                .isFalseFor(null)
            .withArgument(null)
                .isTrueFor(userWithRoles(asList("Admin", "User")))
                .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                .isTrueFor(userWithRoles(singletonList("Admin")))
                .isFalseFor(userWithRoles(asList(null, null, null)))
                .isFalseFor(userWithRoles(listWithNullableIterator()))
                .isFalseFor(userWithRoles(emptyList()))
                .isFalseFor(userWithRoles(emptyList()))
                .isFalseFor(userWithRoles(null))
                .isFalseFor(null)
            .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeastOne(User::balances).isInstanceOf($argument1)")
    public void atLeastOneIsInstanceOf(PredicateContext<User, Number> context, Class<?> clazz) {
        scenario(context, where().atLeastOne(User::balances).isInstanceOf(clazz));
    }

    static Stream<Arguments> atLeastOneIsInstanceOf() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().atLeastOne(User::balances).isInstanceOf($argument1)")
                .withMapper(User::balances)
                .withArgument(Integer.class)
                    .isTrueFor(userWithBalances(asList(100, 200, 300)))
                    .isTrueFor(userWithBalances(asList(1.0, 1L, 1)))
                    .isTrueFor(userWithBalances(singletonList(1)))
                    .isFalseFor(userWithBalances(asList(1.1, 2.2, 3.3)))
                    .isFalseFor(userWithBalances(asList(100L, 200L)))
                    .isFalseFor(userWithBalances(asList(1.0, 1L, null, null)))
                    .isFalseFor(userWithBalances(singletonList(1L)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArgument(Double.class)
                    .isTrueFor(userWithBalances(asList(1.1, 2.2, 3.3)))
                    .isTrueFor(userWithBalances(asList(100.0, 200.5, 300.0)))
                    .isTrueFor(userWithBalances(singletonList(1.0)))
                    .isFalseFor(userWithBalances(asList(100, 200, 300)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isFalseFor(userWithBalances(asList(null, 100, null, 200L)))
                    .isFalseFor(userWithBalances(singletonList(100)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeastOne(User::balances).notInstanceOf($argument1)")
    public void atLeastOneNotInstanceOf(PredicateContext<User, Number> context, Class<?> clazz) {
        scenario(context, where().atLeastOne(User::balances).notInstanceOf(clazz));
    }

    static Stream<Arguments> atLeastOneNotInstanceOf() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().atLeastOne(User::balances).notInstanceOf($argument1)")
                .withMapper(User::balances)
                .withArgument(Integer.class)
                    .isTrueFor(userWithBalances(asList(1.1, 2.2, 3.3)))
                    .isTrueFor(userWithBalances(asList(1, 1.0, 1L)))
                    .isTrueFor(userWithBalances(asList(null, 123, null)))
                    .isTrueFor(userWithBalances(singletonList(10L)))
                    .isTrueFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(asList(100, 200, 300)))
                    .isFalseFor(userWithBalances(singletonList(10)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArgument(Double.class)
                    .isTrueFor(userWithBalances(asList(100, 200, 300)))
                    .isTrueFor(userWithBalances(asList(1, 1.0, 1L)))
                    .isTrueFor(userWithBalances(asList(null, 123.1, null)))
                    .isTrueFor(userWithBalances(singletonList(10L)))
                    .isTrueFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(asList(1.1, 2.2, 3.3)))
                    .isFalseFor(userWithBalances(singletonList(10.0)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithBalances(asList(null, 100, null, 200L)))
                    .isTrueFor(userWithBalances(singletonList(100)))
                    .isTrueFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeastOne(User::roles).isNull()")
    public void atLeastOneIsNull(PredicateContext<User, String> context) {
        scenario(context, where().atLeastOne(User::roles).isNull());
    }

    static Stream<Arguments> atLeastOneIsNull() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeastOne(User::roles).isNull()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(asList(null, "Admin", null)))
                    .isTrueFor(userWithRoles(asList(null, "Admin")))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(asList("Admin", "User")))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeastOne(User::roles).notNull()")
    public void atLeastOneNotNull(PredicateContext<User, String> context) {
        scenario(context, where().atLeastOne(User::roles).notNull());
    }

    static Stream<Arguments> atLeastOneNotNull() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeastOne(User::roles).notNull()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(asList(null, "Admin", null)))
                    .isTrueFor(userWithRoles(asList("Admin", "User")))
                    .isTrueFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeastOne(User::roles).in($argument1)")
    public void atLeastOneIn(PredicateContext<User, String> context, Collection<String> validRoles) {
        scenario(context, where().atLeastOne(User::roles).in(validRoles));
    }

    static Stream<Arguments> atLeastOneIn() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeastOne(User::roles).in($argument1)")
                .withMapper(User::roles)
                    .withArgument(asList("Admin", "User"))
                        .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isTrueFor(userWithRoles(asList("Moderator", "User")))
                        .isTrueFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(asList("Guest", "Moderator")))
                        .isFalseFor(userWithRoles(singletonList("Guest")))
                        .isFalseFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(singletonList("Admin"))
                        .isTrueFor(userWithRoles(asList("Admin", "User")))
                        .isTrueFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(asList("User", "Guest")))
                        .isFalseFor(userWithRoles(singletonList("User")))
                        .isFalseFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(emptyList())
                        .isFalseFor(userWithRoles(asList("Admin", "User")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isFalseFor(userWithRoles(asList("Admin", "User")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeastOne(User::roles).notIn($argument1)")
    public void atLeastOneNotIn(PredicateContext<User, String> context, Collection<String> invalidRoles) {
        scenario(context, where().atLeastOne(User::roles).notIn(invalidRoles));
    }

    static Stream<Arguments> atLeastOneNotIn() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeastOne(User::roles).notIn($argument1)")
                .withMapper(User::roles)
                    .withArgument(asList("Admin", "User"))
                        .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isTrueFor(userWithRoles(asList("Guest", "Moderator")))
                        .isTrueFor(userWithRoles(asList("Moderator", "User")))
                        .isTrueFor(userWithRoles(singletonList(null)))
                        .isTrueFor(userWithRoles(singletonList("Guest")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(singletonList("Admin"))
                        .isTrueFor(userWithRoles(asList("User", "Guest")))
                        .isTrueFor(userWithRoles(asList("Admin", "User")))
                        .isTrueFor(userWithRoles(singletonList("User")))
                        .isTrueFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(emptyList())
                        .isTrueFor(userWithRoles(asList("Admin", "User")))
                        .isTrueFor(userWithRoles(singletonList("Admin")))
                        .isTrueFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isTrueFor(userWithRoles(asList("Admin", "User")))
                        .isTrueFor(userWithRoles(singletonList("Admin")))
                        .isTrueFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeastOne(User::roles).accepts($argument1)")
    public void atLeastOneAccepts(PredicateContext<User, String> context, Predicate<String> predicate) {
        scenario(context, where().atLeastOne(User::roles).accepts(predicate));
    }

    static Stream<Arguments> atLeastOneAccepts() {
        Predicate<String> predicate = role -> role != null && role.trim().equalsIgnoreCase("admin");
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeastOne(User::roles).accepts($argument1)")
                .withMapper(User::roles)
                .withArgument(predicate, "role -> role != null && role.trim().equalsIgnoreCase(\"admin\")")
                    .isTrueFor(userWithRoles(asList("Admin", "admin", "Guest", null, "Moderator")))
                    .isTrueFor(userWithRoles(asList("User", "   Admin   \n\t")))
                    .isTrueFor(userWithRoles(singletonList("ADMIN")))
                    .isFalseFor(userWithRoles(asList("Guest", null, "Moderator", "adm1n")))
                    .isFalseFor(userWithRoles(singletonList("User")))
                    .isFalseFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isFalseFor(userWithRoles(asList("Admin", "admin", null, "Guest")))
                    .isFalseFor(userWithRoles(singletonList("admin")))
                    .isFalseFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeastOne(User::roles).rejects($argument1)")
    public void atLeastOneRejects(PredicateContext<User, String> context, Predicate<String> predicate) {
        scenario(context, where().atLeastOne(User::roles).rejects(predicate));
    }

    static Stream<Arguments> atLeastOneRejects() {
        Predicate<String> predicate = role -> role != null && role.trim().equalsIgnoreCase("admin");
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeastOne(User::roles).rejects($argument1)")
                .withMapper(User::roles)
                .withArgument(predicate, "role -> role != null && role.trim().equalsIgnoreCase(\"admin\")")
                    .isTrueFor(userWithRoles(asList("Admin", "admin", "Guest", null, "Moderator")))
                    .isTrueFor(userWithRoles(asList("Admin", "guest", " admin ")))
                    .isTrueFor(userWithRoles(singletonList("User")))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(asList("admin", "   Admin   \n\t")))
                    .isFalseFor(userWithRoles(singletonList("ADMIN")))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithRoles(asList("Admin", "admin", null, "Guest")))
                    .isTrueFor(userWithRoles(singletonList("admin")))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @Logical("conjunction")
    @CaseAs(provider = FirstArgument.class)
    @As("Logical conjunction of 3 predicates")
    public void whereAtLeastOneConjunction(PredicateContext<User, String> context) {
        scenario(context, where().atLeastOne(User::roles).notNull()
                .and().atLeastOne(User::permissions).isEqualTo("read")
                .and().atLeastOne(User::roles).notIn(ImmutableList.of("Guest", "Moderator")));
    }

    static Stream<Arguments> whereAtLeastOneConjunction() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeastOne(User::roles).notNull()\n" +
                        "       .and().atLeastOne(User::permissions).isEqualTo(\\\"read\\\")\\n" +
                        "       .and().atLeastOne(User::roles).notIn(ImmutableList.of(\"Guest\", \"Moderator\"))")
                .isTrueFor(userWithRolesAndPermissions(asList(null, "User", null, null), ImmutableSet.of("read", "write")))
                .isFalseFor(userWithRolesAndPermissions(asList("User", null), singleton("write"))) // permissions don't contain "read"
                .isFalseFor(userWithRolesAndPermissions(asList("Guest", "Moderator"), ImmutableSet.of("read", "write"))) // roles don't contain null
                .isFalseFor(userWithRolesAndPermissions(emptyList(), ImmutableSet.of("read"))) // roles are empty
                .isFalseFor(userWithRolesAndPermissions(null, ImmutableSet.of("read"))) // roles are null
                .isFalseFor(null) // user is null
                .toStream();
    }
}