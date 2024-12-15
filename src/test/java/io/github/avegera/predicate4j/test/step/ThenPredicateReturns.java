package io.github.avegera.predicate4j.test.step;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import io.github.avegera.predicate4j.api.FluentPredicate;

import static org.assertj.core.api.Assertions.assertThat;

public class ThenPredicateReturns<T> extends Stage<ThenPredicateReturns<T>> {

    @ExpectedScenarioState
    FluentPredicate<T> predicate;

    @ExpectedScenarioState
    T object;

    @As("predicate.test(object) returns $1")
    public void predicate_test_returns(boolean result) {
        if (result) {
            assertThat(predicate).accepts(object);
        } else {
            assertThat(predicate).rejects(object);
        }
    }
}