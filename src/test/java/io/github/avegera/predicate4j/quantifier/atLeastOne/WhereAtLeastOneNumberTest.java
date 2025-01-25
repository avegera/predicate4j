package io.github.avegera.predicate4j.quantifier.atLeastOne;

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
import static io.github.avegera.predicate4j.test.model.User.userWithBalances;
import static io.github.avegera.predicate4j.test.util.ListWithNullableIterator.listWithNullableIterator;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@Where("where")
@Type("number")
@Quantifier("atLeastOne")
@Description("Test predicates under where().atLeastOne().number(...)")
public class WhereAtLeastOneNumberTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeastOne().number(User::balances).isGreaterThan($argument1)")
    public <N extends Number & Comparable<N>> void atLeastOneNumberIsGreaterThan(PredicateContext<User, Iterable<N>> context, N value) {
        scenario(context, where().atLeastOne().number(context.mapper()).isGreaterThan(value));
    }

    static Stream<Arguments> atLeastOneNumberIsGreaterThan() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().atLeastOne().number(User::balances).isGreaterThan($argument1)")
                .withMapper(User::balances)
                    .withArgument(50)
                        .isTrueFor(userWithBalances(asList(100, 200, 30)))
                        .isTrueFor(userWithBalances(singletonList(51)))
                        .isFalseFor(userWithBalances(asList(10, 20, 30)))
                        .isFalseFor(userWithBalances(asList(50, null, 40)))
                        .isFalseFor(userWithBalances(singletonList(null)))
                        .isFalseFor(userWithBalances(listWithNullableIterator()))
                        .isFalseFor(userWithBalances(emptyList()))
                        .isFalseFor(userWithBalances(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isFalseFor(userWithBalances(asList(100, 200, 300)))
                        .isFalseFor(userWithBalances(singletonList(100)))
                        .isFalseFor(userWithBalances(singletonList(null)))
                        .isFalseFor(userWithBalances(listWithNullableIterator()))
                        .isFalseFor(userWithBalances(emptyList()))
                        .isFalseFor(userWithBalances(null))
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeastOne().number(User::balances).isGreaterThanOrEqualTo($argument1)")
    public <N extends Number & Comparable<N>> void atLeastOneNumberIsGreaterThanOrEqualTo(PredicateContext<User, Iterable<N>> context, N value) {
        scenario(context, where().atLeastOne().number(context.mapper()).isGreaterThanOrEqualTo(value));
    }

    static Stream<Arguments> atLeastOneNumberIsGreaterThanOrEqualTo() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().atLeastOne().number(User::balances).isGreaterThanOrEqualTo($argument1)")
                .withMapper(User::balances)
                    .withArgument(50)
                        .isTrueFor(userWithBalances(asList(50, 100, 30)))
                        .isTrueFor(userWithBalances(singletonList(50)))
                        .isTrueFor(userWithBalances(asList(100, 200, 300)))
                        .isFalseFor(userWithBalances(asList(10, 20, 30)))
                        .isFalseFor(userWithBalances(asList(49, null, 30)))
                        .isFalseFor(userWithBalances(singletonList(null)))
                        .isFalseFor(userWithBalances(listWithNullableIterator()))
                        .isFalseFor(userWithBalances(emptyList()))
                        .isFalseFor(userWithBalances(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isFalseFor(userWithBalances(asList(100, 200, 300)))
                        .isFalseFor(userWithBalances(singletonList(100)))
                        .isFalseFor(userWithBalances(singletonList(null)))
                        .isFalseFor(userWithBalances(listWithNullableIterator()))
                        .isFalseFor(userWithBalances(emptyList()))
                        .isFalseFor(userWithBalances(null))
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeastOne().number(User::balances).isLessThan($argument1)")
    public <N extends Number & Comparable<N>> void atLeastOneNumberIsLessThan(PredicateContext<User, Iterable<N>> context, N value) {
        scenario(context, where().atLeastOne().number(context.mapper()).isLessThan(value));
    }

    static Stream<Arguments> atLeastOneNumberIsLessThan() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().atLeastOne().number(User::balances).isLessThan($argument1)")
                .withMapper(User::balances)
                    .withArgument(100)
                        .isTrueFor(userWithBalances(asList(50, 150, 200)))
                        .isTrueFor(userWithBalances(singletonList(99)))
                        .isFalseFor(userWithBalances(asList(100, 150, 200)))
                        .isFalseFor(userWithBalances(asList(150, 200, 300)))
                        .isFalseFor(userWithBalances(asList(100, null, 200)))
                        .isFalseFor(userWithBalances(singletonList(null)))
                        .isFalseFor(userWithBalances(listWithNullableIterator()))
                        .isFalseFor(userWithBalances(emptyList()))
                        .isFalseFor(userWithBalances(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isFalseFor(userWithBalances(asList(50, 75, 99)))
                        .isFalseFor(userWithBalances(singletonList(50)))
                        .isFalseFor(userWithBalances(singletonList(null)))
                        .isFalseFor(userWithBalances(listWithNullableIterator()))
                        .isFalseFor(userWithBalances(emptyList()))
                        .isFalseFor(userWithBalances(null))
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeastOne().number(User::balances).isLessThanOrEqualTo($argument1)")
    public <N extends Number & Comparable<N>> void atLeastOneNumberIsLessThanOrEqualTo(PredicateContext<User, Iterable<N>> context, N value) {
        scenario(context, where().atLeastOne().number(context.mapper()).isLessThanOrEqualTo(value));
    }

    static Stream<Arguments> atLeastOneNumberIsLessThanOrEqualTo() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().atLeastOne().number(User::balances).isLessThanOrEqualTo($argument1)")
                .withMapper(User::balances)
                    .withArgument(100)
                        .isTrueFor(userWithBalances(asList(50, 100, 200)))
                        .isTrueFor(userWithBalances(asList(150, null, 50)))
                        .isTrueFor(userWithBalances(singletonList(100)))
                        .isFalseFor(userWithBalances(asList(150, 200, 300)))
                        .isFalseFor(userWithBalances(singletonList(null)))
                        .isFalseFor(userWithBalances(listWithNullableIterator()))
                        .isFalseFor(userWithBalances(emptyList()))
                        .isFalseFor(userWithBalances(null))
                        .isFalseFor(null)
                    .withArgument(null)
                        .isFalseFor(userWithBalances(asList(50, 100, 200)))
                        .isFalseFor(userWithBalances(singletonList(100)))
                        .isFalseFor(userWithBalances(singletonList(null)))
                        .isFalseFor(userWithBalances(listWithNullableIterator()))
                        .isFalseFor(userWithBalances(emptyList()))
                        .isFalseFor(userWithBalances(null))
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeastOne().number(User::balances).isBetween($argument1, $argument2)")
    public <N extends Number & Comparable<N>> void atLeastOneNumberIsBetween(PredicateContext<User, Iterable<N>> context, N lower, N upper) {
        scenario(context, where().atLeastOne().number(context.mapper()).isBetween(lower, upper));
    }

    static Stream<Arguments> atLeastOneNumberIsBetween() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().atLeastOne().number(User::balances).isBetween($argument1, $argument2)")
                .withMapper(User::balances)
                    .withArguments(50, 150)
                        .isTrueFor(userWithBalances(asList(25, 75, 200)))
                        .isTrueFor(userWithBalances(asList(100, 150)))
                        .isTrueFor(userWithBalances(singletonList(50)))
                        .isFalseFor(userWithBalances(asList(25, 200, 300)))
                        .isFalseFor(userWithBalances(asList(null, 200, 300)))
                        .isFalseFor(userWithBalances(listWithNullableIterator()))
                        .isFalseFor(userWithBalances(emptyList()))
                        .isFalseFor(userWithBalances(null))
                        .isFalseFor(null)
                    .withArguments(null, null)
                        .isFalseFor(userWithBalances(singletonList(50)))
                        .isFalseFor(userWithBalances(singletonList(null)))
                        .isFalseFor(userWithBalances(listWithNullableIterator()))
                        .isFalseFor(userWithBalances(emptyList()))
                        .isFalseFor(userWithBalances(null))
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeastOne().number(User::balances).notBetween($argument1, $argument2)")
    public <N extends Number & Comparable<N>> void atLeastOneNumberNotBetween(PredicateContext<User, Iterable<N>> context, N lower, N upper) {
        scenario(context, where().atLeastOne().number(context.mapper()).notBetween(lower, upper));
    }

    static Stream<Arguments> atLeastOneNumberNotBetween() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().atLeastOne().number(User::balances).notBetween($argument1, $argument2)")
                .withMapper(User::balances)
                    .withArguments(50, 150)
                        .isTrueFor(userWithBalances(asList(25, 200, 300)))
                        .isTrueFor(userWithBalances(asList(null, 25, 300)))
                        .isTrueFor(userWithBalances(singletonList(200)))
                        .isFalseFor(userWithBalances(asList(50, 75, 150)))
                        .isFalseFor(userWithBalances(singletonList(100)))
                        .isFalseFor(userWithBalances(listWithNullableIterator()))
                        .isFalseFor(userWithBalances(emptyList()))
                        .isFalseFor(userWithBalances(null))
                        .isFalseFor(null)
                    .withArguments(null, null)
                        .isTrueFor(userWithBalances(asList(25, 100, 150)))
                        .isTrueFor(userWithBalances(singletonList(25)))
                        .isTrueFor(userWithBalances(singletonList(null)))
                        .isFalseFor(userWithBalances(listWithNullableIterator()))
                        .isFalseFor(userWithBalances(emptyList()))
                        .isFalseFor(userWithBalances(null))
                        .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeastOne().number(User::balances).isEven()")
    public <N extends Number & Comparable<N>> void atLeastOneNumberIsEven(PredicateContext<User, Iterable<N>> context) {
        scenario(context, where().atLeastOne().number(context.mapper()).isEven());
    }

    static Stream<Arguments> atLeastOneNumberIsEven() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().atLeastOne().number(User::balances).isEven()")
                .withMapper(User::balances)
                    .isTrueFor(userWithBalances(asList(1, 2, 3)))
                    .isTrueFor(userWithBalances(asList(null, 2, 5)))
                    .isTrueFor(userWithBalances(singletonList(4)))
                    .isFalseFor(userWithBalances(asList(1, 3, 5)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeastOne().number(User::balances).isOdd()")
    public <N extends Number & Comparable<N>> void atLeastOneNumberIsOdd(PredicateContext<User, Iterable<N>> context) {
        scenario(context, where().atLeastOne().number(context.mapper()).isOdd());
    }

    static Stream<Arguments> atLeastOneNumberIsOdd() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().atLeastOne().number(User::balances).isOdd()")
                .withMapper(User::balances)
                    .isTrueFor(userWithBalances(asList(2, 3, 4)))
                    .isTrueFor(userWithBalances(asList(null, 3, 6)))
                    .isTrueFor(userWithBalances(singletonList(5)))
                    .isFalseFor(userWithBalances(asList(2, 4, 6)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @Logical("conjunction")
    @CaseAs(provider = FirstArgument.class)
    @As("Logical conjunction of 3 predicates with atLeastOne().number(User::balances)")
    public void whereAtLeastOneNumberConjunction(PredicateContext<User, Iterable<Integer>> context) {
        scenario(context, where().atLeastOne().number(context.mapper()).isGreaterThan(10)
                .and().atLeastOne().number(context.mapper()).isLessThan(100)
                .and().atLeastOne().number(context.mapper()).isEven());
    }

    static Stream<Arguments> whereAtLeastOneNumberConjunction() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().atLeastOne().number(User::balances).greaterThan(10)\n" +
                        "       .and().atLeastOne().number(User::balances).lessThan(100)\n" +
                        "       .and().atLeastOne().number(User::balances).isEven()")
                .withMapper(User::balances)
                .isTrueFor(userWithBalances(asList(12, 5, 200, 8))) // At least one number satisfies all predicates
                .isTrueFor(userWithBalances(singletonList(14))) // Single number satisfies all predicates
                .isFalseFor(userWithBalances(asList(5, 105, 201, 7))) // No number satisfies all predicates
                .isFalseFor(userWithBalances(asList(11, 25, 99))) // No even number greater than 10 and less than 100
                .isFalseFor(userWithBalances(listWithNullableIterator())) // Empty list does not satisfy at least one condition
                .isFalseFor(userWithBalances(emptyList())) // Empty list does not satisfy at least one condition
                .isFalseFor(userWithBalances(null)) // Balances are null
                .isFalseFor(null)
                .toStream();
    }
}