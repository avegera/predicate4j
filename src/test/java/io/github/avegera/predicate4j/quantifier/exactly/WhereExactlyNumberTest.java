package io.github.avegera.predicate4j.quantifier.exactly;

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
@Quantifier("exactly")
@Description("Test predicates under where().exactly().number(...)")
public class WhereExactlyNumberTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().exactly(2).number(User::balances).isGreaterThan($argument1)")
    public <N extends Number & Comparable<N>> void exactlyTwoNumberIsGreaterThan(PredicateContext<User, Iterable<N>> context, N value) {
        scenario(context, where().exactly(2).number(context.mapper()).isGreaterThan(value));
    }

    static Stream<Arguments> exactlyTwoNumberIsGreaterThan() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().exactly(2).number(User::balances).isGreaterThan($argument1)")
                .withMapper(User::balances)
                .withArgument(50)
                    .isTrueFor(userWithBalances(asList(51, 60, 40)))
                    .isTrueFor(userWithBalances(asList(75, 55, 30)))
                    .isFalseFor(userWithBalances(asList(50, 49, 40)))
                    .isFalseFor(userWithBalances(asList(51, 60, 70)))
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
    @As("where().exactly(2).number(User::balances).isGreaterThanOrEqualTo($argument1)")
    public <N extends Number & Comparable<N>> void exactlyTwoNumberIsGreaterThanOrEqualTo(PredicateContext<User, Iterable<N>> context, N value) {
        scenario(context, where().exactly(2).number(context.mapper()).isGreaterThanOrEqualTo(value));
    }

    static Stream<Arguments> exactlyTwoNumberIsGreaterThanOrEqualTo() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().exactly(2).number(User::balances).isGreaterThanOrEqualTo($argument1)")
                .withMapper(User::balances)
                .withArgument(50)
                    .isTrueFor(userWithBalances(asList(50, 60, 40)))
                    .isTrueFor(userWithBalances(asList(50, 55, 30)))
                    .isFalseFor(userWithBalances(asList(49, 48, 40)))
                    .isFalseFor(userWithBalances(asList(51, 60, 70)))
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
    @As("where().exactly(2).number(User::balances).isLessThan($argument1)")
    public <N extends Number & Comparable<N>> void exactlyTwoNumberIsLessThan(PredicateContext<User, Iterable<N>> context, N value) {
        scenario(context, where().exactly(2).number(context.mapper()).isLessThan(value));
    }

    static Stream<Arguments> exactlyTwoNumberIsLessThan() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().exactly(2).number(User::balances).isLessThan($argument1)")
                .withMapper(User::balances)
                .withArgument(100)
                    .isTrueFor(userWithBalances(asList(50, 99, 150)))
                    .isTrueFor(userWithBalances(asList(50, 99, 150)))
                    .isFalseFor(userWithBalances(asList(50, 100, 150)))
                    .isFalseFor(userWithBalances(asList(101, 200, 300)))
                    .isFalseFor(userWithBalances(asList(99, null, 100)))
                    .isFalseFor(userWithBalances(asList(null, null, null)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isFalseFor(userWithBalances(asList(50, 99, 150)))
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
    @As("where().exactly(2).number(User::balances).isLessThanOrEqualTo($argument1)")
    public <N extends Number & Comparable<N>> void exactlyTwoNumberIsLessThanOrEqualTo(PredicateContext<User, Iterable<N>> context, N value) {
        scenario(context, where().exactly(2).number(context.mapper()).isLessThanOrEqualTo(value));
    }

    static Stream<Arguments> exactlyTwoNumberIsLessThanOrEqualTo() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().exactly(2).number(User::balances).isLessThanOrEqualTo($argument1)")
                .withMapper(User::balances)
                .withArgument(100)
                    .isTrueFor(userWithBalances(asList(50, 100, 150)))
                    .isTrueFor(userWithBalances(asList(100, null, 50)))
                    .isTrueFor(userWithBalances(asList(50, 99, 150)))
                    .isFalseFor(userWithBalances(asList(100, 200, 300)))
                    .isFalseFor(userWithBalances(asList(101, 102, 103)))
                    .isFalseFor(userWithBalances(asList(null, null, null)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isFalseFor(userWithBalances(asList(50, 100, 150)))
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
    @As("where().exactly(2).number(User::balances).isBetween($argument1, $argument2)")
    public <N extends Number & Comparable<N>> void exactlyTwoNumberIsBetween(PredicateContext<User, Iterable<N>> context, N lower, N upper) {
        scenario(context, where().exactly(2).number(context.mapper()).isBetween(lower, upper));
    }

    static Stream<Arguments> exactlyTwoNumberIsBetween() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().exactly(2).number(User::balances).isBetween($argument1, $argument2)")
                .withMapper(User::balances)
                .withArguments(50, 150)
                    .isTrueFor(userWithBalances(asList(100, 120, 300)))
                    .isTrueFor(userWithBalances(asList(50, 75, 200)))
                    .isTrueFor(userWithBalances(asList(50, 150, 250)))
                    .isTrueFor(userWithBalances(asList(50, null, 150)))
                    .isFalseFor(userWithBalances(asList(50, 60, 70)))
                    .isFalseFor(userWithBalances(asList(151, 200, 300)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArguments(null, null)
                    .isFalseFor(userWithBalances(asList(50, 100, 150)))
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
    @As("where().exactly(2).number(User::balances).notBetween($argument1, $argument2)")
    public <N extends Number & Comparable<N>> void exactlyTwoNumberNotBetween(PredicateContext<User, Iterable<N>> context, N lower, N upper) {
        scenario(context, where().exactly(2).number(context.mapper()).notBetween(lower, upper));
    }

    static Stream<Arguments> exactlyTwoNumberNotBetween() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().exactly(2).number(User::balances).notBetween($argument1, $argument2)")
                .withMapper(User::balances)
                .withArguments(50, 150)
                    .isTrueFor(userWithBalances(asList(40, 160, 100)))
                    .isTrueFor(userWithBalances(asList(30, 180, 75)))
                    .isTrueFor(userWithBalances(asList(45, 160, 50)))
                    .isFalseFor(userWithBalances(asList(50, 75, 100)))
                    .isFalseFor(userWithBalances(asList(50, 100, 150)))
                    .isFalseFor(userWithBalances(asList(50, null, 150)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArguments(null, null)
                    .isFalseFor(userWithBalances(asList(40, 180, 200)))
                    .isFalseFor(userWithBalances(singletonList(200)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(asList(50, 100, 150)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().exactly(2).number(User::balances).isEven()")
    public <N extends Number & Comparable<N>> void exactlyTwoNumberIsEven(PredicateContext<User, Iterable<N>> context) {
        scenario(context, where().exactly(2).number(context.mapper()).isEven());
    }

    static Stream<Arguments> exactlyTwoNumberIsEven() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().exactly(2).number(User::balances).isEven()")
                .withMapper(User::balances)
                    .isTrueFor(userWithBalances(asList(2, 4, 3)))
                    .isTrueFor(userWithBalances(asList(6, 8, 7)))
                    .isTrueFor(userWithBalances(asList(2, null, 4)))
                    .isTrueFor(userWithBalances(asList(10, 12)))
                    .isFalseFor(userWithBalances(asList(2, 4, 6)))
                    .isFalseFor(userWithBalances(asList(1, 3, 5)))
                    .isFalseFor(userWithBalances(singletonList(10)))
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
    @As("where().exactly(2).number(User::balances).isOdd()")
    public <N extends Number & Comparable<N>> void exactlyTwoNumberIsOdd(PredicateContext<User, Iterable<N>> context) {
        scenario(context, where().exactly(2).number(context.mapper()).isOdd());
    }

    static Stream<Arguments> exactlyTwoNumberIsOdd() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().exactly(2).number(User::balances).isOdd()")
                .withMapper(User::balances)
                    .isTrueFor(userWithBalances(asList(3, 5, 4)))
                    .isTrueFor(userWithBalances(asList(1, 7, 2)))
                    .isTrueFor(userWithBalances(asList(3, null, 5)))
                    .isTrueFor(userWithBalances(asList(9, 11)))
                    .isFalseFor(userWithBalances(asList(1, 3, 5)))
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
    @As("Logical conjunction of 3 predicates with exactly(2).number(User::balances)")
    public void exactlyTwoNumberConjunction(PredicateContext<User, Iterable<Integer>> context) {
        scenario(context, where().exactly(2).number(context.mapper()).isOdd()
                .and().exactly(2).number(context.mapper()).isEven()
                .and().exactly(2).number(context.mapper()).isNull());
    }

    static Stream<Arguments> exactlyTwoNumberConjunction() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().exactly(2).number(User::balances).isOdd()\n" +
                           "       .and().exactly(2).number(User::balances).isEven()\n" +
                           "       .and().exactly(2).number(User::balances).isNull()")
                .withMapper(User::balances)
                    .isTrueFor(userWithBalances(asList(1, 3, 2, 4, null, null))) // Two odd, two even, two null
                    .isFalseFor(userWithBalances(asList(1, 3, 5, 7, null, null))) // Four odd
                    .isFalseFor(userWithBalances(asList(2, 4, 6, 8, null, null))) // Four even
                    .isFalseFor(userWithBalances(asList(1, 3, 2, 4, null))) // Only one null
                    .isFalseFor(userWithBalances(singletonList(null))) // Only one null
                    .isFalseFor(userWithBalances(asList(1, 2, 3))) // No even or null
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(emptyList())) // Empty list
                    .isFalseFor(userWithBalances(null)) // Balances are null
                    .isFalseFor(null) // User is null
                .toStream();
    }
}