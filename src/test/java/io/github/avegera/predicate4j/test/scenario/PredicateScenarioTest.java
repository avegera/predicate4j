package io.github.avegera.predicate4j.test.scenario;

import com.tngtech.jgiven.junit5.ScenarioTest;
import io.github.avegera.predicate4j.api.core.FluentPredicate;
import io.github.avegera.predicate4j.test.predicate.PredicateContext;
import io.github.avegera.predicate4j.test.step.GivenPredicate;
import io.github.avegera.predicate4j.test.step.ThenPredicateReturns;
import io.github.avegera.predicate4j.test.step.WhenObjectIs;

public class PredicateScenarioTest<T> extends ScenarioTest<GivenPredicate<T>, WhenObjectIs<T>, ThenPredicateReturns<T>> {

    protected <R> void scenario(PredicateContext<T, R> context, FluentPredicate<T> predicate) {
        given().predicate(predicate, context.name());
        when().object_is_$(context.object());
        then().predicate_test_returns(context.expectedResult());
    }
}