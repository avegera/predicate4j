package io.github.avegera.predicate4j.test.predicate;

import io.github.avegera.predicate4j.test.util.Lambda;

public class PredicateTestCase<T, R> {

    private final Lambda<T, R> mapper;

    private final Object[] arguments;

    private final String argumentsAsString;

    private final boolean expectedResult;

    private final T object;

    public PredicateTestCase(Lambda<T, R> mapper, Object[] arguments, String argumentsAsString, boolean expectedResult, T object) {
        this.mapper = mapper;
        this.arguments = arguments;
        this.argumentsAsString = argumentsAsString;
        this.expectedResult = expectedResult;
        this.object = object;
    }

    public Lambda<T, R> mapper() {
        return mapper;
    }

    public Object[] arguments() {
        return arguments;
    }

    public String argumentsAsString() {
        return argumentsAsString;
    }

    public boolean expectedResult() {
        return expectedResult;
    }

    public T object() {
        return object;
    }
}