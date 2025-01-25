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

import java.util.List;
import java.util.stream.Stream;

import static io.github.avegera.predicate4j.Where.where;
import static io.github.avegera.predicate4j.test.model.User.userWithRoles;
import static io.github.avegera.predicate4j.test.util.ListWithNullableIterator.listWithNullableIterator;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@Where("where")
@Type("string")
@Quantifier("each")
@Description("Test predicates under where().each().string(...)")
public class WhereEachStringTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().each().string(User::roles).isEmpty()")
    public void eachStringIsEmpty(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().each().string(context.mapper()).isEmpty());
    }

    static Stream<Arguments> eachStringIsEmpty() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().each().string(User::roles).isEmpty()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(asList("", "", "")))
                    .isTrueFor(userWithRoles(asList(null, null, "")))
                    .isTrueFor(userWithRoles(singletonList("")))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isTrueFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(asList("Admin", "User", "")))
                    .isFalseFor(userWithRoles(asList("Admin", "User")))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withMapper(null)
                    .isFalseFor(userWithRoles(asList("", "Admin", "")))
                    .isFalseFor(userWithRoles(asList("Admin", "User", "")))
                    .isFalseFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().each().string(User::roles).notEmpty()")
    public void eachStringNotEmpty(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().each().string(context.mapper()).notEmpty());
    }

    static Stream<Arguments> eachStringNotEmpty() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().each().string(User::roles).notEmpty()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isTrueFor(userWithRoles(singletonList("Admin")))
                    .isTrueFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(asList("Admin", "User", "")))
                    .isFalseFor(userWithRoles(asList("Admin", "User", null)))
                    .isFalseFor(userWithRoles(asList("", "", "")))
                    .isFalseFor(userWithRoles(asList(null, null, null)))
                    .isFalseFor(userWithRoles(singletonList("")))
                    .isFalseFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withMapper(null)
                    .isFalseFor(userWithRoles(asList("", "Admin", "")))
                    .isFalseFor(userWithRoles(asList("Admin", "User")))
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
    @As("where().each().string(User::roles).contains($argument1)")
    public void eachStringContains(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().each().string(context.mapper()).contains(value));
    }

    static Stream<Arguments> eachStringContains() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().each().string(User::roles).contains($argument1)")
                .withMapper(User::roles)
                    .withArgument("Admin")
                        .isTrueFor(userWithRoles(asList("Admin", "Administrator", "AdminUser")))
                        .isTrueFor(userWithRoles(singletonList("Admin")))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
                        .isFalseFor(userWithRoles(asList("Admin", "Administrator", "Moderator")))
                        .isFalseFor(userWithRoles(asList("", "Admin", null)))
                        .isFalseFor(userWithRoles(singletonList("")))
                        .isFalseFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isFalseFor(userWithRoles(asList("Admin", "Administrator", "AdminUser")))
                        .isFalseFor(userWithRoles(asList("", null, "")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(singletonList(null)))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                .toStream();
    }


    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().each().string(User::roles).notContains($argument1)")
    public void eachStringNotContains(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().each().string(context.mapper()).notContains(value));
    }

    static Stream<Arguments> eachStringNotContains() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().each().string(User::roles).notContains($argument1)")
                .withMapper(User::roles)
                .withArgument("Admin")
                    .isTrueFor(userWithRoles(asList("User", "Guest", "Moderator")))
                    .isTrueFor(userWithRoles(asList("", null, "")))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isTrueFor(userWithRoles(singletonList("")))
                    .isTrueFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(asList("Admin", "Administrator", "AdminUser")))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isTrueFor(userWithRoles(asList("", "", "")))
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
    @As("where().each().string(User::roles).startsWith($argument1)")
    public void eachStringStartsWith(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().each().string(context.mapper()).startsWith(value));
    }

    static Stream<Arguments> eachStringStartsWith() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().each().string(User::roles).startsWith($argument1)")
                .withMapper(User::roles)
                .withArgument("Admin")
                    .isTrueFor(userWithRoles(asList("Admin", "Administrator", "AdminUser")))
                    .isTrueFor(userWithRoles(singletonList("Admin")))
                    .isTrueFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
                    .isFalseFor(userWithRoles(asList("", null, "Admin")))
                    .isFalseFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(singletonList("")))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isFalseFor(userWithRoles(asList("", null, "")))
                    .isFalseFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().each().string(User::roles).notStartsWith($argument1)")
    public void eachStringNotStartsWith(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().each().string(context.mapper()).notStartsWith(value));
    }

    static Stream<Arguments> eachStringNotStartsWith() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().each().string(User::roles).notStartsWith($argument1)")
                .withMapper(User::roles)
                .withArgument("Admin")
                    .isTrueFor(userWithRoles(asList("User", "Guest", "Moderator")))
                    .isTrueFor(userWithRoles(asList("", null, "User")))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isTrueFor(userWithRoles(singletonList("")))
                    .isTrueFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(asList("Admin", "Administrator", "AdminUser")))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithRoles(singletonList("Admin")))
                    .isTrueFor(userWithRoles(asList("User", "Guest", "Moderator")))
                    .isTrueFor(userWithRoles(singletonList("")))
                    .isTrueFor(userWithRoles(emptyList()))
                    .isTrueFor(userWithRoles(asList("", null, "")))
                    .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().each().string(User::roles).endsWith($argument1)")
    public void eachStringEndsWith(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().each().string(context.mapper()).endsWith(value));
    }

    static Stream<Arguments> eachStringEndsWith() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().each().string(User::roles).endsWith($argument1)")
                .withMapper(User::roles)
                .withArgument("User")
                    .isTrueFor(userWithRoles(asList("AdminUser", "GuestUser", "User")))
                    .isTrueFor(userWithRoles(singletonList("User")))
                    .isTrueFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                    .isFalseFor(userWithRoles(asList("AdminUser", "Guest", null)))
                    .isFalseFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(asList("", null, "")))
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
    @As("where().each().string(User::roles).notEndsWith($argument1)")
    public void eachStringNotEndsWith(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().each().string(context.mapper()).notEndsWith(value));
    }

    static Stream<Arguments> eachStringNotEndsWith() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().each().string(User::roles).notEndsWith($argument1)")
                .withMapper(User::roles)
                .withArgument("User")
                    .isTrueFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isTrueFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(asList("AdminUser", "Guest", null)))
                    .isFalseFor(userWithRoles(asList("AdminUser", "GuestUser", "User")))
                    .isFalseFor(userWithRoles(singletonList("User")))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isTrueFor(userWithRoles(singletonList("Admin")))
                    .isTrueFor(userWithRoles(asList("", null, "")))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isTrueFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .toStream();
    }


    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().each().string(User::roles).matches($argument1)")
    public void eachStringMatches(PredicateContext<User, Iterable<String>> context, String regex) {
        scenario(context, where().each().string(context.mapper()).matches(regex));
    }

    static Stream<Arguments> eachStringMatches() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().each().string(User::roles).matches($argument1)")
                .withMapper(User::roles)
                .withArgument(".*User.*") // Matches strings containing "User"
                    .isTrueFor(userWithRoles(asList("AdminUser", "GuestUser", "User")))
                    .isTrueFor(userWithRoles(singletonList("User")))
                    .isTrueFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                    .isFalseFor(userWithRoles(asList("AdminUser", "Guest", null)))
                    .isFalseFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument("^Admin.*") // Matches strings starting with "Admin"
                    .isTrueFor(userWithRoles(asList("Admin", "AdminUser")))
                    .isTrueFor(userWithRoles(singletonList("AdminUser")))
                    .isTrueFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
                    .isFalseFor(userWithRoles(asList("AdminUser", "Guest", null)))
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
    @As("where().each().string(User::roles).notMatches($argument1)")
    public void eachStringNotMatches(PredicateContext<User, Iterable<String>> context, String regex) {
        scenario(context, where().each().string(context.mapper()).notMatches(regex));
    }

    static Stream<Arguments> eachStringNotMatches() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().each().string(User::roles).notMatches($argument1)")
                .withMapper(User::roles)
                .withArgument(".*User.*") // Does not match strings containing "User"
                    .isTrueFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                    .isTrueFor(userWithRoles(singletonList("Admin")))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isTrueFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(asList("AdminUser", "GuestUser", "User")))
                    .isFalseFor(userWithRoles(asList("Admin", "User", null)))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument("^Admin.*") // Does not match strings starting with "Admin"
                    .isTrueFor(userWithRoles(asList("User", "Guest", "Moderator")))
                    .isTrueFor(userWithRoles(singletonList("User")))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isTrueFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(asList("Admin", "AdminUser")))
                    .isFalseFor(userWithRoles(asList("AdminUser", "Admin", null)))
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
    @As("where().each().string(User::roles).length().isEqualTo($argument1)")
    public void eachStringLengthIsEqualTo(PredicateContext<User, Iterable<String>> context, Integer length) {
        scenario(context, where().each().string(context.mapper()).length().isEqualTo(length));
    }

    static Stream<Arguments> eachStringLengthIsEqualTo() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().each().string(User::roles).length().isEqualTo($argument1)")
                .withMapper(User::roles)
                    .withArgument(5)
                        .isTrueFor(userWithRoles(asList("Admin", "Guest", "Moder")))
                        .isTrueFor(userWithRoles(singletonList("Admin")))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("Admin", "Admin", "User")))
                        .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isFalseFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                .withMapper(null)
                    .withArgument(5)
                        .isFalseFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                        .isFalseFor(userWithRoles(asList("Admin", "Admin", "User")))
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
    @Logical("conjunction")
    @CaseAs(provider = FirstArgument.class)
    @As("Logical conjunction of 3 predicates with each().string(User::roles)")
    public void whereEachStringConjunction(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().each().string(User::roles).startsWith("Admin")
                .and().each().string(User::roles).endsWith("User")
                .and().each().string(User::roles).length().isGreaterThan(5));
    }

    static Stream<Arguments> whereEachStringConjunction() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().each().string(User::roles).startsWith(\"Admin\")\n" +
                        "       .and().each().string(User::roles).endsWith(\"User\")\n" +
                        "       .and().each().string(User::roles).length().greaterThan(5)")
                .isTrueFor(userWithRoles(asList("AdminUser", "AdminSuperUser", "AdminManagerUser"))) // All roles satisfy the predicates
                .isTrueFor(userWithRoles(emptyList())) // Empty list satisfies all predicates
                .isFalseFor(userWithRoles(asList("AdminUser", "User", "AdminManagerUser"))) // One role does not end with "User"
                .isFalseFor(userWithRoles(asList("AdminUser", "Admin", "AdminManagerUser"))) // One role length <= 5
                .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator"))) // No role starts with "Admin"
                .isFalseFor(userWithRoles(null)) // Roles are null
                .isFalseFor(null)
                .toStream();
    }
}