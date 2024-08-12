package io.github.avegera.predicate4j.test.predicate;

public class PredicateTestCaseData<T> {

    private final Object[] arguments;

    private final String argumentsAsString;

    private final T object;

    private final boolean expectedResult;

    public PredicateTestCaseData(Object[] arguments, String argumentsAsString, boolean expectedResult, T object) {
        this.arguments = arguments;
        this.argumentsAsString = argumentsAsString;
        this.expectedResult = expectedResult;
        this.object = object;
    }

    public PredicateTestCaseData(boolean expectedResult, T object) {
        this(null, null, expectedResult, object);
    }

    public Object[] arguments() {
        return arguments;
    }

    public String argumentsAsString() {
        return argumentsAsString;
    }

    public T object() {
        return object;
    }

    public boolean expectedResult() {
        return expectedResult;
    }
}