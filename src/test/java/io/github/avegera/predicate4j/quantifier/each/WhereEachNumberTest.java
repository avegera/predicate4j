package io.github.avegera.predicate4j.quantifier.each;

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
@Quantifier("each")
@Description("Test predicates under where().each().number(...)")
public class WhereEachNumberTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().each().number(User::balances).isGreaterThan($argument1)")
    public <N extends Number & Comparable<N>> void eachNumberIsGreaterThan(PredicateContext<User, Iterable<N>> context, N value) {
        scenario(context, where().each().number(context.mapper()).isGreaterThan(value));
    }

    static Stream<Arguments> eachNumberIsGreaterThan() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().each().number(User::balances).isGreaterThan($argument1)")
                .withMapper(User::balances)
                .withArgument(50)
                    .isTrueFor(userWithBalances(asList(100, 200, 300)))
                    .isTrueFor(userWithBalances(singletonList(51)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(50, 100, 200)))
                    .isFalseFor(userWithBalances(asList(25, 50, 75)))
                    .isFalseFor(userWithBalances(asList(100, null, 200)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(100, 200, 300)))
                    .isFalseFor(userWithBalances(singletonList(100)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().each().number(User::balances).isGreaterThanOrEqualTo($argument1)")
    public <N extends Number & Comparable<N>> void eachNumberIsGreaterThanOrEqualTo(PredicateContext<User, Iterable<N>> context, N value) {
        scenario(context, where().each().number(context.mapper()).isGreaterThanOrEqualTo(value));
    }

    static Stream<Arguments> eachNumberIsGreaterThanOrEqualTo() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().each().number(User::balances).isGreaterThanOrEqualTo($argument1)")
                .withMapper(User::balances)
                .withArgument(50)
                    .isTrueFor(userWithBalances(asList(50, 100, 200)))
                    .isTrueFor(userWithBalances(singletonList(50)))
                    .isTrueFor(userWithBalances(asList(100, 200, 300)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(25, 50, 75)))
                    .isFalseFor(userWithBalances(asList(49, null, 50)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(100, 200, 300)))
                    .isFalseFor(userWithBalances(singletonList(100)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().each().number(User::balances).isLessThan($argument1)")
    public <N extends Number & Comparable<N>> void eachNumberIsLessThan(PredicateContext<User, Iterable<N>> context, N value) {
        scenario(context, where().each().number(context.mapper()).isLessThan(value));
    }

    static Stream<Arguments> eachNumberIsLessThan() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().each().number(User::balances).isLessThan($argument1)")
                .withMapper(User::balances)
                .withArgument(100)
                    .isTrueFor(userWithBalances(asList(50, 75, 99)))
                    .isTrueFor(userWithBalances(singletonList(99)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(100, 200, 300)))
                    .isFalseFor(userWithBalances(asList(100, null, 50)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArgument(null)
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(50, 75, 99)))
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
    @As("where().each().number(User::balances).isLessThanOrEqualTo($argument1)")
    public <N extends Number & Comparable<N>> void eachNumberIsLessThanOrEqualTo(PredicateContext<User, Iterable<N>> context, N value) {
        scenario(context, where().each().number(context.mapper()).isLessThanOrEqualTo(value));
    }

    static Stream<Arguments> eachNumberIsLessThanOrEqualTo() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().each().number(User::balances).isLessThanOrEqualTo($argument1)")
                .withMapper(User::balances)
                .withArgument(100)
                    .isTrueFor(userWithBalances(asList(50, 75, 100)))
                    .isTrueFor(userWithBalances(singletonList(100)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(101, 200, 300)))
                    .isFalseFor(userWithBalances(asList(100, null, 50)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArgument(null)
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
    @As("where().each().number(User::balances).isBetween($argument1, $argument2)")
    public <N extends Number & Comparable<N>> void eachNumberIsBetween(PredicateContext<User, Iterable<N>> context, N lower, N upper) {
        scenario(context, where().each().number(context.mapper()).isBetween(lower, upper));
    }

    static Stream<Arguments> eachNumberIsBetween() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().each().number(User::balances).isBetween($argument1, $argument2)")
                .withMapper(User::balances)
                .withArguments(50, 150)
                    .isTrueFor(userWithBalances(asList(50, 75, 150)))
                    .isTrueFor(userWithBalances(asList(100, 150)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(25, 50, 200)))
                    .isFalseFor(userWithBalances(asList(50, null, 150)))
                    .isFalseFor(userWithBalances(singletonList(200)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArguments(null, null)
                .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(50, 100, 150)))
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
    @As("where().each().number(User::balances).notBetween($argument1, $argument2)")
    public <N extends Number & Comparable<N>> void eachNumberNotBetween(PredicateContext<User, Iterable<N>> context, N lower, N upper) {
        scenario(context, where().each().number(context.mapper()).notBetween(lower, upper));
    }

    static Stream<Arguments> eachNumberNotBetween() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().each().number(User::balances).notBetween($argument1, $argument2)")
                .withMapper(User::balances)
                .withArguments(50, 150)
                    .isTrueFor(userWithBalances(asList(25, 200, 250)))
                    .isTrueFor(userWithBalances(asList(null, 25, 200)))
                    .isTrueFor(userWithBalances(singletonList(200)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(50, 75, 150)))
                    .isFalseFor(userWithBalances(asList(50, null, 150)))
                    .isFalseFor(userWithBalances(singletonList(100)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .withArguments(null, null)
                    .isTrueFor(userWithBalances(asList(50, 100, 150)))
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
    @As("where().each().number(User::balances).isEven()")
    public <N extends Number & Comparable<N>> void eachNumberIsEven(PredicateContext<User, Iterable<N>> context) {
        scenario(context, where().each().number(context.mapper()).isEven());
    }

    static Stream<Arguments> eachNumberIsEven() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().each().number(User::balances).isEven()")
                .withMapper(User::balances)
                    .isTrueFor(userWithBalances(asList(2, 4, 6, 8)))
                    .isTrueFor(userWithBalances(singletonList(0)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(1, 3, 5, 7)))
                    .isFalseFor(userWithBalances(asList(2, 4, null, 6)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().each().number(User::balances).isOdd()")
    public <N extends Number & Comparable<N>> void eachNumberIsOdd(PredicateContext<User, Iterable<N>> context) {
        scenario(context, where().each().number(context.mapper()).isOdd());
    }

    static Stream<Arguments> eachNumberIsOdd() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().each().number(User::balances).isOdd()")
                .withMapper(User::balances)
                    .isTrueFor(userWithBalances(asList(1, 3, 5, 7)))
                    .isTrueFor(userWithBalances(singletonList(1)))
                    .isTrueFor(userWithBalances(emptyList()))
                    .isFalseFor(userWithBalances(asList(2, 4, 6, 8)))
                    .isFalseFor(userWithBalances(asList(1, 3, null, 5)))
                    .isFalseFor(userWithBalances(singletonList(null)))
                    .isFalseFor(userWithBalances(listWithNullableIterator()))
                    .isFalseFor(userWithBalances(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @Logical("conjunction")
    @CaseAs(provider = FirstArgument.class)
    @As("Logical conjunction of 3 predicates with each().number(User::balances)")
    public void whereEachNumberConjunction(PredicateContext<User, Iterable<Integer>> context) {
        scenario(context, where().each().number(context.mapper()).isGreaterThan(10)
                .and().each().number(context.mapper()).isLessThan(100)
                .and().each().number(context.mapper()).isEven());
    }

    static Stream<Arguments> whereEachNumberConjunction() {
        return PredicateTest.<User, List<Number>>builder()
                .predicate("where().each().number(User::balances).greaterThan(10)\n" +
                        "       .and().each().number(User::balances).lessThan(100)\n" +
                        "       .and().each().number(User::balances).isEven()")
                .withMapper(User::balances)
                    .isTrueFor(userWithBalances(asList(12, 14, 20, 50))) // All numbers satisfy the predicates
                    .isTrueFor(userWithBalances(emptyList())) // Empty list satisfies all predicates
                    .isFalseFor(userWithBalances(asList(8, 14, 20, 50))) // One number is not greater than 10
                    .isFalseFor(userWithBalances(asList(12, 14, 20, 150))) // One number is not less than 100
                    .isFalseFor(userWithBalances(asList(12, 14, 21, 50))) // One number is not even
                    .isFalseFor(userWithBalances(listWithNullableIterator())) // List with nullable iterator
                    .isFalseFor(userWithBalances(null)) // Balances are null
                    .isFalseFor(null)
                .toStream();
    }
}