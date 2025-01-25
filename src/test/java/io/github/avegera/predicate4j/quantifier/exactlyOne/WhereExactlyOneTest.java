package io.github.avegera.predicate4j.quantifier.exactlyOne;

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
import static io.github.avegera.predicate4j.test.model.User.userWithBalances;
import static io.github.avegera.predicate4j.test.model.User.userWithRoles;
import static io.github.avegera.predicate4j.test.util.ListWithNullableIterator.listWithNullableIterator;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@Where("where")
@Type("bject")
@Quantifier("exactlyOne")
@Description("Test predicates under where().exactlyOne(...)")
public class WhereExactlyOneTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().exactlyOne(User::roles).isEqualTo($argument1)")
    public void exactlyOneIsEqualTo(PredicateContext<User, String> context, String value) {
        scenario(context, where().exactlyOne(User::roles).isEqualTo(value));
    }

    static Stream<Arguments> exactlyOneIsEqualTo() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactlyOne(User::roles).isEqualTo($argument1)")
                .withMapper(User::roles)
                .withArgument("Admin")
                    .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isTrueFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(asList("Admin", "Admin", "User")))
                    .isFalseFor(userWithRoles(asList("User", "Guest", null)))
                    .isFalseFor(userWithRoles(asList(null, null)))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithRoles(asList("Admin", null, "User")))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(asList(null, null)))
                    .isFalseFor(userWithRoles(asList("Admin", "User")))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().exactlyOne(User::roles).notEqualTo($argument1)")
    public void exactlyOneNotEqualTo(PredicateContext<User, String> context, String value) {
        scenario(context, where().exactlyOne(User::roles).notEqualTo(value));
    }

    static Stream<Arguments> exactlyOneNotEqualTo() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactlyOne(User::roles).notEqualTo($argument1)")
                .withMapper(User::roles)
                .withArgument("Admin")
                    .isTrueFor(userWithRoles(asList("Admin", "Admin", "Guest")))
                    .isTrueFor(userWithRoles(asList("Admin", "User")))
                    .isTrueFor(userWithRoles(asList(null, "Admin")))
                    .isTrueFor(userWithRoles(singletonList("User")))
                    .isFalseFor(userWithRoles(asList("Admin", "Admin")))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithRoles(asList(null, null, "Admin")))
                    .isFalseFor(userWithRoles(asList("Admin", null, "User")))
                    .isFalseFor(userWithRoles(asList("User", "Guest")))
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
    @As("where().exactlyOne(User::balances).isInstanceOf($argument1)")
    public void exactlyOneIsInstanceOf(PredicateContext<User, Number> context, Class<?> clazz) {
        scenario(context, where().exactlyOne(User::balances).isInstanceOf(clazz));
    }

    static Stream<Arguments> exactlyOneIsInstanceOf() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().exactlyOne(User::balances).isInstanceOf($argument1)")
                .withMapper(User::balances)
                .withArgument(Integer.class)
                    .isTrueFor(userWithBalances(asList(100L, 200, 3.0)))
                    .isTrueFor(userWithBalances(asList(1, 1.0, null)))
                    .isTrueFor(userWithBalances(singletonList(100)))
                    .isFalseFor(userWithBalances(asList(1, 2, 3)))
                    .isFalseFor(userWithBalances(asList(1.1, 2.2, 3.3)))
                    .isFalseFor(userWithBalances(asList(null, null, null)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArgument(Double.class)
                    .isTrueFor(userWithBalances(asList(1.1, 100, 200)))
                    .isTrueFor(userWithBalances(asList(null, 2.2, 3)))
                    .isTrueFor(userWithBalances(singletonList(1.0)))
                    .isFalseFor(userWithBalances(asList(1.1, 2.2, 3.3)))
                    .isFalseFor(userWithBalances(asList(100, 200, 300)))
                    .isFalseFor(userWithBalances(asList(null, null, null)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isFalseFor(userWithBalances(asList(100, 200, 300)))
                    .isFalseFor(userWithBalances(asList(1.1, 2.2, 3.3)))
                    .isFalseFor(userWithBalances(asList(null, null, null)))
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
    @As("where().exactlyOne(User::balances).notInstanceOf($argument1)")
    public void exactlyOneNotInstanceOf(PredicateContext<User, Number> context, Class<?> clazz) {
        scenario(context, where().exactlyOne(User::balances).notInstanceOf(clazz));
    }

    static Stream<Arguments> exactlyOneNotInstanceOf() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().exactlyOne(User::balances).notInstanceOf($argument1)")
                .withMapper(User::balances)
                .withArgument(Integer.class)
                    .isTrueFor(userWithBalances(asList(1.1, 200, 300)))
                    .isTrueFor(userWithBalances(asList(100, null, 300)))
                    .isTrueFor(userWithBalances(singletonList(1.1)))
                    .isTrueFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(asList(100, 200, 300)))
                    .isFalseFor(userWithBalances(asList(1, 1, 1)))
                    .isFalseFor(userWithBalances(asList(null, null, null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArgument(Double.class)
                    .isTrueFor(userWithBalances(asList(11.1, 20.0, 300)))
                    .isTrueFor(userWithBalances(asList(1.1, null, 2.2)))
                    .isTrueFor(userWithBalances(singletonList(100)))
                    .isTrueFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(asList(1.1, 2.2, 3.3)))
                    .isFalseFor(userWithBalances(asList(100.0, 200.5, 300.0)))
                    .isFalseFor(userWithBalances(asList(null, null, null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(asList(100, 200, 300)))
                    .isFalseFor(userWithBalances(asList(1.1, 2.2, 3.3)))
                    .isFalseFor(userWithBalances(asList(null, null, null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().exactlyOne(User::roles).isNull()")
    public void exactlyOneIsNull(PredicateContext<User, String> context) {
        scenario(context, where().exactlyOne(User::roles).isNull());
    }

    static Stream<Arguments> exactlyOneIsNull() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactlyOne(User::roles).isNull()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(asList(null, "Admin", "User")))
                    .isTrueFor(userWithRoles(asList("Admin", null, "Guest")))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isFalseFor(userWithRoles(asList(null, null, "Admin")))
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
    @As("where().exactlyOne(User::roles).notNull()")
    public void exactlyOneNotNull(PredicateContext<User, String> context) {
        scenario(context, where().exactlyOne(User::roles).notNull());
    }

    static Stream<Arguments> exactlyOneNotNull() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactlyOne(User::roles).notNull()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(asList(null, "Admin", null)))
                    .isTrueFor(userWithRoles(asList("Admin", null, null)))
                    .isTrueFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isFalseFor(userWithRoles(asList(null, "Admin", "User")))
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
    @As("where().exactlyOne(User::roles).in($argument1)")
    public void exactlyOneIn(PredicateContext<User, String> context, Collection<String> validRoles) {
        scenario(context, where().exactlyOne(User::roles).in(validRoles));
    }

    static Stream<Arguments> exactlyOneIn() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactlyOne(User::roles).in($argument1)")
                .withMapper(User::roles)
                    .withArgument(asList("Admin", "User"))
                        .isTrueFor(userWithRoles(asList("Admin", null, null)))
                        .isTrueFor(userWithRoles(asList("User", null, null)))
                        .isTrueFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isFalseFor(userWithRoles(asList("Guest", "Moderator")))
                        .isFalseFor(userWithRoles(singletonList("Guest")))
                        .isFalseFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(singletonList("Admin"))
                        .isTrueFor(userWithRoles(asList("Admin", null, null)))
                        .isTrueFor(userWithRoles(singletonList("Admin")))
                        .isTrueFor(userWithRoles(asList("Admin", "User")))
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
    @As("where().exactlyOne(User::roles).notIn($argument1)")
    public void exactlyOneNotIn(PredicateContext<User, String> context, Collection<String> invalidRoles) {
        scenario(context, where().exactlyOne(User::roles).notIn(invalidRoles));
    }

    static Stream<Arguments> exactlyOneNotIn() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactlyOne(User::roles).notIn($argument1)")
                .withMapper(User::roles)
                    .withArgument(asList("Admin", "User"))
                        .isTrueFor(userWithRoles(asList("User", "Admin", "Moderator")))
                        .isTrueFor(userWithRoles(singletonList("Guest")))
                        .isTrueFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(asList("Guest", null, null)))
                        .isFalseFor(userWithRoles(asList("Moderator", null, null)))
                        .isFalseFor(userWithRoles(asList("Admin", "AdminUser", "Guest")))
                        .isFalseFor(userWithRoles(asList("Admin", "User")))
                        .isFalseFor(userWithRoles(asList("Admin", null, null)))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(singletonList("Admin"))
                        .isTrueFor(userWithRoles(asList("Admin", "User", "Admin")))
                        .isTrueFor(userWithRoles(asList("Admin", null)))
                        .isTrueFor(userWithRoles(singletonList("User")))
                        .isTrueFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(asList("Admin", null, null)))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(emptyList())
                        .isTrueFor(userWithRoles(singletonList("Admin")))
                        .isTrueFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(asList("Admin", "User")))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isTrueFor(userWithRoles(singletonList("Admin")))
                        .isTrueFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(asList("Admin", "User")))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().exactlyOne(User::roles).accepts($argument1)")
    public void exactlyOneAccepts(PredicateContext<User, String> context, Predicate<String> predicate) {
        scenario(context, where().exactlyOne(User::roles).accepts(predicate));
    }

    static Stream<Arguments> exactlyOneAccepts() {
        Predicate<String> predicate = role -> role != null && role.trim().equalsIgnoreCase("admin");
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactlyOne(User::roles).accepts($argument1)")
                .withMapper(User::roles)
                .withArgument(predicate, "role -> role != null && role.trim().equalsIgnoreCase(\"admin\")")
                    .isTrueFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                    .isTrueFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(asList("Admin", "admin", "Guest", "Moderator")))
                    .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
                    .isFalseFor(userWithRoles(asList(null, null, null)))
                    .isFalseFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isFalseFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
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
    @As("where().exactlyOne(User::roles).rejects($argument1)")
    public void exactlyOneRejects(PredicateContext<User, String> context, Predicate<String> predicate) {
        scenario(context, where().exactlyOne(User::roles).rejects(predicate));
    }

    static Stream<Arguments> exactlyOneRejects() {
        Predicate<String> predicate = role -> role != null && role.trim().equalsIgnoreCase("admin");
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactlyOne(User::roles).rejects($argument1)")
                .withMapper(User::roles)
                .withArgument(predicate, "role -> role != null && role.trim().equalsIgnoreCase(\"admin\")")
                    .isTrueFor(userWithRoles(asList("Admin", "admin", "Moderator")))
                    .isTrueFor(userWithRoles(singletonList("User")))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                    .isFalseFor(userWithRoles(asList(null, "Admin", "User")))
                    .isFalseFor(userWithRoles(asList("Admin", "admin")))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithRoles(singletonList("User")))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                    .isFalseFor(userWithRoles(asList("User", "Guest", "Admin")))
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
    @As("Logical conjunction of 3 predicates with exactlyOne(User::roles)")
    public void exactlyOneConjunction(PredicateContext<User, String> context) {
        scenario(context, where().exactlyOne(User::roles).isNull()
                .and().exactlyOne(User::roles).isEqualTo("Admin")
                .and().exactlyOne(User::roles).in(asList("Guest", "Moderator")));
    }

    static Stream<Arguments> exactlyOneConjunction() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactlyOne(User::roles).isNull()\n" +
                        "       .and().exactlyOne(User::roles).isEqualTo(\"Admin\")\n" +
                        "       .and().exactlyOne(User::roles).in(asList(\"Guest\", \"Moderator\"))")
                .isTrueFor(userWithRoles(asList("Admin", "Guest", null))) // Exactly one role is "Admin", one is null, and one in list
                .isFalseFor(userWithRoles(asList("Admin", "Admin", "User"))) // More than one "Admin" violates exactly one condition
                .isFalseFor(userWithRoles(asList("Admin", "Moderator", "User"))) // One role is in excluded list
                .isFalseFor(userWithRoles(asList(null, "Guest", "Moderator"))) // No "Admin" role present
                .isFalseFor(userWithRoles(listWithNullableIterator())) // List with nullable iterator
                .isFalseFor(userWithRoles(emptyList())) // Only null role
                .isFalseFor(userWithRoles(emptyList())) // Empty list
                .isFalseFor(userWithRoles(null)) // Roles list is null
                .isFalseFor(null) // User is null
                .toStream();
    }
}