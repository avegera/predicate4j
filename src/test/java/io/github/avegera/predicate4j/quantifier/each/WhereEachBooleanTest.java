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
import static io.github.avegera.predicate4j.test.model.User.userWithFlags;
import static io.github.avegera.predicate4j.test.util.ListWithNullableIterator.listWithNullableIterator;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@Where("where")
@Type("boolean")
@Quantifier("each")
@Description("Test predicates under where().each().booleanValue(...)")
public class WhereEachBooleanTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().each().boolean(User::flags).isTrue()")
    public void eachBooleanIsTrue(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().each().booleanValue(context.mapper()).isTrue());
    }

    static Stream<Arguments> eachBooleanIsTrue() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().each().boolean(User::flags).isTrue()")
                .withMapper(User::flags)
                    .isTrueFor(userWithFlags(asList(true, true, true)))
                    .isTrueFor(userWithFlags(singletonList(true)))
                    .isTrueFor(userWithFlags(emptyList()))
                    .isFalseFor(userWithFlags(asList(true, false, true)))
                    .isFalseFor(userWithFlags(asList(false, false, false)))
                    .isFalseFor(userWithFlags(asList(true, null, true)))
                    .isFalseFor(userWithFlags(singletonList(null)))
                    .isFalseFor(userWithFlags(listWithNullableIterator()))
                    .isFalseFor(userWithFlags(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().each().boolean(User::flags).notTrue()")
    public void eachBooleanNotTrue(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().each().booleanValue(context.mapper()).notTrue());
    }

    static Stream<Arguments> eachBooleanNotTrue() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().each().boolean(User::flags).notTrue()")
                .withMapper(User::flags)
                    .isTrueFor(userWithFlags(asList(false, false, false)))
                    .isTrueFor(userWithFlags(asList(false, null, false)))
                    .isTrueFor(userWithFlags(singletonList(false)))
                    .isTrueFor(userWithFlags(singletonList(null)))
                    .isTrueFor(userWithFlags(emptyList()))
                    .isFalseFor(userWithFlags(asList(true, false, true)))
                    .isFalseFor(userWithFlags(asList(true, true, true)))
                    .isFalseFor(userWithFlags(singletonList(true)))
                    .isFalseFor(userWithFlags(listWithNullableIterator()))
                    .isFalseFor(userWithFlags(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().each().booleanValue(User::flags).isFalse()")
    public void eachBooleanIsFalse(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().each().booleanValue(context.mapper()).isFalse());
    }

    static Stream<Arguments> eachBooleanIsFalse() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().each().booleanValue(User::flags).isFalse()")
                .withMapper(User::flags)
                    .isTrueFor(userWithFlags(asList(false, false, false)))
                    .isTrueFor(userWithFlags(singletonList(false)))
                    .isTrueFor(userWithFlags(emptyList()))
                    .isFalseFor(userWithFlags(asList(true, false, true)))
                    .isFalseFor(userWithFlags(asList(true, true, true)))
                    .isFalseFor(userWithFlags(asList(false, null, false)))
                    .isFalseFor(userWithFlags(singletonList(true)))
                    .isFalseFor(userWithFlags(singletonList(null)))
                    .isFalseFor(userWithFlags(listWithNullableIterator()))
                    .isFalseFor(userWithFlags(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("where().each().booleanValue(User::flags).notFalse()")
    public void eachBooleanNotFalse(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().each().booleanValue(context.mapper()).notFalse());
    }

    static Stream<Arguments> eachBooleanNotFalse() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().each().booleanValue(User::flags).notFalse()")
                .withMapper(User::flags)
                    .isTrueFor(userWithFlags(asList(true, true, true)))
                    .isTrueFor(userWithFlags(asList(true, null, true)))
                    .isTrueFor(userWithFlags(singletonList(true)))
                    .isTrueFor(userWithFlags(singletonList(null)))
                    .isTrueFor(userWithFlags(emptyList()))
                    .isFalseFor(userWithFlags(asList(false, true, false)))
                    .isFalseFor(userWithFlags(asList(false, false, false)))
                    .isFalseFor(userWithFlags(singletonList(false)))
                    .isFalseFor(userWithFlags(listWithNullableIterator()))
                    .isFalseFor(userWithFlags(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @Logical("conjunction")
    @CaseAs(provider = FirstArgument.class)
    @As("Logical conjunction of 3 predicates with each().booleanValue(User::flags)")
    public void whereEachBooleanConjunction(PredicateContext<User, Iterable<Boolean>> context) {
        scenario(context, where().each().booleanValue(User::flags).isTrue()
                .and().each().booleanValue(User::flags).notFalse());
    }

    static Stream<Arguments> whereEachBooleanConjunction() {
        return PredicateTest.<User, List<Boolean>>builder()
                .predicate("where().each().booleanValue(User::flags).isTrue()\n" +
                        "       .and().each().booleanValue(User::flags).notFalse()")
                .isTrueFor(userWithFlags(asList(true, true, true))) // All flags are true
                .isTrueFor(userWithFlags(emptyList())) // Empty list satisfies all predicates
                .isFalseFor(userWithFlags(asList(true, false, true))) // One flag is false
                .isFalseFor(userWithFlags(asList(true, null, true))) // One flag is null
                .isFalseFor(userWithFlags(singletonList(false))) // Single flag is false
                .isFalseFor(userWithFlags(listWithNullableIterator())) // List with nullable iterator
                .isFalseFor(userWithFlags(null)) // Flags are null
                .isFalseFor(null)
                .toStream();
    }

}