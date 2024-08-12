package io.github.avegera.predicate4j.test.predicate;

import io.github.avegera.predicate4j.test.util.format.IndentLevel;

import java.util.function.Consumer;

public class PredicateTestCaseMapper<T, R> {

    private final PredicateTest<T, R> predicateTest;

    private final Consumer<PredicateTestCaseData<T>> testCaseDataConsumer;

    public PredicateTestCaseMapper(PredicateTest<T, R> predicateTest, Consumer<PredicateTestCaseData<T>> testCaseDataConsumer) {
        this.predicateTest = predicateTest;
        this.testCaseDataConsumer = testCaseDataConsumer;
    }

    @IndentLevel(1)
    public PredicateTestCaseMapperWithArgument<T, R> withArgument(Object argument) {
        return new PredicateTestCaseMapperWithArgument<>(predicateTest, testCaseDataConsumer, argument);
    }

    @IndentLevel(1)
    public PredicateTestCaseMapperWithArgument<T, R> withArgument(Object argument, String argumentAsString) {
        return new PredicateTestCaseMapperWithArgument<>(predicateTest, testCaseDataConsumer, argumentAsString, argument);
    }

    @IndentLevel(1)
    public PredicateTestCaseMapperWithArgument<T, R> withArguments(Object... arguments) {
        return new PredicateTestCaseMapperWithArgument<>(predicateTest, testCaseDataConsumer, arguments);
    }

    @IndentLevel(1)
    public PredicateTestCaseMapperWithArgument<T, R> withArguments(String argumentAsString, Object... arguments) {
        return new PredicateTestCaseMapperWithArgument<>(predicateTest, testCaseDataConsumer, argumentAsString, arguments);
    }

    @IndentLevel(1)
    public PredicateTestCaseMapperNoArgument<T, R> isTrueFor(T object) {
        testCaseDataConsumer.accept(new PredicateTestCaseData<>(true, object));
        return new PredicateTestCaseMapperNoArgument<>(predicateTest, testCaseDataConsumer);
    }

    @IndentLevel(1)
    public PredicateTestCaseMapperNoArgument<T, R> isFalseFor(T object) {
        testCaseDataConsumer.accept(new PredicateTestCaseData<>(false, object));
        return new PredicateTestCaseMapperNoArgument<>(predicateTest, testCaseDataConsumer);
    }
}