package io.github.avegera.predicate4j;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.CaseAs;
import com.tngtech.jgiven.annotation.Description;
import io.github.avegera.predicate4j.test.argument.FirstArgument;
import io.github.avegera.predicate4j.test.model.User;
import io.github.avegera.predicate4j.test.predicate.PredicateContext;
import io.github.avegera.predicate4j.test.predicate.PredicateTest;
import io.github.avegera.predicate4j.test.scenario.PredicateScenarioTest;
import io.github.avegera.predicate4j.test.tag.Type;
import io.github.avegera.predicate4j.test.tag.Where;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.github.avegera.predicate4j.Where.where;
import static io.github.avegera.predicate4j.test.model.User.userWithActiveFlag;

@Where("where")
@Type("boolean")
@Description("Build predicate with single boolean condition")
public class WhereBooleanTest extends PredicateScenarioTest<User> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().booleanValue(mapper).isTrue()")
    public void whereBooleanValueIsTrue(PredicateContext<User, Boolean> context) {
        scenario(context, where().booleanValue(User::active).isTrue());
    }

    static Stream<Arguments> whereBooleanValueIsTrue() {
        return PredicateTest.<User, Boolean>builder()
                .predicate("where().booleanValue(mapper).isTrue()")
                .withMapper(User::active)
                    .isTrueFor(userWithActiveFlag(true))
                    .isFalseFor(userWithActiveFlag(false))
                    .isFalseFor(userWithActiveFlag(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().booleanValue(mapper).notTrue()")
    public void whereBooleanValueNotTrue(PredicateContext<User, Boolean> context) {
        scenario(context, where().booleanValue(User::active).notTrue());
    }

    static Stream<Arguments> whereBooleanValueNotTrue() {
        return PredicateTest.<User, Boolean>builder()
                .predicate("where().booleanValue(mapper).notTrue()")
                .withMapper(User::active)
                    .isFalseFor(userWithActiveFlag(true))
                    .isTrueFor(userWithActiveFlag(false))
                    .isTrueFor(userWithActiveFlag(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().booleanValue(mapper).isFalse()")
    public void whereBooleanValueIsFalse(PredicateContext<User, Boolean> context) {
        scenario(context, where().booleanValue(User::active).isFalse());
    }

    static Stream<Arguments> whereBooleanValueIsFalse() {
        return PredicateTest.<User, Boolean>builder()
                .predicate("where().booleanValue(mapper).isFalse()")
                .withMapper(User::active)
                    .isTrueFor(userWithActiveFlag(false))
                    .isFalseFor(userWithActiveFlag(true))
                    .isFalseFor(userWithActiveFlag(null))
                    .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("_where().booleanValue(mapper).notFalse()")
    public void whereBooleanValueNotFalse(PredicateContext<User, Boolean> context) {
        scenario(context, where().booleanValue(User::active).notFalse());
    }

    static Stream<Arguments> whereBooleanValueNotFalse() {
        return PredicateTest.<User, Boolean>builder()
                .predicate("where().booleanValue(mapper).notFalse()")
                .withMapper(User::active)
                    .isFalseFor(userWithActiveFlag(false))
                    .isTrueFor(userWithActiveFlag(true))
                    .isTrueFor(userWithActiveFlag(null))
                    .isFalseFor(null)
                .toStream();
    }
}