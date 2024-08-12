package io.github.avegera.predicate4j.test.predicate;

import io.github.avegera.predicate4j.test.util.Lambda;
import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static io.github.avegera.predicate4j.test.util.ArrayUtils.getArray;
import static io.github.avegera.predicate4j.test.util.LambdaUtils.lambdaToString;
import static io.github.avegera.predicate4j.test.util.StringUtils.getString;

public class PredicateTest<T, R> {

    private final List<PredicateTestCase<T, R>> testCases = new ArrayList<>();

    private String predicate;

    private Lambda<T, R> currentMapper;

    public PredicateTest<T, R> predicate(String predicate) {
        this.predicate = predicate;
        return this;
    }

    public PredicateTestCaseMapper<T, R> withMapper(Lambda<T, R> mapper) {
        currentMapper = mapper;
        return new PredicateTestCaseMapper<T, R>(this, this::addTestCase);
    }

    public PredicateTestCaseNoMapperNoArgument<T, R> isTrueFor(T object) {
        addTestCase(new PredicateTestCaseData<>(true, object));
        return new PredicateTestCaseNoMapperNoArgument<>(this, this::addTestCase);
    }

    public PredicateTestCaseNoMapperNoArgument<T, R> isFalseFor(T object) {
        addTestCase(new PredicateTestCaseData<>(false, object));
        return new PredicateTestCaseNoMapperNoArgument<>(this, this::addTestCase);
    }

    public Stream<Arguments> toStream() {
        return testCases.stream().map(testCase -> Arguments.of(getArray(getContext(testCase), testCase.arguments())));
    }

    public static <T, R> PredicateTest<T, R> builder() {
        return new PredicateTest<>();
    }

    private void addTestCase(PredicateTestCaseData<T> data) {
        testCases.add(new PredicateTestCase<>(
                currentMapper,
                data.arguments(),
                data.argumentsAsString(),
                data.expectedResult(),
                data.object()
        ));
    }

    private PredicateContext<T, R> getContext(PredicateTestCase<T, R> testCase) {
        return PredicateContext.<T, R>builder()
                .name(getPredicateName())
                .predicate(getStringWithArguments(testCase))
                .object(testCase.object())
                .mapper(testCase.mapper())
                .expectedResult(testCase.expectedResult())
                .build();
    }

    private String getPredicateName() {
        return predicate.replaceAll("\\$argument[0-9]+", "value");
    }

    private String getStringWithArguments(PredicateTestCase<T, R> testCase) {
        String mapperString = lambdaToString(testCase.mapper());
        String replaced = predicate.replace("(mapper)", "(" + mapperString + ")") + ".test(object)";

        if (testCase.arguments() == null) {
            return replaced;
        }

        if (testCase.argumentsAsString() != null) {
            return replaced.replaceAll("\\$argument\\d+(,\\s*\\$argument\\d+)*", testCase.argumentsAsString());
        }

        for (int i = 0; i < testCase.arguments().length; i++) {
            replaced = replaced.replace("$argument" + (i + 1), getString(testCase.arguments()[i], null));
        }
        return replaced;
    }
}