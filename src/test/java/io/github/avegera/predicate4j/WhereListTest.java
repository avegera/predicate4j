package io.github.avegera.predicate4j;

import com.google.common.collect.ImmutableList;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.CaseAs;
import com.tngtech.jgiven.annotation.Description;
import io.github.avegera.predicate4j.test.argument.FirstArgument;
import io.github.avegera.predicate4j.test.model.User;
import io.github.avegera.predicate4j.test.predicate.PredicateContext;
import io.github.avegera.predicate4j.test.predicate.PredicateTest;
import io.github.avegera.predicate4j.test.scenario.PredicateScenarioTest;
import io.github.avegera.predicate4j.test.tag.Type;
import io.github.avegera.predicate4j.test.tag.Where;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static io.github.avegera.predicate4j.Where.where;
import static io.github.avegera.predicate4j.test.model.User.userWithRoles;
import static java.util.Arrays.asList;

@Where("where")
@Type("list")
@Description("Build predicates with list conditions")
public class WhereListTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().list(mapper).isEmpty()")
    public void whereListIsEmpty(PredicateContext<User, List<String>> context) {
        scenario(context, where().list(context.mapper()).isEmpty());
    }

    static Stream<Arguments> whereListIsEmpty() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().list(mapper).isEmpty()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(new ArrayList<>()))
                    .isFalseFor(userWithRoles(ImmutableList.of("Admin")))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().list(mapper).notEmpty()")
    public void whereListNotEmpty(PredicateContext<User, List<String>> context) {
        scenario(context, where().list(context.mapper()).notEmpty());
    }

    static Stream<Arguments> whereListNotEmpty() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().list(mapper).notEmpty()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(ImmutableList.of("Admin")))
                    .isFalseFor(userWithRoles(new ArrayList<>()))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().list(mapper).contains(value)")
    public void whereListContains(PredicateContext<User, List<String>> context, String role) {
        scenario(context, where().list(context.mapper()).contains(role));
    }

    static Stream<Arguments> whereListContains() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().list(mapper).contains($argument1)")
                .withMapper(User::roles)
                    .withArgument("Admin")
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .withArgument("Guest")
                        .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .withArgument(null)
                        .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .withArgument(null)
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().list(mapper).notContains(value)")
    public void whereListNotContains(PredicateContext<User, List<String>> context, String role) {
        scenario(context, where().list(context.mapper()).notContains(role));
    }

    static Stream<Arguments> whereListNotContains() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().list(mapper).notContains($argument1)")
                .withMapper(User::roles)
                    .withArgument("Guest")
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .withArgument("Admin")
                        .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .withArgument(null)
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .withArgument(null)
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().list(mapper).size().isEqualTo(value)")
    public void whereListSizeIsEqualTo(PredicateContext<User, List<String>> context, Integer size) {
        scenario(context, where().list(context.mapper()).size().isEqualTo(size));
    }

    static Stream<Arguments> whereListSizeIsEqualTo() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().list(mapper).size().isEqualTo($argument1)")
                .withMapper(User::roles)
                    .withArgument(2)
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .withArgument(3)
                        .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .withArgument(0)
                        .isTrueFor(userWithRoles(new ArrayList<>()))
                    .withArgument(0)
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @As("_where().list(mapper).isEqualTo(value)")
    @CaseAs(provider = FirstArgument.class)
    public void whereListIsEqualTo(PredicateContext<User, List<String>> context, List<String> roles) {
        scenario(context, where().list(context.mapper()).isEqualTo(roles));
    }

    static Stream<Arguments> whereListIsEqualTo() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().list(mapper).isEqualTo($argument1)")
                .withMapper(User::roles)
                    .withArgument(asList("Admin", "User"))
                        .isTrueFor(userWithRoles(asList("Admin", "User")))
                        .isFalseFor(userWithRoles(asList("Admin", "Guest")))
                    .withArgument(asList("Admin"))
                        .isFalseFor(userWithRoles(asList("Guest")))
                        .isFalseFor(null)
                .withMapper(null)
                    .withArgument(asList("Admin", "User"))
                        .isFalseFor(userWithRoles(asList("Admin", "User")))
                    .withArgument(null)
                        .isFalseFor(userWithRoles(asList("Admin", "User")))
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().list(mapper).notEqualTo(value)")
    public void whereListNotEqualTo(PredicateContext<User, List<String>> context, List<String> roles) {
        scenario(context, where().list(context.mapper()).notEqualTo(roles));
    }

    static Stream<Arguments> whereListNotEqualTo() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().list(mapper).notEqualTo($argument1)")
                .withMapper(User::roles)
                    .withArgument(ImmutableList.of("Guest"))
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .withArgument(ImmutableList.of("Admin", "User"))
                        .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .withArgument(null)
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .withArgument(null)
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().list(mapper).isNull()")
    public void whereListIsNull(PredicateContext<User, List<String>> context) {
        scenario(context, where().list(context.mapper()).isNull());
    }

    static Stream<Arguments> whereListIsNull() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().list(mapper).isNull()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(null))
                    .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .isFalseFor(null)
                .withMapper(null)
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().list(mapper).notNull()")
    public void whereListNotNull(PredicateContext<User, List<String>> context) {
        scenario(context, where().list(context.mapper()).notNull());
    }

    static Stream<Arguments> whereListNotNull() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().list(mapper).notNull()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().list(mapper).isInstanceOf(clazz)")
    public void whereListIsInstanceOf(PredicateContext<User, List<String>> context, Class<?> clazz) {
        scenario(context, where().list(context.mapper()).isInstanceOf(clazz));
    }

    static Stream<Arguments> whereListIsInstanceOf() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().list(mapper).isInstanceOf($argument1)")
                .withMapper(User::roles)
                    .withArgument(List.class)
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .withArgument(String.class)
                        .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .withArgument(List.class)
                        .isFalseFor(userWithRoles(null))
                    .withArgument(List.class)
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().list(mapper).notInstanceOf(clazz)")
    public void whereListNotInstanceOf(PredicateContext<User, List<String>> context, Class<?> clazz) {
        scenario(context, where().list(context.mapper()).notInstanceOf(clazz));
    }

    static Stream<Arguments> whereListNotInstanceOf() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().list(mapper).notInstanceOf($argument1)")
                .withMapper(User::roles)
                    .withArgument(String.class)
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .withArgument(List.class)
                        .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .withArgument(List.class)
                        .isTrueFor(userWithRoles(null))
                    .withArgument(List.class)
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().list(mapper).accepts(predicate)")
    public void whereListAccepts(PredicateContext<User, List<String>> context, Predicate<List<String>> predicate) {
        scenario(context, where().list(context.mapper()).accepts(predicate));
    }

    static Stream<Arguments> whereListAccepts() {
        Predicate<List<String>> hasAdmin = roles -> roles != null && roles.contains("Admin");
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().list(mapper).accepts($argument1)")
                .withMapper(User::roles)
                    .withArgument(hasAdmin, "roles -> roles != null && roles.contains(\"Admin\")")
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                        .isFalseFor(userWithRoles(ImmutableList.of("Guest", "User")))
                    .withArgument(null)
                        .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().list(mapper).rejects(predicate)")
    public void whereListRejects(PredicateContext<User, List<String>> context, Predicate<List<String>> predicate) {
        scenario(context, where().list(context.mapper()).rejects(predicate));
    }

    static Stream<Arguments> whereListRejects() {
        Predicate<List<String>> hasAdmin = roles -> roles != null && roles.contains("Admin");
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().list(mapper).rejects($argument1)")
                .withMapper(User::roles)
                    .withArgument(hasAdmin, "roles -> roles != null && roles.contains(\"Admin\")")
                        .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                        .isTrueFor(userWithRoles(ImmutableList.of("Guest", "User")))
                    .withArgument(null)
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                        .isFalseFor(null)
                .toStream();
    }
}