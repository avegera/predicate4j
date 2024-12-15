package io.github.avegera.predicate4j;

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

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static io.github.avegera.predicate4j.Where.where;
import static io.github.avegera.predicate4j.test.model.User.userWithId;
import static io.github.avegera.predicate4j.test.model.User.userWithName;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@Where("where")
@Type("object")
@Description("Build object predicates with single condition")
public class WhereObjectTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where(mapper).isEqualTo(value)")
    public void whereIsEqualTo(PredicateContext<User, Object> context, Integer userId) {
        scenario(context, where(User::id).isEqualTo(userId));
    }

    static Stream<Arguments> whereIsEqualTo() {
        return PredicateTest.<User, Object>builder()
                .predicate("where(User::id).isEqualTo($argument1)")
                .withMapper(User::id)
                    .withArgument(123)
                        .isTrueFor(userWithId(123))
                    .withArgument(null)
                        .isTrueFor(userWithId(null))
                    .withArgument(456)
                        .isFalseFor(userWithId(123))
                    .withArgument(123)
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where(mapper).notEqualTo(value)")
    public void whereNotEqualTo(PredicateContext<User, Object> context, Integer userId) {
        scenario(context, where(User::id).notEqualTo(userId));
    }

    static Stream<Arguments> whereNotEqualTo() {
        return PredicateTest.<User, Object>builder()
                .predicate("where(User::id).notEqualTo($argument1)")
                .withMapper(User::id)
                    .withArgument(456)
                        .isTrueFor(userWithId(123))
                    .withArgument(123)
                        .isFalseFor(userWithId(123))
                    .withArgument(null)
                        .isFalseFor(userWithId(null))
                    .withArgument(123)
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where(mapper).isInstanceOf(clazz)")
    public void whereIsInstanceOf(PredicateContext<User, Object> context, Class<?> clazz) {
        scenario(context, where(User::id).isInstanceOf(clazz));
    }

    static Stream<Arguments> whereIsInstanceOf() {
        return PredicateTest.<User, Object>builder()
                .predicate("where(User::id).isInstanceOf($argument1)")
                .withMapper(User::id)
                    .withArgument(Integer.class)
                            .isTrueFor(userWithId(123))
                    .withArgument(Integer.class)
                            .isFalseFor(userWithId(null))
                    .withArgument(String.class)
                            .isFalseFor(userWithId(123))
                    .withArgument(null)
                            .isFalseFor(userWithId(123))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where(mapper).isNull()")
    public void whereIsNull(PredicateContext<User, Object> context) {
        scenario(context, where(User::id).isNull());
    }

    static Stream<Arguments> whereIsNull() {
        return PredicateTest.<User, Object>builder()
                .predicate("where(User::id).isNull()")
                .withMapper(User::id)
                    .isTrueFor(userWithId(null))
                    .isFalseFor(userWithId(123))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where(mapper).notNull()")
    public void whereNotNull(PredicateContext<User, Object> context) {
        scenario(context, where(User::id).notNull());
    }

    static Stream<Arguments> whereNotNull() {
        return PredicateTest.<User, Object>builder()
                .predicate("where(User::id).notNull()")
                .withMapper(User::id)
                    .isTrueFor(userWithId(123))
                    .isFalseFor(userWithId(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where(mapper).notInstanceOf(clazz)")
    public void whereNotInstanceOf(PredicateContext<User, Object> context, Class<?> clazz) {
        scenario(context, where(User::id).notInstanceOf(clazz));
    }

    static Stream<Arguments> whereNotInstanceOf() {
        return PredicateTest.<User, Object>builder()
                .predicate("where(User::id).notInstanceOf($argument1)")
                .withMapper(User::id)
                    .withArgument(Integer.class)
                        .isFalseFor(userWithId(123))
                    .withArgument(String.class)
                            .isTrueFor(userWithId(123))
                    .withArgument(Integer.class)
                            .isTrueFor(userWithId(null))
                    .withArgument(null)
                            .isTrueFor(userWithId(123))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where(mapper).in(collection)")
    public void whereIn(PredicateContext<User, String> context, Collection<String> validNames) {
        scenario(context, where(User::name).in(validNames));
    }

    static Stream<Arguments> whereIn() {
        return PredicateTest.<User, String>builder()
                .predicate("where(User::name).in($argument1)")
                .withMapper(User::name)
                    .withArgument(asList("John", "Jane"))
                        .isTrueFor(userWithName("John"))
                        .isTrueFor(userWithName("Jane"))
                        .isFalseFor(userWithName("Doe"))
                        .isFalseFor(userWithName(null))
                        .isFalseFor(null)
                    .withArgument(singletonList("Doe"))
                        .isTrueFor(userWithName("Doe"))
                        .isFalseFor(userWithName("John"))
                    .withArgument(emptyList())
                        .isFalseFor(userWithName("John"))
                        .isFalseFor(userWithName(null))
                    .withArgument(null)
                        .isFalseFor(userWithName("John"))
                        .isFalseFor(userWithName(null))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where(mapper).notIn(collection)")
    public void whereNotIn(PredicateContext<User, String> context, Collection<String> invalidNames) {
        scenario(context, where(User::name).notIn(invalidNames));
    }

    static Stream<Arguments> whereNotIn() {
        return PredicateTest.<User, String>builder()
                .predicate("where(User::name).notIn($argument1)")
                .withMapper(User::name)
                    .withArgument(asList("John", "Jane"))
                        .isTrueFor(userWithName("Doe"))
                        .isTrueFor(userWithName(null))
                        .isFalseFor(userWithName("John"))
                        .isFalseFor(userWithName("Jane"))
                    .withArgument(singletonList("Doe"))
                        .isTrueFor(userWithName("John"))
                        .isFalseFor(userWithName("Doe"))
                    .withArgument(emptyList())
                        .isTrueFor(userWithName("John"))
                        .isTrueFor(userWithName(null))
                    .withArgument(null)
                        .isTrueFor(userWithName("John"))
                        .isTrueFor(userWithName(null))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where(mapper).accepts(predicate)")
    public void whereAccepts(PredicateContext<User, Object> context, Predicate<Integer> predicate) {
        scenario(context, where(User::id).accepts(predicate));
    }

    static Stream<Arguments> whereAccepts() {
        Predicate<Integer> predicate = id -> id == 123;
        return PredicateTest.<User, Object>builder()
                .predicate("where(User::id).accepts($argument1)")
                .withMapper(User::id)
                .withArgument(predicate, "id -> id == 123")
                    .isTrueFor(userWithId(123))
                    .isFalseFor(userWithId(456))
                .withArgument(null)
                    .isFalseFor(userWithId(123))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where(mapper).rejects(predicate)")
    public void whereRejects(PredicateContext<User, Object> context, Predicate<Integer> predicate) {
        scenario(context, where(User::id).rejects(predicate));
    }

    static Stream<Arguments> whereRejects() {
        Predicate<Integer> predicate = id -> id == 123;
        return PredicateTest.<User, Object>builder()
                .predicate("where(User::id).rejects($argument1)")
                .withMapper(User::id)
                    .withArgument(predicate, "id -> id == 123")
                        .isFalseFor(userWithId(123))
                    .withArgument(predicate)
                        .isTrueFor(userWithId(456))
                    .withArgument(null)
                        .isTrueFor(userWithId(123))
                .toStream();
    }
}