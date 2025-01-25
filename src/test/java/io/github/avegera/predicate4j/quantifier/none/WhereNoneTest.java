package io.github.avegera.predicate4j.quantifier.none;

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
@Quantifier("none")
@Description("Test predicates under where().none(...)")
public class WhereNoneTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().none(User::roles).isEqualTo($argument1)")
    public void noneIsEqualTo(PredicateContext<User, String> context, String value) {
        scenario(context, where().none(User::roles).isEqualTo(value));
    }

    static Stream<Arguments> noneIsEqualTo() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().none(User::roles).isEqualTo($argument1)")
                .withMapper(User::roles)
                .withArgument("Admin")
                    .isTrueFor(userWithRoles(asList("User", "Guest", "Moderator")))
                    .isTrueFor(userWithRoles(singletonList("User")))
                    .isTrueFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(asList("Admin", "User", "Admin")))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(asList(null, "Admin", null)))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isTrueFor(userWithRoles(singletonList("Admin")))
                    .isTrueFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(asList(null, null, null)))
                    .isFalseFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().none(User::roles).notEqualTo($argument1)")
    public void noneNotEqualTo(PredicateContext<User, String> context, String value) {
        scenario(context, where().none(User::roles).notEqualTo(value));
    }

    static Stream<Arguments> noneNotEqualTo() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().none(User::roles).notEqualTo($argument1)")
                .withMapper(User::roles)
                .withArgument("Admin")
                    .isTrueFor(userWithRoles(asList("Admin", "Admin", "Admin")))
                    .isTrueFor(userWithRoles(singletonList("Admin")))
                    .isTrueFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(asList("User", "Guest", "Admin")))
                    .isFalseFor(userWithRoles(asList("Admin", "Guest", "User")))
                    .isFalseFor(userWithRoles(asList(null, "Admin", null)))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithRoles(asList(null, null, null)))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isTrueFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isFalseFor(userWithRoles(asList(null, "", "User")))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().none(User::balances).isInstanceOf($argument1)")
    public void noneIsInstanceOf(PredicateContext<User, Number> context, Class<?> clazz) {
        scenario(context, where().none(User::balances).isInstanceOf(clazz));
    }

    static Stream<Arguments> noneIsInstanceOf() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().none(User::balances).isInstanceOf($argument1)")
                .withMapper(User::balances)
                .withArgument(Integer.class)
                    .isTrueFor(userWithBalances(asList(1.1, 2.2, 3.3)))
                    .isTrueFor(userWithBalances(singletonList(1L)))
                    .isTrueFor(userWithBalances(singletonList(null)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(100, 200, 300)))
                    .isFalseFor(userWithBalances(singletonList(1)))
                    .isFalseFor(userWithBalances(asList(100, null, 200)))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArgument(Double.class)
                    .isTrueFor(userWithBalances(asList(100, 200, 300)))
                    .isTrueFor(userWithBalances(asList(1, 1L, 2)))
                    .isTrueFor(userWithBalances(singletonList(null)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(1.1, 2.2, 3.3)))
                    .isFalseFor(userWithBalances(asList(1.1, null, 2.2)))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithBalances(asList(1.1, 2.2, 3.3)))
                    .isTrueFor(userWithBalances(asList(100, 200, 300)))
                    .isTrueFor(userWithBalances(singletonList(null)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().none(User::balances).notInstanceOf($argument1)")
    public void noneNotInstanceOf(PredicateContext<User, Number> context, Class<?> clazz) {
        scenario(context, where().none(User::balances).notInstanceOf(clazz));
    }

    static Stream<Arguments> noneNotInstanceOf() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().none(User::balances).notInstanceOf($argument1)")
                .withMapper(User::balances)
                .withArgument(Integer.class)
                    .isTrueFor(userWithBalances(asList(100, 200, 300)))
                    .isTrueFor(userWithBalances(singletonList(1)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(1.1, 2.2, 3.3)))
                    .isFalseFor(userWithBalances(asList(100, null, 200)))
                    .isFalseFor(userWithBalances(singletonList(1L)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArgument(Double.class)
                    .isTrueFor(userWithBalances(asList(1.1, 2.2, 3.3)))
                    .isTrueFor(userWithBalances(singletonList(1.0)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(100, 200, 300)))
                    .isFalseFor(userWithBalances(asList(1.1, null, 2.2)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(100, 200, 300)))
                    .isFalseFor(userWithBalances(asList(1.1, 2.2, 3.3)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().none(User::balances).isNull()")
    public void noneIsNull(PredicateContext<User, Number> context) {
        scenario(context, where().none(User::balances).isNull());
    }

    static Stream<Arguments> noneIsNull() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().none(User::balances).isNull()")
                .withMapper(User::balances)
                    .isTrueFor(userWithBalances(asList(100, 200, 300)))
                    .isTrueFor(userWithBalances(singletonList(1)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(100, null, 200)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().none(User::balances).notNull()")
    public void noneNotNull(PredicateContext<User, Number> context) {
        scenario(context, where().none(User::balances).notNull());
    }

    static Stream<Arguments> noneNotNull() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().none(User::balances).notNull()")
                .withMapper(User::balances)
                .isTrueFor(userWithBalances(asList(null, null, null)))
                .isTrueFor(userWithBalances(singletonList(null)))
                .isTrueFor(userWithBalances(emptyList()))
                .isFalseFor(userWithBalances(asList(100, null, 200)))
                .isFalseFor(userWithBalances(singletonList(100)))
                .isFalseFor(userWithBalances(null))
                .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().none(User::roles).in($argument1)")
    public void noneIn(PredicateContext<User, String> context, Collection<String> validRoles) {
        scenario(context, where().none(User::roles).in(validRoles));
    }

    static Stream<Arguments> noneIn() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().none(User::roles).in($argument1)")
                .withMapper(User::roles)
                    .withArgument(asList("Admin", "User", "Guest"))
                        .isTrueFor(userWithRoles(asList("Unknown", "Other")))
                        .isTrueFor(userWithRoles(singletonList("Unknown")))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(asList("Admin", null)))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(singletonList("Admin"))
                        .isTrueFor(userWithRoles(asList("User", "Guest")))
                        .isTrueFor(userWithRoles(singletonList("Unknown")))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(asList("Admin", "User")))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(emptyList())
                        .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isTrueFor(userWithRoles(singletonList("Admin")))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isTrueFor(userWithRoles(singletonList("Admin")))
                        .isTrueFor(userWithRoles(singletonList(null)))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().none(User::roles).notIn($argument1)")
    public void noneNotIn(PredicateContext<User, String> context, Collection<String> invalidRoles) {
        scenario(context, where().none(User::roles).notIn(invalidRoles));
    }

    static Stream<Arguments> noneNotIn() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().none(User::roles).notIn($argument1)")
                .withMapper(User::roles)
                    .withArgument(asList("Admin", "User", "Guest"))
                        .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isTrueFor(userWithRoles(singletonList("Admin")))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("Unknown", "Other")))
                        .isFalseFor(userWithRoles(singletonList("Unknown")))
                        .isFalseFor(userWithRoles(asList(null, "Unknown")))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(singletonList("Admin"))
                        .isTrueFor(userWithRoles(singletonList("Admin")))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("User", "Guest")))
                        .isFalseFor(userWithRoles(singletonList("Unknown")))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(emptyList())
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
                        .isFalseFor(userWithRoles(asList(null, "Unknown")))
                        .isFalseFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().none(User::roles).accepts($argument1)")
    public void noneAccepts(PredicateContext<User, String> context, Predicate<String> predicate) {
        scenario(context, where().none(User::roles).accepts(predicate));
    }

    static Stream<Arguments> noneAccepts() {
        Predicate<String> predicate = role -> role != null && role.trim().equalsIgnoreCase("admin");
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().none(User::roles).accepts($argument1)")
                .withMapper(User::roles)
                .withArgument(predicate, "role -> role != null && role.trim().equalsIgnoreCase(\"admin\")")
                    .isTrueFor(userWithRoles(asList("User", "Guest", "Moderator")))
                    .isTrueFor(userWithRoles(singletonList("User")))
                    .isTrueFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(asList("Admin", "ADMIN", "   admin   ")))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(asList("Admin", "User")))
                    .isFalseFor(userWithRoles(asList("Admin", null, "User")))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
                    .isFalseFor(userWithRoles(singletonList("User")))
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
    @As("where().none(User::roles).rejects($argument1)")
    public void noneRejects(PredicateContext<User, String> context, Predicate<String> predicate) {
        scenario(context, where().none(User::roles).rejects(predicate));
    }

    static Stream<Arguments> noneRejects() {
        Predicate<String> predicate = role -> role != null && role.trim().equalsIgnoreCase("admin");
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().none(User::roles).rejects($argument1)")
                .withMapper(User::roles)
                .withArgument(predicate, "role -> role != null && role.trim().equalsIgnoreCase(\"admin\")")
                    .isTrueFor(userWithRoles(asList("Admin", "ADMIN", "   admin   ")))
                    .isTrueFor(userWithRoles(singletonList("Admin")))
                    .isTrueFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(asList("Admin", "User")))
                    .isFalseFor(userWithRoles(asList("Admin", null, "User")))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                    .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
                    .isFalseFor(userWithRoles(singletonList("User")))
                .withArgument(null)
                    .isTrueFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @Logical("conjunction")
    @CaseAs(provider = FirstArgument.class)
    @As("Logical conjunction of 3 predicates with none using only accepts")
    public void whereNoneConjunctionWithAccepts(PredicateContext<User, String> context) {
        scenario(context, where().none(User::roles).accepts(role -> role.equalsIgnoreCase("guest"))
                .and().none(User::roles).accepts(role -> role.startsWith("mod"))
                .and().none(User::roles).accepts(role -> role.length() > 4));
    }

    static Stream<Arguments> whereNoneConjunctionWithAccepts() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().none(User::roles).accepts(role -> role.equalsIgnoreCase(\"guest\"))\n" +
                        "       .and().none(User::roles).accepts(role -> role.startsWith(\"mod\"))\n" +
                        "       .and().none(User::roles).accepts(role -> role.length() > 4)")
                .isTrueFor(userWithRoles(asList("User", "Edit", "Help"))) // None of the roles are "guest", start with "mod", or have length > 4
                .isTrueFor(userWithRoles(emptyList())) // Empty list satisfies all predicates
                .isFalseFor(userWithRoles(asList("Guest", "Moderator", "Editor"))) // Contains "guest"
                .isFalseFor(userWithRoles(asList("moderator", "Admin", "User"))) // Contains role starting with "mod"
                .isFalseFor(userWithRoles(asList("Admin", "Editor", "Helper"))) // Contains role with length > 4
                .isFalseFor(userWithRoles(null)) // Roles are null
                .isFalseFor(null)
                .toStream();
    }
}