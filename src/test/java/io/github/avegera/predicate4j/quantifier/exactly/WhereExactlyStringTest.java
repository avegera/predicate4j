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
@Quantifier("exactly")
@Description("Test predicates under where().exactly().string(...)")
public class WhereExactlyStringTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().exactly(2).string(User::roles).isEmpty()")
    public void exactlyStringIsEmpty(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().exactly(2).string(context.mapper()).isEmpty());
    }

    static Stream<Arguments> exactlyStringIsEmpty() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactly(2).string(User::roles).isEmpty()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(asList("", "", "Admin")))
                    .isFalseFor(userWithRoles(asList("", "Admin", "User")))
                    .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isFalseFor(userWithRoles(asList("", "", null)))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
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
    @As("where().exactly(2).string(User::roles).notEmpty()")
    public void exactlyStringNotEmpty(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().exactly(2).string(context.mapper()).notEmpty());
    }

    static Stream<Arguments> exactlyStringNotEmpty() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactly(2).string(User::roles).notEmpty()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(asList("", "Admin", "User")))
                    .isTrueFor(userWithRoles(asList("Admin", "", "User")))
                    .isTrueFor(userWithRoles(asList("Admin", "User", null)))
                    .isFalseFor(userWithRoles(asList("", "", "User")))
                    .isFalseFor(userWithRoles(asList("", "", null)))
                    .isFalseFor(userWithRoles(singletonList("")))
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
    @As("where().exactly(2).string(User::roles).contains($argument1)")
    public void exactlyStringContains(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().exactly(2).string(context.mapper()).contains(value));
    }

    static Stream<Arguments> exactlyStringContains() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactly(2).string(User::roles).contains($argument1)")
                .withMapper(User::roles)
                .withArgument("Admin")
                    .isTrueFor(userWithRoles(asList("Admin", "Admin", "User")))
                    .isTrueFor(userWithRoles(asList("Admin", "Admin", null)))
                    .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
                    .isFalseFor(userWithRoles(asList(null, null, null)))
                    .isFalseFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isFalseFor(userWithRoles(asList(null, "Admin", null)))
                    .isFalseFor(userWithRoles(asList(null, null, "User")))
                    .isFalseFor(userWithRoles(asList("Admin", "Admin", "User")))
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
    @As("where().exactly(2).string(User::roles).notContains($argument1)")
    public void exactlyStringNotContains(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().exactly(2).string(context.mapper()).notContains(value));
    }

    static Stream<Arguments> exactlyStringNotContains() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactly(2).string(User::roles).notContains($argument1)")
                .withMapper(User::roles)
                .withArgument("Admin")
                    .isTrueFor(userWithRoles(asList("User", "Guest", "Admin")))
                    .isTrueFor(userWithRoles(asList("Moderator", "Admin", "Guest")))
                    .isTrueFor(userWithRoles(asList(null, null, "Admin")))
                    .isFalseFor(userWithRoles(asList("Admin", "Admin", "User")))
                    .isFalseFor(userWithRoles(asList("Admin", "Admin", null)))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isFalseFor(userWithRoles(asList("User", "Guest", "Admin")))
                    .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
                    .isFalseFor(userWithRoles(asList(null, null, "Admin")))
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
    @As("where().exactly(2).string(User::roles).startsWith($argument1)")
    public void exactlyStringStartsWith(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().exactly(2).string(context.mapper()).startsWith(value));
    }

    static Stream<Arguments> exactlyStringStartsWith() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactly(2).string(User::roles).startsWith($argument1)")
                .withMapper(User::roles)
                .withArgument("Admin")
                    .isTrueFor(userWithRoles(asList("AdminUser", "AdminRole", "User")))
                    .isTrueFor(userWithRoles(asList("Admin", "AdminManager", "Guest")))
                    .isFalseFor(userWithRoles(asList("User", "Admin", "Guest")))
                    .isFalseFor(userWithRoles(asList("AdminUser", "AdminUser", "Admin")))
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
    @As("where().exactly(2).string(User::roles).endsWith($argument1)")
    public void exactlyStringEndsWith(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().exactly(2).string(context.mapper()).endsWith(value));
    }

    static Stream<Arguments> exactlyStringEndsWith() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactly(2).string(User::roles).endsWith($argument1)")
                .withMapper(User::roles)
                .withArgument("User")
                    .isTrueFor(userWithRoles(asList("AdminUser", "GuestUser", "Admin")))
                    .isTrueFor(userWithRoles(asList("User", "ModeratorUser", "Guest")))
                    .isTrueFor(userWithRoles(asList("User", "User", "Guest")))
                    .isFalseFor(userWithRoles(asList("AdminUser", "Guest", "Moderator")))
                    .isFalseFor(userWithRoles(singletonList("User")))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
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
    @As("where().exactly(2).string(User::roles).notStartsWith($argument1)")
    public void exactlyStringNotStartsWith(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().exactly(2).string(context.mapper()).notStartsWith(value));
    }

    static Stream<Arguments> exactlyStringNotStartsWith() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactly(2).string(User::roles).notStartsWith($argument1)")
                .withMapper(User::roles)
                .withArgument("Admin")
                    .isTrueFor(userWithRoles(asList("User", "AdminUser", "Guest")))
                    .isTrueFor(userWithRoles(asList("Moderator", "AdminManager", "User")))
                    .isFalseFor(userWithRoles(asList("Admin", "AdminUser", "User")))
                    .isFalseFor(userWithRoles(asList("AdminManager", "Administrator", "Guest")))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
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
    @As("where().exactly(2).string(User::roles).notEndsWith($argument1)")
    public void exactlyStringNotEndsWith(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().exactly(2).string(context.mapper()).notEndsWith(value));
    }

    static Stream<Arguments> exactlyStringNotEndsWith() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactly(2).string(User::roles).notEndsWith($argument1)")
                .withMapper(User::roles)
                .withArgument("User")
                    .isTrueFor(userWithRoles(asList("Admin", "Guest", "ModeratorUser")))
                    .isTrueFor(userWithRoles(asList("Admin", "GuestUser", "Manager")))
                    .isFalseFor(userWithRoles(asList("Admin", "User", "GuestUser")))
                    .isFalseFor(userWithRoles(asList("AdminUser", "Guest", "User")))
                    .isFalseFor(userWithRoles(singletonList("User")))
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
    @As("where().exactly(2).string(User::roles).matches($argument1)")
    public void exactlyStringMatches(PredicateContext<User, Iterable<String>> context, String regex) {
        scenario(context, where().exactly(2).string(context.mapper()).matches(regex));
    }

    static Stream<Arguments> exactlyStringMatches() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactly(2).string(User::roles).matches($argument1)")
                .withMapper(User::roles)
                .withArgument(".*User.*")
                    .isTrueFor(userWithRoles(asList("AdminUser", "GuestUser", "Manager")))
                    .isTrueFor(userWithRoles(asList("Admin", "GuestUser", "User")))
                    .isTrueFor(userWithRoles(asList("AdminUser", "Guest", "User")))
                    .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isFalseFor(userWithRoles(singletonList("User")))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument("^Admin.*")
                    .isTrueFor(userWithRoles(asList("Admin", "AdminUser", "Guest")))
                    .isTrueFor(userWithRoles(asList("AdminUser", "Administrator", "Manager")))
                    .isFalseFor(userWithRoles(asList("Guest", "User", "Admin")))
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
    @As("where().exactly(2).string(User::roles).notMatches($argument1)")
    public void exactlyStringNotMatches(PredicateContext<User, Iterable<String>> context, String regex) {
        scenario(context, where().exactly(2).string(context.mapper()).notMatches(regex));
    }

    static Stream<Arguments> exactlyStringNotMatches() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactly(2).string(User::roles).notMatches($argument1)")
                .withMapper(User::roles)
                .withArgument(".*User.*")
                    .isTrueFor(userWithRoles(asList("Admin", "Guest", "ModeratorUser")))
                    .isTrueFor(userWithRoles(asList("AdminUser", "Guest", "Manager")))
                    .isFalseFor(userWithRoles(asList("AdminUser", "GuestUser", "User")))
                    .isFalseFor(userWithRoles(asList("User", "UserManager", "AdminUser")))
                    .isFalseFor(userWithRoles(singletonList("User")))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument("^Admin.*")
                    .isTrueFor(userWithRoles(asList("User", "Guest", "Admin")))
                    .isTrueFor(userWithRoles(asList("AdminUser", "GuestUser", "Manager")))
                    .isFalseFor(userWithRoles(asList("Admin", "AdminUser", "User")))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isFalseFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().exactly(2).string(User::roles).length().isEqualTo($argument1)")
    public void exactlyTwoStringLengthIsEqualTo(PredicateContext<User, Iterable<String>> context, Integer length) {
        scenario(context, where().exactly(2).string(context.mapper()).length().isEqualTo(length));
    }

    static Stream<Arguments> exactlyTwoStringLengthIsEqualTo() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactly(2).string(User::roles).length().isEqualTo($argument1)")
                .withMapper(User::roles)
                    .withArgument(5)
                        .isTrueFor(userWithRoles(asList("Admin", "Guest", "User")))
                        .isTrueFor(userWithRoles(asList("Admin", "Admin", "Moderator")))
                        .isFalseFor(userWithRoles(asList("Admin", "User", "Moderator")))
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
    @As("Logical conjunction of 3 predicates with exactly(2).string(User::roles)")
    public void exactlyStringConjunction(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().exactly(2).string(User::roles).startsWith("Admin")
                .and().exactly(2).string(User::roles).endsWith("User")
                .and().exactly(2).string(User::roles).contains("Manager"));
    }

    static Stream<Arguments> exactlyStringConjunction() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactly(2).string(User::roles).startsWith(\"Admin\")\n" +
                           "       .and().exactly(2).string(User::roles).endsWith(\"User\")\n" +
                           "       .and().exactly(2).string(User::roles).contains(\"Manager\")")
                .isTrueFor(userWithRoles(asList("AdminUser", "AdminManager", "ManagerUser", "Guest")))
                .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                .isFalseFor(userWithRoles(asList("AdminUser", "AdminUser", "AdminManager")))
                .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
                .isFalseFor(userWithRoles(emptyList()))
                .isFalseFor(userWithRoles(null))
                .isFalseFor(null)
                .toStream();
    }

}