package io.github.avegera.predicate4j.quantifier.exactly;

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
@Type("object")
@Quantifier("exactly")
@Description("Test predicates under where().exactly(...)")
public class WhereExactlyTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().exactly(2, User::roles).isEqualTo($argument1)")
    public void exactlyTwoIsEqualTo(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().exactly(2, context.mapper()).isEqualTo(value));
    }

    static Stream<Arguments> exactlyTwoIsEqualTo() {
        return PredicateTest.<User, Iterable<String>>builder()
                .predicate("where().exactly(2, User::roles).isEqualTo($argument1)")
                .withMapper(User::roles)
                    .withArgument("Admin")
                        .isTrueFor(userWithRoles(asList("Admin", "Admin", "User")))
                        .isTrueFor(userWithRoles(asList("Admin", "Admin", "Guest")))
                        .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isFalseFor(userWithRoles(asList("Admin", "Admin", "Admin")))
                        .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isTrueFor(userWithRoles(asList(null, null, "User")))
                        .isTrueFor(userWithRoles(asList(null, null, "Admin")))
                        .isFalseFor(userWithRoles(asList(null, null, null)))
                        .isFalseFor(userWithRoles(asList("Admin", null, "User")))
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
    @As("where().exactly(2, User::roles).notEqualTo($argument1)")
    public void exactlyTwoNotEqualTo(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().exactly(2, context.mapper()).notEqualTo(value));
    }

    static Stream<Arguments> exactlyTwoNotEqualTo() {
        return PredicateTest.<User, Iterable<String>>builder()
                .predicate("where().exactly(2, User::roles).notEqualTo($argument1)")
                .withMapper(User::roles)
                    .withArgument("Admin")
                        .isTrueFor(userWithRoles(asList("User", "Guest", "Admin")))
                        .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
                        .isFalseFor(userWithRoles(asList("Admin", "Admin", "User")))
                        .isFalseFor(userWithRoles(asList("Admin", "Admin", "Admin")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isTrueFor(userWithRoles(asList(null, "Admin", "User")))
                        .isTrueFor(userWithRoles(asList("User", "Guest")))
                        .isFalseFor(userWithRoles(asList("Admin", "User", "Admin")))
                        .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isFalseFor(userWithRoles(asList(null, null, "User")))
                        .isFalseFor(userWithRoles(asList(null, null, null)))
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
    @As("where().exactly(2, User::balances).isInstanceOf($argument1)")
    public void exactlyTwoIsInstanceOf(PredicateContext<User, Iterable<Number>> context, Class<?> clazz) {
        scenario(context, where().exactly(2, context.mapper()).isInstanceOf(clazz));
    }

    static Stream<Arguments> exactlyTwoIsInstanceOf() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().exactly(2, User::balances).isInstanceOf($argument1)")
                .withMapper(User::balances)
                    .withArgument(Integer.class)
                        .isTrueFor(userWithBalances(asList(100, 200, 3.0)))
                        .isTrueFor(userWithBalances(asList(1, 1, null)))
                        .isTrueFor(userWithBalances(asList(100, 200, null)))
                        .isFalseFor(userWithBalances(asList(1, 2, 3)))
                        .isFalseFor(userWithBalances(asList(1.1, 2.2, 3.3)))
                        .isFalseFor(userWithBalances(asList(null, null, null)))
                        .isFalseFor(userWithBalances(singletonList(null)))
                        .isFalseFor(userWithBalances(listWithNullableIterator()))
                        .isFalseFor(userWithBalances(emptyList()))
                        .isFalseFor(userWithBalances(null))
                        .isFalseFor(null)
                    .withArgument(Double.class)
                        .isTrueFor(userWithBalances(asList(1.1, 2.2, 3)))
                        .isTrueFor(userWithBalances(asList(null, 1.1, 2.2)))
                        .isTrueFor(userWithBalances(asList(1.1, 2.2, 100)))
                        .isFalseFor(userWithBalances(asList(1.1, 2.2, 3.3)))
                        .isFalseFor(userWithBalances(asList(100, 200, 300)))
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
    @As("where().exactly(2, User::balances).notInstanceOf($argument1)")
    public void exactlyTwoNotInstanceOf(PredicateContext<User, Iterable<Number>> context, Class<?> clazz) {
        scenario(context, where().exactly(2, context.mapper()).notInstanceOf(clazz));
    }

    static Stream<Arguments> exactlyTwoNotInstanceOf() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().exactly(2, User::balances).notInstanceOf($argument1)")
                .withMapper(User::balances)
                    .withArgument(Integer.class)
                        .isTrueFor(userWithBalances(asList(1.1, 2.2, 3)))
                        .isTrueFor(userWithBalances(asList(1, 2.2, null)))
                        .isTrueFor(userWithBalances(asList(1.1, null, 3)))
                        .isFalseFor(userWithBalances(asList(100, 200, 3.0)))
                        .isFalseFor(userWithBalances(asList(1, 1, null)))
                        .isFalseFor(userWithBalances(asList(100, 200, null)))
                        .isFalseFor(userWithBalances(asList(100, 200, 300)))
                        .isFalseFor(userWithBalances(asList(null, null, null)))
                        .isFalseFor(userWithBalances(singletonList(null)))
                        .isFalseFor(userWithBalances(listWithNullableIterator()))
                        .isFalseFor(userWithBalances(emptyList()))
                        .isFalseFor(userWithBalances(null))
                        .isFalseFor(null)
                    .withArgument(Double.class)
                        .isTrueFor(userWithBalances(asList(100, 200, 3.0)))
                        .isTrueFor(userWithBalances(asList(100, 2.0, null)))
                        .isTrueFor(userWithBalances(asList(1.1, 2, 3)))
                        .isFalseFor(userWithBalances(asList(1.1, 2.2, 3)))
                        .isFalseFor(userWithBalances(asList(null, 1.1, 2.2)))
                        .isFalseFor(userWithBalances(asList(1.1, 2.2, 100)))
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
    @As("where().exactly(2, User::roles).isNull()")
    public void exactlyTwoIsNull(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().exactly(2, context.mapper()).isNull());
    }

    static Stream<Arguments> exactlyTwoIsNull() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactly(2, User::roles).isNull()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(asList(null, null, "User")))
                    .isTrueFor(userWithRoles(asList("Admin", null, null)))
                    .isFalseFor(userWithRoles(asList(null, null, null, "Guest")))
                    .isFalseFor(userWithRoles(asList(null, "Admin", "User")))
                    .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
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
    @As("where().exactly(2, User::roles).notNull()")
    public void exactlyTwoNotNull(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().exactly(2, context.mapper()).notNull());
    }

    static Stream<Arguments> exactlyTwoNotNull() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactly(2, User::roles).notNull()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(asList("Admin", "User", null)))
                    .isTrueFor(userWithRoles(asList("Admin", null, "Guest")))
                    .isTrueFor(userWithRoles(asList(null, "User", "Guest", null)))
                    .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isFalseFor(userWithRoles(asList("Admin", null, null)))
                    .isFalseFor(userWithRoles(asList(null, null, "Guest")))
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
    @As("where().exactly(2, User::roles).in($argument1)")
    public void exactlyTwoIn(PredicateContext<User, Iterable<String>> context, Collection<String> validRoles) {
        scenario(context, where().exactly(2, context.mapper()).in(validRoles));
    }

    static Stream<Arguments> exactlyTwoIn() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactly(2, User::roles).in($argument1)")
                .withMapper(User::roles)
                    .withArgument(asList("Admin", "User"))
                        .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isTrueFor(userWithRoles(asList("Admin", "User", null)))
                        .isTrueFor(userWithRoles(asList("Admin", "Admin", "Guest")))
                        .isFalseFor(userWithRoles(asList("Admin", "User", "User")))
                        .isFalseFor(userWithRoles(asList("Guest", "Moderator")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(singletonList("Admin"))
                        .isTrueFor(userWithRoles(asList("Admin", "Admin", "Guest")))
                        .isTrueFor(userWithRoles(asList("Admin", "Admin", null)))
                        .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isFalseFor(userWithRoles(asList("Guest", "Moderator")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
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
    @As("where().exactly(2, User::roles).notIn($argument1)")
    public void exactlyTwoNotIn(PredicateContext<User, Iterable<String>> context, Collection<String> invalidRoles) {
        scenario(context, where().exactly(2, context.mapper()).notIn(invalidRoles));
    }

    static Stream<Arguments> exactlyTwoNotIn() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactly(2, User::roles).notIn($argument1)")
                .withMapper(User::roles)
                    .withArgument(asList("Admin", "User"))
                        .isTrueFor(userWithRoles(asList("Guest", "Moderator", "Admin")))
                        .isTrueFor(userWithRoles(asList("Guest", "Moderator", "User")))
                        .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isFalseFor(userWithRoles(asList("Admin", "User", null)))
                        .isFalseFor(userWithRoles(asList("Admin", "Admin", "Guest")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(singletonList("Admin"))
                        .isTrueFor(userWithRoles(asList("User", "Guest", "Admin")))
                        .isTrueFor(userWithRoles(asList("User", "Admin", null)))
                        .isFalseFor(userWithRoles(asList("Admin", "Admin", "Guest")))
                        .isFalseFor(userWithRoles(asList("Admin", "Admin", null)))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(emptyList())
                        .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
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
    @As("where().exactly(2, User::roles).accepts($argument1)")
    public void exactlyTwoAccepts(PredicateContext<User, Iterable<String>> context, Predicate<String> predicate) {
        scenario(context, where().exactly(2, context.mapper()).accepts(predicate));
    }

    static Stream<Arguments> exactlyTwoAccepts() {
        Predicate<String> predicate = role -> role != null && role.startsWith("Admin");
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactly(2, User::roles).accepts($argument1)")
                .withMapper(User::roles)
                    .withArgument(predicate, "role -> role != null && role.startsWith(\"Admin\")")
                        .isTrueFor(userWithRoles(asList("Admin", "AdminUser", "Guest")))
                        .isTrueFor(userWithRoles(asList("Admin", "AdminAssistant", null)))
                        .isFalseFor(userWithRoles(asList("Admin", "Admin", "Admin")))
                        .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
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
    @As("where().exactly(2, User::roles).rejects($argument1)")
    public void exactlyTwoRejects(PredicateContext<User, Iterable<String>> context, Predicate<String> predicate) {
        scenario(context, where().exactly(2, context.mapper()).rejects(predicate));
    }

    static Stream<Arguments> exactlyTwoRejects() {
        Predicate<String> predicate = role -> role != null && role.startsWith("Admin");
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactly(2, User::roles).rejects($argument1)")
                .withMapper(User::roles)
                .withArgument(predicate, "role -> role != null && role.startsWith(\"Admin\")")
                    .isTrueFor(userWithRoles(asList("User", "Guest", "Admin")))
                    .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isFalseFor(userWithRoles(asList("AdminAssistant", "Moderator", "AdminUser")))
                    .isFalseFor(userWithRoles(asList("Admin", "AdminAssistant", "AdminUser")))
                    .isFalseFor(userWithRoles(asList("Admin", "AdminAssistant", "Guest")))
                    .isFalseFor(userWithRoles(asList("Admin", "Admin", "Admin")))
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
    @Logical("conjunction")
    @CaseAs(provider = FirstArgument.class)
    @As("Logical conjunction of 3 predicates with exactly(2, User::roles)")
    public void exactlyTwoConjunction(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where()
                .exactly(2, User::roles).isNull()
                .and().exactly(2, User::roles).notNull()
                .and().exactly(2, User::roles).accepts(role -> role != null && role.startsWith("Admin")));
    }

    static Stream<Arguments> exactlyTwoConjunction() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactly(2, User::roles).isNull()\n" +
                           "       .and().exactly(2, User::roles).notNull()\n" +
                           "       .and().exactly(2, User::roles).accepts(role -> role != null && role.startsWith(\"Admin\"))")
                .isTrueFor(userWithRoles(asList("Admin", null, "AdminAssistant", null))) // Exactly 2 nulls, 2 non-nulls, and 2 roles starting with "Admin"
                .isFalseFor(userWithRoles(asList("Admin", "AdminAssistant", "Guest", null, null))) // 2 nulls, 3 non-nulls, but only 2 matching "Admin"
                .isFalseFor(userWithRoles(asList("Admin", "AdminAssistant", "AdminUser", "Admin", null))) // 1 null, too many matches for "Admin"
                .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator", null, null))) // 2 nulls, no matches for "Admin"
                .isFalseFor(userWithRoles(listWithNullableIterator())) // List with nullable iterator
                .isFalseFor(userWithRoles(emptyList())) // Only 1 null
                .isFalseFor(userWithRoles(emptyList())) // Empty list
                .isFalseFor(userWithRoles(null)) // Roles list is null
                .isFalseFor(null) // User is null
                .toStream();
    }


}