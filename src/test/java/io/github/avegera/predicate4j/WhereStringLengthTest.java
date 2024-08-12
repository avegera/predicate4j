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
import static io.github.avegera.predicate4j.test.model.User.userWithName;

@Where("where")
@Type("string length")
@Description("Build predicates with single string length condition")
public class WhereStringLengthTest extends PredicateScenarioTest<User>{

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().string(mapper).length().isEqualTo(value)")
    public void whereStringLengthIsEqualTo(PredicateContext<User, String> context, Integer length) {
        scenario(context, where().string(context.mapper()).length().isEqualTo(length));
    }

    static Stream<Arguments> whereStringLengthIsEqualTo() {
        return PredicateTest.<User, String>builder()
                .predicate("where().string(mapper).length().isEqualTo($argument1)")
                .withMapper(User::name)
                    .withArgument(4)
                        .isTrueFor(userWithName("John"))
                    .withArgument(3)
                        .isFalseFor(userWithName("John"))
                    .withArgument(0)
                        .isTrueFor(userWithName(""))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isFalseFor(userWithName("John"))
                        .isFalseFor(null)
                .withMapper(null)
                    .withArgument(5)
                        .isFalseFor(userWithName("John"))
                        .isFalseFor(userWithName(""))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isFalseFor(userWithName("John"))
                        .isFalseFor(userWithName(""))
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().string(mapper).length().isGreaterThan(value)")
    public void whereStringLengthIsGreaterThan(PredicateContext<User, String> context, Integer length) {
        scenario(context, where().string(context.mapper()).length().isGreaterThan(length));
    }

    static Stream<Arguments> whereStringLengthIsGreaterThan() {
        return PredicateTest.<User, String>builder()
                .predicate("where().string(mapper).length().isGreaterThan($argument1)")
                .withMapper(User::name)
                    .withArgument(3)
                        .isTrueFor(userWithName("John"))
                    .withArgument(4)
                        .isFalseFor(userWithName("John"))
                    .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().string(mapper).length().isGreaterThanOrEqualTo(value)")
    public void whereStringLengthIsGreaterThanOrEqualTo(PredicateContext<User, String> context, Integer length) {
        scenario(context, where().string(context.mapper()).length().isGreaterThanOrEqualTo(length));
    }

    static Stream<Arguments> whereStringLengthIsGreaterThanOrEqualTo() {
        return PredicateTest.<User, String>builder()
                .predicate("where().string(mapper).length().isGreaterThanOrEqualTo($argument1)")
                .withMapper(User::name)
                    .withArgument(4)
                        .isTrueFor(userWithName("John"))
                    .withArgument(3)
                        .isTrueFor(userWithName("John"))
                    .withArgument(5)
                        .isFalseFor(userWithName("John"))
                    .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().string(mapper).length().isLessThan(value)")
    public void whereStringLengthIsLessThan(PredicateContext<User, String> context, Integer length) {
        scenario(context, where().string(context.mapper()).length().isLessThan(length));
    }

    static Stream<Arguments> whereStringLengthIsLessThan() {
        return PredicateTest.<User, String>builder()
                .predicate("where().string(mapper).length().isLessThan($argument1)")
                .withMapper(User::name)
                    .withArgument(5)
                        .isTrueFor(userWithName("John"))
                    .withArgument(4)
                        .isFalseFor(userWithName("John"))
                    .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().string(mapper).length().isLessThanOrEqualTo(value)")
    public void whereStringLengthIsLessThanOrEqualTo(PredicateContext<User, String> context, Integer length) {
        scenario(context, where().string(context.mapper()).length().isLessThanOrEqualTo(length));
    }

    static Stream<Arguments> whereStringLengthIsLessThanOrEqualTo() {
        return PredicateTest.<User, String>builder()
                .predicate("where().string(mapper).length().isLessThanOrEqualTo($argument1)")
                .withMapper(User::name)
                    .withArgument(4)
                        .isTrueFor(userWithName("John"))
                    .withArgument(5)
                        .isTrueFor(userWithName("John"))
                    .withArgument(3)
                        .isFalseFor(userWithName("John"))
                    .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().string(mapper).length().isBetween(startInclusive, endInclusive)")
    public void whereStringLengthIsBetween(PredicateContext<User, String> context, Integer startInclusive, Integer endInclusive) {
        scenario(context, where().string(context.mapper()).length().isBetween(startInclusive, endInclusive));
    }

    static Stream<Arguments> whereStringLengthIsBetween() {
        return PredicateTest.<User, String>builder()
                .predicate("where().string(mapper).length().isBetween($argument1, $argument2)")
                .withMapper(User::name)
                    .withArguments(3, 5)
                        .isTrueFor(userWithName("John"))
                    .withArguments(4, 4)
                        .isTrueFor(userWithName("John"))
                    .withArguments(5, 6)
                        .isFalseFor(userWithName("John"))
                    .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().string(mapper).length().notBetween(startInclusive, endInclusive)")
    public void whereStringLengthNotBetween(PredicateContext<User, String> context, Integer startInclusive, Integer endInclusive) {
        scenario(context, where().string(context.mapper()).length().notBetween(startInclusive, endInclusive));
    }

    static Stream<Arguments> whereStringLengthNotBetween() {
        return PredicateTest.<User, String>builder()
                .predicate("where().string(mapper).length().notBetween($argument1, $argument2)")
                .withMapper(User::name)
                    .withArguments(5, 6)
                        .isTrueFor(userWithName("John"))
                    .withArguments(3, 4)
                        .isFalseFor(userWithName("John"))
                    .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().string(mapper).length().isEven()")
    public void whereStringLengthIsEven(PredicateContext<User, String> context) {
        scenario(context, where().string(context.mapper()).length().isEven());
    }

    static Stream<Arguments> whereStringLengthIsEven() {
        return PredicateTest.<User, String>builder()
                .predicate("where().string(mapper).length().isEven()")
                .withMapper(User::name)
                    .isTrueFor(userWithName("John")) // length 4
                    .isFalseFor(userWithName("Doe")) // length 3
                    .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().string(mapper).length().isOdd()")
    public void whereStringLengthIsOdd(PredicateContext<User, String> context) {
        scenario(context, where().string(context.mapper()).length().isOdd());
    }

    static Stream<Arguments> whereStringLengthIsOdd() {
        return PredicateTest.<User, String>builder()
                .predicate("where().string(mapper).length().isOdd()")
                .withMapper(User::name)
                    .isTrueFor(userWithName("Doe")) // length 3
                    .isFalseFor(userWithName("John")) // length 4
                    .toStream();
    }
}