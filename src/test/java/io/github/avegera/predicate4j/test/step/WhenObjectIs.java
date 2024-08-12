package io.github.avegera.predicate4j.test.step;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

public class WhenObjectIs<T> extends Stage<WhenObjectIs<T>> {

    @ProvidedScenarioState
    T object;

    public void object_is_$(T object) {
        this.object = object;
    }
}