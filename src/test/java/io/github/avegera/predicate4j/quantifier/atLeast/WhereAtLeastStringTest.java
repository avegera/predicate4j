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

import java.util.List;
import java.util.stream.Stream;

import static io.github.avegera.predicate4j.Where.where;
import static io.github.avegera.predicate4j.test.model.User.userWithFlags;
import static io.github.avegera.predicate4j.test.model.User.userWithRoles;
import static io.github.avegera.predicate4j.test.util.ListWithNullableIterator.listWithNullableIterator;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@Where("where")
@Type("string")
@Quantifier("atLeast")
@Description("Test predicates under where().atLeast().string(...)")
public class WhereAtLeastStringTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeast(2).string(User::roles).isEmpty()")
    public void atLeastTwoStringIsEmpty(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().atLeast(2).string(context.mapper()).isEmpty());
    }

    static Stream<Arguments> atLeastTwoStringIsEmpty() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeast(2).string(User::roles).isEmpty()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(asList("", "", "User")))
                    .isTrueFor(userWithRoles(asList("", "", null)))
                    .isTrueFor(userWithRoles(asList("", "", "")))
                    .isTrueFor(userWithRoles(asList(null, "Admin", "")))
                    .isFalseFor(userWithRoles(asList("Admin", "", "User")))
                    .isFalseFor(userWithRoles(singletonList("")))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeast(2).string(User::roles).notEmpty()")
    public void atLeastTwoStringNotEmpty(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().atLeast(2).string(context.mapper()).notEmpty());
    }

    static Stream<Arguments> atLeastTwoStringNotEmpty() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeast(2).string(User::roles).notEmpty()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isTrueFor(userWithRoles(asList("Admin", "User", null)))
                    .isTrueFor(userWithRoles(asList("Admin", "User", "Admin")))
                    .isFalseFor(userWithRoles(asList("Admin", "", null)))
                    .isFalseFor(userWithRoles(asList("", "", "")))
                    .isFalseFor(userWithRoles(singletonList("User")))
                    .isFalseFor(userWithRoles(asList("", null)))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeast(2).string(User::roles).contains($argument1)")
    public void atLeastTwoStringContains(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().atLeast(2).string(context.mapper()).contains(value));
    }

    static Stream<Arguments> atLeastTwoStringContains() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeast(2).string(User::roles).contains($argument1)")
                .withMapper(User::roles)
                    .withArgument("Admin")
                        .isTrueFor(userWithRoles(asList("Admin", "Admin", "User")))
                        .isTrueFor(userWithRoles(asList("Admin", "User", "Admin")))
                        .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isFalseFor(userWithRoles(asList("Admin", null, "User")))
                        .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(asList("", null, "Admin")))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isFalseFor(userWithRoles(asList("Admin", null, "Admin")))
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
    @As("where().atLeast(2).string(User::roles).notContains($argument1)")
    public void atLeastTwoStringNotContains(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().atLeast(2).string(context.mapper()).notContains(value));
    }

    static Stream<Arguments> atLeastTwoStringNotContains() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeast(2).string(User::roles).notContains($argument1)")
                .withMapper(User::roles)
                    .withArgument("Admin")
                        .isTrueFor(userWithRoles(asList("User", "Guest", "Moderator")))
                        .isTrueFor(userWithRoles(asList("User", "Moderator", "Guest")))
                        .isTrueFor(userWithRoles(asList("User", null, "Guest")))
                        .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isFalseFor(userWithRoles(asList("Admin", "Admin", "User")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(asList("User", "Admin", "Admin")))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isTrueFor(userWithRoles(asList("User", "Guest", "Moderator")))
                        .isTrueFor(userWithRoles(asList(null, null, "User")))
                        .isTrueFor(userWithRoles(asList(null, "Admin", null)))
                        .isFalseFor(userWithRoles(singletonList("User")))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeast(2).string(User::roles).startsWith($argument1)")
    public void atLeastTwoStringStartsWith(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().atLeast(2).string(context.mapper()).startsWith(value));
    }

    static Stream<Arguments> atLeastTwoStringStartsWith() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeast(2).string(User::roles).startsWith($argument1)")
                .withMapper(User::roles)
                    .withArgument("Admin")
                        .isTrueFor(userWithRoles(asList("Administrator", "Admin", "AdminUser")))
                        .isTrueFor(userWithRoles(asList("Admin", "AdminUser", "Guest")))
                        .isTrueFor(userWithRoles(asList("Admin", "AdminManager", "User")))
                        .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
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
    @As("where().atLeast(2).string(User::roles).endsWith($argument1)")
    public void atLeastTwoStringEndsWith(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().atLeast(2).string(context.mapper()).endsWith(value));
    }

    static Stream<Arguments> atLeastTwoStringEndsWith() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeast(2).string(User::roles).endsWith($argument1)")
                .withMapper(User::roles)
                    .withArgument("User")
                        .isTrueFor(userWithRoles(asList("AdminUser", "GuestUser", "Moderator")))
                        .isTrueFor(userWithRoles(asList("AdminUser", "GuestUser", "User")))
                        .isFalseFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                        .isFalseFor(userWithRoles(singletonList("User")))
                        .isFalseFor(userWithRoles(asList("Admin", "Moderator", "User")))
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
    @As("where().atLeast(2).string(User::roles).notStartsWith($argument1)")
    public void atLeastTwoStringNotStartsWith(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().atLeast(2).string(context.mapper()).notStartsWith(value));
    }

    static Stream<Arguments> atLeastTwoStringNotStartsWith() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeast(2).string(User::roles).notStartsWith($argument1)")
                .withMapper(User::roles)
                    .withArgument("Admin")
                        .isTrueFor(userWithRoles(asList("User", "Guest", "Moderator")))
                        .isTrueFor(userWithRoles(asList("User", "AdminUser", "Moderator")))
                        .isTrueFor(userWithRoles(asList("Guest", "User", "Admin")))
                        .isFalseFor(userWithRoles(asList("Admin", "Administrator", "AdminUser")))
                        .isFalseFor(userWithRoles(asList("Admin", "User", "AdminAssistant")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isTrueFor(userWithRoles(asList("User", "Guest", "Moderator")))
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
    @As("where().atLeast(2).string(User::roles).notEndsWith($argument1)")
    public void atLeastTwoStringNotEndsWith(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().atLeast(2).string(context.mapper()).notEndsWith(value));
    }

    static Stream<Arguments> atLeastTwoStringNotEndsWith() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeast(2).string(User::roles).notEndsWith($argument1)")
                .withMapper(User::roles)
                .withArgument("User")
                    .isTrueFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                    .isTrueFor(userWithRoles(asList("AdminUser", "AdminAssistant", "Moderator")))
                    .isTrueFor(userWithRoles(asList("Admin", "GuestUser", "Moderator")))
                    .isFalseFor(userWithRoles(asList("AdminUser", "GuestUser", "User")))
                    .isFalseFor(userWithRoles(asList("User", "GuestUser", "Admin")))
                    .isFalseFor(userWithRoles(singletonList("User")))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
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
    @As("where().atLeast(2).string(User::roles).matches($argument1)")
    public void atLeastTwoStringMatches(PredicateContext<User, Iterable<String>> context, String regex) {
        scenario(context, where().atLeast(2).string(context.mapper()).matches(regex));
    }

    static Stream<Arguments> atLeastTwoStringMatches() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeast(2).string(User::roles).matches($argument1)")
                .withMapper(User::roles)
                .withArgument(".*User.*") // Matches strings containing "User"
                    .isTrueFor(userWithRoles(asList("AdminUser", "GuestUser", "Moderator")))
                    .isTrueFor(userWithRoles(asList("AdminUser", "GuestUser", "User")))
                    .isTrueFor(userWithRoles(asList("Admin", "User", "GuestUser")))
                    .isTrueFor(userWithRoles(asList("User", "Guest", "User")))
                    .isFalseFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                    .isFalseFor(userWithRoles(singletonList("User")))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument("^Admin.*") // Matches strings starting with "Admin"
                    .isTrueFor(userWithRoles(asList("Admin", "AdminUser", "Guest")))
                    .isTrueFor(userWithRoles(asList("AdminUser", "Administrator", "Guest")))
                    .isTrueFor(userWithRoles(asList("AdminAssistant", "Admin", "User")))
                    .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
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
    @As("where().atLeast(2).string(User::roles).notMatches($argument1)")
    public void atLeastTwoStringNotMatches(PredicateContext<User, Iterable<String>> context, String regex) {
        scenario(context, where().atLeast(2).string(context.mapper()).notMatches(regex));
    }

    static Stream<Arguments> atLeastTwoStringNotMatches() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeast(2).string(User::roles).notMatches($argument1)")
                .withMapper(User::roles)
                .withArgument(".*User.*") // Does not match strings containing "User"
                    .isTrueFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                    .isTrueFor(userWithRoles(asList("Admin", "Guest", "GuestUser")))
                    .isTrueFor(userWithRoles(asList("AdminUser", "Moderator", "Guest")))
                    .isFalseFor(userWithRoles(asList("AdminUser", "GuestUser", "User")))
                    .isFalseFor(userWithRoles(asList("User", "GuestUser", "Moderator")))
                    .isFalseFor(userWithRoles(singletonList("User")))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument("^Admin.*") // Does not match strings starting with "Admin"
                    .isTrueFor(userWithRoles(asList("User", "Guest", "Moderator")))
                    .isTrueFor(userWithRoles(asList("User", "Guest", "AdminUser")))
                    .isTrueFor(userWithRoles(asList("Moderator", "Guest", "User")))
                    .isFalseFor(userWithRoles(asList("Admin", "AdminUser", "Guest")))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
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
    @As("where().atLeast(2).string(User::roles).length().isEqualTo($argument1)")
    public void atLeastTwoStringLengthIsEqualTo(PredicateContext<User, Iterable<String>> context, Integer length) {
        scenario(context, where().atLeast(2).string(context.mapper()).length().isEqualTo(length));
    }

    static Stream<Arguments> atLeastTwoStringLengthIsEqualTo() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeast(2).string(User::roles).length().isEqualTo($argument1)")
                .withMapper(User::roles)
                    .withArgument(5)
                        .isTrueFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                        .isTrueFor(userWithRoles(asList("Admin", "Admin", "User")))
                        .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
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
    @As("Logical conjunction of 3 predicates with atLeast(2).string(User::roles)")
    public void atLeastTwoStringConjunction(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().atLeast(2).string(User::roles).startsWith("Admin")
                .and().atLeast(2).string(User::roles).endsWith("User")
                .and().atLeast(2).string(User::roles).notEmpty());
    }

    static Stream<Arguments> atLeastTwoStringConjunction() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().atLeast(2).string(User::roles).startsWith(\"Admin\")\n" +
                        "       .and().atLeast(2).string(User::roles).endsWith(\"User\")\n" +
                        "       .and().atLeast(2).string(User::roles).notEmpty()")
                .isTrueFor(userWithRoles(asList("AdminUser", "AdministratorUser", "GuestUser")))
                .isTrueFor(userWithRoles(asList("AdminUser", "AdminUser", "Guest")))
                .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator"))) // No role starts with "Admin"
                .isFalseFor(userWithRoles(asList("Admin", "Admin", "Admin"))) // Does not meet the "endsWith('User')" condition
                .isFalseFor(userWithRoles(asList("Admin", "User", "Guest"))) // Does not meet the "atLeast(2)" condition
                .isFalseFor(userWithFlags(listWithNullableIterator())) // List with nullable iterator
                .isFalseFor(userWithRoles(emptyList())) // Empty list does not satisfy conditions
                .isFalseFor(userWithRoles(null)) // Roles are null
                .isFalseFor(null) // User is null
                .toStream();
    }
}