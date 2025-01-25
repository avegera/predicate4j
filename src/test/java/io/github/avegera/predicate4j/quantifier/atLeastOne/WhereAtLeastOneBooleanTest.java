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
import static io.github.avegera.predicate4j.test.model.User.userWithFlags;
import static io.github.avegera.predicate4j.test.util.ListWithNullableIterator.listWithNullableIterator;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@Where("where")
@Type("boolean")
@Quantifier("atLeastOne")
@Description("Test predicates under where().atLeastOne().booleanValue(...)")
public class WhereAtLeastOneBooleanTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeastOne().booleanValue(User::flags).isTrue()")
    public void atLeastOneBooleanIsTrue(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().atLeastOne().booleanValue(context.mapper()).isTrue());
    }

    static Stream<Arguments> atLeastOneBooleanIsTrue() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().atLeastOne().booleanValue(User::flags).isTrue()")
                .withMapper(User::flags)
                    .isTrueFor(userWithFlags(asList(true, false, false)))
                    .isTrueFor(userWithFlags(singletonList(true)))
                    .isTrueFor(userWithFlags(asList(true, null, false)))
                    .isFalseFor(userWithFlags(asList(false, false, false)))
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
    @As("where().atLeastOne().booleanValue(User::flags).notTrue()")
    public void atLeastOneBooleanNotTrue(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().atLeastOne().booleanValue(context.mapper()).notTrue());
    }

    static Stream<Arguments> atLeastOneBooleanNotTrue() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().atLeastOne().booleanValue(User::flags).notTrue()")
                .withMapper(User::flags)
                    .isTrueFor(userWithFlags(asList(false, true, false)))
                    .isTrueFor(userWithFlags(asList(false, null, false)))
                    .isTrueFor(userWithFlags(singletonList(false)))
                    .isTrueFor(userWithFlags(singletonList(null)))
                    .isFalseFor(userWithFlags(listWithNullableIterator()))
                    .isFalseFor(userWithFlags(emptyList()))
                    .isFalseFor(userWithFlags(asList(true, true, true)))
                    .isFalseFor(userWithFlags(singletonList(true)))
                    .isFalseFor(userWithFlags(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().atLeastOne().booleanValue(User::flags).isFalse()")
    public void atLeastOneBooleanIsFalse(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().atLeastOne().booleanValue(context.mapper()).isFalse());
    }

    static Stream<Arguments> atLeastOneBooleanIsFalse() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().atLeastOne().booleanValue(User::flags).isFalse()")
                .withMapper(User::flags)
                    .isTrueFor(userWithFlags(asList(false, true, true)))
                    .isTrueFor(userWithFlags(singletonList(false)))
                    .isTrueFor(userWithFlags(asList(false, null, true)))
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
    @As("where().atLeastOne().booleanValue(User::flags).notFalse()")
    public void atLeastOneBooleanNotFalse(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().atLeastOne().booleanValue(context.mapper()).notFalse());
    }

    static Stream<Arguments> atLeastOneBooleanNotFalse() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().atLeastOne().booleanValue(User::flags).notFalse()")
                .withMapper(User::flags)
                    .isTrueFor(userWithFlags(asList(true, false, false)))
                    .isTrueFor(userWithFlags(asList(true, null, false)))
                    .isTrueFor(userWithFlags(singletonList(true)))
                    .isTrueFor(userWithFlags(singletonList(null)))
                    .isFalseFor(userWithFlags(listWithNullableIterator()))
                    .isFalseFor(userWithFlags(emptyList()))
                    .isFalseFor(userWithFlags(asList(false, false, false)))
                    .isFalseFor(userWithFlags(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @Logical("conjunction")
    @CaseAs(provider = FirstArgument.class)
    @As("Logical conjunction of 2 predicates with atLeastOne().booleanValue(User::flags)")
    public void whereAtLeastOneBooleanConjunction(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().atLeastOne().booleanValue(User::flags).isTrue()
                .and().atLeastOne().booleanValue(User::flags).notFalse());
    }

    static Stream<Arguments> whereAtLeastOneBooleanConjunction() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().atLeastOne().booleanValue(User::flags).isTrue()\n" +
                        "       .and().atLeastOne().booleanValue(User::flags).notFalse()")
                .isTrueFor(userWithFlags(asList(true, false, true))) // At least one flag is true and not false
                .isTrueFor(userWithFlags(singletonList(true))) // Single true flag satisfies predicates
                .isFalseFor(userWithFlags(asList(false, false, false))) // All flags are false
                .isFalseFor(userWithFlags(asList(false, null, false))) // No true flags
                .isFalseFor(userWithFlags(singletonList(null))) // Only null flags
                .isFalseFor(userWithFlags(listWithNullableIterator())) // List with nullable iterator
                .isFalseFor(userWithFlags(emptyList())) // Empty list does not satisfy at least one condition
                .isFalseFor(userWithFlags(null)) // Flags are null
                .isFalseFor(null)
                .toStream();
    }
}