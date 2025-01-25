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
import static io.github.avegera.predicate4j.test.model.User.userWithFlags;
import static io.github.avegera.predicate4j.test.util.ListWithNullableIterator.listWithNullableIterator;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@Where("where")
@Type("boolean")
@Quantifier("none")
@Description("Test predicates under where().none().booleanValue(...)")
public class WhereNoneBooleanTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().none().booleanValue(User::flags).isTrue()")
    public void noneBooleanIsTrue(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().none().booleanValue(context.mapper()).isTrue());
    }

    static Stream<Arguments> noneBooleanIsTrue() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().none().booleanValue(User::flags).isTrue()")
                .withMapper(User::flags)
                    .isTrueFor(userWithFlags(asList(false, false, false)))
                    .isTrueFor(userWithFlags(emptyList()))
                    .isFalseFor(userWithFlags(asList(true, false, false)))
                    .isFalseFor(userWithFlags(singletonList(true)))
                    .isFalseFor(userWithFlags(asList(true, null, false)))
                    .isFalseFor(userWithFlags(null))
                    .isFalseFor(null)
                .withMapper(null)
                    .isFalseFor(userWithFlags(asList(false, true, false)))
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
    @As("where().none().booleanValue(User::flags).notTrue()")
    public void noneBooleanNotTrue(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().none().booleanValue(context.mapper()).notTrue());
    }

    static Stream<Arguments> noneBooleanNotTrue() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().none().booleanValue(User::flags).notTrue()")
                .withMapper(User::flags)
                    .isTrueFor(userWithFlags(asList(true, true, true)))
                    .isTrueFor(userWithFlags(emptyList()))
                    .isFalseFor(userWithFlags(asList(false, true, false)))
                    .isFalseFor(userWithFlags(singletonList(false)))
                    .isFalseFor(userWithFlags(asList(null, false, null)))
                    .isFalseFor(userWithFlags(null))
                    .isFalseFor(null)
                .withMapper(null)
                    .isFalseFor(userWithFlags(asList(true, false, null)))
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
    @As("where().none().booleanValue(User::flags).isFalse()")
    public void noneBooleanIsFalse(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().none().booleanValue(context.mapper()).isFalse());
    }

    static Stream<Arguments> noneBooleanIsFalse() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().none().booleanValue(User::flags).isFalse()")
                .withMapper(User::flags)
                    .isTrueFor(userWithFlags(asList(true, true, null)))
                    .isTrueFor(userWithFlags(singletonList(true)))
                    .isTrueFor(userWithFlags(emptyList()))
                    .isFalseFor(userWithFlags(asList(false, true, true)))
                    .isFalseFor(userWithFlags(singletonList(false)))
                    .isFalseFor(userWithFlags(asList(false, null, true)))
                    .isFalseFor(userWithFlags(null))
                    .isFalseFor(null)
                .withMapper(null)
                    .isFalseFor(userWithFlags(asList(true, false, null)))
                    .isFalseFor(userWithFlags(singletonList(false)))
                    .isFalseFor(userWithFlags(listWithNullableIterator()))
                    .isFalseFor(userWithFlags(emptyList()))
                    .isFalseFor(userWithFlags(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().none().booleanValue(User::flags).notFalse()")
    public void noneBooleanNotFalse(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().none().booleanValue(context.mapper()).notFalse());
    }

    static Stream<Arguments> noneBooleanNotFalse() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().none().booleanValue(User::flags).notFalse()")
                .withMapper(User::flags)
                    .isTrueFor(userWithFlags(asList(false, false, false)))
                    .isTrueFor(userWithFlags(singletonList(false)))
                    .isTrueFor(userWithFlags(emptyList()))
                    .isFalseFor(userWithFlags(asList(true, false, false)))
                    .isFalseFor(userWithFlags(singletonList(true)))
                    .isFalseFor(userWithFlags(asList(true, null, false)))
                    .isFalseFor(userWithFlags(null))
                    .isFalseFor(null)
                .withMapper(null)
                    .isFalseFor(userWithFlags(asList(false, true, null)))
                    .isFalseFor(userWithFlags(singletonList(true)))
                    .isFalseFor(userWithFlags(listWithNullableIterator()))
                    .isFalseFor(userWithFlags(emptyList()))
                    .isFalseFor(userWithFlags(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("Logical conjunction of 2 predicates with none().booleanValue(User::flags)")
    public void whereNoneBooleanConjunction(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().none().booleanValue(User::flags).isTrue()
                .and().none().booleanValue(User::flags).notFalse());
    }

    static Stream<Arguments> whereNoneBooleanConjunction() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().none().booleanValue(User::flags).isTrue()\n" +
                        "       .and().none().booleanValue(User::flags).notFalse()")
                .withMapper(User::flags)
                    .isTrueFor(userWithFlags(asList(false, false, false))) // All flags are false
                    .isTrueFor(userWithFlags(singletonList(false))) // Single false flag satisfies the conjunction
                    .isTrueFor(userWithFlags(emptyList())) // Empty list satisfies the conjunction
                    .isFalseFor(userWithFlags(asList(true, false, null))) // At least one flag is true
                    .isFalseFor(userWithFlags(singletonList(true))) // Single true flag does not satisfy the conjunction
                    .isFalseFor(userWithFlags(null)) // Flags are null
                    .isFalseFor(null) // Entire input is null
                .toStream();
    }
}