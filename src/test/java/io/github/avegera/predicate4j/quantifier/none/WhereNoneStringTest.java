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
@Quantifier("none")
@Description("Test predicates under where().none().string(...)")
public class WhereNoneStringTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().none().string(User::roles).isEmpty()")
    public void noneStringIsEmpty(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().none().string(context.mapper()).isEmpty());
    }

    static Stream<Arguments> noneStringIsEmpty() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().none().string(User::roles).isEmpty()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isTrueFor(userWithRoles(singletonList("Admin")))
                    .isTrueFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(asList("Admin", "", "User")))
                    .isFalseFor(userWithRoles(asList("", "", "")))
                    .isFalseFor(userWithRoles(singletonList("")))
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
    @As("where().none().string(User::roles).notEmpty()")
    public void noneStringNotEmpty(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().none().string(context.mapper()).notEmpty());
    }

    static Stream<Arguments> noneStringNotEmpty() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().none().string(User::roles).notEmpty()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(asList("", "", "")))
                    .isTrueFor(userWithRoles(singletonList("")))
                    .isTrueFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(asList("Admin", "", "User")))
                    .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                    .isFalseFor(userWithRoles(singletonList("Admin")))
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
    @As("where().none().string(User::roles).contains($argument1)")
    public void noneStringContains(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().none().string(context.mapper()).contains(value));
    }

    static Stream<Arguments> noneStringContains() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().none().string(User::roles).contains($argument1)")
                .withMapper(User::roles)
                    .withArgument("Admin")
                        .isTrueFor(userWithRoles(asList("User", "Guest", "Moderator")))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("Admin", "User", "Moderator")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(asList("Admin", null)))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isTrueFor(userWithRoles(asList("Admin", "User", "Moderator")))
                        .isTrueFor(userWithRoles(singletonList("Admin")))
                        .isTrueFor(userWithRoles(asList(null, null)))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                .withMapper(null)
                    .withArgument("Admin")
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
    @As("where().none().string(User::roles).notContains($argument1)")
    public void noneStringNotContains(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().none().string(context.mapper()).notContains(value));
    }

    static Stream<Arguments> noneStringNotContains() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().none().string(User::roles).notContains($argument1)")
                .withMapper(User::roles)
                    .withArgument("Admin")
                        .isTrueFor(userWithRoles(asList("Admin", "AdminUser", "Administrator")))
                        .isTrueFor(userWithRoles(singletonList("Admin")))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                        .isFalseFor(userWithRoles(asList("Admin", "User", "Moderator")))
                        .isFalseFor(userWithRoles(asList("", null, "User")))
                        .isFalseFor(userWithRoles(asList("Admin", null)))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
                        .isFalseFor(userWithRoles(singletonList("User")))
                        .isFalseFor(userWithRoles(asList("", "", "")))
                        .isFalseFor(userWithRoles(asList("", null, "")))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                .withMapper(null)
                    .withArgument("Admin")
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
    @As("where().none().string(User::roles).startsWith($argument1)")
    public void noneStringStartsWith(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().none().string(context.mapper()).startsWith(value));
    }

    static Stream<Arguments> noneStringStartsWith() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().none().string(User::roles).startsWith($argument1)")
                .withMapper(User::roles)
                    .withArgument("Admin")
                        .isTrueFor(userWithRoles(asList("User", "Guest", "Moderator")))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("Admin", "User", "Moderator")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(asList("Admin", null)))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isTrueFor(userWithRoles(asList("User", "Guest", "Moderator")))
                        .isTrueFor(userWithRoles(singletonList("User")))
                        .isTrueFor(userWithRoles(asList("", "", "")))
                        .isTrueFor(userWithRoles(asList("", null, "")))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                .withMapper(null)
                    .withArgument("Admin")
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
    @As("where().none().string(User::roles).endsWith($argument1)")
    public void noneStringEndsWith(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().none().string(context.mapper()).endsWith(value));
    }

    static Stream<Arguments> noneStringEndsWith() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().none().string(User::roles).endsWith($argument1)")
                .withMapper(User::roles)
                    .withArgument("User")
                        .isTrueFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("AdminUser", "GuestUser", "User")))
                        .isFalseFor(userWithRoles(singletonList("User")))
                        .isFalseFor(userWithRoles(asList("AdminUser", null)))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isTrueFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                        .isTrueFor(userWithRoles(singletonList("Admin")))
                        .isTrueFor(userWithRoles(asList("", "", "")))
                        .isTrueFor(userWithRoles(asList("", null, "")))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                .withMapper(null)
                    .withArgument("User")
                        .isFalseFor(userWithRoles(asList("", "User", "")))
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
    @As("where().none().string(User::roles).notStartsWith($argument1)")
    public void noneStringNotStartsWith(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().none().string(context.mapper()).notStartsWith(value));
    }

    static Stream<Arguments> noneStringNotStartsWith() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().none().string(User::roles).notStartsWith($argument1)")
                .withMapper(User::roles)
                    .withArgument("Admin")
                        .isTrueFor(userWithRoles(asList("Admin", "Administrator", "AdminUser")))
                        .isTrueFor(userWithRoles(singletonList("Admin")))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
                        .isFalseFor(userWithRoles(asList("", "Admin", null)))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
                        .isFalseFor(userWithRoles(asList("Admin", "Administrator", "AdminUser")))
                        .isFalseFor(userWithRoles(asList("", null, "User")))
                        .isFalseFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(singletonList("")))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                .withMapper(null)
                    .withArgument("Admin")
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
    @As("where().none().string(User::roles).notEndsWith($argument1)")
    public void noneStringNotEndsWith(PredicateContext<User, Iterable<String>> context, String value) {
        scenario(context, where().none().string(context.mapper()).notEndsWith(value));
    }

    static Stream<Arguments> noneStringNotEndsWith() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().none().string(User::roles).notEndsWith($argument1)")
                .withMapper(User::roles)
                    .withArgument("User")
                        .isTrueFor(userWithRoles(asList("AdminUser", "GuestUser", "User")))
                        .isTrueFor(userWithRoles(singletonList("User")))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                        .isFalseFor(userWithRoles(asList("", null, "User")))
                        .isFalseFor(userWithRoles(asList("AdminUser", "", null)))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                        .isFalseFor(userWithRoles(asList("Admin", "Guest", "")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(asList(null, null, null)))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                .withMapper(null)
                    .withArgument("User")
                        .isFalseFor(userWithRoles(asList("", "User", "")))
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
    @As("where().none().string(User::roles).matches($argument1)")
    public void noneStringMatches(PredicateContext<User, Iterable<String>> context, String regex) {
        scenario(context, where().none().string(context.mapper()).matches(regex));
    }

    static Stream<Arguments> noneStringMatches() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().none().string(User::roles).matches($argument1)")
                .withMapper(User::roles)
                    .withArgument(".*User.*")
                        .isTrueFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                        .isTrueFor(userWithRoles(singletonList("Admin")))
                        .isTrueFor(userWithRoles(asList(null, "Admin", "")))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("AdminUser", "GuestUser", "User")))
                        .isFalseFor(userWithRoles(singletonList("User")))
                        .isFalseFor(userWithRoles(asList("Admin", "User", null)))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument("^Admin.*")
                        .isTrueFor(userWithRoles(asList("User", "Guest", "Moderator")))
                        .isTrueFor(userWithRoles(singletonList("User")))
                        .isTrueFor(userWithRoles(asList("", null, "Guest")))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("Admin", "Administrator", "AdminUser")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(asList("Admin", "Guest", null)))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isTrueFor(userWithRoles(singletonList("Admin")))
                        .isTrueFor(userWithRoles(singletonList(null)))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                .withMapper(null)
                    .withArgument(".*User.*")
                        .isFalseFor(userWithRoles(asList("", "User", "")))
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
    @As("where().none().string(User::roles).notMatches($argument1)")
    public void noneStringNotMatches(PredicateContext<User, Iterable<String>> context, String regex) {
        scenario(context, where().none().string(context.mapper()).notMatches(regex));
    }

    static Stream<Arguments> noneStringNotMatches() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().none().string(User::roles).notMatches($argument1)")
                .withMapper(User::roles)
                    .withArgument(".*User.*")
                        .isTrueFor(userWithRoles(asList("AdminUser", "GuestUser", "User")))
                        .isTrueFor(userWithRoles(singletonList("User")))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("Admin", "User", null)))
                        .isFalseFor(userWithRoles(asList("Admin", "Guest", "Moderator")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(asList(null, "Admin", "")))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument("^Admin.*")
                        .isTrueFor(userWithRoles(asList("Admin", "Administrator", "AdminUser")))
                        .isTrueFor(userWithRoles(singletonList("Admin")))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("Admin", "Guest", null)))
                        .isFalseFor(userWithRoles(asList("User", "Guest", "Moderator")))
                        .isFalseFor(userWithRoles(singletonList("User")))
                        .isFalseFor(userWithRoles(asList("", null, "Guest")))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(singletonList(null)))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                .withMapper(null)
                    .withArgument(".*User.*")
                        .isFalseFor(userWithRoles(asList("", "User", "")))
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
    @As("where().none().string(User::roles).length().isEqualTo($argument1)")
    public void noneStringLengthIsEqualTo(PredicateContext<User, Iterable<String>> context, Integer length) {
        scenario(context, where().none().string(context.mapper()).length().isEqualTo(length));
    }

    static Stream<Arguments> noneStringLengthIsEqualTo() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().none().string(User::roles).length().isEqualTo($argument1)")
                .withMapper(User::roles)
                    .withArgument(5)
                        .isTrueFor(userWithRoles(asList("Administrator", "User", "Moderator")))
                        .isTrueFor(userWithRoles(asList("Manager", "User")))
                        .isTrueFor(userWithRoles(singletonList("User")))
                        .isTrueFor(userWithRoles(singletonList(null)))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(asList("Admin", "Guest", "Admin")))
                        .isFalseFor(userWithRoles(singletonList("Admin")))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isTrueFor(userWithRoles(asList("Admin", "User", "Guest")))
                        .isTrueFor(userWithRoles(singletonList("Admin")))
                        .isTrueFor(userWithRoles(singletonList(null)))
                        .isTrueFor(userWithRoles(emptyList()))
                        .isFalseFor(userWithRoles(listWithNullableIterator()))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                .withMapper(null)
                    .withArgument(5)
                        .isFalseFor(userWithRoles(asList("Admin", "User", "Moderator")))
                        .isFalseFor(userWithRoles(asList("Moderator", "Guest", "User")))
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
    @As("Logical conjunction of 3 predicates with none().string(User::roles)")
    public void whereNoneStringConjunction(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().none().string(User::roles).startsWith("Admin")
                .and().none().string(User::roles).endsWith("User")
                .and().none().string(User::roles).matches(".*Guest.*"));
    }

    static Stream<Arguments> whereNoneStringConjunction() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().none().string(User::roles).startsWith(\"Admin\")\n" +
                        "       .and().none().string(User::roles).endsWith(\"User\")\n" +
                        "       .and().none().string(User::roles).matches(\".*Guest.*\")")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(asList("Manager", "Owner", "Developer"))) // All roles satisfy the predicates
                    .isTrueFor(userWithRoles(emptyList())) // Empty list satisfies all predicates
                    .isFalseFor(userWithRoles(asList("AdminUser", "Owner", "Developer"))) // One role starts with "Admin"
                    .isFalseFor(userWithRoles(asList("Manager", "GuestUser", "Developer"))) // One role ends with "User"
                    .isFalseFor(userWithRoles(asList("Manager", "Guest", "Developer"))) // One role matches ".*Guest.*"
                    .isFalseFor(userWithRoles(null)) // Roles are null
                    .isFalseFor(null)
                .toStream();
    }
}