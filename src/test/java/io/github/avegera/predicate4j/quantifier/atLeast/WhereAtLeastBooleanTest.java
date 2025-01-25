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
import static io.github.avegera.predicate4j.test.model.User.userWithFlags;
import static io.github.avegera.predicate4j.test.util.ListWithNullableIterator.listWithNullableIterator;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@Where("where")
@Type("boolean")
@Quantifier("atLeast")
@Description("Test predicates under where().atLeast().booleanValue(...)")
public class WhereAtLeastBooleanTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeast(2).booleanValue(User::flags).isTrue()")
    public void atLeastTwoBooleanIsTrue(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().atLeast(2).booleanValue(context.mapper()).isTrue());
    }

    static Stream<Arguments> atLeastTwoBooleanIsTrue() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().atLeast(2).booleanValue(User::flags).isTrue()")
                .withMapper(User::flags)
                    .isTrueFor(userWithFlags(asList(true, true, false)))
                    .isTrueFor(userWithFlags(asList(true, true, null)))
                    .isTrueFor(userWithFlags(asList(true, true, true)))
                    .isFalseFor(userWithFlags(asList(true, false, false)))
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
    @As("where().atLeast(2).booleanValue(User::flags).notTrue()")
    public void atLeastTwoBooleanNotTrue(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().atLeast(2).booleanValue(context.mapper()).notTrue());
    }

    static Stream<Arguments> atLeastTwoBooleanNotTrue() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().atLeast(2).booleanValue(User::flags).notTrue()")
                .withMapper(User::flags)
                    .isTrueFor(userWithFlags(asList(false, false, true)))
                    .isTrueFor(userWithFlags(asList(false, null, false)))
                    .isTrueFor(userWithFlags(asList(false, null, null)))
                    .isTrueFor(userWithFlags(asList(false, false, false)))
                    .isFalseFor(userWithFlags(asList(true, true, false)))
                    .isFalseFor(userWithFlags(asList(true, true, true)))
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
    @As("where().atLeast(2).booleanValue(User::flags).isFalse()")
    public void atLeastTwoBooleanIsFalse(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().atLeast(2).booleanValue(context.mapper()).isFalse());
    }

    static Stream<Arguments> atLeastTwoBooleanIsFalse() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().atLeast(2).booleanValue(User::flags).isFalse()")
                .withMapper(User::flags)
                    .isTrueFor(userWithFlags(asList(false, false, true)))
                    .isTrueFor(userWithFlags(asList(false, false, null)))
                    .isTrueFor(userWithFlags(asList(false, false, false)))
                    .isFalseFor(userWithFlags(asList(true, false, null)))
                    .isFalseFor(userWithFlags(asList(true, true, false)))
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
    @As("where().atLeast(2).booleanValue(User::flags).notFalse()")
    public void atLeastTwoBooleanNotFalse(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().atLeast(2).booleanValue(context.mapper()).notFalse());
    }

    static Stream<Arguments> atLeastTwoBooleanNotFalse() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().atLeast(2).booleanValue(User::flags).notFalse()")
                .withMapper(User::flags)
                    .isTrueFor(userWithFlags(asList(true, true, false)))
                    .isTrueFor(userWithFlags(asList(true, true, null)))
                    .isTrueFor(userWithFlags(asList(true, true, true)))
                    .isTrueFor(userWithFlags(asList(false, true, null)))
                    .isFalseFor(userWithFlags(asList(false, false, true)))
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
    @Logical("conjunction")
    @CaseAs(provider = FirstArgument.class)
    @As("Logical conjunction of 2 predicates with atLeast(2).booleanValue(User::flags)")
    public void whereAtLeastTwoBooleanConjunction(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where()
                .atLeast(2).booleanValue(User::flags).isTrue()
                .and().atLeast(2).booleanValue(User::flags).notFalse());
    }

    static Stream<Arguments> whereAtLeastTwoBooleanConjunction() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().atLeast(2).booleanValue(User::flags).isTrue()\n" +
                        "       .and().atLeast(2).booleanValue(User::flags).notFalse()")
                .isTrueFor(userWithFlags(asList(true, true, true))) // At least 2 flags are true and not false
                .isTrueFor(userWithFlags(asList(true, true, null))) // At least 2 flags are true and not false (ignoring nulls)
                .isFalseFor(userWithFlags(asList(true, false, false))) // Only 1 true flag
                .isFalseFor(userWithFlags(asList(false, null, false))) // No true flags
                .isFalseFor(userWithFlags(singletonList(true))) // Only 1 true flag
                .isFalseFor(userWithFlags(singletonList(null))) // Only null flags
                .isFalseFor(userWithFlags(listWithNullableIterator())) // List with nullable iterator
                .isFalseFor(userWithFlags(emptyList())) // Empty list
                .isFalseFor(userWithFlags(null)) // Flags are null
                .isFalseFor(null) // User is null
                .toStream();
    }
}