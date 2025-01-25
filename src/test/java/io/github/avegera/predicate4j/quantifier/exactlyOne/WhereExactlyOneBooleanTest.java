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
import static io.github.avegera.predicate4j.test.model.User.userWithFlags;
import static io.github.avegera.predicate4j.test.util.ListWithNullableIterator.listWithNullableIterator;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@Where("where")
@Type("boolean")
@Quantifier("exactlyOne")
@Description("Test predicates under where().exactlyOne().booleanValue(...)")
public class WhereExactlyOneBooleanTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().exactlyOne().booleanValue(User::flags).isTrue()")
    public void exactlyOneBooleanIsTrue(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().exactlyOne().booleanValue(context.mapper()).isTrue());
    }

    static Stream<Arguments> exactlyOneBooleanIsTrue() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().exactlyOne().booleanValue(User::flags).isTrue()")
                .withMapper(User::flags)
                    .isTrueFor(userWithFlags(asList(true, false, false)))
                    .isTrueFor(userWithFlags(singletonList(true)))
                    .isFalseFor(userWithFlags(asList(true, true, false)))
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
    @As("where().exactlyOne().booleanValue(User::flags).notTrue()")
    public void exactlyOneBooleanNotTrue(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().exactlyOne().booleanValue(context.mapper()).notTrue());
    }

    static Stream<Arguments> exactlyOneBooleanNotTrue() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().exactlyOne().booleanValue(User::flags).notTrue()")
                .withMapper(User::flags)
                    .isTrueFor(userWithFlags(asList(true, true, false)))
                    .isTrueFor(userWithFlags(asList(false, true)))
                    .isTrueFor(userWithFlags(singletonList(false)))
                    .isFalseFor(userWithFlags(asList(false, false, false)))
                    .isFalseFor(userWithFlags(asList(false, null, true)))
                    .isFalseFor(userWithFlags(listWithNullableIterator()))
                    .isFalseFor(userWithFlags(emptyList()))
                    .isFalseFor(userWithFlags(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().exactlyOne().booleanValue(User::flags).isFalse()")
    public void exactlyOneBooleanIsFalse(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().exactlyOne().booleanValue(context.mapper()).isFalse());
    }

    static Stream<Arguments> exactlyOneBooleanIsFalse() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().exactlyOne().booleanValue(User::flags).isFalse()")
                .withMapper(User::flags)
                    .isTrueFor(userWithFlags(asList(false, true, true)))
                    .isTrueFor(userWithFlags(singletonList(false)))
                    .isFalseFor(userWithFlags(asList(false, false, true)))
                    .isFalseFor(userWithFlags(asList(true, true, true)))
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
    @As("where().exactlyOne().booleanValue(User::flags).notFalse()")
    public void exactlyOneBooleanNotFalse(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().exactlyOne().booleanValue(context.mapper()).notFalse());
    }

    static Stream<Arguments> exactlyOneBooleanNotFalse() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().exactlyOne().booleanValue(User::flags).notFalse()")
                .withMapper(User::flags)
                    .isTrueFor(userWithFlags(asList(true, false, false)))
                    .isTrueFor(userWithFlags(singletonList(true)))
                    .isTrueFor(userWithFlags(singletonList(null)))
                    .isFalseFor(userWithFlags(asList(true, true, false)))
                    .isFalseFor(userWithFlags(asList(false, false, false)))
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
    @As("Logical conjunction of 2 predicates with exactlyOne().booleanValue(User::flags)")
    public void exactlyOneBooleanConjunction(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().exactlyOne().booleanValue(User::flags).isTrue()
                .and().exactlyOne().booleanValue(User::flags).notFalse());
    }

    static Stream<Arguments> exactlyOneBooleanConjunction() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().exactlyOne().booleanValue(User::flags).isTrue()\n" +
                        "       .and().exactlyOne().booleanValue(User::flags).notFalse()")
                .isTrueFor(userWithFlags(asList(true, false, false))) // Exactly one flag is true and not false
                .isTrueFor(userWithFlags(singletonList(true))) // Single true flag satisfies predicates
                .isFalseFor(userWithFlags(asList(false, false, false))) // No true flags
                .isFalseFor(userWithFlags(asList(true, true, false))) // More than one true flag violates exactly one condition
                .isFalseFor(userWithFlags(singletonList(null))) // Only null flags
                .isFalseFor(userWithFlags(listWithNullableIterator())) // List with nullable iterator
                .isFalseFor(userWithFlags(emptyList())) // Empty list does not satisfy exactly one condition
                .isFalseFor(userWithFlags(null)) // Flags are null
                .isFalseFor(null) // User is null
                .toStream();
    }
}