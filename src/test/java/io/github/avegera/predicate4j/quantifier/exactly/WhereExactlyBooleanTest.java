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
import static io.github.avegera.predicate4j.test.model.User.userWithFlags;
import static io.github.avegera.predicate4j.test.util.ListWithNullableIterator.listWithNullableIterator;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@Where("where")
@Type("boolean")
@Quantifier("exactly")
@Description("Test predicates under where().exactly().booleanValue(...)")
public class WhereExactlyBooleanTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().exactly(2).booleanValue(User::flags).isTrue()")
    public void exactlyTwoBooleanIsTrue(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().exactly(2).booleanValue(context.mapper()).isTrue());
    }

    static Stream<Arguments> exactlyTwoBooleanIsTrue() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().exactly(2).booleanValue(User::flags).isTrue()")
                .withMapper(User::flags)
                    .isTrueFor(userWithFlags(asList(true, true, false)))
                    .isTrueFor(userWithFlags(asList(true, false, true)))
                    .isFalseFor(userWithFlags(asList(true, true, true)))
                    .isFalseFor(userWithFlags(asList(false, false, false)))
                    .isFalseFor(userWithFlags(singletonList(true)))
                    .isFalseFor(userWithFlags(singletonList(null)))
                    .isFalseFor(userWithFlags(listWithNullableIterator()))
                    .isFalseFor(userWithFlags(emptyList()))
                    .isFalseFor(userWithFlags(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().exactly(2).booleanValue(User::flags).notTrue()")
    public void exactlyTwoBooleanNotTrue(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().exactly(2).booleanValue(context.mapper()).notTrue());
    }

    static Stream<Arguments> exactlyTwoBooleanNotTrue() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().exactly(2).booleanValue(User::flags).notTrue()")
                .withMapper(User::flags)
                    .isTrueFor(userWithFlags(asList(false, false, true)))
                    .isTrueFor(userWithFlags(asList(false, null, true)))
                    .isFalseFor(userWithFlags(asList(false, false, false)))
                    .isFalseFor(userWithFlags(asList(true, true, true)))
                    .isFalseFor(userWithFlags(asList(false, null, null)))
                    .isFalseFor(userWithFlags(singletonList(false)))
                    .isFalseFor(userWithFlags(singletonList(null)))
                    .isFalseFor(userWithFlags(listWithNullableIterator()))
                    .isFalseFor(userWithFlags(emptyList()))
                    .isFalseFor(userWithFlags(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().exactly(2).booleanValue(User::flags).isFalse()")
    public void exactlyTwoBooleanIsFalse(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().exactly(2).booleanValue(context.mapper()).isFalse());
    }

    static Stream<Arguments> exactlyTwoBooleanIsFalse() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().exactly(2).booleanValue(User::flags).isFalse()")
                .withMapper(User::flags)
                    .isTrueFor(userWithFlags(asList(false, false, true)))
                    .isTrueFor(userWithFlags(asList(false, true, false)))
                    .isFalseFor(userWithFlags(asList(false, false, false)))
                    .isFalseFor(userWithFlags(asList(true, true, true)))
                    .isFalseFor(userWithFlags(singletonList(false)))
                    .isFalseFor(userWithFlags(singletonList(null)))
                    .isFalseFor(userWithFlags(listWithNullableIterator()))
                    .isFalseFor(userWithFlags(emptyList()))
                    .isFalseFor(userWithFlags(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().exactly(2).booleanValue(User::flags).notFalse()")
    public void exactlyTwoBooleanNotFalse(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().exactly(2).booleanValue(context.mapper()).notFalse());
    }

    static Stream<Arguments> exactlyTwoBooleanNotFalse() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().exactly(2).booleanValue(User::flags).notFalse()")
                .withMapper(User::flags)
                    .isTrueFor(userWithFlags(asList(true, null, false)))
                    .isTrueFor(userWithFlags(asList(null, null, false)))
                    .isTrueFor(userWithFlags(asList(true, true, false)))
                    .isFalseFor(userWithFlags(asList(true, false, false)))
                    .isFalseFor(userWithFlags(asList(false, false, false)))
                    .isFalseFor(userWithFlags(singletonList(null)))
                    .isFalseFor(userWithFlags(singletonList(true)))
                    .isFalseFor(userWithFlags(listWithNullableIterator()))
                    .isFalseFor(userWithFlags(emptyList()))
                    .isFalseFor(userWithFlags(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @Logical("conjunction")
    @CaseAs(provider = FirstArgument.class)
    @As("Logical conjunction of 2 predicates with exactly(2) and exactly(3).booleanValue(User::flags)")
    public void exactlyTwoAndThreeBooleanConjunction(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().exactly(2).booleanValue(User::flags).isTrue()
                .and().exactly(3).booleanValue(User::flags).isNull());
    }

    static Stream<Arguments> exactlyTwoAndThreeBooleanConjunction() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().exactly(2).booleanValue(User::flags).isTrue()\n" +
                        "       .and().exactly(3).booleanValue(User::flags).isNull()")
                .isTrueFor(userWithFlags(asList(true, true, null, null, null, false))) // 2 true flags and 3 null flags
                .isTrueFor(userWithFlags(asList(true, true, null, null, null))) // 2 true flags and 3 null flags
                .isFalseFor(userWithFlags(asList(true, false, false, null, null, null))) // Less than 2 true flags
                .isFalseFor(userWithFlags(asList(true, true, null, null))) // Less than 3 null flags
                .isFalseFor(userWithFlags(asList(true, true, true, null, null, null))) // More than 2 true flags
                .isFalseFor(userWithFlags(singletonList(true))) // Less than 2 true flags
                .isFalseFor(userWithFlags(singletonList(null))) // Less than 3 null flags
                .isFalseFor(userWithFlags(listWithNullableIterator())) // List with nullable iterator
                .isFalseFor(userWithFlags(emptyList())) // Empty list
                .isFalseFor(userWithFlags(null)) // Flags are null
                .isFalseFor(null) // User is null
                .toStream();
    }
}