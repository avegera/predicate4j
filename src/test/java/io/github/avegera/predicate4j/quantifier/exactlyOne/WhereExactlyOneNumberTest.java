package io.github.avegera.predicate4j.quantifier.exactlyOne;

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
@Quantifier("exactlyOne")
@Description("Test predicates under where().exactlyOne().number(...)")
public class WhereExactlyOneNumberTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().exactlyOne().number(User::balances).isGreaterThan($argument1)")
    public <N extends Number & Comparable<N>> void exactlyOneNumberIsGreaterThan(PredicateContext<User, Iterable<N>> context, N value) {
        scenario(context, where().exactlyOne().number(context.mapper()).isGreaterThan(value));
    }

    static Stream<Arguments> exactlyOneNumberIsGreaterThan() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().exactlyOne().number(User::balances).isGreaterThan($argument1)")
                .withMapper(User::balances)
                .withArgument(50)
                    .isTrueFor(userWithBalances(asList(51, 50, 40)))
                    .isTrueFor(userWithBalances(singletonList(51)))
                    .isFalseFor(userWithBalances(asList(100, 200, 300)))
                    .isFalseFor(userWithBalances(asList(50, 49, 40)))
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
    @As("where().exactlyOne().number(User::balances).isGreaterThanOrEqualTo($argument1)")
    public <N extends Number & Comparable<N>> void exactlyOneNumberIsGreaterThanOrEqualTo(PredicateContext<User, Iterable<N>> context, N value) {
        scenario(context, where().exactlyOne().number(context.mapper()).isGreaterThanOrEqualTo(value));
    }

    static Stream<Arguments> exactlyOneNumberIsGreaterThanOrEqualTo() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().exactlyOne().number(User::balances).isGreaterThanOrEqualTo($argument1)")
                .withMapper(User::balances)
                .withArgument(50)
                    .isTrueFor(userWithBalances(asList(50, 49, 40)))
                    .isTrueFor(userWithBalances(asList(50, null, 40)))
                    .isTrueFor(userWithBalances(singletonList(50)))
                    .isFalseFor(userWithBalances(asList(50, 100, 200)))
                    .isFalseFor(userWithBalances(asList(49, 48, 47)))
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
    @As("where().exactlyOne().number(User::balances).isLessThan($argument1)")
    public <N extends Number & Comparable<N>> void exactlyOneNumberIsLessThan(PredicateContext<User, Iterable<N>> context, N value) {
        scenario(context, where().exactlyOne().number(context.mapper()).isLessThan(value));
    }

    static Stream<Arguments> exactlyOneNumberIsLessThan() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().exactlyOne().number(User::balances).isLessThan($argument1)")
                .withMapper(User::balances)
                .withArgument(100)
                    .isTrueFor(userWithBalances(asList(50, 100, 150)))
                    .isTrueFor(userWithBalances(asList(100, null, 50)))
                    .isTrueFor(userWithBalances(singletonList(99)))
                    .isFalseFor(userWithBalances(asList(50, 75, 99)))
                    .isFalseFor(userWithBalances(asList(100, 150, 200)))
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
    @As("where().exactlyOne().number(User::balances).isLessThanOrEqualTo($argument1)")
    public <N extends Number & Comparable<N>> void exactlyOneNumberIsLessThanOrEqualTo(PredicateContext<User, Iterable<N>> context, N value) {
        scenario(context, where().exactlyOne().number(context.mapper()).isLessThanOrEqualTo(value));
    }

    static Stream<Arguments> exactlyOneNumberIsLessThanOrEqualTo() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().exactlyOne().number(User::balances).isLessThanOrEqualTo($argument1)")
                .withMapper(User::balances)
                .withArgument(100)
                    .isTrueFor(userWithBalances(asList(100, 200, 300)))
                    .isTrueFor(userWithBalances(singletonList(100)))
                    .isFalseFor(userWithBalances(asList(50, 75, 100)))
                    .isFalseFor(userWithBalances(asList(50, 100, 150)))
                    .isFalseFor(userWithBalances(asList(101, 200, 300)))
                    .isFalseFor(userWithBalances(asList(100, null, 50)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isFalseFor(userWithBalances(asList(50, 75, 100)))
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
    @As("where().exactlyOne().number(User::balances).isBetween($argument1, $argument2)")
    public <N extends Number & Comparable<N>> void exactlyOneNumberIsBetween(PredicateContext<User, Iterable<N>> context, N lower, N upper) {
        scenario(context, where().exactlyOne().number(context.mapper()).isBetween(lower, upper));
    }

    static Stream<Arguments> exactlyOneNumberIsBetween() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().exactlyOne().number(User::balances).isBetween($argument1, $argument2)")
                .withMapper(User::balances)
                .withArguments(50, 150)
                    .isTrueFor(userWithBalances(asList(100, 200, 300)))
                    .isTrueFor(userWithBalances(asList(25, 50, 200)))
                    .isTrueFor(userWithBalances(singletonList(100)))
                    .isFalseFor(userWithBalances(asList(50, 100, 150)))
                    .isFalseFor(userWithBalances(asList(50, null, 150)))
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
    @As("where().exactlyOne().number(User::balances).notBetween($argument1, $argument2)")
    public <N extends Number & Comparable<N>> void exactlyOneNumberNotBetween(PredicateContext<User, Iterable<N>> context, N lower, N upper) {
        scenario(context, where().exactlyOne().number(context.mapper()).notBetween(lower, upper));
    }

    static Stream<Arguments> exactlyOneNumberNotBetween() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().exactlyOne().number(User::balances).notBetween($argument1, $argument2)")
                .withMapper(User::balances)
                .withArguments(50, 150)
                    .isTrueFor(userWithBalances(asList(50, null, 150)))
                    .isTrueFor(userWithBalances(singletonList(25)))
                    .isFalseFor(userWithBalances(asList(50, 75, 150)))
                    .isFalseFor(userWithBalances(asList(25, 200, 300)))
                    .isFalseFor(userWithBalances(singletonList(100)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArguments(null, null)
                    .isTrueFor(userWithBalances(singletonList(50)))
                    .isTrueFor(userWithBalances(singletonList(null)))
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
    @As("where().exactlyOne().number(User::balances).isEven()")
    public <N extends Number & Comparable<N>> void exactlyOneNumberIsEven(PredicateContext<User, Iterable<N>> context) {
        scenario(context, where().exactlyOne().number(context.mapper()).isEven());
    }

    static Stream<Arguments> exactlyOneNumberIsEven() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().exactlyOne().number(User::balances).isEven()")
                .withMapper(User::balances)
                    .isTrueFor(userWithBalances(asList(2, 3, 5)))
                    .isTrueFor(userWithBalances(singletonList(4)))
                    .isFalseFor(userWithBalances(asList(2, 4, 6)))
                    .isFalseFor(userWithBalances(asList(1, 3, 5)))
                    .isFalseFor(userWithBalances(asList(2, null, 4)))
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
    @As("where().exactlyOne().number(User::balances).isOdd()")
    public <N extends Number & Comparable<N>> void exactlyOneNumberIsOdd(PredicateContext<User, Iterable<N>> context) {
        scenario(context, where().exactlyOne().number(context.mapper()).isOdd());
    }

    static Stream<Arguments> exactlyOneNumberIsOdd() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().exactlyOne().number(User::balances).isOdd()")
                .withMapper(User::balances)
                    .isTrueFor(userWithBalances(asList(3, 4, 6)))
                    .isTrueFor(userWithBalances(singletonList(5)))
                    .isFalseFor(userWithBalances(asList(1, 3, 5)))
                    .isFalseFor(userWithBalances(asList(2, 4, 6)))
                    .isFalseFor(userWithBalances(asList(3, null, 5)))
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
    @As("Logical conjunction of 3 predicates with exactlyOne().number(User::balances)")
    public void whereExactlyOneNumberConjunction(PredicateContext<User, Iterable<Integer>> context) {
        scenario(context, where().exactlyOne().number(context.mapper()).isBetween(50, 100)
                .and().exactlyOne().number(context.mapper()).isOdd()
                .and().exactlyOne().number(context.mapper()).isNull());
    }

    static Stream<Arguments> whereExactlyOneNumberConjunction() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().exactlyOne().number(User::balances).isBetween(50, 100)\n" +
                           "       .and().exactlyOne().number(User::balances).isOdd()\n" +
                           "       .and().exactlyOne().number(User::balances).isNull()")
                .withMapper(User::balances)
                    .isTrueFor(userWithBalances(asList(76, 3, null))) // Exactly one number between 50-100, one odd, and one null
                    .isTrueFor(userWithBalances(asList(60, null, 11))) // Exactly one number satisfies each condition
                    .isFalseFor(userWithBalances(asList(75, 99, null))) // Two numbers between 50-100
                    .isFalseFor(userWithBalances(asList(51, 3, 5))) // Two odd numbers
                    .isFalseFor(userWithBalances(asList(75, null, null))) // Two null values
                    .isFalseFor(userWithBalances(singletonList(null))) // Only null
                    .isFalseFor(userWithBalances(asList(3, 5, 7))) // No number between 50-100
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(emptyList())) // Empty list
                    .isFalseFor(userWithBalances(null)) // Balances are null
                    .isFalseFor(null) // User is null
                .toStream();
    }
}