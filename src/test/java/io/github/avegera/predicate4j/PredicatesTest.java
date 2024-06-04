package io.github.avegera.predicate4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static io.github.avegera.predicate4j.Predicates.*;
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
}