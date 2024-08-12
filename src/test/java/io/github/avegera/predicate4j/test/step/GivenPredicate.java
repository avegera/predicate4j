package io.github.avegera.predicate4j.test.step;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.Hidden;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import io.github.avegera.predicate4j.api.RichPredicate;

public class GivenPredicate<T> extends Stage<GivenPredicate<T>> {

    @ProvidedScenarioState
    RichPredicate<T> predicate;

    @As("predicate = $1")
    public GivenPredicate<T> predicate(@Hidden RichPredicate<T> predicate, String name) {
        this.predicate = predicate;
        return self();
    }
}