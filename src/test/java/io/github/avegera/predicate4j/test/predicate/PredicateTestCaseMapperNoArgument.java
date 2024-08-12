package io.github.avegera.predicate4j.test.predicate;

import io.github.avegera.predicate4j.test.util.Lambda;
import io.github.avegera.predicate4j.test.util.format.IndentLevel;
import org.junit.jupiter.params.provider.Arguments;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class PredicateTestCaseMapperNoArgument<T, R> {

    private final PredicateTest<T, R> predicateTest;

    private final Consumer<PredicateTestCaseData<T>> testCaseDataConsumer;

    public PredicateTestCaseMapperNoArgument(PredicateTest<T, R> predicateTest, Consumer<PredicateTestCaseData<T>> testCaseDataConsumer) {
        this.predicateTest = predicateTest;
        this.testCaseDataConsumer = testCaseDataConsumer;
    }

    @IndentLevel(1)
    public PredicateTestCaseMapperNoArgument<T, R> isTrueFor(T object) {
        testCaseDataConsumer.accept(new PredicateTestCaseData<>(true, object));
        return this;
    }

    @IndentLevel(1)
    public PredicateTestCaseMapperNoArgument<T, R> isFalseFor(T object) {
        testCaseDataConsumer.accept(new PredicateTestCaseData<>(false, object));
        return this;
    }

    public PredicateTestCaseMapper<T, R> withMapper(Lambda<T, R> mapper) {
        return predicateTest.withMapper(mapper);
    }

    public Stream<Arguments> toStream() {
        return predicateTest.toStream();
    }
}