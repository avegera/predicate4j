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

import java.util.List;
import java.util.stream.Stream;

import static io.github.avegera.predicate4j.Where.where;
import static io.github.avegera.predicate4j.test.model.User.userWithRoles;
import static java.util.Arrays.asList;

@Where("where")
@Type("list size")
@Description("Build predicates with single list size condition")
public class WhereListSizeTest extends PredicateScenarioTest<User> {

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
                    .withArgument(3)
                        .isTrueFor(userWithRoles(asList("item1", "item2", "item3")))
                    .withArgument(2)
                        .isFalseFor(userWithRoles(asList("item1", "item2", "item3")))
                    .withArgument(0)
                        .isTrueFor(userWithRoles(null))
                    .withArgument(null)
                        .isFalseFor(userWithRoles(asList("item1", "item2")))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                .withMapper(null)
                    .withArgument(3)
                        .isFalseFor(userWithRoles(asList("item1", "item2", "item3")))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isFalseFor(userWithRoles(asList("item1", "item2")))
                        .isFalseFor(userWithRoles(null))
                        .isFalseFor(null)
                    .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().list(mapper).size().isGreaterThan(value)")
    public void whereListSizeIsGreaterThan(PredicateContext<User, List<String>> context, Integer size) {
        scenario(context, where().list(context.mapper()).size().isGreaterThan(size));
    }

    static Stream<Arguments> whereListSizeIsGreaterThan() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().list(mapper).size().isGreaterThan($argument1)")
                .withMapper(User::roles)
                    .withArgument(2)
                        .isTrueFor(userWithRoles(asList("item1", "item2", "item3")))
                    .withArgument(3)
                        .isFalseFor(userWithRoles(asList("item1", "item2", "item3")))
                    .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().list(mapper).size().isGreaterThanOrEqualTo(value)")
    public void whereListSizeIsGreaterThanOrEqualTo(PredicateContext<User, List<String>> context, Integer size) {
        scenario(context, where().list(context.mapper()).size().isGreaterThanOrEqualTo(size));
    }

    static Stream<Arguments> whereListSizeIsGreaterThanOrEqualTo() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().list(mapper).size().isGreaterThanOrEqualTo($argument1)")
                .withMapper(User::roles)
                    .withArgument(3)
                        .isTrueFor(userWithRoles(asList("item1", "item2", "item3")))
                    .withArgument(2)
                        .isTrueFor(userWithRoles(asList("item1", "item2", "item3")))
                    .withArgument(4)
                        .isFalseFor(userWithRoles(asList("item1", "item2", "item3")))
                    .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().list(mapper).size().isLessThan(value)")
    public void whereListSizeIsLessThan(PredicateContext<User, List<String>> context, Integer size) {
        scenario(context, where().list(context.mapper()).size().isLessThan(size));
    }

    static Stream<Arguments> whereListSizeIsLessThan() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().list(mapper).size().isLessThan($argument1)")
                .withMapper(User::roles)
                    .withArgument(4)
                        .isTrueFor(userWithRoles(asList("item1", "item2", "item3")))
                    .withArgument(3)
                        .isFalseFor(userWithRoles(asList("item1", "item2", "item3")))
                    .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().list(mapper).size().isLessThanOrEqualTo(value)")
    public void whereListSizeIsLessThanOrEqualTo(PredicateContext<User, List<String>> context, Integer size) {
        scenario(context, where().list(context.mapper()).size().isLessThanOrEqualTo(size));
    }

    static Stream<Arguments> whereListSizeIsLessThanOrEqualTo() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().list(mapper).size().isLessThanOrEqualTo($argument1)")
                .withMapper(User::roles)
                    .withArgument(3)
                        .isTrueFor(userWithRoles(asList("item1", "item2", "item3")))
                    .withArgument(4)
                        .isTrueFor(userWithRoles(asList("item1", "item2", "item3")))
                    .withArgument(2)
                        .isFalseFor(userWithRoles(asList("item1", "item2", "item3")))
                    .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().list(mapper).size().isBetween(startInclusive, endInclusive)")
    public void whereListSizeIsBetween(PredicateContext<User, List<String>> context, Integer startInclusive, Integer endInclusive) {
        scenario(context, where().list(context.mapper()).size().isBetween(startInclusive, endInclusive));
    }

    static Stream<Arguments> whereListSizeIsBetween() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().list(mapper).size().isBetween($argument1, $argument2)")
                .withMapper(User::roles)
                    .withArguments(2, 4)
                        .isTrueFor(userWithRoles(asList("item1", "item2", "item3")))
                    .withArguments(3, 3)
                        .isTrueFor(userWithRoles(asList("item1", "item2", "item3")))
                    .withArguments(4, 5)
                        .isFalseFor(userWithRoles(asList("item1", "item2", "item3")))
                    .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().list(mapper).size().notBetween(startInclusive, endInclusive)")
    public void whereListSizeNotBetween(PredicateContext<User, List<String>> context, Integer startInclusive, Integer endInclusive) {
        scenario(context, where().list(context.mapper()).size().notBetween(startInclusive, endInclusive));
    }

    static Stream<Arguments> whereListSizeNotBetween() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().list(mapper).size().notBetween($argument1, $argument2)")
                .withMapper(User::roles)
                    .withArguments(4, 5)
                        .isTrueFor(userWithRoles(asList("item1", "item2", "item3")))
                    .withArguments(2, 3)
                        .isFalseFor(userWithRoles(asList("item1", "item2", "item3")))
                    .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().list(mapper).size().isEven()")
    public void whereListSizeIsEven(PredicateContext<User, List<String>> context) {
        scenario(context, where().list(context.mapper()).size().isEven());
    }

    static Stream<Arguments> whereListSizeIsEven() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().list(mapper).size().isEven()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(asList("item1", "item2", "item3", "item4"))) // size 4
                    .isFalseFor(userWithRoles(asList("item1", "item2", "item3"))) // size 3
                    .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().list(mapper).size().isOdd()")
    public void whereListSizeIsOdd(PredicateContext<User, List<String>> context) {
        scenario(context, where().list(context.mapper()).size().isOdd());
    }

    static Stream<Arguments> whereListSizeIsOdd() {
        return PredicateTest.<User, List<String>>builder()
                .predicate("where().list(mapper).size().isOdd()")
                .withMapper(User::roles)
                    .isTrueFor(userWithRoles(asList("item1", "item2", "item3"))) // size 3
                    .isFalseFor(userWithRoles(asList("item1", "item2", "item3", "item4"))) // size 4
                    .toStream();
    }
}