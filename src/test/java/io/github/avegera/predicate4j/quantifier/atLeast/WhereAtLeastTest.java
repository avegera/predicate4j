package io.github.avegera.predicate4j.quantifier.atLeast;

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
@Quantifier("atLeast")
@Description("Test predicates under where().atLeast(...)")
public class WhereAtLeastTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeast(2, User::roles).isEqualTo($argument1)")
    public void atLeastTwoIsEqualTo(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().atLeast(2, context.mapper()).isEqualTo(value));
    }

    static Stream<Arguments> atLeastTwoIsEqualTo() {
        return PredicateTest.<User, Iterable<String>>builder()
                .predicate("where().atLeast(2, User::roles).isEqualTo($argument1)")
                .withMapper(User::roles)
                    .withArgument("Admin")
                        .isTrueFor(userWithRoles(asList("Admin", "Admin", "User")))
                        .isTrueFor(userWithRoles(asList("Admin", "Admin", "Guest")))
                        .isTrueFor(userWithRoles(asList("Admin", "Admin", "Admin")))
                        .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
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
                        .isTrueFor(userWithRoles(asList(null, null, null)))
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
    @As("where().atLeast(2, User::roles).notEqualTo($argument1)")
    public void atLeastTwoNotEqualTo(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().atLeast(2, context.mapper()).notEqualTo(value));
    }

    static Stream<Arguments> atLeastTwoNotEqualTo() {
        return PredicateTest.<User, Iterable<String>>builder()
                .predicate("where().atLeast(2, User::roles).notEqualTo($argument1)")
                .withMapper(User::roles)
                    .withArgument("Admin")
                        .isTrueFor(userWithRoles(asList("User", "Guest", "Admin")))
                        .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isTrueFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                        .isFalseFor(userWithRoles(asList("Admin", "Admin", "Guest")))
                        .isFalseFor(userWithRoles(asList("Admin", "Admin", "Admin")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isTrueFor(userWithRoles(asList("Admin", "User", null)))
                        .isTrueFor(userWithRoles(asList("User", "Guest", null)))
                        .isFalseFor(userWithRoles(asList(null, null, "Admin")))
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
    @As("where().atLeast(2, User::balances).isInstanceOf($argument1)")
    public void atLeastTwoIsInstanceOf(PredicateContext<User, Iterable<Number>> context, Class<?> clazz) {
        scenario(context, where().atLeast(2, context.mapper()).isInstanceOf(clazz));
    }

    static Stream<Arguments> atLeastTwoIsInstanceOf() {
        return PredicateTest.<User, Iterable<Number>>builder()
                .predicate("where().atLeast(2, User::balances).isInstanceOf($argument1)")
                .withMapper(User::balances)
                    .withArgument(Integer.class)
                        .isTrueFor(userWithBalances(asList(100, 200, 3.0)))
                        .isTrueFor(userWithBalances(asList(1, 1, null)))
                        .isTrueFor(userWithBalances(asList(100, 200, null)))
                        .isTrueFor(userWithBalances(asList(1, 2, 3)))
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
                        .isTrueFor(userWithBalances(asList(1.1, 2.2, 3.3)))
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
    @As("where().atLeast(2, User::balances).notInstanceOf($argument1)")
    public void atLeastTwoNotInstanceOf(PredicateContext<User, Iterable<Number>> context, Class<?> clazz) {
        scenario(context, where().atLeast(2, context.mapper()).notInstanceOf(clazz));
    }

    static Stream<Arguments> atLeastTwoNotInstanceOf() {
        return PredicateTest.<User, Iterable<Number>>builder()
                .predicate("where().atLeast(2, User::balances).notInstanceOf($argument1)")
                .withMapper(User::balances)
                    .withArgument(Integer.class)
                        .isTrueFor(userWithBalances(asList(1.1, 2.2, 3)))
                        .isTrueFor(userWithBalances(asList(1, 2.2, null)))
                        .isTrueFor(userWithBalances(asList(1.1, null, 3)))
                        .isTrueFor(userWithBalances(asList(null, null, null)))
                        .isFalseFor(userWithBalances(asList(100, 200, 3.0)))
                        .isFalseFor(userWithBalances(asList(1, 1, null)))
                        .isFalseFor(userWithBalances(asList(100, 200, null)))
                        .isFalseFor(userWithBalances(asList(100, 200, 300)))
                        .isFalseFor(userWithBalances(singletonList(null)))
                        .isFalseFor(userWithBalances(listWithNullableIterator()))
                        .isFalseFor(userWithBalances(emptyList()))
                        .isFalseFor(userWithBalances(null))
                        .isFalseFor(null)
                    .withArgument(Double.class)
                        .isTrueFor(userWithBalances(asList(100, 200, 3.0)))
                        .isTrueFor(userWithBalances(asList(100, 2.0, null)))
                        .isTrueFor(userWithBalances(asList(1.1, 2, 3)))
                        .isTrueFor(userWithBalances(asList(null, null, null)))
                        .isFalseFor(userWithBalances(asList(1.1, 2.2, 3)))
                        .isFalseFor(userWithBalances(asList(null, 1.1, 2.2)))
                        .isFalseFor(userWithBalances(asList(1.1, 2.2, 100)))
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
    @As("where().atLeast(2, User::roles).isNull()")
    public void atLeastTwoIsNull(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().atLeast(2, context.mapper()).isNull());
    }

    static Stream<Arguments> atLeastTwoIsNull() {
        return PredicateTest.<User, Iterable<String>>builder()
                .predicate("where().atLeast(2, User::roles).isNull()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(asList(null, null, "User")))
                    .isTrueFor(userWithRoles(asList(null, null, null)))
                    .isTrueFor(userWithRoles(asList("Admin", null, null)))
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
    @As("where().atLeast(2, User::roles).notNull()")
    public void atLeastTwoNotNull(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().atLeast(2, context.mapper()).notNull());
    }

    static Stream<Arguments> atLeastTwoNotNull() {
        return PredicateTest.<User, Iterable<String>>builder()
                .predicate("where().atLeast(2, User::roles).notNull()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(asList("Admin", "User", null)))
                    .isTrueFor(userWithRoles(asList("Admin", "Guest", "User")))
                    .isTrueFor(userWithRoles(asList("Admin", "User", "User", null)))
                    .isFalseFor(userWithRoles(asList(null, "User", null)))
                    .isFalseFor(userWithRoles(asList(null, null, "User")))
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
    @As("where().atLeast(2, User::roles).in($argument1)")
    public void atLeastTwoIn(PredicateContext<User, Iterable<String>> context, Collection<String> validRoles) {
        scenario(context, where().atLeast(2, context.mapper()).in(validRoles));
    }

    static Stream<Arguments> atLeastTwoIn() {
        return PredicateTest.<User, Iterable<String>>builder()
                .predicate("where().atLeast(2, User::roles).in($argument1)")
                .withMapper(User::roles)
                    .withArgument(asList("Admin", "User"))
                        .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isTrueFor(userWithRoles(asList("Admin", "User", "User")))
                        .isTrueFor(userWithRoles(asList("Admin", "Admin", "User")))
                        .isFalseFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
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
                        .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
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
    @As("where().atLeast(2, User::roles).notIn($argument1)")
    public void atLeastTwoNotIn(PredicateContext<User, Iterable<String>> context, Collection<String> invalidRoles) {
        scenario(context, where().atLeast(2, context.mapper()).notIn(invalidRoles));
    }

    static Stream<Arguments> atLeastTwoNotIn() {
        return PredicateTest.<User, Iterable<String>>builder()
                .predicate("where().atLeast(2, User::roles).notIn($argument1)")
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
                        .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
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
    @As("where().atLeast(2, User::roles).accepts($argument1)")
    public void atLeastTwoAccepts(PredicateContext<User, Iterable<String>> context, Predicate<String> predicate) {
        scenario(context, where().atLeast(2, context.mapper()).accepts(predicate));
    }

    static Stream<Arguments> atLeastTwoAccepts() {
        Predicate<String> predicate = role -> role != null && role.startsWith("Admin");
        return PredicateTest.<User, Iterable<String>>builder()
                .predicate("where().atLeast(2, User::roles).accepts($argument1)")
                .withMapper(User::roles)
                .withArgument(predicate, "role -> role != null && role.startsWith(\"Admin\")")
                    .isTrueFor(userWithRoles(asList("Admin", "AdminAssistant", "Guest")))
                    .isTrueFor(userWithRoles(asList("Admin", "AdminUser", null, "Moderator")))
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
    @As("where().atLeast(2, User::roles).rejects($argument1)")
    public void atLeastTwoRejects(PredicateContext<User, Iterable<String>> context, Predicate<String> predicate) {
        scenario(context, where().atLeast(2, context.mapper()).rejects(predicate));
    }

    static Stream<Arguments> atLeastTwoRejects() {
        Predicate<String> predicate = role -> role != null && role.startsWith("Admin");
        return PredicateTest.<User, Iterable<String>>builder()
                .predicate("where().atLeast(2, User::roles).rejects($argument1)")
                .withMapper(User::roles)
                .withArgument(predicate, "role -> role != null && role.startsWith(\"Admin\")")
                    .isTrueFor(userWithRoles(asList("User", "Guest", "Admin")))
                    .isTrueFor(userWithRoles(asList("User", "Moderator", "AdminAssistant", null)))
                    .isTrueFor(userWithRoles(asList("User", "Guest", "Moderator")))
                    .isFalseFor(userWithRoles(asList("Admin", "AdminAssistant", "Guest")))
                    .isFalseFor(userWithRoles(asList("Admin", "AdminUser", "AdminAssistant", null)))
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
    @As("Logical conjunction of 3 predicates with atLeast(2, User::roles)")
    public void atLeastTwoConjunction(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where()
                .atLeast(2, User::roles).notNull()
                .and().atLeast(2, User::roles).notIn(asList("Guest", "Moderator"))
                .and().atLeast(2, User::roles).accepts(role -> role != null && role.startsWith("Admin")));
    }

    static Stream<Arguments> atLeastTwoConjunction() {
        return PredicateTest.<User, Iterable<String>>builder()
                .predicate("where().atLeast(2, User::roles).isNotNull()\n" +
                           "       .and().atLeast(2, User::roles).notIn(asList(\"Guest\", \"Moderator\"))\n" +
                           "       .and().atLeast(2, User::roles).accepts(role -> role != null && role.startsWith(\"Admin\"))")
                .isTrueFor(userWithRoles(asList("AdminUser", "AdminAssistant", "User")))
                .isTrueFor(userWithRoles(asList("Admin", "AdminUser", "AdminAssistant", null)))
                .isTrueFor(userWithRoles(asList("Admin", "Guest", "Moderator", "AdminAssistant")))
                .isTrueFor(userWithRoles(asList("Admin", "AdminAssistant", "AdminManager", "Guest")))
                .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                .isFalseFor(userWithRoles(asList("Admin", null, "User", "Guest")))
                .isFalseFor(userWithRoles(singletonList("Admin")))
                .isFalseFor(userWithRoles(singletonList(null)))
                .isFalseFor(userWithRoles(listWithNullableIterator()))
                .isFalseFor(userWithRoles(emptyList()))
                .isFalseFor(userWithRoles(null))
                .isFalseFor(null)
                .toStream();
    }
}