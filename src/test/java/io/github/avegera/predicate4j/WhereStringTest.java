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
@Type("string")
@Description("Test predicates for string properties")
public class WhereStringTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().string(mapper).isEmpty()")
    public void whereStringIsEmpty(PredicateContext<User, String> context) {
        scenario(context, where().string(context.mapper()).isEmpty());
    }

    static Stream<Arguments> whereStringIsEmpty() {
        return PredicateTest.<User, String>builder()
                .predicate("where().string(mapper).isEmpty()")
                .withMapper(User::name)
                    .isTrueFor(userWithName(""))
                    .isTrueFor(userWithName(null))
                    .isFalseFor(userWithName("John"))
                    .isFalseFor(null)
                .withMapper(null)
                    .isFalseFor(userWithName("John"))
                    .isFalseFor(userWithName(""))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().string(mapper).notEmpty()")
    public void whereStringNotEmpty(PredicateContext<User, String> context) {
        scenario(context, where().string(context.mapper()).notEmpty());
    }

    static Stream<Arguments> whereStringNotEmpty() {
        return PredicateTest.<User, String>builder()
                .predicate("where().string(mapper).notEmpty()")
                .withMapper(User::name)
                    .isTrueFor(userWithName("John"))
                    .isFalseFor(userWithName(""))
                    .isFalseFor(userWithName(null))
                    .isFalseFor(null)
                .withMapper(null)
                    .isFalseFor(userWithName("John"))
                    .isFalseFor(userWithName(""))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().string(mapper).contains(value)")
    public void whereStringContains(PredicateContext<User, String> context, String substring) {
        scenario(context, where().string(context.mapper()).contains(substring));
    }

    static Stream<Arguments> whereStringContains() {
        return PredicateTest.<User, String>builder()
                .predicate("where().string(mapper).contains($argument1)")
                .withMapper(User::name)
                    .withArgument("oh")
                        .isTrueFor(userWithName("John"))
                    .withArgument("Doe")
                        .isFalseFor(userWithName("John"))
                    .withArgument(null)
                        .isFalseFor(userWithName("John"))
                    .withArgument(null)
                        .isFalseFor(null)
                .withMapper(null)
                    .withArgument("John")
                        .isFalseFor(userWithName("John"))
                    .withArgument("")
                        .isFalseFor(userWithName("John"))
                    .withArgument(null)
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().string(mapper).notContains(value)")
    public void whereStringNotContains(PredicateContext<User, String> context, String substring) {
        scenario(context, where().string(context.mapper()).notContains(substring));
    }

    static Stream<Arguments> whereStringNotContains() {
        return PredicateTest.<User, String>builder()
                .predicate("where().string(mapper).notContains($argument1)")
                .withMapper(User::name)
                    .withArgument("oh")
                        .isFalseFor(userWithName("John"))
                    .withArgument("Doe")
                        .isTrueFor(userWithName("John"))
                    .withArgument(null)
                        .isTrueFor(userWithName("John"))
                        .isFalseFor(null)
                .withMapper(null)
                    .withArgument("John")
                        .isFalseFor(userWithName("John"))
                    .withArgument("")
                        .isFalseFor(userWithName("John"))
                    .withArgument(null)
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().string(mapper).startsWith(value)")
    public void whereStringStartsWith(PredicateContext<User, String> context, String prefix) {
        scenario(context, where().string(context.mapper()).startsWith(prefix));
    }

    static Stream<Arguments> whereStringStartsWith() {
        return PredicateTest.<User, String>builder()
                .predicate("where().string(mapper).startsWith($argument1)")
                .withMapper(User::name)
                    .withArgument("Jo")
                        .isTrueFor(userWithName("John"))
                    .withArgument("Do")
                        .isFalseFor(userWithName("John"))
                    .withArgument(null)
                        .isFalseFor(userWithName("John"))
                    .withArgument(null)
                        .isFalseFor(null)
                .withMapper(null)
                    .withArgument("John")
                        .isFalseFor(userWithName("John"))
                    .withArgument("")
                        .isFalseFor(userWithName("John"))
                    .withArgument(null)
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().string(mapper).notStartsWith(value)")
    public void whereStringNotStartsWith(PredicateContext<User, String> context, String prefix) {
        scenario(context, where().string(context.mapper()).notStartsWith(prefix));
    }

    static Stream<Arguments> whereStringNotStartsWith() {
        return PredicateTest.<User, String>builder()
                .predicate("where().string(mapper).notStartsWith($argument1)")
                .withMapper(User::name)
                    .withArgument("Jo")
                        .isFalseFor(userWithName("John"))
                    .withArgument("Do")
                        .isTrueFor(userWithName("John"))
                    .withArgument(null)
                        .isTrueFor(userWithName("John"))
                        .isFalseFor(null)
                .withMapper(null)
                    .withArgument("John")
                        .isFalseFor(userWithName("John"))
                    .withArgument("")
                        .isFalseFor(userWithName("John"))
                    .withArgument(null)
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().string(mapper).endsWith(value)")
    public void whereStringEndsWith(PredicateContext<User, String> context, String suffix) {
        scenario(context, where().string(context.mapper()).endsWith(suffix));
    }

    static Stream<Arguments> whereStringEndsWith() {
        return PredicateTest.<User, String>builder()
                .predicate("where().string(mapper).endsWith($argument1)")
                .withMapper(User::name)
                    .withArgument("hn")
                        .isTrueFor(userWithName("John"))
                    .withArgument("Doe")
                        .isFalseFor(userWithName("John"))
                    .withArgument(null)
                        .isFalseFor(userWithName("John"))
                    .withArgument(null)
                        .isFalseFor(null)
                .withMapper(null)
                    .withArgument("John")
                        .isFalseFor(userWithName("John"))
                    .withArgument("")
                        .isFalseFor(userWithName("John"))
                    .withArgument(null)
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().string(mapper).notEndsWith(value)")
    public void whereStringNotEndsWith(PredicateContext<User, String> context, String suffix) {
        scenario(context, where().string(context.mapper()).notEndsWith(suffix));
    }

    static Stream<Arguments> whereStringNotEndsWith() {
        return PredicateTest.<User, String>builder()
                .predicate("where().string(mapper).notEndsWith($argument1)")
                .withMapper(User::name)
                    .withArgument("hn")
                        .isFalseFor(userWithName("John"))
                    .withArgument("Doe")
                        .isTrueFor(userWithName("John"))
                    .withArgument(null)
                        .isTrueFor(userWithName("John"))
                        .isFalseFor(null)
                .withMapper(null)
                    .withArgument("John")
                        .isFalseFor(userWithName("John"))
                    .withArgument("")
                        .isFalseFor(userWithName("John"))
                    .withArgument(null)
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().string(mapper).matches(regex)")
    public void whereStringMatches(PredicateContext<User, String> context, String regex) {
        scenario(context, where().string(context.mapper()).matches(regex));
    }

    static Stream<Arguments> whereStringMatches() {
        return PredicateTest.<User, String>builder()
                .predicate("where().string(mapper).matches($argument1)")
                .withMapper(User::name)
                    .withArgument("J.*n")
                        .isTrueFor(userWithName("John"))
                    .withArgument("D.*e")
                        .isFalseFor(userWithName("John"))
                    .withArgument(null)
                        .isFalseFor(userWithName("John"))
                    .withArgument(null)
                        .isFalseFor(null)
                .withMapper(null)
                    .withArgument("John")
                        .isFalseFor(userWithName("John"))
                    .withArgument("")
                        .isFalseFor(userWithName("John"))
                    .withArgument(null)
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().string(mapper).notMatches(regex)")
    public void whereStringNotMatches(PredicateContext<User, String> context, String regex) {
        scenario(context, where().string(context.mapper()).notMatches(regex));
    }

    static Stream<Arguments> whereStringNotMatches() {
        return PredicateTest.<User, String>builder()
                .predicate("where().string(mapper).notMatches($argument1)")
                .withMapper(User::name)
                    .withArgument("J.*n")
                        .isFalseFor(userWithName("John"))
                    .withArgument("D.*e")
                        .isTrueFor(userWithName("John"))
                    .withArgument(null)
                        .isTrueFor(userWithName("John"))
                        .isFalseFor(null)
                .withMapper(null)
                    .withArgument("John")
                        .isFalseFor(userWithName("John"))
                    .withArgument("")
                        .isFalseFor(userWithName("John"))
                    .withArgument(null)
                        .isFalseFor(null)
                .toStream();
    }
}