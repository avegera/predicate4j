package io.github.avegera.predicate4j.test.predicate;

import org.junit.jupiter.params.provider.Arguments;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class PredicateTestCaseNoMapperNoArgument<T, R> {

    private final PredicateTest<T, R> predicateTest;

    private final Consumer<PredicateTestCaseData<T>> testCaseDataConsumer;

    public PredicateTestCaseNoMapperNoArgument(PredicateTest<T, R> predicateTest, Consumer<PredicateTestCaseData<T>> testCaseDataConsumer) {
        this.predicateTest = predicateTest;
        this.testCaseDataConsumer = testCaseDataConsumer;
    }

    public PredicateTestCaseNoMapperNoArgument<T, R> isTrueFor(T object) {
        testCaseDataConsumer.accept(new PredicateTestCaseData<>(true, object));
        return this;
    }

    public PredicateTestCaseNoMapperNoArgument<T, R> isFalseFor(T object) {
        testCaseDataConsumer.accept(new PredicateTestCaseData<>(false, object));
        return this;
    }

    public Stream<Arguments> toStream() {
        return predicateTest.toStream();
    }
}