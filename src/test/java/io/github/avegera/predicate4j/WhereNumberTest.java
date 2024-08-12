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

import java.util.stream.Stream;

import static io.github.avegera.predicate4j.Where.where;
import static io.github.avegera.predicate4j.test.model.User.userWithId;

@Where("where")
@Type("number")
@Description("Build predicate with single number condition")
public class WhereNumberTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().number(mapper).isGreaterThan(value)")
    public void whereNumberIsGreaterThan(PredicateContext<User, Number> context, Integer value) {
        scenario(context, where().number(User::id).isGreaterThan(value));
    }

    static Stream<Arguments> whereNumberIsGreaterThan() {
        return PredicateTest.<User, Number>builder()
                .predicate("where().number(User::id).isGreaterThan($argument1)")
                .withMapper(User::id)
                    .withArgument(100)
                        .isTrueFor(userWithId(150))
                    .withArgument(200)
                        .isFalseFor(userWithId(150))
                    .withArgument(150)
                        .isFalseFor(userWithId(150))
                    .withArgument(null)
                        .isFalseFor(userWithId(100))
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().number(mapper).isGreaterThanOrEqualTo(value)")
    public void whereNumberIsGreaterThanOrEqualTo(PredicateContext<User, Number> context, Integer value) {
        scenario(context, where().number(User::id).isGreaterThanOrEqualTo(value));
    }

    static Stream<Arguments> whereNumberIsGreaterThanOrEqualTo() {
        return PredicateTest.<User, Number>builder()
                .predicate("where().number(User::id).isGreaterThanOrEqualTo($argument1)")
                .withMapper(User::id)
                    .withArgument(100)
                        .isTrueFor(userWithId(150))
                    .withArgument(150)
                        .isTrueFor(userWithId(150))
                    .withArgument(200)
                        .isFalseFor(userWithId(150))
                    .withArgument(null).isFalseFor(userWithId(150))
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().number(mapper).isLessThan(value)")
    public void whereNumberIsLessThan(PredicateContext<User, Number> context, Integer value) {
        scenario(context, where().number(User::id).isLessThan(value));
    }

    static Stream<Arguments> whereNumberIsLessThan() {
        return PredicateTest.<User, Number>builder()
                .predicate("where().number(User::id).isLessThan($argument1)")
                .withMapper(User::id)
                    .withArgument(200)
                        .isTrueFor(userWithId(150))
                    .withArgument(150)
                        .isFalseFor(userWithId(150))
                    .withArgument(100)
                        .isFalseFor(userWithId(150))
                    .withArgument(null)
                        .isFalseFor(userWithId(150))
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().number(mapper).isLessThanOrEqualTo(value)")
    public void whereNumberIsLessThanOrEqualTo(PredicateContext<User, Number> context, Integer value) {
        scenario(context, where().number(User::id).isLessThanOrEqualTo(value));
    }

    static Stream<Arguments> whereNumberIsLessThanOrEqualTo() {
        return PredicateTest.<User, Number>builder()
                .predicate("where().number(User::id).isLessThanOrEqualTo($argument1)")
                .withMapper(User::id)
                    .withArgument(200)
                        .isTrueFor(userWithId(150))
                    .withArgument(150)
                        .isTrueFor(userWithId(150))
                    .withArgument(100)
                        .isFalseFor(userWithId(150))
                    .withArgument(null)
                        .isFalseFor(userWithId(150))
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().number(mapper).isBetween(startInclusive, endInclusive)")
    public void whereNumberIsBetween(PredicateContext<User, Number> context, Integer startInclusive, Integer endInclusive) {
        scenario(context, where().number(User::id).isBetween(startInclusive, endInclusive));
    }

    static Stream<Arguments> whereNumberIsBetween() {
        return PredicateTest.<User, Number>builder()
                .predicate("where().number(User::id).isBetween($argument1, $argument2)")
                .withMapper(User::id)
                    .withArguments(100, 200)
                        .isTrueFor(userWithId(150))
                    .withArguments(150, 150)
                        .isTrueFor(userWithId(150))
                    .withArguments(200, 300)
                        .isFalseFor(userWithId(150))
                    .withArguments(null, 200)
                        .isFalseFor(userWithId(150))
                        .isFalseFor(null)
                    .withArguments(150, null)
                        .isFalseFor(userWithId(150))
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().number(mapper).notBetween(startInclusive, endInclusive)")
    public void whereNumberNotBetween(PredicateContext<User, Integer> context, Integer startInclusive, Integer endInclusive) {
        scenario(context, where().number(User::id).notBetween(startInclusive, endInclusive));
    }

    static Stream<Arguments> whereNumberNotBetween() {
        return PredicateTest.<User, Number>builder()
                .predicate("where().number(User::id).notBetween($argument1, $argument2)")
                .withMapper(User::id)
                    .withArguments(200, 300)
                        .isTrueFor(userWithId(150))
                    .withArguments(null, 100)
                        .isTrueFor(userWithId(150))
                    .withArguments(100, 200)
                        .isFalseFor(userWithId(150))
                    .withArguments(100, null)
                        .isTrueFor(userWithId(150))
                    .withArguments(150, 150)
                        .isFalseFor(userWithId(150))
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().number(mapper).isEven()")
    public void whereNumberIsEven(PredicateContext<User, Number> context) {
        scenario(context, where().number(User::id).isEven());
    }

    static Stream<Arguments> whereNumberIsEven() {
        return PredicateTest.<User, Number>builder()
                .predicate("where().number(User::id).isEven()")
                .withMapper(User::id)
                    .isTrueFor(userWithId(2))
                    .isFalseFor(userWithId(1))
                    .isFalseFor(userWithId(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().number(mapper).isOdd()")
    public void whereNumberIsOdd(PredicateContext<User, Number> context) {
        scenario(context, where().number(User::id).isOdd());
    }

    static Stream<Arguments> whereNumberIsOdd() {
        return PredicateTest.<User, Number>builder()
                .predicate("where().number(User::id).isOdd()")
                .withMapper(User::id)
                    .isTrueFor(userWithId(1))
                    .isFalseFor(userWithId(2))
                    .isFalseFor(userWithId(null))
                    .isFalseFor(null)
                .toStream();
    }
}