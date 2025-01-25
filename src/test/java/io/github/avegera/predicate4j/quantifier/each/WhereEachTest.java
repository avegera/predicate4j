package io.github.avegera.predicate4j.quantifier.each;

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
@Quantifier("each")
@Description("Test predicates under where().each(...)")
public class WhereEachTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().each(User::roles).isEqualTo($argument1)")
    public void eachIsEqualTo(PredicateContext<User, String> context, String value) {
        scenario(context, where().each(User::roles).isEqualTo(value));
    }

    static Stream<Arguments> eachIsEqualTo() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().each(User::roles).isEqualTo($argument1)")
                .withMapper(User::roles)
                    .withArgument("Admin")
                        .isTrueFor(userWithRoles(asList("Admin", "Admin", "Admin")))
                        .isTrueFor(userWithRoles(singletonList("Admin")))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("Admin", "User", "Admin")))
                        .isFalseFor(userWithRoles(asList("Admin", "Guest")))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isTrueFor(userWithRoles(asList(null, null, null)))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("Admin", null, "User")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().each(User::roles).notEqualTo($argument1)")
    public void eachNotEqualTo(PredicateContext<User, String> context, String value) {
        scenario(context, where().each(User::roles).notEqualTo(value));
    }

    static Stream<Arguments> eachNotEqualTo() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().each(User::roles).notEqualTo($argument1)")
                .withMapper(User::roles)
                    .withArgument("Admin")
                        .isTrueFor(userWithRoles(asList("User", "Guest")))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("Admin", "Admin", "Admin")))
                        .isFalseFor(userWithRoles(asList("Admin", "User")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isTrueFor(userWithRoles(asList("Admin", "Admin")))
                        .isTrueFor(userWithRoles(asList("Admin", "Guest")))
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
    @As("where().each(User::balances).isInstanceOf($argument1)")
    public void eachIsInstanceOf(PredicateContext<User, Number> context, Class<?> clazz) {
        scenario(context, where().each(User::balances).isInstanceOf(clazz));
    }

    static Stream<Arguments> eachIsInstanceOf() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().each(User::balances).isInstanceOf($argument1)")
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
                    .isFalseFor(null) // user is null
                .withArgument(Double.class)
                    .isTrueFor(userWithBalances(asList(1.1, 2.2, 3.3)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(100, 200, 300)))
                    .isFalseFor(userWithBalances(asList(1.1, null, 2.2, 3)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isFalseFor(userWithBalances(asList(1.1, 2.2, 3.3)))
                    .isFalseFor(userWithBalances(asList(100, 200, 300)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().each(User::balances).notInstanceOf($argument1)")
    public void eachNotInstanceOf(PredicateContext<User, Number> context, Class<?> clazz) {
        scenario(context, where().each(User::balances).notInstanceOf(clazz));
    }

    static Stream<Arguments> eachNotInstanceOf() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().each(User::balances).notInstanceOf($argument1)")
                .withMapper(User::balances)
                .withArgument(Integer.class)
                    .isTrueFor(userWithBalances(asList(1.1, 2.2, 3.3)))
                    .isTrueFor(userWithBalances(asList(1.0, null, 2.0)))
                    .isTrueFor(userWithBalances(singletonList(1L)))
                    .isTrueFor(userWithBalances(singletonList(null)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(100, 200, 300)))
                    .isFalseFor(userWithBalances(singletonList(1)))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArgument(Double.class)
                    .isTrueFor(userWithBalances(asList(100, 200, 300)))
                    .isTrueFor(userWithBalances(asList(1, 1L, 2)))
                    .isTrueFor(userWithBalances(singletonList(null)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(1.1, 2.2, 3.3)))
                    .isFalseFor(userWithBalances(singletonList(1.0)))
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
    @As("where().each(User::balances).isNull()")
    public void eachIsNull(PredicateContext<User, Number> context) {
        scenario(context, where().each(User::balances).isNull());
    }

    static Stream<Arguments> eachIsNull() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().each(User::balances).isNull()")
                .withMapper(User::balances)
                    .isTrueFor(userWithBalances(asList(null, null, null)))
                    .isTrueFor(userWithBalances(singletonList(null)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(null, 100, null)))
                    .isFalseFor(userWithBalances(asList(100, null, 200)))
                    .isFalseFor(userWithBalances(singletonList(100)))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().each(User::balances).notNull()")
    public void eachNotNull(PredicateContext<User, Number> context) {
        scenario(context, where().each(User::balances).notNull());
    }

    static Stream<Arguments> eachNotNull() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().each(User::balances).notNull()")
                .withMapper(User::balances)
                    .isTrueFor(userWithBalances(asList(100, 200, 300)))
                    .isTrueFor(userWithBalances(singletonList(1)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(null, 100, null)))
                    .isFalseFor(userWithBalances(asList(100, null, 200)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().each(User::roles).in($argument1)")
    public void eachIn(PredicateContext<User, String> context, Collection<String> validRoles) {
        scenario(context, where().each(User::roles).in(validRoles));
    }

    static Stream<Arguments> eachIn() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().each(User::roles).in($argument1)")
                .withMapper(User::roles)
                    .withArgument(asList("Admin", "User", "Guest"))
                        .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isTrueFor(userWithRoles(singletonList("Admin")))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("Admin", "Unknown")))
                        .isFalseFor(userWithRoles(singletonList("Unknown")))
                        .isFalseFor(userWithRoles(asList(null, "Admin")))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(singletonList("Admin"))
                        .isTrueFor(userWithRoles(singletonList("Admin")))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("Admin", "User")))
                        .isFalseFor(userWithRoles(singletonList("User")))
                        .isFalseFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(emptyList())
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
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
    @CaseAs(provider = FirstArgument.class)
    @As("where().each(User::roles).notIn($argument1)")
    public void eachNotIn(PredicateContext<User, String> context, Collection<String> invalidRoles) {
        scenario(context, where().each(User::roles).notIn(invalidRoles));
    }

    static Stream<Arguments> eachNotIn() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().each(User::roles).notIn($argument1)")
                .withMapper(User::roles)
                .withArgument(asList("Admin", "User", "Guest"))
                    .isTrueFor(userWithRoles(asList("Unknown", "Other")))
                    .isTrueFor(userWithRoles(singletonList("Unknown")))
                    .isTrueFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(asList(null, "Admin")))
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
    @As("where().each(User::roles).accepts($argument1)")
    public void eachAccepts(PredicateContext<User, String> context, Predicate<String> predicate) {
        scenario(context, where().each(User::roles).accepts(predicate));
    }

    static Stream<Arguments> eachAccepts() {
        Predicate<String> predicate = role -> role != null && role.trim().equalsIgnoreCase("admin");
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().each(User::roles).accepts($argument1)")
                .withMapper(User::roles)
                .withArgument(predicate, "role -> role != null && role.trim().equalsIgnoreCase(\"admin\")")
                    .isTrueFor(userWithRoles(asList("Admin", "ADMIN", "   admin   ")))
                    .isTrueFor(userWithRoles(singletonList("Admin")))
                    .isTrueFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(asList("Admin", "User")))
                    .isFalseFor(userWithRoles(asList("Admin", null, "User")))
                    .isFalseFor(userWithRoles(singletonList(null)))
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
    @As("where().each(User::roles).rejects($argument1)")
    public void eachRejects(PredicateContext<User, String> context, Predicate<String> predicate) {
        scenario(context, where().each(User::roles).rejects(predicate));
    }

    static Stream<Arguments> eachRejects() {
        Predicate<String> predicate = role -> role != null && role.trim().equalsIgnoreCase("admin");
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().each(User::roles).rejects($argument1)")
                .withMapper(User::roles)
                .withArgument(predicate, "role -> role != null && role.trim().equalsIgnoreCase(\"admin\")")
                    .isTrueFor(userWithRoles(asList("User", "Guest")))
                    .isTrueFor(userWithRoles(asList(null, "Guest", "User")))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isTrueFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(asList("Admin", "ADMIN", "   admin   ")))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(asList("Admin", "User")))
                    .isFalseFor(userWithRoles(asList("Admin", null, "User")))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithRoles(emptyList()))
                    .isTrueFor(userWithRoles(asList("User", "Guest")))
                    .isTrueFor(userWithRoles(singletonList("User")))
                    .isTrueFor(userWithRoles(asList(null, "Admin", null)))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @Logical("conjunction")
    @CaseAs(provider = FirstArgument.class)
    @As("Logical conjunction of 3 predicates with rejects")
    public void whereEachConjunctionRejects(PredicateContext<User, String> context) {
        scenario(context, where().each(User::roles).notNull()
                .and().each(User::roles).rejects(role -> role.equalsIgnoreCase("guest"))
                .and().each(User::roles).rejects(role -> role.startsWith("mod")));
    }

    static Stream<Arguments> whereEachConjunctionRejects() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().each(User::roles).notNull()\n" +
                        "       .and().each(User::roles).rejects(role -> role.equalsIgnoreCase(\"guest\"))\n" +
                        "       .and().each(User::roles).rejects(role -> role.startsWith(\"mod\"))")
                .isTrueFor(userWithRoles(asList("Admin", "User", "Editor"))) // None of the roles are null, "guest", or start with "mod"
                .isTrueFor(userWithRoles(emptyList())) // Empty list satisfies all predicates
                .isFalseFor(userWithRoles(asList("Admin", "Guest", "Editor"))) // Contains "guest"
                .isFalseFor(userWithRoles(asList("moderator", "Admin", "User"))) // Contains role starting with "mod"
                .isFalseFor(userWithRoles(asList("Admin", null, "User"))) // Contains null
                .isFalseFor(userWithRoles(null)) // Roles are null
                .isFalseFor(null)
                .toStream();
    }
}