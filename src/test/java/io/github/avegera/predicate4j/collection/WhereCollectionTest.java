package io.github.avegera.predicate4j.collection;

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
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static io.github.avegera.predicate4j.Where.where;
import static io.github.avegera.predicate4j.test.model.User.userWithRoles;

@Where("where")
@Type("collection")
@Description("Build predicates with collection conditions")
public class WhereCollectionTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().collection(mapper).isEmpty()")
    public void whereCollectionIsEmpty(PredicateContext<User, Collection<String>> context) {
        scenario(context, where().collection(context.mapper()).isEmpty());
    }

    static Stream<Arguments> whereCollectionIsEmpty() {
        return PredicateTest.<User, Collection<String>>builder()
                .predicate("where().collection(mapper).isEmpty()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(new ArrayList<>()))
                    .isFalseFor(userWithRoles(ImmutableList.of("Admin")))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().collection(mapper).notEmpty()")
    public void whereCollectionNotEmpty(PredicateContext<User, Collection<String>> context) {
        scenario(context, where().collection(context.mapper()).notEmpty());
    }

    static Stream<Arguments> whereCollectionNotEmpty() {
        return PredicateTest.<User, Collection<String>>builder()
                .predicate("where().collection(mapper).notEmpty()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(ImmutableList.of("Admin")))
                    .isFalseFor(userWithRoles(new ArrayList<>()))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().collection(mapper).contains(value)")
    public void whereCollectionContains(PredicateContext<User, Collection<String>> context, String value) {
        scenario(context, where().collection(context.mapper()).contains(value));
    }

    static Stream<Arguments> whereCollectionContains() {
        return PredicateTest.<User, Collection<String>>builder()
                .predicate("where().collection(mapper).contains($argument1)")
                .withMapper(User::roles)
                    .withArgument("Admin")
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .withArgument("Guest")
                        .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().collection(mapper).notContains(value)")
    public void whereCollectionNotContains(PredicateContext<User, Collection<String>> context, String value) {
        scenario(context, where().collection(context.mapper()).notContains(value));
    }

    static Stream<Arguments> whereCollectionNotContains() {
        return PredicateTest.<User, Collection<String>>builder()
                .predicate("where().collection(mapper).notContains($argument1)")
                .withMapper(User::roles)
                    .withArgument("Guest")
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .withArgument("Admin")
                        .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().collection(mapper).containsAll(values)")
    public void whereCollectionContainsAll(PredicateContext<User, Collection<String>> context, Collection<String> values) {
        scenario(context, where().collection(context.mapper()).containsAll(values));
    }

    static Stream<Arguments> whereCollectionContainsAll() {
        return PredicateTest.<User, Collection<String>>builder()
                .predicate("where().collection(mapper).containsAll($argument1)")
                .withMapper(User::roles)
                    .withArgument(ImmutableList.of("Admin", "User"))
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User", "Guest")))
                    .withArgument(ImmutableList.of("Admin", "Guest"))
                        .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().collection(mapper).notContainsAll(values)")
    public void whereCollectionNotContainsAll(PredicateContext<User, Collection<String>> context, Collection<String> values) {
        scenario(context, where().collection(context.mapper()).notContainsAll(values));
    }

    static Stream<Arguments> whereCollectionNotContainsAll() {
        return PredicateTest.<User, Collection<String>>builder()
                .predicate("where().collection(mapper).notContainsAll($argument1)")
                .withMapper(User::roles)
                    .withArgument(ImmutableList.of("Admin", "Guest"))
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .withArgument(ImmutableList.of("Admin", "User"))
                        .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().collection(mapper).size().isEqualTo(value)")
    public void whereCollectionSizeIsEqualTo(PredicateContext<User, Collection<String>> context, Integer size) {
        scenario(context, where().collection(context.mapper()).size().isEqualTo(size));
    }

    static Stream<Arguments> whereCollectionSizeIsEqualTo() {
        return PredicateTest.<User, Collection<String>>builder()
                .predicate("where().collection(mapper).size().isEqualTo($argument1)")
                .withMapper(User::roles)
                    .withArgument(2)
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .withArgument(3)
                        .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().collection(mapper).size().isGreaterThan(value)")
    public void whereCollectionSizeIsGreaterThan(PredicateContext<User, Collection<String>> context, Integer size) {
        scenario(context, where().collection(context.mapper()).size().isGreaterThan(size));
    }

    static Stream<Arguments> whereCollectionSizeIsGreaterThan() {
        return PredicateTest.<User, Collection<String>>builder()
                .predicate("where().collection(mapper).size().isGreaterThan($argument1)")
                .withMapper(User::roles)
                    .withArgument(1)
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .withArgument(2)
                        .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().collection(mapper).size().isLessThan(value)")
    public void whereCollectionSizeIsLessThan(PredicateContext<User, Collection<String>> context, Integer size) {
        scenario(context, where().collection(context.mapper()).size().isLessThan(size));
    }

    static Stream<Arguments> whereCollectionSizeIsLessThan() {
        return PredicateTest.<User, Collection<String>>builder()
                .predicate("where().collection(mapper).size().isLessThan($argument1)")
                .withMapper(User::roles)
                    .withArgument(3)
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .withArgument(2)
                        .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @As("_where().collection(mapper).isEqualTo(value)")
    @CaseAs(provider = FirstArgument.class)
    public void whereCollectionIsEqualTo(PredicateContext<User, Collection<String>> context, Collection<String> roles) {
        scenario(context, where().collection(context.mapper()).isEqualTo(roles));
    }

    static Stream<Arguments> whereCollectionIsEqualTo() {
        return PredicateTest.<User, Collection<String>>builder()
                .predicate("where().collection(mapper).isEqualTo($argument1)")
                .withMapper(User::roles)
                    .withArgument(ImmutableList.of("Admin", "User"))
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                        .isFalseFor(userWithRoles(ImmutableList.of("Admin", "Guest")))
                    .withArgument(ImmutableList.of("Admin"))
                        .isFalseFor(userWithRoles(ImmutableList.of("Guest")))
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().collection(mapper).notEqualTo(value)")
    public void whereCollectionNotEqualTo(PredicateContext<User, Collection<String>> context, Collection<String> roles) {
        scenario(context, where().collection(context.mapper()).notEqualTo(roles));
    }

    static Stream<Arguments> whereCollectionNotEqualTo() {
        return PredicateTest.<User, Collection<String>>builder()
                .predicate("where().collection(mapper).notEqualTo($argument1)")
                .withMapper(User::roles)
                    .withArgument(ImmutableList.of("Guest"))
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .withArgument(ImmutableList.of("Admin", "User"))
                        .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .withArgument(null)
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().collection(mapper).isNull()")
    public void whereCollectionIsNull(PredicateContext<User, Collection<String>> context) {
        scenario(context, where().collection(context.mapper()).isNull());
    }

    static Stream<Arguments> whereCollectionIsNull() {
        return PredicateTest.<User, Collection<String>>builder()
                .predicate("where().collection(mapper).isNull()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(null))
                    .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().collection(mapper).notNull()")
    public void whereCollectionNotNull(PredicateContext<User, Collection<String>> context) {
        scenario(context, where().collection(context.mapper()).notNull());
    }

    static Stream<Arguments> whereCollectionNotNull() {
        return PredicateTest.<User, Collection<String>>builder()
                .predicate("where().collection(mapper).notNull()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .isFalseFor(userWithRoles(null))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().collection(mapper).isInstanceOf(clazz)")
    public void whereCollectionIsInstanceOf(PredicateContext<User, Collection<String>> context, Class<?> clazz) {
        scenario(context, where().collection(context.mapper()).isInstanceOf(clazz));
    }

    static Stream<Arguments> whereCollectionIsInstanceOf() {
        return PredicateTest.<User, Collection<String>>builder()
                .predicate("where().collection(mapper).isInstanceOf($argument1)")
                .withMapper(User::roles)
                    .withArgument(Collection.class)
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .withArgument(String.class)
                        .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().collection(mapper).notInstanceOf(clazz)")
    public void whereCollectionNotInstanceOf(PredicateContext<User, Collection<String>> context, Class<?> clazz) {
        scenario(context, where().collection(context.mapper()).notInstanceOf(clazz));
    }

    static Stream<Arguments> whereCollectionNotInstanceOf() {
        return PredicateTest.<User, Collection<String>>builder()
                .predicate("where().collection(mapper).notInstanceOf($argument1)")
                .withMapper(User::roles)
                    .withArgument(String.class)
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .withArgument(Collection.class)
                        .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().collection(mapper).accepts(predicate)")
    public void whereCollectionAccepts(PredicateContext<User, Collection<String>> context, Predicate<Collection<String>> predicate) {
        scenario(context, where().collection(context.mapper()).accepts(predicate));
    }

    static Stream<Arguments> whereCollectionAccepts() {
        Predicate<Collection<String>> hasAdmin = roles -> roles != null && roles.contains("Admin");
        return PredicateTest.<User, Collection<String>>builder()
                .predicate("where().collection(mapper).accepts($argument1)")
                .withMapper(User::roles)
                    .withArgument(hasAdmin, "roles -> roles != null && roles.contains(\"Admin\")")
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                        .isFalseFor(userWithRoles(ImmutableList.of("Guest", "User")))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().collection(mapper).rejects(predicate)")
    public void whereCollectionRejects(PredicateContext<User, Collection<String>> context, Predicate<Collection<String>> predicate) {
        scenario(context, where().collection(context.mapper()).rejects(predicate));
    }

    static Stream<Arguments> whereCollectionRejects() {
        Predicate<Collection<String>> hasAdmin = roles -> roles != null && roles.contains("Admin");
        return PredicateTest.<User, Collection<String>>builder()
                .predicate("where().collection(mapper).rejects($argument1)")
                .withMapper(User::roles)
                    .withArgument(hasAdmin, "roles -> roles != null && roles.contains(\"Admin\")")
                        .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                        .isTrueFor(userWithRoles(ImmutableList.of("Guest", "User")))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("Logical conjunction of 3 predicates")
    public void whereCollectionConjunction(PredicateContext<User, Collection<String>> context) {
        scenario(context, where().collection(User::roles).notEmpty()
                .and().collection(User::roles).contains("Admin")
                .and().collection(User::roles).size().isLessThan(4));
    }

    static Stream<Arguments> whereCollectionConjunction() {
        return PredicateTest.<User, Collection<String>>builder()
                .predicate("where().collection(User::roles).notEmpty()\n" +
                        "       .and().collection(User::roles).contains(\"Admin\")\n" +
                        "       .and().collection(User::roles).size().isLessThan(4)")
                .isTrueFor(userWithRoles(ImmutableList.of("Admin"))) // Matches all conditions
                .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User", "Guest", "Other"))) // size >= 4
                .isFalseFor(userWithRoles(ImmutableList.of("User", "Guest"))) // does not contain "Admin"
                .isFalseFor(userWithRoles(new ArrayList<>())) // Empty collection
                .isFalseFor(userWithRoles(null)) // roles are null
                .isFalseFor(null) // user is null
                .toStream();
    }
}