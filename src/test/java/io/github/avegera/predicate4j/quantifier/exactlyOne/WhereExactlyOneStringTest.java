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
@Quantifier("exactlyOne")
@Description("Test predicates under where().exactlyOne().string(...)")
public class WhereExactlyOneStringTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().exactlyOne().string(User::roles).isEmpty()")
    public void exactlyOneStringIsEmpty(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().exactlyOne().string(context.mapper()).isEmpty());
    }

    static Stream<Arguments> exactlyOneStringIsEmpty() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactlyOne().string(User::roles).isEmpty()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(asList("Admin", "", "User")))
                    .isTrueFor(userWithRoles(singletonList("")))
                    .isFalseFor(userWithRoles(asList("", "", "User")))
                    .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
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
    @As("where().exactlyOne().string(User::roles).notEmpty()")
    public void exactlyOneStringNotEmpty(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().exactlyOne().string(context.mapper()).notEmpty());
    }

    static Stream<Arguments> exactlyOneStringNotEmpty() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactlyOne().string(User::roles).notEmpty()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(asList("", "Admin", null)))
                    .isTrueFor(userWithRoles(singletonList("User")))
                    .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
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
    @As("where().exactlyOne().string(User::roles).contains($argument1)")
    public void exactlyOneStringContains(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().exactlyOne().string(context.mapper()).contains(value));
    }

    static Stream<Arguments> exactlyOneStringContains() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactlyOne().string(User::roles).contains($argument1)")
                .withMapper(User::roles)
                .withArgument("Admin")
                    .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isTrueFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(asList("Admin", "Admin", "User")))
                    .isFalseFor(userWithRoles(asList("User", "Guest", null)))
                    .isFalseFor(userWithRoles(asList(null, null)))
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
    @As("where().exactlyOne().string(User::roles).notContains($argument1)")
    public void exactlyOneStringNotContains(PredicateContext<User, Iterable<String>> context, String value) {
         scenario(context, where().exactlyOne().string(context.mapper()).notContains(value));
    }

    static Stream<Arguments> exactlyOneStringNotContains() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactlyOne().string(User::roles).notContains($argument1)")
                .withMapper(User::roles)
                .withArgument("Admin")
                    .isTrueFor(userWithRoles(asList("User", "Admin", "Administrator")))
                    .isTrueFor(userWithRoles(asList("Admin", "Admin", "User")))
                    .isTrueFor(userWithRoles(singletonList("User")))
                    .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isFalseFor(userWithRoles(asList("User", "Guest", "Admin")))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithRoles(singletonList("Admin")))
                    .isTrueFor(userWithRoles(singletonList("User")))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
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
    @As("where().exactlyOne().string(User::roles).startsWith($argument1)")
    public void exactlyOneStringStartsWith(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().exactlyOne().string(context.mapper()).startsWith(value));
    }

    static Stream<Arguments> exactlyOneStringStartsWith() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactlyOne().string(User::roles).startsWith($argument1)")
                .withMapper(User::roles)
                .withArgument("Admin")
                    .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isTrueFor(userWithRoles(singletonList("Admin")))
                    .isTrueFor(userWithRoles(asList("AdminUser", "Guest", "Moderator")))
                    .isTrueFor(userWithRoles(asList(null, "Admin", "User")))
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
    @As("where().exactlyOne().string(User::roles).endsWith($argument1)")
    public void exactlyOneStringEndsWith(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().exactlyOne().string(context.mapper()).endsWith(value));
    }

    static Stream<Arguments> exactlyOneStringEndsWith() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactlyOne().string(User::roles).endsWith($argument1)")
                .withMapper(User::roles)
                .withArgument("User")
                    .isTrueFor(userWithRoles(asList("Guest", "AdminUser", "Moderator")))
                    .isTrueFor(userWithRoles(singletonList("AdminUser")))
                    .isTrueFor(userWithRoles(singletonList("User")))
                    .isTrueFor(userWithRoles(asList("Admin", "User", null)))
                    .isFalseFor(userWithRoles(asList("Admin", "User", "User")))
                    .isFalseFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
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
    @As("where().exactlyOne().string(User::roles).notStartsWith($argument1)")
    public void exactlyOneStringNotStartsWith(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().exactlyOne().string(context.mapper()).notStartsWith(value));
    }

    static Stream<Arguments> exactlyOneStringNotStartsWith() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactlyOne().string(User::roles).notStartsWith($argument1)")
                .withMapper(User::roles)
                .withArgument("Admin")
                    .isTrueFor(userWithRoles(asList("User", "Admin", "Administrator")))
                    .isTrueFor(userWithRoles(asList("AdminUser", "Admin", null)))
                    .isTrueFor(userWithRoles(singletonList("User")))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithRoles(singletonList("User")))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
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
    @As("where().exactlyOne().string(User::roles).notEndsWith($argument1)")
    public void exactlyOneStringNotEndsWith(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().exactlyOne().string(context.mapper()).notEndsWith(value));
    }

    static Stream<Arguments> exactlyOneStringNotEndsWith() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactlyOne().string(User::roles).notEndsWith($argument1)")
                .withMapper(User::roles)
                .withArgument("User")
                    .isTrueFor(userWithRoles(asList("Admin", "GuestUser", "ModeratorUser")))
                    .isTrueFor(userWithRoles(asList("AdministratorUser", "AdminUser", null)))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(asList("AdminUser", "GuestUser", "User")))
                    .isFalseFor(userWithRoles(singletonList("User")))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithRoles(singletonList("Admin")))
                    .isTrueFor(userWithRoles(singletonList(null)))
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
    @As("where().exactlyOne().string(User::roles).matches($argument1)")
    public void exactlyOneStringMatches(PredicateContext<User, Iterable<String>> context, String regex) {
        scenario(context, where().exactlyOne().string(context.mapper()).matches(regex));
    }

    static Stream<Arguments> exactlyOneStringMatches() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactlyOne().string(User::roles).matches($argument1)")
                .withMapper(User::roles)
                .withArgument(".*User.*")
                    .isTrueFor(userWithRoles(asList("AdminUser", "Guest", "Admin")))
                    .isTrueFor(userWithRoles(singletonList("User")))
                    .isTrueFor(userWithRoles(asList("Admin", "User", null)))
                    .isFalseFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                    .isFalseFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(singletonList("")))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument("^Admin.*")
                    .isTrueFor(userWithRoles(asList("Guest", "Admin", "User")))
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
    @As("where().exactlyOne().string(User::roles).notMatches($argument1)")
    public void exactlyOneStringNotMatches(PredicateContext<User, Iterable<String>> context, String regex) {
        scenario(context, where().exactlyOne().string(context.mapper()).notMatches(regex));
    }

    static Stream<Arguments> exactlyOneStringNotMatches() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactlyOne().string(User::roles).notMatches($argument1)")
                .withMapper(User::roles)
                .withArgument(".*User.*")
                    .isTrueFor(userWithRoles(asList("AdminUser", "User", "Moderator")))
                    .isTrueFor(userWithRoles(asList("AdminUser", "User", null)))
                    .isTrueFor(userWithRoles(singletonList("Admin")))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isFalseFor(userWithRoles(asList("AdminUser", "GuestUser", "User")))
                    .isFalseFor(userWithRoles(singletonList("User")))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument("^Admin.*")
                    .isTrueFor(userWithRoles(asList("Admin", "AdminUser", "Manager")))
                    .isTrueFor(userWithRoles(asList("Administrator", null, "AdminUser")))
                    .isTrueFor(userWithRoles(singletonList(null)))
                    .isTrueFor(userWithRoles(singletonList("")))
                    .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
                    .isFalseFor(userWithRoles(asList("Admin", "AdminUser", "AdminManager")))
                    .isFalseFor(userWithRoles(asList("", null, "User")))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithRoles(singletonList("Admin")))
                    .isTrueFor(userWithRoles(singletonList(null)))
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
    @As("where().exactlyOne().string(User::roles).length().isEqualTo($argument1)")
    public void exactlyOneStringLengthIsEqualTo(PredicateContext<User, Iterable<String>> context, Integer length) {
        scenario(context, where().exactlyOne().string(context.mapper()).length().isEqualTo(length));
    }

    static Stream<Arguments> exactlyOneStringLengthIsEqualTo() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactlyOne().string(User::roles).length().isEqualTo($argument1)")
                .withMapper(User::roles)
                    .withArgument(5)
                        .isTrueFor(userWithRoles(asList("Admin", "User", "Moderator")))
                        .isTrueFor(userWithRoles(asList("Admin", "User", "User")))
                        .isTrueFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(asList("Admin", "Admin", "Guest")))
                        .isFalseFor(userWithRoles(asList("Admin", "Guest", "User")))
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
    @As("Logical conjunction of 3 predicates with exactlyOne().string(User::roles)")
    public void whereExactlyOneStringConjunction(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().exactlyOne().string(User::roles).startsWith("Admin")
                .and().exactlyOne().string(User::roles).endsWith("User")
                .and().exactlyOne().string(User::roles).matches(".*Manager.*"));
    }

    static Stream<Arguments> whereExactlyOneStringConjunction() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().exactlyOne().string(User::roles).startsWith(\"Admin\")\n" +
                        "       .and().exactlyOne().string(User::roles).endsWith(\"User\")\n" +
                        "       .and().exactlyOne().string(User::roles).matches(\".*Manager.*\")")
                .isTrueFor(userWithRoles(asList("Administrator", "ManagerUser", "Guest")))
                .isTrueFor(userWithRoles(singletonList("AdminManagerUser")))
                .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
                .isFalseFor(userWithRoles(asList("Admin", "AdminUser", "Administrator")))
                .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                .isFalseFor(userWithRoles(emptyList()))
                .isFalseFor(userWithRoles(null))
                .isFalseFor(null)
                .toStream();
    }
}