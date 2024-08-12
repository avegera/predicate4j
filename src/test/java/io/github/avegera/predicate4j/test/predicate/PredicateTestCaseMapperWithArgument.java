package io.github.avegera.predicate4j.test.predicate;

import io.github.avegera.predicate4j.test.util.Lambda;
import io.github.avegera.predicate4j.test.util.format.IndentLevel;
import org.junit.jupiter.params.provider.Arguments;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class PredicateTestCaseMapperWithArgument<T, R> {

    private final PredicateTest<T, R> predicateTest;

    private final Consumer<PredicateTestCaseData<T>> testCaseDataConsumer;

    private final String argumentsAsString;

    private final Object[] arguments;

    public PredicateTestCaseMapperWithArgument(PredicateTest<T, R> predicateTest, Consumer<PredicateTestCaseData<T>> testCaseDataConsumer, Object... arguments) {
        this.predicateTest = predicateTest;
        this.testCaseDataConsumer = testCaseDataConsumer;
        this.arguments = arguments;
        this.argumentsAsString = null;
    }

    public PredicateTestCaseMapperWithArgument(PredicateTest<T, R> predicateTest, Consumer<PredicateTestCaseData<T>> testCaseDataConsumer, String argumentsAsString, Object... arguments) {
        this.predicateTest = predicateTest;
        this.testCaseDataConsumer = testCaseDataConsumer;
        this.arguments = arguments;
        this.argumentsAsString = argumentsAsString;
    }

    @IndentLevel(1)
    public PredicateTestCaseMapperWithArgument<T, R> withArgument(Object argument) {
        return new PredicateTestCaseMapperWithArgument<>(predicateTest, testCaseDataConsumer, argument);
    }

    @IndentLevel(1)
    public PredicateTestCaseMapperWithArgument<T, R> withArgument(Object argument, String argumentsAsString) {
        return new PredicateTestCaseMapperWithArgument<>(predicateTest, testCaseDataConsumer, argumentsAsString, argument);
    }

    @IndentLevel(1)
    public PredicateTestCaseMapperWithArgument<T, R> withArguments(Object... arguments) {
        return new PredicateTestCaseMapperWithArgument<>(predicateTest, testCaseDataConsumer, arguments);
    }

    @IndentLevel(2)
    public PredicateTestCaseMapperWithArgument<T, R> isTrueFor(T object) {
        testCaseDataConsumer.accept(new PredicateTestCaseData<>(arguments, argumentsAsString, true, object));
        return this;
    }

    @IndentLevel(2)
    public PredicateTestCaseMapperWithArgument<T, R> isFalseFor(T object) {
        testCaseDataConsumer.accept(new PredicateTestCaseData<>(arguments, argumentsAsString, false, object));
        return this;
    }

    public PredicateTestCaseMapper<T, R> withMapper(Lambda<T, R> mapper) {
        return predicateTest.withMapper(mapper);
    }

    public Stream<Arguments> toStream() {
        return predicateTest.toStream();
    }
}