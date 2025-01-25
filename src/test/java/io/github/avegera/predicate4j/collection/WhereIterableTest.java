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
import java.util.function.Predicate;
import java.util.stream.Stream;

import static io.github.avegera.predicate4j.Where.where;
import static io.github.avegera.predicate4j.test.model.User.userWithRoles;
import static io.github.avegera.predicate4j.test.util.ListWithNullableIterator.listWithNullableIterator;
import static java.util.Collections.emptyList;

@Where("where")
@Type("iterable")
@Description("Build predicates with iterable conditions")
public class WhereIterableTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().iterable(mapper).isEmpty()")
    public void whereIterableIsEmpty(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().iterable(context.mapper()).isEmpty());
    }

    static Stream<Arguments> whereIterableIsEmpty() {
        return PredicateTest.<User, Iterable<String>>builder()
                .predicate("where().iterable(mapper).isEmpty()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(listWithNullableIterator()))
                    .isTrueFor(userWithRoles(emptyList()))
                    .isTrueFor(userWithRoles(null))
                    .isFalseFor(userWithRoles(ImmutableList.of("Admin")))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().iterable(mapper).notEmpty()")
    public void whereIterableNotEmpty(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().iterable(context.mapper()).notEmpty());
    }

    static Stream<Arguments> whereIterableNotEmpty() {
        return PredicateTest.<User, Iterable<String>>builder()
                .predicate("where().iterable(mapper).notEmpty()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(ImmutableList.of("Admin")))
                    .isFalseFor(userWithRoles(listWithNullableIterator()))
                    .isFalseFor(userWithRoles(emptyList()))
                    .isFalseFor(userWithRoles(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().iterable(mapper).isEqualTo(value)")
    public void whereIterableIsEqualTo(PredicateContext<User, Iterable<String>> context, Iterable<String> roles) {
        scenario(context, where().iterable(context.mapper()).isEqualTo(roles));
    }

    static Stream<Arguments> whereIterableIsEqualTo() {
        return PredicateTest.<User, Iterable<String>>builder()
                .predicate("where().iterable(mapper).isEqualTo($argument1)")
                .withMapper(User::roles)
                    .withArgument(ImmutableList.of("Admin", "User"))
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                        .isFalseFor(userWithRoles(ImmutableList.of("Admin", "Guest")))
                    .withArgument(null)
                        .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                        .isTrueFor(userWithRoles(null))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().iterable(mapper).notEqualTo(value)")
    public void whereIterableNotEqualTo(PredicateContext<User, Iterable<String>> context, Iterable<String> roles) {
        scenario(context, where().iterable(context.mapper()).notEqualTo(roles));
    }

    static Stream<Arguments> whereIterableNotEqualTo() {
        return PredicateTest.<User, Iterable<String>>builder()
                .predicate("where().iterable(mapper).notEqualTo($argument1)")
                .withMapper(User::roles)
                    .withArgument(ImmutableList.of("Admin", "User"))
                        .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "Guest")))
                    .withArgument(null)
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                        .isFalseFor(userWithRoles(null))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().iterable(mapper).isNull()")
    public void whereIterableIsNull(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().iterable(context.mapper()).isNull());
    }

    static Stream<Arguments> whereIterableIsNull() {
        return PredicateTest.<User, Iterable<String>>builder()
                .predicate("where().iterable(mapper).isNull()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(null))
                    .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().iterable(mapper).notNull()")
    public void whereIterableNotNull(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().iterable(context.mapper()).notNull());
    }

    static Stream<Arguments> whereIterableNotNull() {
        return PredicateTest.<User, Iterable<String>>builder()
                .predicate("where().iterable(mapper).notNull()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                    .isFalseFor(userWithRoles(null))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().iterable(mapper).accepts(predicate)")
    public void whereIterableAccepts(PredicateContext<User, Iterable<String>> context, Predicate<Iterable<String>> predicate) {
        scenario(context, where().iterable(context.mapper()).accepts(predicate));
    }

    static Stream<Arguments> whereIterableAccepts() {
        Predicate<Iterable<String>> containsAdmin = roles -> roles != null && roles.iterator().hasNext() && roles.iterator().next().equals("Admin");
        return PredicateTest.<User, Iterable<String>>builder()
                .predicate("where().iterable(mapper).accepts($argument1)")
                .withMapper(User::roles)
                    .withArgument(containsAdmin, "roles -> roles contains Admin")
                        .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User")))
                        .isFalseFor(userWithRoles(ImmutableList.of("User", "Guest")))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().iterable(mapper).rejects(predicate)")
    public void whereIterableRejects(PredicateContext<User, Iterable<String>> context, Predicate<Iterable<String>> predicate) {
        scenario(context, where().iterable(context.mapper()).rejects(predicate));
    }

    static Stream<Arguments> whereIterableRejects() {
        Predicate<Iterable<String>> containsAdmin = roles -> roles != null && roles.iterator().hasNext() && roles.iterator().next().equals("Admin");
        return PredicateTest.<User, Iterable<String>>builder()
                .predicate("where().iterable(mapper).rejects($argument1)")
                .withMapper(User::roles)
                    .withArgument(containsAdmin, "roles -> roles contains Admin")
                        .isFalseFor(userWithRoles(ImmutableList.of("Admin", "User")))
                        .isTrueFor(userWithRoles(ImmutableList.of("User", "Guest")))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("Logical conjunction of 3 predicates")
    public void whereIterableConjunction(PredicateContext<User, Iterable<String>> context) {
        scenario(context, where().iterable(User::roles).notEmpty()
                .and().iterable(User::roles).accepts(roles -> roles.iterator().hasNext() && roles.iterator().next().equals("Admin"))
                .and().iterable(User::roles).isEqualTo(ImmutableList.of("Admin", "User")));
    }

    static Stream<Arguments> whereIterableConjunction() {
        return PredicateTest.<User, Iterable<String>>builder()
                .predicate("where().iterable(User::roles).notEmpty()\n" +
                        "       .and().iterable(User::roles).accepts(roles -> roles.iterator().next().equals(\"Admin\"))\n" +
                        "       .and().iterable(User::roles).isEqualTo(ImmutableList.of(\"Admin\", \"User\"))")
                .isTrueFor(userWithRoles(ImmutableList.of("Admin", "User"))) // Matches all conditions
                .isFalseFor(userWithRoles(ImmutableList.of("User", "Admin"))) // Order mismatch for isEqualTo
                .isFalseFor(userWithRoles(ImmutableList.of("Admin"))) // Not equal to expected list
                .isFalseFor(userWithRoles(listWithNullableIterator())) // Fails notEmpty
                .isFalseFor(userWithRoles(emptyList())) // Fails notEmpty
                .isFalseFor(userWithRoles(null)) // Fails notEmpty
                .isFalseFor(null) // Null user
                .toStream();
    }
}