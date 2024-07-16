package io.github.avegera.predicate4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static io.github.avegera.predicate4j.Predicates.*;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class PredicatesTest {

    @Nested
    @DisplayName("Object predicate")
    class ObjectPredicate {

        @Nested
        @DisplayName("isEqualTo()")
        class IsEqualTo {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("object is equal to provided object")
                void objectIsEqualToProvidedObject() {
                    Object object = new Object();
                    Predicate<Object> predicate = isEqualTo(object);
                    assertThat(predicate).accepts(object);
                }

                @Test
                @DisplayName("object is null and provided object is null")
                void objectIsNullAndProvidedObjectIsNull() {
                    Predicate<Object> predicate = isEqualTo(null);
                    assertThat(predicate).accepts((Object) null);
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("object is not equal to the provided object")
                void objectIsNotEqualToTheProvidedObject() {
                    Object object = new Object();
                    Predicate<Object> predicate = isEqualTo(object);
                    assertThat(predicate).rejects(new Object());
                }

                @Test
                @DisplayName("object is null and provided object is not null")
                void objectIsNullAndProvidedObjectIsNotNull() {
                    Predicate<Object> predicate = isEqualTo(null);
                    assertThat(predicate).rejects(new Object());
                }

                @Test
                @DisplayName("object is not null and provided object is null")
                void objectIsNotNullAndProvidedObjectIsNull() {
                    Predicate<Object> predicate = isEqualTo(new Object());
                    assertThat(predicate).rejects((Object) null);
                }
            }
        }

        @Nested
        @DisplayName("notEqualTo()")
        class NotEqualTo {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("object is not equal to the provided object")
                void objectIsNotEqualToTheProvidedObject() {
                    Object object = new Object();
                    Predicate<Object> predicate = notEqualTo(object);
                    assertThat(predicate).accepts(new Object());
                }

                @Test
                @DisplayName("object is null and provided object is not null")
                void objectIsNullAndProvidedObjectIsNotNull() {
                    Predicate<Object> predicate = notEqualTo(new Object());
                    assertThat(predicate).accepts((Object) null);
                }

                @Test
                @DisplayName("object is not null and provided object is null")
                void objectIsNotNullAndProvidedObjectIsNull() {
                    Predicate<Object> predicate = notEqualTo(null);
                    assertThat(predicate).accepts(new Object());
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("object is equal to provided object")
                void objectIsEqualToProvidedObject() {
                    Object object = new Object();
                    Predicate<Object> predicate = notEqualTo(object);
                    assertThat(predicate).rejects(object);
                }

                @Test
                @DisplayName("object is null and provided object is null")
                void objectIsnullAndProvidedObjectIsNull() {
                    Predicate<Object> predicate = notEqualTo(null);
                    assertThat(predicate).rejects((Object) null);
                }
            }
        }

        @Nested
        @DisplayName("isNull()")
        class IsNull {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("object is null")
                void objectIsNull() {
                    Predicate<Object> predicate = isNull();
                    assertThat(predicate).accepts((Object) null);
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("object is not null")
                void objectIsNotNull() {
                    Predicate<Object> predicate = isNull();
                    assertThat(predicate).rejects(new Object());
                }
            }
        }

        @Nested
        @DisplayName("notNull()")
        class NotNull {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("object is not null")
                void objectIsNotNull() {
                    Predicate<Object> predicate = notNull();
                    assertThat(predicate).accepts(new Object());
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("object is null")
                void objectIsNull() {
                    Predicate<Object> predicate = notNull();
                    assertThat(predicate).rejects((Object) null);
                }
            }
        }

        @Nested
        @DisplayName("isInstanceOf()")
        class IsInstanceOf {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("object is an instance of the provided class")
                void objectIsAnInstanceOfTheProvidedClass() {
                    Predicate<Object> predicate = isInstanceOf(String.class);
                    assertThat(predicate).accepts("test string");
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("object is not an instance of the provided class")
                void objectIsNotAnInstanceOfTheProvidedClass() {
                    Predicate<Object> predicate = isInstanceOf(String.class);
                    assertThat(predicate).rejects(123);
                }

                @Test
                @DisplayName("class is null")
                void classIsNull() {
                    Predicate<Object> predicate = isInstanceOf(null);
                    assertThat(predicate).rejects("test string");
                }
            }
        }

        @Nested
        @DisplayName("notInstanceOf()")
        class NotInstanceOf {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("object is not an instance of the provided class")
                void objectIsNotAnInstanceOfTheProvidedClass() {
                    Predicate<Object> predicate = notInstanceOf(String.class);
                    assertThat(predicate).accepts(123);
                }

                @Test
                @DisplayName("class is null")
                void classIsNull() {
                    Predicate<Object> predicate = notInstanceOf(null);
                    assertThat(predicate).accepts("test string");
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("object is an instance of the provided class")
                void objectIsAnInstanceOfTheProvidedClass() {
                    Predicate<Object> predicate = notInstanceOf(String.class);
                    assertThat(predicate).rejects("test string");
                }
            }
        }

        @Nested
        @DisplayName("alwaysTrue()")
        class AlwaysTrue {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("object is null")
                void objectIsNull() {
                    Predicate<Object> predicate = alwaysTrue();
                    assertThat(predicate).accepts((Object) null);
                }

                @Test
                @DisplayName("object is non-null")
                void objectIsNonNull() {
                    Predicate<Object> predicate = alwaysTrue();
                    assertThat(predicate).accepts(new Object());
                }

                @Test
                @DisplayName("object is a string")
                void objectIsString() {
                    Predicate<Object> predicate = alwaysTrue();
                    assertThat(predicate).accepts("test string");
                }

                @Test
                @DisplayName("object is an integer")
                void objectIsInteger() {
                    Predicate<Object> predicate = alwaysTrue();
                    assertThat(predicate).accepts(123);
                }
            }
        }

        @Nested
        @DisplayName("alwaysFalse()")
        class AlwaysFalse {

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("object is null")
                void objectIsNull() {
                    Predicate<Object> predicate = alwaysFalse();
                    assertThat(predicate).rejects((Object) null);
                }

                @Test
                @DisplayName("object is non-null")
                void objectIsNonNull() {
                    Predicate<Object> predicate = alwaysFalse();
                    assertThat(predicate).rejects(new Object());
                }

                @Test
                @DisplayName("object is a string")
                void objectIsString() {
                    Predicate<Object> predicate = alwaysFalse();
                    assertThat(predicate).rejects("test string");
                }

                @Test
                @DisplayName("object is an integer")
                void objectIsInteger() {
                    Predicate<Object> predicate = alwaysFalse();
                    assertThat(predicate).rejects(123);
                }
            }
        }
    }

    @Nested
    @DisplayName("Boolean predicate")
    class BooleanPredicate {

        @Nested
        @DisplayName("isTrue()")
        class IsTrue {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("boolean is true")
                void booleanIsTrue() {
                    Predicate<Boolean> predicate = isTrue();
                    assertThat(predicate).accepts(true);
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("boolean is false")
                void booleanIsFalse() {
                    Predicate<Boolean> predicate = isTrue();
                    assertThat(predicate).rejects(false);
                }

                @Test
                @DisplayName("boolean is null")
                void booleanIsNull() {
                    Predicate<Boolean> predicate = isTrue();
                    assertThat(predicate).rejects((Boolean) null);
                }
            }
        }

        @Nested
        @DisplayName("notTrue()")
        class NotTrue {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("boolean is false")
                void booleanIsFalse() {
                    Predicate<Boolean> predicate = notTrue();
                    assertThat(predicate).accepts(false);
                }

                @Test
                @DisplayName("boolean is null")
                void booleanIsNull() {
                    Predicate<Boolean> predicate = notTrue();
                    assertThat(predicate).accepts((Boolean) null);
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("boolean is true")
                void booleanIsTrue() {
                    Predicate<Boolean> predicate = notTrue();
                    assertThat(predicate).rejects(true);
                }
            }
        }

        @Nested
        @DisplayName("isFalse()")
        class IsFalse {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("boolean is false")
                void booleanIsFalse() {
                    Predicate<Boolean> predicate = isFalse();
                    assertThat(predicate).accepts(false);
                }

                @Test
                @DisplayName("boolean is not null and false")
                void booleanIsNotNullAndFalse() {
                    Predicate<Boolean> predicate = isFalse();
                    assertThat(predicate).accepts(Boolean.FALSE);
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("boolean is true")
                void booleanIsTrue() {
                    Predicate<Boolean> predicate = isFalse();
                    assertThat(predicate).rejects(true);
                }

                @Test
                @DisplayName("boolean is null")
                void booleanIsNull() {
                    Predicate<Boolean> predicate = isFalse();
                    assertThat(predicate).rejects((Boolean) null);
                }
            }
        }

        @Nested
        @DisplayName("notFalse()")
        class NotFalse {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("boolean is true")
                void booleanIsTrue() {
                    Predicate<Boolean> predicate = notFalse();
                    assertThat(predicate).accepts(true);
                }

                @Test
                @DisplayName("boolean is null")
                void booleanIsNull() {
                    Predicate<Boolean> predicate = notFalse();
                    assertThat(predicate).accepts((Boolean) null);
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("boolean is false")
                void booleanIsFalse() {
                    Predicate<Boolean> predicate = notFalse();
                    assertThat(predicate).rejects(false);
                }
            }
        }
    }

    @Nested
    @DisplayName("List predicate")
    class ListPredicate {

        @Nested
        @DisplayName("isEmpty()")
        class IsEmpty {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("list is empty")
                void listIsEmpty() {
                    Predicate<List<String>> predicate = isEmpty();
                    assertThat(predicate).accepts(new ArrayList<>());
                }

                @Test
                @DisplayName("list is null")
                void listIsNull() {
                    Predicate<List<String>> predicate = isEmpty();
                    assertThat(predicate).accepts((List<String>) null);
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("list is not empty")
                void listIsNotEmpty() {
                    Predicate<List<String>> predicate = isEmpty();
                    assertThat(predicate).rejects(singletonList("item"));
                }
            }
        }

        @Nested
        @DisplayName("notEmpty()")
        class NotEmpty {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("list is not empty")
                void listIsNotEmpty() {
                    Predicate<List<String>> predicate = notEmpty();
                    assertThat(predicate).accepts(singletonList("item"));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("list is empty")
                void listIsEmpty() {
                    Predicate<List<String>> predicate = notEmpty();
                    assertThat(predicate).rejects(new ArrayList<>());
                }

                @Test
                @DisplayName("list is null")
                void listIsNull() {
                    Predicate<List<String>> predicate = notEmpty();
                    assertThat(predicate).rejects((List<String>) null);
                }
            }
        }

        @Nested
        @DisplayName("hasSize(size)")
        class HasSize {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("list has the specified size")
                void listHasSpecifiedSize() {
                    Predicate<List<String>> predicate = hasSize(1);
                    assertThat(predicate).accepts(singletonList("item"));
                }

                @Test
                @DisplayName("list is empty and specified size is zero")
                void listIsEmptyAndSpecifiedSizeIsZero() {
                    Predicate<List<String>> predicate = hasSize(0);
                    assertThat(predicate).accepts(new ArrayList<>());
                }

                @Test
                @DisplayName("list is null and specified size is zero")
                void listIsNullAndSpecifiedSizeIsZero() {
                    Predicate<List<String>> predicate = hasSize(0);
                    assertThat(predicate).accepts((List<String>) null);
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("list has a different size than specified")
                void listHasDifferentSize() {
                    Predicate<List<String>> predicate = hasSize(2);
                    assertThat(predicate).rejects(singletonList("item"));
                }

                @Test
                @DisplayName("list is empty and size is not zero")
                void listIsEmptyAndSizeIsNotZero() {
                    Predicate<List<String>> predicate = hasSize(1);
                    assertThat(predicate).rejects(new ArrayList<>());
                }

                @Test
                @DisplayName("list is null and size is not zero")
                void listIsNullAndSizeIsNotZero() {
                    Predicate<List<String>> predicate = hasSize(1);
                    assertThat(predicate).rejects((List<String>) null);
                }
            }
        }

        @Nested
        @DisplayName("notHaveSize(size)")
        class NotHaveSize {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("list does not have the specified size")
                void listDoesNotHaveSpecifiedSize() {
                    Predicate<List<String>> predicate = notHaveSize(2);
                    assertThat(predicate).accepts(singletonList("item"));
                }

                @Test
                @DisplayName("list is empty and size is not zero")
                void listIsEmptyAndSizeIsNotZero() {
                    Predicate<List<String>> predicate = notHaveSize(1);
                    assertThat(predicate).accepts(new ArrayList<>());
                }

                @Test
                @DisplayName("list is null and size is not zero")
                void listIsNullAndSizeIsNotZero() {
                    Predicate<List<String>> predicate = notHaveSize(1);
                    assertThat(predicate).accepts((List<String>) null);
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("list has the specified size")
                void listHasSpecifiedSize() {
                    Predicate<List<String>> predicate = notHaveSize(1);
                    assertThat(predicate).rejects(singletonList("item"));
                }

                @Test
                @DisplayName("list is empty and specified size is zero")
                void listIsEmptyAndSpecifiedSizeIsZero() {
                    Predicate<List<String>> predicate = notHaveSize(0);
                    assertThat(predicate).rejects(new ArrayList<>());
                }

                @Test
                @DisplayName("list is null and specified size is zero")
                void listIsNullAndSpecifiedSizeIsZero() {
                    Predicate<List<String>> predicate = notHaveSize(0);
                    assertThat(predicate).rejects((List<String>) null);
                }
            }
        }

        @Nested
        @DisplayName("contains(element)")
        class Contains {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("list contains the specified element")
                void listContainsSpecifiedElement() {
                    Predicate<List<String>> predicate = containsElement("item");
                    assertThat(predicate).accepts(singletonList("item"));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("list does not contain the specified element")
                void listDoesNotContainSpecifiedElement() {
                    Predicate<List<String>> predicate = containsElement("item");
                    assertThat(predicate).rejects(singletonList("anotherItem"));
                }

                @Test
                @DisplayName("list is null")
                void listIsNull() {
                    Predicate<List<String>> predicate = containsElement("item");
                    assertThat(predicate).rejects((List<String>) null);
                }

                @Test
                @DisplayName("list is empty")
                void listIsEmpty() {
                    Predicate<List<String>> predicate = containsElement("item");
                    assertThat(predicate).rejects(new ArrayList<>());
                }
            }
        }

        @Nested
        @DisplayName("notContains(element)")
        class NotContains {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("list does not contain the specified element")
                void listDoesNotContainSpecifiedElement() {
                    Predicate<List<String>> predicate = notContainsElement("item");
                    assertThat(predicate).accepts(singletonList("anotherItem"));
                }

                @Test
                @DisplayName("list is null")
                void listIsNull() {
                    Predicate<List<String>> predicate = notContainsElement("item");
                    assertThat(predicate).accepts((List<String>) null);
                }

                @Test
                @DisplayName("list is empty")
                void listIsEmpty() {
                    Predicate<List<String>> predicate = notContainsElement("item");
                    assertThat(predicate).accepts(new ArrayList<>());
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("list contains the specified element")
                void listContainsSpecifiedElement() {
                    Predicate<List<String>> predicate = notContainsElement("item");
                    assertThat(predicate).rejects(singletonList("item"));
                }
            }
        }
    }

    @Nested
    @DisplayName("Number predicate")
    class NumberPredicate {

        @Nested
        @DisplayName("isGreaterThan()")
        class IsGreaterThan {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("number is greater than the provided value")
                void numberIsGreaterThanProvidedValue() {
                    Predicate<Integer> predicate = isGreaterThan(10);
                    assertThat(predicate).accepts(15);
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("number is less than the provided value")
                void numberIsLessThanProvidedValue() {
                    Predicate<Integer> predicate = isGreaterThan(10);
                    assertThat(predicate).rejects(5);
                }

                @Test
                @DisplayName("number is equal to the provided value")
                void numberIsEqualToProvidedValue() {
                    Predicate<Integer> predicate = isGreaterThan(10);
                    assertThat(predicate).rejects(10);
                }

                @Test
                @DisplayName("number is null")
                void numberIsNull() {
                    Predicate<Integer> predicate = isGreaterThan(10);
                    assertThat(predicate).rejects((Integer) null);
                }
            }
        }

        @Nested
        @DisplayName("isLessThan()")
        class IsLessThan {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("number is less than the provided value")
                void numberIsLessThanProvidedValue() {
                    Predicate<Integer> predicate = isLessThan(10);
                    assertThat(predicate).accepts(5);
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("number is greater than the provided value")
                void numberIsGreaterThanProvidedValue() {
                    Predicate<Integer> predicate = isLessThan(10);
                    assertThat(predicate).rejects(15);
                }

                @Test
                @DisplayName("number is equal to the provided value")
                void numberIsEqualToProvidedValue() {
                    Predicate<Integer> predicate = isLessThan(10);
                    assertThat(predicate).rejects(10);
                }

                @Test
                @DisplayName("number is null")
                void numberIsNull() {
                    Predicate<Integer> predicate = isLessThan(10);
                    assertThat(predicate).rejects((Integer) null);
                }
            }
        }

        @Nested
        @DisplayName("isGreaterThanOrEqualTo()")
        class IsGreaterThanOrEqualTo {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("number is greater than the provided value")
                void numberIsGreaterThanProvidedValue() {
                    Predicate<Integer> predicate = isGreaterThanOrEqualTo(10);
                    assertThat(predicate).accepts(15);
                }

                @Test
                @DisplayName("number is equal to the provided value")
                void numberIsEqualToProvidedValue() {
                    Predicate<Integer> predicate = isGreaterThanOrEqualTo(10);
                    assertThat(predicate).accepts(10);
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("number is less than the provided value")
                void numberIsLessThanProvidedValue() {
                    Predicate<Integer> predicate = isGreaterThanOrEqualTo(10);
                    assertThat(predicate).rejects(5);
                }

                @Test
                @DisplayName("number is null")
                void numberIsNull() {
                    Predicate<Integer> predicate = isGreaterThanOrEqualTo(10);
                    assertThat(predicate).rejects((Integer) null);
                }
            }
        }

        @Nested
        @DisplayName("isLessThanOrEqualTo()")
        class IsLessThanOrEqualTo {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("number is less than the provided value")
                void numberIsLessThanProvidedValue() {
                    Predicate<Integer> predicate = isLessThanOrEqualTo(10);
                    assertThat(predicate).accepts(5);
                }

                @Test
                @DisplayName("number is equal to the provided value")
                void numberIsEqualToProvidedValue() {
                    Predicate<Integer> predicate = isLessThanOrEqualTo(10);
                    assertThat(predicate).accepts(10);
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("number is greater than the provided value")
                void numberIsGreaterThanProvidedValue() {
                    Predicate<Integer> predicate = isLessThanOrEqualTo(10);
                    assertThat(predicate).rejects(15);
                }

                @Test
                @DisplayName("number is null")
                void numberIsNull() {
                    Predicate<Integer> predicate = isLessThanOrEqualTo(10);
                    assertThat(predicate).rejects((Integer) null);
                }
            }
        }

        @Nested
        @DisplayName("isBetween()")
        class IsBetween {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("number is within the range")
                void numberIsWithinRange() {
                    Predicate<Integer> predicate = isBetween(10, 20);
                    assertThat(predicate).accepts(15);
                }

                @Test
                @DisplayName("number is equal to the start of the range")
                void numberIsEqualToStartOfRange() {
                    Predicate<Integer> predicate = isBetween(10, 20);
                    assertThat(predicate).accepts(10);
                }

                @Test
                @DisplayName("number is equal to the end of the range")
                void numberIsEqualToEndOfRange() {
                    Predicate<Integer> predicate = isBetween(10, 20);
                    assertThat(predicate).accepts(20);
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("number is less than the start of the range")
                void numberIsLessThanStartOfRange() {
                    Predicate<Integer> predicate = isBetween(10, 20);
                    assertThat(predicate).rejects(5);
                }

                @Test
                @DisplayName("number is greater than the end of the range")
                void numberIsGreaterThanEndOfRange() {
                    Predicate<Integer> predicate = isBetween(10, 20);
                    assertThat(predicate).rejects(25);
                }

                @Test
                @DisplayName("number is null")
                void numberIsNull() {
                    Predicate<Integer> predicate = isBetween(10, 20);
                    assertThat(predicate).rejects((Integer) null);
                }
            }
        }

        @Nested
        @DisplayName("notBetween()")
        class NotBetween {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("number is less than the start of the range")
                void numberIsLessThanStartOfRange() {
                    Predicate<Integer> predicate = notBetween(10, 20);
                    assertThat(predicate).accepts(5);
                }

                @Test
                @DisplayName("number is greater than the end of the range")
                void numberIsGreaterThanEndOfRange() {
                    Predicate<Integer> predicate = notBetween(10, 20);
                    assertThat(predicate).accepts(25);
                }

                @Test
                @DisplayName("number is null")
                void numberIsNull() {
                    Predicate<Integer> predicate = notBetween(10, 20);
                    assertThat(predicate).accepts((Integer) null);
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("number is within the range")
                void numberIsWithinRange() {
                    Predicate<Integer> predicate = notBetween(10, 20);
                    assertThat(predicate).rejects(15);
                }

                @Test
                @DisplayName("number is equal to the start of the range")
                void numberIsEqualToStartOfRange() {
                    Predicate<Integer> predicate = notBetween(10, 20);
                    assertThat(predicate).rejects(10);
                }

                @Test
                @DisplayName("number is equal to the end of the range")
                void numberIsEqualToEndOfRange() {
                    Predicate<Integer> predicate = notBetween(10, 20);
                    assertThat(predicate).rejects(20);
                }
            }
        }

        @Nested
        @DisplayName("isEven()")
        class IsEven {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("number is even")
                void numberIsEven() {
                    Predicate<Number> predicate = isEven();
                    assertThat(predicate).accepts(4);
                }

                @Test
                @DisplayName("number is zero")
                void numberIsZero() {
                    Predicate<Number> predicate = isEven();
                    assertThat(predicate).accepts(0);
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("number is odd")
                void numberIsOdd() {
                    Predicate<Number> predicate = isEven();
                    assertThat(predicate).rejects(3);
                }

                @Test
                @DisplayName("number is null")
                void numberIsNull() {
                    Predicate<Number> predicate = isEven();
                    assertThat(predicate).rejects((Number) null);
                }
            }
        }

        @Nested
        @DisplayName("isOdd()")
        class IsOdd {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("number is odd")
                void numberIsOdd() {
                    Predicate<Number> predicate = isOdd();
                    assertThat(predicate).accepts(3);
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("number is even")
                void numberIsEven() {
                    Predicate<Number> predicate = isOdd();
                    assertThat(predicate).rejects(4);
                }

                @Test
                @DisplayName("number is null")
                void numberIsNull() {
                    Predicate<Number> predicate = isOdd();
                    assertThat(predicate).rejects((Number) null);
                }
            }
        }
    }

    @Nested
    @DisplayName("String predicate")
    class StringPredicate {

        @Nested
        @DisplayName("isEmpty()")
        class IsEmpty {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("string is empty")
                void stringIsEmpty() {
                    Predicate<String> predicate = isEmptyString();
                    assertThat(predicate).accepts("");
                }

                @Test
                @DisplayName("string is null")
                void stringIsNull() {
                    Predicate<String> predicate = isEmptyString();
                    assertThat(predicate).accepts((String) null);
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("string is not empty")
                void stringIsNotEmpty() {
                    Predicate<String> predicate = isEmptyString();
                    assertThat(predicate).rejects("Laptop");
                }
            }
        }

        @Nested
        @DisplayName("notEmpty()")
        class NotEmpty {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("string is not empty")
                void stringIsNotEmpty() {
                    Predicate<String> predicate = notEmptyString();
                    assertThat(predicate).accepts("Laptop");
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("string is empty")
                void stringIsEmpty() {
                    Predicate<String> predicate = notEmptyString();
                    assertThat(predicate).rejects("");
                }

                @Test
                @DisplayName("string is null")
                void stringIsNull() {
                    Predicate<String> predicate = notEmptyString();
                    assertThat(predicate).rejects((String) null);
                }
            }
        }

        @Nested
        @DisplayName("contains(substring)")
        class Contains {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("string contains the substring")
                void stringContainsSubstring() {
                    Predicate<String> predicate = containsSubstring("Lap");
                    assertThat(predicate).accepts("Laptop");
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("string does not contain the substring")
                void stringDoesNotContainSubstring() {
                    Predicate<String> predicate = containsSubstring("Phone");
                    assertThat(predicate).rejects("Laptop");
                }

                @Test
                @DisplayName("string is null")
                void stringIsNull() {
                    Predicate<String> predicate = containsSubstring("Lap");
                    assertThat(predicate).rejects((String) null);
                }
            }
        }

        @Nested
        @DisplayName("notContains(substring)")
        class NotContains {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("string does not contain the substring")
                void stringDoesNotContainSubstring() {
                    Predicate<String> predicate = notContainsSubstring("Phone");
                    assertThat(predicate).accepts("Laptop");
                }

                @Test
                @DisplayName("string is null")
                void stringIsNull() {
                    Predicate<String> predicate = notContainsSubstring("Lap");
                    assertThat(predicate).accepts((String) null);
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("string contains the substring")
                void stringContainsSubstring() {
                    Predicate<String> predicate = notContainsSubstring("Lap");
                    assertThat(predicate).rejects("Laptop");
                }
            }
        }

        @Nested
        @DisplayName("startsWith(prefix)")
        class StartsWith {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("string starts with the prefix")
                void stringStartsWithPrefix() {
                    Predicate<String> predicate = startsWith("Lap");
                    assertThat(predicate).accepts("Laptop");
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("string does not start with the prefix")
                void stringDoesNotStartWithPrefix() {
                    Predicate<String> predicate = startsWith("Comp");
                    assertThat(predicate).rejects("Laptop");
                }

                @Test
                @DisplayName("string is null")
                void stringIsNull() {
                    Predicate<String> predicate = startsWith("Lap");
                    assertThat(predicate).rejects((String) null);
                }

                @Test
                @DisplayName("prefix is null")
                void prefixIsNull() {
                    Predicate<String> predicate = startsWith(null);
                    assertThat(predicate).rejects("Laptop");
                }
            }
        }

        @Nested
        @DisplayName("notStartsWith(prefix)")
        class NotStartsWith {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("string does not start with the prefix")
                void stringDoesNotStartWithPrefix() {
                    Predicate<String> predicate = notStartsWith("Comp");
                    assertThat(predicate).accepts("Laptop");
                }

                @Test
                @DisplayName("string is null")
                void stringIsNull() {
                    Predicate<String> predicate = notStartsWith("Lap");
                    assertThat(predicate).accepts((String) null);
                }

                @Test
                @DisplayName("prefix is null")
                void prefixIsNull() {
                    Predicate<String> predicate = notStartsWith(null);
                    assertThat(predicate).accepts("Laptop");
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("string starts with the prefix")
                void stringStartsWithPrefix() {
                    Predicate<String> predicate = notStartsWith("Lap");
                    assertThat(predicate).rejects("Laptop");
                }
            }
        }

        @Nested
        @DisplayName("endsWith(suffix)")
        class EndsWith {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("string ends with the suffix")
                void stringEndsWithSuffix() {
                    Predicate<String> predicate = endsWith("top");
                    assertThat(predicate).accepts("Laptop");
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("string does not end with the suffix")
                void stringDoesNotEndWithSuffix() {
                    Predicate<String> predicate = endsWith("phone");
                    assertThat(predicate).rejects("Laptop");
                }

                @Test
                @DisplayName("string is null")
                void stringIsNull() {
                    Predicate<String> predicate = endsWith("top");
                    assertThat(predicate).rejects((String) null);
                }

                @Test
                @DisplayName("suffix is null")
                void suffixIsNull() {
                    Predicate<String> predicate = endsWith(null);
                    assertThat(predicate).rejects("Laptop");
                }
            }
        }

        @Nested
        @DisplayName("notEndsWith(suffix)")
        class NotEndsWith {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("string does not end with the suffix")
                void stringDoesNotEndWithSuffix() {
                    Predicate<String> predicate = notEndsWith("phone");
                    assertThat(predicate).accepts("Laptop");
                }

                @Test
                @DisplayName("string is null")
                void stringIsNull() {
                    Predicate<String> predicate = notEndsWith("top");
                    assertThat(predicate).accepts((String) null);
                }

                @Test
                @DisplayName("suffix is null")
                void suffixIsNull() {
                    Predicate<String> predicate = notEndsWith(null);
                    assertThat(predicate).accepts("Laptop");
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("string ends with the suffix")
                void stringEndsWithSuffix() {
                    Predicate<String> predicate = notEndsWith("top");
                    assertThat(predicate).rejects("Laptop");
                }
            }
        }

        @Nested
        @DisplayName("matches(regex)")
        class Matches {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("string matches the regex")
                void stringMatchesRegex() {
                    Predicate<String> predicate = matches("\\w+top");
                    assertThat(predicate).accepts("Laptop");
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("string does not match the regex")
                void stringDoesNotMatchRegex() {
                    Predicate<String> predicate = matches("\\d+");
                    assertThat(predicate).rejects("Laptop");
                }

                @Test
                @DisplayName("string is null")
                void stringIsNull() {
                    Predicate<String> predicate = matches("\\w+top");
                    assertThat(predicate).rejects((String) null);
                }

                @Test
                @DisplayName("regex is null")
                void regexIsNull() {
                    Predicate<String> predicate = matches(null);
                    assertThat(predicate).rejects("Laptop");
                }
            }
        }

        @Nested
        @DisplayName("notMatches(regex)")
        class NotMatches {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrue {

                @Test
                @DisplayName("string does not match the regex")
                void stringDoesNotMatchRegex() {
                    Predicate<String> predicate = notMatches("\\d+");
                    assertThat(predicate).accepts("Laptop");
                }

                @Test
                @DisplayName("string is null")
                void stringIsNull() {
                    Predicate<String> predicate = notMatches("\\w+top");
                    assertThat(predicate).accepts((String) null);
                }

                @Test
                @DisplayName("regex is null")
                void regexIsNull() {
                    Predicate<String> predicate = notMatches(null);
                    assertThat(predicate).accepts("Laptop");
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalse {

                @Test
                @DisplayName("string matches the regex")
                void stringMatchesRegex() {
                    Predicate<String> predicate = notMatches("\\w+top");
                    assertThat(predicate).rejects("Laptop");
                }
            }
        }
    }
}