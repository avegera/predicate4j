package io.github.avegera.predicate4j.quantifier.none;

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
@Quantifier("none")
@Description("Test predicates under where().none().number(...)")
public class WhereNoneNumberTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().none().number(User::balances).isGreaterThan($argument1)")
    public <N extends Number & Comparable<N>> void noneNumberIsGreaterThan(PredicateContext<User, Iterable<N>> context, N value) {
        scenario(context, where().none().number(context.mapper()).isGreaterThan(value));
    }

    static Stream<Arguments> noneNumberIsGreaterThan() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().none().number(User::balances).isGreaterThan($argument1)")
                .withMapper(User::balances)
                .withArgument(50)
                    .isTrueFor(userWithBalances(asList(10, 20, 30)))
                    .isTrueFor(userWithBalances(singletonList(40)))
                    .isTrueFor(userWithBalances(asList(50, null, 40)))
                    .isTrueFor(userWithBalances(singletonList(null)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(100, 200, 30)))
                    .isFalseFor(userWithBalances(singletonList(51)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithBalances(emptyList()))
                    .isTrueFor(userWithBalances(asList(100, 200, 300)))
                    .isTrueFor(userWithBalances(singletonList(100)))
                    .isTrueFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().none().number(User::balances).isGreaterThanOrEqualTo($argument1)")
    public <N extends Number & Comparable<N>> void noneNumberIsGreaterThanOrEqualTo(PredicateContext<User, Iterable<N>> context, N value) {
        scenario(context, where().none().number(context.mapper()).isGreaterThanOrEqualTo(value));
    }

    static Stream<Arguments> noneNumberIsGreaterThanOrEqualTo() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().none().number(User::balances).isGreaterThanOrEqualTo($argument1)")
                .withMapper(User::balances)
                .withArgument(50)
                    .isTrueFor(userWithBalances(asList(10, 20, 30)))
                    .isTrueFor(userWithBalances(singletonList(40)))
                    .isTrueFor(userWithBalances(asList(49, null, 30)))
                    .isTrueFor(userWithBalances(singletonList(null)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(50, 100, 30)))
                    .isFalseFor(userWithBalances(singletonList(50)))
                    .isFalseFor(userWithBalances(asList(100, 200, 300)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithBalances(asList(50, 100, 200)))
                    .isTrueFor(userWithBalances(singletonList(50)))
                    .isTrueFor(userWithBalances(singletonList(null)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().none().number(User::balances).isLessThan($argument1)")
    public <N extends Number & Comparable<N>> void noneNumberIsLessThan(PredicateContext<User, Iterable<N>> context, N value) {
        scenario(context, where().none().number(context.mapper()).isLessThan(value));
    }

    static Stream<Arguments> noneNumberIsLessThan() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().none().number(User::balances).isLessThan($argument1)")
                .withMapper(User::balances)
                .withArgument(100)
                    .isTrueFor(userWithBalances(asList(100, 150, 200)))
                    .isTrueFor(userWithBalances(asList(150, null, 200)))
                    .isTrueFor(userWithBalances(singletonList(200)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(50, 150, 200)))
                    .isFalseFor(userWithBalances(singletonList(99)))
                    .isFalseFor(userWithBalances(asList(100, null, 50)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithBalances(asList(50, 75, 99)))
                    .isTrueFor(userWithBalances(singletonList(50)))
                    .isTrueFor(userWithBalances(singletonList(null)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().none().number(User::balances).isLessThanOrEqualTo($argument1)")
    public <N extends Number & Comparable<N>> void noneNumberIsLessThanOrEqualTo(PredicateContext<User, Iterable<N>> context, N value) {
        scenario(context, where().none().number(context.mapper()).isLessThanOrEqualTo(value));
    }

    static Stream<Arguments> noneNumberIsLessThanOrEqualTo() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().none().number(User::balances).isLessThanOrEqualTo($argument1)")
                .withMapper(User::balances)
                .withArgument(100)
                    .isTrueFor(userWithBalances(asList(150, 200, 300)))
                    .isTrueFor(userWithBalances(singletonList(150)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(50, 100, 150)))
                    .isFalseFor(userWithBalances(asList(100, null, 150)))
                    .isFalseFor(userWithBalances(singletonList(100)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithBalances(asList(50, 100, 150)))
                    .isTrueFor(userWithBalances(singletonList(100)))
                    .isTrueFor(userWithBalances(singletonList(null)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().none().number(User::balances).isBetween($argument1, $argument2)")
    public <N extends Number & Comparable<N>> void noneNumberIsBetween(PredicateContext<User, Iterable<N>> context, N lower, N upper) {
        scenario(context, where().none().number(context.mapper()).isBetween(lower, upper));
    }

    static Stream<Arguments> noneNumberIsBetween() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().none().number(User::balances).isBetween($argument1, $argument2)")
                .withMapper(User::balances)
                .withArguments(50, 150)
                    .isTrueFor(userWithBalances(asList(25, 200, 300)))
                    .isTrueFor(userWithBalances(singletonList(200)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(50, 100, 150)))
                    .isFalseFor(userWithBalances(asList(75, null, 150)))
                    .isFalseFor(userWithBalances(singletonList(100)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArguments(null, null)
                    .isTrueFor(userWithBalances(asList(25, 200, 300)))
                    .isTrueFor(userWithBalances(singletonList(200)))
                    .isTrueFor(userWithBalances(singletonList(null)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().none().number(User::balances).notBetween($argument1, $argument2)")
    public <N extends Number & Comparable<N>> void noneNumberNotBetween(PredicateContext<User, Iterable<N>> context, N lower, N upper) {
        scenario(context, where().none().number(context.mapper()).notBetween(lower, upper));
    }

    static Stream<Arguments> noneNumberNotBetween() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().none().number(User::balances).notBetween($argument1, $argument2)")
                .withMapper(User::balances)
                .withArguments(50, 150)
                    .isTrueFor(userWithBalances(asList(75, 100, 125)))
                    .isTrueFor(userWithBalances(singletonList(100)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(25, 200, 300)))
                    .isFalseFor(userWithBalances(asList(null, 25, 200)))
                    .isFalseFor(userWithBalances(singletonList(200)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArguments(null, null)
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(50, 75, 100)))
                    .isFalseFor(userWithBalances(singletonList(50)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().none().number(User::balances).isEven()")
    public <N extends Number & Comparable<N>> void noneNumberIsEven(PredicateContext<User, Iterable<N>> context) {
        scenario(context, where().none().number(context.mapper()).isEven());
    }

    static Stream<Arguments> noneNumberIsEven() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().none().number(User::balances).isEven()")
                .withMapper(User::balances)
                    .isTrueFor(userWithBalances(asList(1, 3, 5)))
                    .isTrueFor(userWithBalances(singletonList(7)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(2, 3, 5)))
                    .isFalseFor(userWithBalances(asList(null, 4, 5)))
                    .isFalseFor(userWithBalances(singletonList(6)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().none().number(User::balances).isOdd()")
    public <N extends Number & Comparable<N>> void noneNumberIsOdd(PredicateContext<User, Iterable<N>> context) {
        scenario(context, where().none().number(context.mapper()).isOdd());
    }

    static Stream<Arguments> noneNumberIsOdd() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().none().number(User::balances).isOdd()")
                .withMapper(User::balances)
                    .isTrueFor(userWithBalances(asList(2, 4, 6)))
                    .isTrueFor(userWithBalances(singletonList(8)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(1, 2, 4)))
                    .isFalseFor(userWithBalances(asList(null, 3, 6)))
                    .isFalseFor(userWithBalances(singletonList(7)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @Logical("conjunction")
    @CaseAs(provider = FirstArgument.class)
    @As("Logical conjunction of 3 predicates with none().number(User::balances)")
    public void whereNoneNumberConjunction(PredicateContext<User, Iterable<Integer>> context) {
        scenario(context, where().none().number(context.mapper()).isLessThan(10)
                .and().none().number(context.mapper()).isGreaterThan(100)
                .and().none().number(context.mapper()).isEven());
    }

    static Stream<Arguments> whereNoneNumberConjunction() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().none().number(User::balances).isLessThan(10)\n" +
                        "       .and().none().number(User::balances).isGreaterThan(100)\n" +
                        "       .and().none().number(User::balances).isEven()")
                .withMapper(User::balances)
                    .isTrueFor(userWithBalances(asList(11, 15, 17))) // All numbers are < 100 and > 10, none is even
                    .isTrueFor(userWithBalances(singletonList(51))) // Single number < 100 and > 10 and odd
                    .isTrueFor(userWithBalances(emptyList())) // Empty list satisfies all predicates
                    .isFalseFor(userWithBalances(asList(12, 200, 8))) // Some numbers violate predicates
                    .isFalseFor(userWithBalances(asList(15, 50, 105))) // Some numbers < 100
                    .isFalseFor(userWithBalances(asList(10, 20, 30))) // Some even numbers
                    .isFalseFor(userWithBalances(listWithNullableIterator())) // List with nullable iterator
                    .isFalseFor(userWithBalances(null)) // Balances are null
                    .isFalseFor(null)
                .toStream();
    }
}