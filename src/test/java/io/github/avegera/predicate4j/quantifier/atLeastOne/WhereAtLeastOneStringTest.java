package io.github.avegera.predicate4j.quantifier.atLeastOne;

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
@Quantifier("atLeastOne")
@Description("Test predicates under where().atLeastOne().string(...)")
public class WhereAtLeastOneStringTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeastOne().string(User::roles).isEmpty()")
    public void atLeastOneStringIsEmpty(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().atLeastOne().string(context.mapper()).isEmpty());
    }

    static Stream<Arguments> atLeastOneStringIsEmpty() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeastOne().string(User::roles).isEmpty()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(asList("Admin", "", "User")))
                    .isTrueFor(userWithRoles(singletonList("")))
                    .isTrueFor(userWithRoles(asList(null, "")))
                    .isTrueFor(userWithRoles(asList(null, "Admin")))
                    .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withMapper(null)
                    .isFalseFor(userWithRoles(asList("", "Admin", "")))
                    .isFalseFor(userWithRoles(asList("Admin", "User", "")))
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
    @As("where().atLeastOne().string(User::roles).notEmpty()")
    public void atLeastOneStringNotEmpty(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().atLeastOne().string(context.mapper()).notEmpty());
    }

    static Stream<Arguments> atLeastOneStringNotEmpty() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeastOne().string(User::roles).notEmpty()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(asList("Admin", "", "User")))
                    .isTrueFor(userWithRoles(singletonList("Admin")))
                    .isTrueFor(userWithRoles(asList(null, "User")))
                    .isFalseFor(userWithRoles(asList("", "", "")))
                    .isFalseFor(userWithRoles(singletonList("")))
                    .isFalseFor(userWithRoles(asList(null, null)))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
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
    @As("where().atLeastOne().string(User::roles).contains($argument1)")
    public void atLeastOneStringContains(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().atLeastOne().string(context.mapper()).contains(value));
    }

    static Stream<Arguments> atLeastOneStringContains() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeastOne().string(User::roles).contains($argument1)")
                .withMapper(User::roles)
                .withArgument("Admin")
                    .isTrueFor(userWithRoles(asList("Admin", "User", "Moderator")))
                    .isTrueFor(userWithRoles(singletonList("Admin")))
                    .isTrueFor(userWithRoles(asList("Admin", null)))
                    .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
                    .isFalseFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isFalseFor(userWithRoles(asList("Admin", "User", "Moderator")))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(asList(null, null)))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeastOne().string(User::roles).notContains($argument1)")
    public void atLeastOneStringNotContains(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().atLeastOne().string(context.mapper()).notContains(value));
    }

    static Stream<Arguments> atLeastOneStringNotContains() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeastOne().string(User::roles).notContains($argument1)")
                .withMapper(User::roles)
                .withArgument("Admin")
                    .isTrueFor(userWithRoles(asList("User", "Guest", "Moderator")))
                    .isTrueFor(userWithRoles(asList("Admin", "User", "Moderator")))
                    .isTrueFor(userWithRoles(asList("User", "Guest", null)))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(asList("Admin", "Administrator")))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithRoles(asList("User", "Guest", "Moderator")))
                    .isTrueFor(userWithRoles(singletonList("User")))
                    .isTrueFor(userWithRoles(asList(null, "Admin")))
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
    @As("where().atLeastOne().string(User::roles).startsWith($argument1)")
    public void atLeastOneStringStartsWith(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().atLeastOne().string(context.mapper()).startsWith(value));
    }

    static Stream<Arguments> atLeastOneStringStartsWith() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeastOne().string(User::roles).startsWith($argument1)")
                .withMapper(User::roles)
                .withArgument("Admin")
                    .isTrueFor(userWithRoles(asList("Admin", "Administrator", "User")))
                    .isTrueFor(userWithRoles(asList("", "Admin", null)))
                    .isTrueFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
                    .isFalseFor(userWithRoles(singletonList(null)))
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
    @As("where().atLeastOne().string(User::roles).endsWith($argument1)")
    public void atLeastOneStringEndsWith(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().atLeastOne().string(context.mapper()).endsWith(value));
    }

    static Stream<Arguments> atLeastOneStringEndsWith() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeastOne().string(User::roles).endsWith($argument1)")
                .withMapper(User::roles)
                .withArgument("User")
                    .isTrueFor(userWithRoles(asList("AdminUser", "GuestUser", "User")))
                    .isTrueFor(userWithRoles(asList("AdminUser", "", null)))
                    .isTrueFor(userWithRoles(singletonList("User")))
                    .isFalseFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                    .isFalseFor(userWithRoles(singletonList(null)))
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
    @As("where().atLeastOne().string(User::roles).notStartsWith($argument1)")
    public void atLeastOneStringNotStartsWith(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().atLeastOne().string(context.mapper()).notStartsWith(value));
    }

    static Stream<Arguments> atLeastOneStringNotStartsWith() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeastOne().string(User::roles).notStartsWith($argument1)")
                .withMapper(User::roles)
                .withArgument("Admin")
                    .isTrueFor(userWithRoles(asList("User", "Guest", "Moderator")))
                    .isTrueFor(userWithRoles(asList("", null, "User")))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isTrueFor(userWithRoles(singletonList("")))
                    .isFalseFor(userWithRoles(asList("Admin", "Administrator", "AdminUser")))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithRoles(asList("User", "Guest", "Moderator")))
                    .isTrueFor(userWithRoles(asList("Admin", "Administrator", "AdminUser")))
                    .isTrueFor(userWithRoles(singletonList("")))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeastOne().string(User::roles).notEndsWith($argument1)")
    public void atLeastOneStringNotEndsWith(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().atLeastOne().string(context.mapper()).notEndsWith(value));
    }

    static Stream<Arguments> atLeastOneStringNotEndsWith() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeastOne().string(User::roles).notEndsWith($argument1)")
                .withMapper(User::roles)
                .withArgument("User")
                    .isTrueFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                    .isTrueFor(userWithRoles(asList("AdminUser", "", null)))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isTrueFor(userWithRoles(singletonList("")))
                    .isFalseFor(userWithRoles(asList("AdminUser", "GuestUser", "User")))
                    .isFalseFor(userWithRoles(singletonList("User")))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isTrueFor(userWithRoles(singletonList("Admin")))
                    .isTrueFor(userWithRoles(singletonList("")))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeastOne().string(User::roles).matches($argument1)")
    public void atLeastOneStringMatches(PredicateContext<User, Iterable<String>> context, String regex) {
        scenario(context, where().atLeastOne().string(context.mapper()).matches(regex));
    }

    static Stream<Arguments> atLeastOneStringMatches() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeastOne().string(User::roles).matches($argument1)")
                .withMapper(User::roles)
                .withArgument(".*User.*") // Matches strings containing "User"
                    .isTrueFor(userWithRoles(asList("AdminUser", "GuestUser", "User")))
                    .isTrueFor(userWithRoles(singletonList("User")))
                    .isTrueFor(userWithRoles(asList("Admin", "User", null)))
                    .isFalseFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                    .isFalseFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(singletonList("")))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument("^Admin.*") // Matches strings starting with "Admin"
                    .isTrueFor(userWithRoles(asList("Admin", "AdminUser", "User")))
                    .isTrueFor(userWithRoles(singletonList("Admin")))
                    .isTrueFor(userWithRoles(asList("AdminUser", "Guest", null)))
                    .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
                    .isFalseFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(singletonList("")))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(singletonList("")))
                    .isFalseFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeastOne().string(User::roles).notMatches($argument1)")
    public void atLeastOneStringNotMatches(PredicateContext<User, Iterable<String>> context, String regex) {
        scenario(context, where().atLeastOne().string(context.mapper()).notMatches(regex));
    }

    static Stream<Arguments> atLeastOneStringNotMatches() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeastOne().string(User::roles).notMatches($argument1)")
                .withMapper(User::roles)
                .withArgument(".*User.*") // Does not match strings containing "User"
                    .isTrueFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                    .isTrueFor(userWithRoles(asList("Admin", "User", null)))
                    .isTrueFor(userWithRoles(singletonList("Admin")))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(asList("AdminUser", "GuestUser", "User")))
                    .isFalseFor(userWithRoles(singletonList("User")))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument("^Admin.*") // Does not match strings starting with "Admin"
                    .isTrueFor(userWithRoles(asList("User", "Guest", "Moderator")))
                    .isTrueFor(userWithRoles(asList("Admin", "AdminUser", "User")))
                    .isTrueFor(userWithRoles(asList("", null, "User")))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isTrueFor(userWithRoles(singletonList("")))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
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
    @As("where().atLeastOne().string(User::roles).length().isEqualTo($argument1)")
    public void atLeastOneStringLengthIsEqualTo(PredicateContext<User, Iterable<String>> context, Integer length) {
        scenario(context, where().atLeastOne().string(context.mapper()).length().isEqualTo(length));
    }

    static Stream<Arguments> atLeastOneStringLengthIsEqualTo() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeastOne().string(User::roles).length().isEqualTo($argument1)")
                .withMapper(User::roles)
                    .withArgument(5)
                        .isTrueFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                        .isTrueFor(userWithRoles(asList("Admin", "Admin", "User")))
                        .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isTrueFor(userWithRoles(singletonList("Admin")))
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
    @As("Logical conjunction of 3 predicates with atLeastOne().string(User::roles)")
    public void whereAtLeastOneStringConjunction(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().atLeastOne().string(User::roles).startsWith("Admin")
                .and().atLeastOne().string(User::roles).endsWith("User")
                .and().atLeastOne().string(User::roles).length().isGreaterThan(5));
    }

    static Stream<Arguments> whereAtLeastOneStringConjunction() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeastOne().string(User::roles).startsWith(\"Admin\")\n" +
                        "       .and().atLeastOne().string(User::roles).endsWith(\"User\")\n" +
                        "       .and().atLeastOne().string(User::roles).length().greaterThan(5)")
                .isTrueFor(userWithRoles(asList("AdminUser", "GuestUser", "AdminManagerUser")))
                .isTrueFor(userWithRoles(asList("Admin", "User", "AdminManager")))
                .isTrueFor(userWithRoles(singletonList("AdminUser")))
                .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator"))) // No role starts with "Admin"
                .isFalseFor(userWithRoles(asList("Admin", "AdminUserRole", "Administrator"))) // No role ends with "User"
                .isFalseFor(userWithRoles(asList("Admin", "User", "Guest"))) // No role with length >5
                .isFalseFor(userWithRoles(emptyList())) // Empty list does not satisfy at least one condition
                .isFalseFor(userWithRoles(null)) // Roles are null
                .isFalseFor(null)
                .toStream();
    }
}