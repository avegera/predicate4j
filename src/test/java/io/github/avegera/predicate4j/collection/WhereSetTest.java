package io.github.avegera.predicate4j.collection;

import com.google.common.collect.ImmutableSet;
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
import io.github.avegera.predicate4j.test.util.SetWithNullableIterator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static io.github.avegera.predicate4j.Where.where;
import static io.github.avegera.predicate4j.test.model.User.userWithPermissions;

@Where("where")
@Type("set")
@Description("Build predicates with set conditions")
public class WhereSetTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().set(mapper).isEmpty()")
    public void whereSetIsEmpty(PredicateContext<User, Set<String>> context) {
        scenario(context, where().set(context.mapper()).isEmpty());
    }

    static Stream<Arguments> whereSetIsEmpty() {
        return PredicateTest.<User, Set<String>>builder()
                .predicate("where().set(mapper).isEmpty()")
                .withMapper(User::permissions)
                    .isTrueFor(userWithPermissions(new HashSet<>()))
                    .isTrueFor(userWithPermissions(new SetWithNullableIterator<>()))
                    .isFalseFor(userWithPermissions(ImmutableSet.of("READ")))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().set(mapper).notEmpty()")
    public void whereSetNotEmpty(PredicateContext<User, Set<String>> context) {
        scenario(context, where().set(context.mapper()).notEmpty());
    }

    static Stream<Arguments> whereSetNotEmpty() {
        return PredicateTest.<User, Set<String>>builder()
                .predicate("where().set(mapper).notEmpty()")
                .withMapper(User::permissions)
                    .isTrueFor(userWithPermissions(ImmutableSet.of("READ")))
                    .isFalseFor(userWithPermissions(new SetWithNullableIterator<>()))
                    .isFalseFor(userWithPermissions(new HashSet<>()))
                    .isFalseFor(userWithPermissions(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().set(mapper).contains(value)")
    public void whereSetContains(PredicateContext<User, Set<String>> context, String permission) {
        scenario(context, where().set(context.mapper()).contains(permission));
    }

    static Stream<Arguments> whereSetContains() {
        return PredicateTest.<User, Set<String>>builder()
                .predicate("where().set(mapper).contains($argument1)")
                .withMapper(User::permissions)
                    .withArgument("READ")
                        .isTrueFor(userWithPermissions(ImmutableSet.of("READ", "WRITE")))
                    .withArgument("EXECUTE")
                        .isFalseFor(userWithPermissions(ImmutableSet.of("READ", "WRITE")))
                    .withArgument(null)
                        .isFalseFor(userWithPermissions(ImmutableSet.of("READ", "WRITE")))
                    .withArgument(null)
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().set(mapper).notContains(value)")
    public void whereSetNotContains(PredicateContext<User, Set<String>> context, String permission) {
        scenario(context, where().set(context.mapper()).notContains(permission));
    }

    static Stream<Arguments> whereSetNotContains() {
        return PredicateTest.<User, Set<String>>builder()
                .predicate("where().set(mapper).notContains($argument1)")
                .withMapper(User::permissions)
                    .withArgument("EXECUTE")
                        .isTrueFor(userWithPermissions(ImmutableSet.of("READ", "WRITE")))
                    .withArgument("READ")
                        .isFalseFor(userWithPermissions(ImmutableSet.of("READ", "WRITE")))
                    .withArgument(null)
                        .isTrueFor(userWithPermissions(ImmutableSet.of("READ", "WRITE")))
                    .withArgument(null)
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().set(mapper).containsAll(value)")
    public void whereSetContainsAll(PredicateContext<User, Set<String>> context, Set<String> permissions) {
        scenario(context, where().set(context.mapper()).containsAll(permissions));
    }

    static Stream<Arguments> whereSetContainsAll() {
        return PredicateTest.<User, Set<String>>builder()
                .predicate("where().set(mapper).containsAll($argument1)")
                .withMapper(User::permissions)
                    .withArgument(ImmutableSet.of("READ", "WRITE"))
                        .isTrueFor(userWithPermissions(ImmutableSet.of("READ", "WRITE", "EXECUTE")))
                    .withArgument(ImmutableSet.of("READ", "EXECUTE"))
                        .isFalseFor(userWithPermissions(ImmutableSet.of("READ", "WRITE")))
                    .withArgument(null)
                        .isFalseFor(userWithPermissions(ImmutableSet.of("READ", "WRITE")))
                    .withArgument(ImmutableSet.of("READ"))
                        .isTrueFor(userWithPermissions(ImmutableSet.of("READ", "WRITE")))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().set(mapper).notContainsAll(value)")
    public void whereSetNotContainsAll(PredicateContext<User, Set<String>> context, Set<String> permissions) {
        scenario(context, where().set(context.mapper()).notContainsAll(permissions));
    }

    static Stream<Arguments> whereSetNotContainsAll() {
        return PredicateTest.<User, Set<String>>builder()
                .predicate("where().set(mapper).notContainsAll($argument1)")
                .withMapper(User::permissions)
                    .withArgument(ImmutableSet.of("READ", "EXECUTE"))
                        .isTrueFor(userWithPermissions(ImmutableSet.of("READ", "WRITE")))
                    .withArgument(ImmutableSet.of("READ", "WRITE"))
                        .isFalseFor(userWithPermissions(ImmutableSet.of("READ", "WRITE")))
                    .withArgument(null)
                        .isTrueFor(userWithPermissions(ImmutableSet.of("READ", "WRITE")))
                    .withArgument(ImmutableSet.of("EXECUTE"))
                        .isTrueFor(userWithPermissions(ImmutableSet.of("READ")))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().set(mapper).isEqualTo(value)")
    public void whereSetIsEqualTo(PredicateContext<User, Set<String>> context, Set<String> permissions) {
        scenario(context, where().set(context.mapper()).isEqualTo(permissions));
    }

    static Stream<Arguments> whereSetIsEqualTo() {
        return PredicateTest.<User, Set<String>>builder()
                .predicate("where().set(mapper).isEqualTo($argument1)")
                .withMapper(User::permissions)
                    .withArgument(ImmutableSet.of("READ", "WRITE"))
                        .isTrueFor(userWithPermissions(ImmutableSet.of("READ", "WRITE")))
                        .isFalseFor(userWithPermissions(ImmutableSet.of("READ", "EXECUTE")))
                    .withArgument(ImmutableSet.of("READ"))
                        .isFalseFor(userWithPermissions(ImmutableSet.of("EXECUTE")))
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().set(mapper).notEqualTo(value)")
    public void whereSetNotEqualTo(PredicateContext<User, Set<String>> context, Set<String> permissions) {
        scenario(context, where().set(context.mapper()).notEqualTo(permissions));
    }

    static Stream<Arguments> whereSetNotEqualTo() {
        return PredicateTest.<User, Set<String>>builder()
                .predicate("where().set(mapper).notEqualTo($argument1)")
                .withMapper(User::permissions)
                    .withArgument(ImmutableSet.of("EXECUTE"))
                        .isTrueFor(userWithPermissions(ImmutableSet.of("READ", "WRITE")))
                    .withArgument(ImmutableSet.of("READ", "WRITE"))
                        .isFalseFor(userWithPermissions(ImmutableSet.of("READ", "WRITE")))
                    .withArgument(null)
                        .isTrueFor(userWithPermissions(ImmutableSet.of("READ", "WRITE")))
                    .withArgument(null)
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("Logical conjunction of 3 predicates")
    public void whereSetConjunction(PredicateContext<User, Set<String>> context) {
        scenario(context, where().set(User::permissions).notEmpty()
                .and().set(User::permissions).contains("DELETE")
                .and().set(User::permissions).size().isLessThan(4));
    }

    static Stream<Arguments> whereSetConjunction() {
        return PredicateTest.<User, Set<String>>builder()
                .predicate("where().set(User::permissions).notEmpty()\n" +
                        "       .and().set(User::permissions).contains(\"DELETE\")\n" +
                        "       .and().set(User::permissions).size().isLessThan(4)")
                .isTrueFor(userWithPermissions(ImmutableSet.of("DELETE")))
                .isFalseFor(userWithPermissions(ImmutableSet.of("CREATE", "READ", "UPDATE", "DELETE"))) //size >= 4
                .isFalseFor(userWithPermissions(ImmutableSet.of("READ", "WRITE"))) //does not contain DELETE
                .isFalseFor(userWithPermissions(null)) // permissions are null
                .isFalseFor(null) // user is null
                .toStream();
    }
}