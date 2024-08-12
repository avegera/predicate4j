package io.github.avegera.predicate4j;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.CaseAs;
import com.tngtech.jgiven.annotation.Description;
import io.github.avegera.predicate4j.test.argument.FirstArgument;
import io.github.avegera.predicate4j.test.model.Address;
import io.github.avegera.predicate4j.test.predicate.PredicateContext;
import io.github.avegera.predicate4j.test.predicate.PredicateTest;
import io.github.avegera.predicate4j.test.scenario.PredicateScenarioTest;
import io.github.avegera.predicate4j.test.tag.Logical;
import io.github.avegera.predicate4j.test.tag.Where;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.github.avegera.predicate4j.Where.where;
import static java.util.Arrays.asList;

@Where("where")
@Logical("conjunction")
@Description("Build predicate conjunction")
public class ConjunctionTest extends PredicateScenarioTest<Address> {

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("Logical conjunction of 2 predicates")
    public void logicalConjunctionTwoPredicates(PredicateContext<Address, Object> context) {
        scenario(context, where(Address::country).isEqualTo("USA").and(Address::zipCode).isNull());
    }

    static Stream<Arguments> logicalConjunctionTwoPredicates() {
        return PredicateTest.<Address, Object>builder()
                .predicate("where(Address::country).isEqualTo(\"USA\")\n" +
                        "       .and(Address::zipCode).isNull()")
                .isTrueFor(new Address("USA", null))
                .isFalseFor(new Address("Ireland", null))
                .isFalseFor(new Address("USA", 123))
                .isFalseFor(new Address("Ireland", 123))
                .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("Logical conjunction of 3 predicates")
    public void logicalConjunctionOfThreePredicates(PredicateContext<Address, Object> context) {
        scenario(context, where(Address::country).notNull()
                .and().string(Address::state).startsWith("some")
                .and(Address::zipCode).isInstanceOf(Integer.class)
        );
    }

    static Stream<Arguments> logicalConjunctionOfThreePredicates() {
        return PredicateTest.<Address, Object>builder()
                .predicate("where(Address::country).notNull()\n" +
                        "       .and().string(Address::state).startsWith(\"some\")\n" +
                        "       .and(Address::zipCode).isInstanceOf(Integer.class)")
                .isTrueFor(new Address("USA", "someState", 123))
                .isFalseFor(new Address("USA", "testState", 123))
                .isFalseFor(new Address("USA", "someState", null))
                .isFalseFor(new Address("USA", "someState", null))
                .isFalseFor(new Address(null, "testState", 123))
                .isFalseFor(new Address(null, "testState", null))
                .isFalseFor(new Address(null, null, null))
                .isFalseFor(null)
                .toStream();
    }

    @MethodSource
    @ParameterizedTest
    @CaseAs(provider = FirstArgument.class)
    @As("Logical conjunction of 7 predicates")
    public void logicalConjunctionOfSevenPredicates(PredicateContext<Address, Object> context) {
        scenario(context, where(Address::country).isEqualTo("USA")
                .and(Address::state).notNull()
                .and().string(Address::street).notStartsWith("str.")
                .and().string(Address::building).length().isGreaterThanOrEqualTo(3)
                .and().number(Address::zipCode).isGreaterThan(100)
                .and().list(Address::tenants).contains("Tenant1")
                .and().booleanValue(Address::active).isTrue());
    }

    static Stream<Arguments> logicalConjunctionOfSevenPredicates() {
        return PredicateTest.<Address, Object>builder()
                .predicate("where(Address::country).isEqualTo(\"USA\")\n" +
                        "       .and(Address::state).notNull()\n" +
                        "       .and().string(Address::street).notStartsWith(\"str.\")\n" +
                        "       .and().string(Address::building).length().isGreaterThanOrEqualTo(3)\n" +
                        "       .and().number(Address::zipCode).isGreaterThan(100)\n" +
                        "       .and().list(Address::tenants).contains(\"Tenant1\")\n" +
                        "       .and().booleanValue(Address::active).isTrue()")
                .isTrueFor(new Address("USA", "testValue", "someStreet", "abc", 123, asList("Tenant1", "Tenant2"), true))
                .isFalseFor(new Address("Canada", "testValue", "someStreet", "abc", 123, asList("Tenant1", "Tenant2"), true))
                .isFalseFor(new Address("USA", null, "someStreet", "abc", 123, asList("Tenant1", "Tenant2"), true))
                .isFalseFor(new Address("USA", "testValue", "str. Some Street", "abc", 123, asList("Tenant1", "Tenant2"), true))
                .isFalseFor(new Address("USA", "testValue", "someStreet", "x", 123, asList("Tenant1", "Tenant2"), true))
                .isFalseFor(new Address("USA", "testValue", "someStreet", "abc", 45, asList("Tenant1", "Tenant2"), true))
                .isFalseFor(new Address("USA", "testValue", "someStreet", "abc", 123, asList("Tenant2", "Tenant3", "Tenant4"), true))
                .isFalseFor(new Address("USA", "testValue", "someStreet", "abc", 123, asList("Tenant1", "Tenant2"), false))
                .isFalseFor(new Address("USA", "testValue", "str. Some Street", "x", null, asList("Tenant1", "Tenant2"), true))
                .isFalseFor(new Address("Canada", null, "someStreet", "abc", 123, asList("Tenant2", "Tenant3"), false))
                .isFalseFor(new Address("USA", "testValue", "someStreet", "x", 10, asList("Tenant1", "Tenant2"), null))
                .isFalseFor(null)
                .toStream();
    }
}