package io.github.avegera.predicate4j.quantifier.atLeast;

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
import static io.github.avegera.predicate4j.test.model.User.userWithFlags;
import static io.github.avegera.predicate4j.test.util.ListWithNullableIterator.listWithNullableIterator;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@Where("where")
@Type("number")
@Quantifier("atLeast")
@Description("Test predicates under where().atLeast().number(...)")
public class WhereAtLeastNumberTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeast(2).number(User::balances).isGreaterThan($argument1)")
    public <N extends Number & Comparable<N>> void atLeastTwoNumberIsGreaterThan(PredicateContext<User, Iterable<N>> context, N value) {
        scenario(context, where().atLeast(2).number(context.mapper()).isGreaterThan(value));
    }

    static Stream<Arguments> atLeastTwoNumberIsGreaterThan() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().atLeast(2).number(User::balances).isGreaterThan($argument1)")
                .withMapper(User::balances)
                    .withArgument(50)
                        .isTrueFor(userWithBalances(asList(100, 60, 30)))
                        .isTrueFor(userWithBalances(asList(100, 200, 30)))
                        .isFalseFor(userWithBalances(asList(10, 20, 30)))
                        .isFalseFor(userWithBalances(asList(50, 40, null)))
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
    @As("where().atLeast(2).number(User::balances).isGreaterThanOrEqualTo($argument1)")
    public <N extends Number & Comparable<N>> void atLeastTwoNumberIsGreaterThanOrEqualTo(PredicateContext<User, Iterable<N>> context, N value) {
        scenario(context, where().atLeast(2).number(context.mapper()).isGreaterThanOrEqualTo(value));
    }

    static Stream<Arguments> atLeastTwoNumberIsGreaterThanOrEqualTo() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().atLeast(2).number(User::balances).isGreaterThanOrEqualTo($argument1)")
                .withMapper(User::balances)
                .withArgument(50)
                    .isTrueFor(userWithBalances(asList(100, 50, 30)))
                    .isTrueFor(userWithBalances(asList(50, 50, 200)))
                    .isTrueFor(userWithBalances(asList(50, 60, null)))
                    .isFalseFor(userWithBalances(asList(10, 20, 30)))
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
    @As("where().atLeast(2).number(User::balances).isLessThan($argument1)")
    public <N extends Number & Comparable<N>> void atLeastTwoNumberIsLessThan(PredicateContext<User, Iterable<N>> context, N value) {
        scenario(context, where().atLeast(2).number(context.mapper()).isLessThan(value));
    }

    static Stream<Arguments> atLeastTwoNumberIsLessThan() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().atLeast(2).number(User::balances).isLessThan($argument1)")
                .withMapper(User::balances)
                .withArgument(100)
                    .isTrueFor(userWithBalances(asList(50, 75, 150)))
                    .isTrueFor(userWithBalances(asList(10, 20, 300)))
                    .isFalseFor(userWithBalances(asList(99, 100, 200)))
                    .isFalseFor(userWithBalances(asList(150, 200, 300)))
                    .isFalseFor(userWithBalances(singletonList(50)))
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
    @As("where().atLeast(2).number(User::balances).isLessThanOrEqualTo($argument1)")
    public <N extends Number & Comparable<N>> void atLeastTwoNumberIsLessThanOrEqualTo(PredicateContext<User, Iterable<N>> context, N value) {
        scenario(context, where().atLeast(2).number(context.mapper()).isLessThanOrEqualTo(value));
    }

    static Stream<Arguments> atLeastTwoNumberIsLessThanOrEqualTo() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().atLeast(2).number(User::balances).isLessThanOrEqualTo($argument1)")
                .withMapper(User::balances)
                .withArgument(100)
                    .isTrueFor(userWithBalances(asList(50, 100, 150)))
                    .isTrueFor(userWithBalances(asList(100, 100, 200)))
                    .isTrueFor(userWithBalances(asList(10, 20, 300)))
                    .isFalseFor(userWithBalances(asList(150, 200, 300)))
                    .isFalseFor(userWithBalances(singletonList(100)))
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
    @As("where().atLeast(2).number(User::balances).isBetween($argument1, $argument2)")
    public <N extends Number & Comparable<N>> void atLeastTwoNumberIsBetween(PredicateContext<User, Iterable<N>> context, N lower, N upper) {
        scenario(context, where().atLeast(2).number(context.mapper()).isBetween(lower, upper));
    }

    static Stream<Arguments> atLeastTwoNumberIsBetween() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().atLeast(2).number(User::balances).isBetween($argument1, $argument2)")
                .withMapper(User::balances)
                .withArguments(50, 150)
                    .isTrueFor(userWithBalances(asList(60, 70, 160)))
                    .isTrueFor(userWithBalances(asList(100, 150, 200)))
                    .isTrueFor(userWithBalances(asList(50, 75, 125)))
                    .isFalseFor(userWithBalances(asList(160, 200, 300)))
                    .isFalseFor(userWithBalances(singletonList(100)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArguments(null, null)
                    .isFalseFor(userWithBalances(asList(50, 100, 150)))
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
    @As("where().atLeast(2).number(User::balances).notBetween($argument1, $argument2)")
    public <N extends Number & Comparable<N>> void atLeastTwoNumberNotBetween(PredicateContext<User, Iterable<N>> context, N lower, N upper) {
        scenario(context, where().atLeast(2).number(context.mapper()).notBetween(lower, upper));
    }

    static Stream<Arguments> atLeastTwoNumberNotBetween() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().atLeast(2).number(User::balances).notBetween($argument1, $argument2)")
                .withMapper(User::balances)
                .withArguments(50, 150)
                    .isTrueFor(userWithBalances(asList(40, 160, 200)))
                    .isTrueFor(userWithBalances(asList(null, 30, 170)))
                    .isTrueFor(userWithBalances(asList(10, 20, 50)))
                    .isFalseFor(userWithBalances(asList(60, 70, 80)))
                    .isFalseFor(userWithBalances(singletonList(100)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArguments(null, null)
                    .isTrueFor(userWithBalances(asList(40, 60, 80)))
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
    @As("where().atLeast(2).number(User::balances).isEven()")
    public <N extends Number & Comparable<N>> void atLeastTwoNumberIsEven(PredicateContext<User, Iterable<N>> context) {
        scenario(context, where().atLeast(2).number(context.mapper()).isEven());
    }

    static Stream<Arguments> atLeastTwoNumberIsEven() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().atLeast(2).number(User::balances).isEven()")
                .withMapper(User::balances)
                    .isTrueFor(userWithBalances(asList(2, 4, 5)))
                    .isTrueFor(userWithBalances(asList(6, 8, null)))
                    .isTrueFor(userWithBalances(asList(0, 10, 3)))
                    .isFalseFor(userWithBalances(asList(1, 3, 5)))
                    .isFalseFor(userWithBalances(singletonList(2)))
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
    @As("where().atLeast(2).number(User::balances).isOdd()")
    public <N extends Number & Comparable<N>> void atLeastTwoNumberIsOdd(PredicateContext<User, Iterable<N>> context) {
        scenario(context, where().atLeast(2).number(context.mapper()).isOdd());
    }

    static Stream<Arguments> atLeastTwoNumberIsOdd() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().atLeast(2).number(User::balances).isOdd()")
                .withMapper(User::balances)
                    .isTrueFor(userWithBalances(asList(1, 3, 4)))
                    .isTrueFor(userWithBalances(asList(7, 9, null)))
                    .isTrueFor(userWithBalances(asList(5, 11, 2)))
                    .isFalseFor(userWithBalances(asList(2, 4, 6)))
                    .isFalseFor(userWithBalances(singletonList(3)))
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
    @As("Logical conjunction of 3 predicates with atLeast(2).number(User::balances)")
    public void whereAtLeastTwoNumberConjunction(PredicateContext<User, Iterable<Integer>> context) {
        scenario(context, where().atLeast(2).number(context.mapper()).isGreaterThan(10)
                .and().atLeast(2).number(context.mapper()).isLessThan(100)
                .and().atLeast(2).number(context.mapper()).isEven());
    }

    static Stream<Arguments> whereAtLeastTwoNumberConjunction() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().atLeast(2).number(User::balances).isGreaterThan(10)\n" +
                        "       .and().atLeast(2).number(User::balances).isLessThan(100)\n" +
                        "       .and().atLeast(2).number(User::balances).isEven()")
                .withMapper(User::balances)
                    .isTrueFor(userWithBalances(asList(12, 14, 200, 8))) // At least two numbers satisfy all predicates
                    .isTrueFor(userWithBalances(asList(16, 18, 22))) // All numbers satisfy all predicates
                    .isFalseFor(userWithBalances(asList(5, 105, 7, 11))) // No two numbers satisfy all predicates
                    .isFalseFor(userWithBalances(asList(9, 21, 33))) // No even number greater than 10 and less than 100
                    .isFalseFor(userWithFlags(listWithNullableIterator())) // List with nullable iterator
                    .isFalseFor(userWithBalances(emptyList())) // Empty list does not satisfy at least two condition
                    .isFalseFor(userWithBalances(null)) // Balances are null
                    .isFalseFor(null)
                .toStream();
    }
}