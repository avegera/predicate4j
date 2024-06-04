package io.github.avegera.predicate4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static io.github.avegera.predicate4j.Where.where;
import static org.assertj.core.api.Assertions.assertThat;

class WhereTest {

    private static final int TEST_ID_1 = 123;

    private static final int TEST_ID_2 = 456;

    @Nested
    @DisplayName("Predicate from method where(mapper)")
    class WhereObjectImpl {

        @Nested
        @DisplayName("and method isEqualTo(value)")
        class IsEqualTo {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped value is equal to provided value")
                void mappedValueIsEqualToProvidedValue() {
                    Predicate<User> predicate = where(User::id).isEqualTo(TEST_ID_1);
                    assertThat(predicate).accepts(new User(TEST_ID_1));
                }

                @Test
                @DisplayName("mapped value is null and provided value is null")
                void mappedValueIsNullAndProvidedValueIsNull() {
                    Predicate<User> predicate = where(User::id).isEqualTo(null);
                    assertThat(predicate).accepts(new User(null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value is not equal to provided value")
                void mappedValueIsNotEqualToProvidedValue() {
                    Predicate<User> predicate = where(User::id).isEqualTo(TEST_ID_1);
                    assertThat(predicate).rejects(new User(TEST_ID_2));
                }

                @Test
                @DisplayName("mapped value is not null and provided value is null")
                void mappedValueIsNotNullAndProvidedValueIsNull() {
                    Predicate<User> predicate = where(User::id).isEqualTo(null);
                    assertThat(predicate).rejects(new User(TEST_ID_2));
                }

                @Test
                @DisplayName("mapped value is null and provided value is not null")
                void mappedValueIsNullAndProvidedValueIsNotNull() {
                    Predicate<User> predicate = where(User::id).isEqualTo(TEST_ID_1);
                    assertThat(predicate).rejects(new User(null));
                }
            }
        }

        @Nested
        @DisplayName("and method notEqualTo(value)")
        class NotEqualTo {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped value is not equal to provided value")
                void mappedValueIsNotEqualToProvidedValue() {
                    Predicate<User> predicate = where(User::id).notEqualTo(TEST_ID_1);
                    assertThat(predicate).accepts(new User(TEST_ID_2));
                }

                @Test
                @DisplayName("mapped value is not null and provided value is null")
                void mappedValueIsNotNullAndProvidedValueIsNull() {
                    Predicate<User> predicate = where(User::id).notEqualTo(null);
                    assertThat(predicate).accepts(new User(TEST_ID_2));
                }

                @Test
                @DisplayName("mapped value is null and provided value is not null")
                void mappedValueIsNullAndProvidedValueIsNotNull() {
                    Predicate<User> predicate = where(User::id).notEqualTo(TEST_ID_1);
                    assertThat(predicate).accepts(new User(null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value is equal to provided value")
                void mappedValueIsEqualToProvidedValue() {
                    Predicate<User> predicate = where(User::id).notEqualTo(TEST_ID_1);
                    assertThat(predicate).rejects(new User(TEST_ID_1));
                }

                @Test
                @DisplayName("mapped value is null and provided value is null")
                void mappedValueIsNullAndProvidedValueIsNull() {
                    Predicate<User> predicate = where(User::id).notEqualTo(null);
                    assertThat(predicate).rejects(new User(null));
                }
            }
        }

        @Nested
        @DisplayName("and method isNull()")
        class IsNull {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped value is null")
                void mappedValueIsNull() {
                    Predicate<User> predicate = where(User::id).isNull();
                    assertThat(predicate).accepts(new User(null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value is not null")
                void mappedValueIsNotNull() {
                    Predicate<User> predicate = where(User::id).isNull();
                    assertThat(predicate).rejects(new User(TEST_ID_1));
                }
            }
        }

        @Nested
        @DisplayName("and method notNull()")
        class NotNull {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped value is not null")
                void mappedValueIsNotNull() {
                    Predicate<User> predicate = where(User::id).notNull();
                    assertThat(predicate).accepts(new User(TEST_ID_1));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value is null")
                void mappedValueIsNull() {
                    Predicate<User> predicate = where(User::id).notNull();
                    assertThat(predicate).rejects(new User(null));
                }
            }
        }

        @Nested
        @DisplayName("and method isInstanceOf(clazz)")
        class IsInstanceOf {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped value is instance of provided class")
                void mappedValueIsInstanceOfProvidedClass() {
                    Predicate<User> predicate = where(User::id).isInstanceOf(Integer.class);
                    assertThat(predicate).accepts(new User(TEST_ID_1));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value is not instance of provided class")
                void mappedValueIsNotInstanceOfProvidedClass() {
                    Predicate<User> predicate = where(User::id).isInstanceOf(String.class);
                    assertThat(predicate).rejects(new User(TEST_ID_1));
                }

                @Test
                @DisplayName("mapped value is null and provided class is not null")
                void mappedValueIsNullAndProvidedClassIsNotNull() {
                    Predicate<User> predicate = where(User::id).isInstanceOf(Integer.class);
                    assertThat(predicate).rejects(new User(null));
                }

                @Test
                @DisplayName("mapped value is not null and provided class is null")
                void providedClassIsNull() {
                    Predicate<User> predicate = where(User::id).isInstanceOf(null);
                    assertThat(predicate).rejects(new User(TEST_ID_1));
                }

                @Test
                @DisplayName("mapped value is null and provided class is null")
                void mappedValueIsNullAndProvidedClassIsNull() {
                    Predicate<User> predicate = where(User::id).isInstanceOf(null);
                    assertThat(predicate).rejects(new User(null));
                }
            }
        }

        @Nested
        @DisplayName("and method notInstanceOf(clazz)")
        class NotInstanceOf {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped value is not instance of provided class")
                void mappedValueIsNotInstanceOfProvidedClass() {
                    Predicate<User> predicate = where(User::id).notInstanceOf(String.class);
                    assertThat(predicate).accepts(new User(TEST_ID_1));
                }

                @Test
                @DisplayName("mapped value is null and provided class is not null")
                void mappedValueIsNullAndProvidedClassIsNotNull() {
                    Predicate<User> predicate = where(User::id).notInstanceOf(Integer.class);
                    assertThat(predicate).accepts(new User(null));
                }

                @Test
                @DisplayName("mapped value is not null and provided class is null")
                void providedClassIsNull() {
                    Predicate<User> predicate = where(User::id).notInstanceOf(null);
                    assertThat(predicate).accepts(new User(TEST_ID_1));
                }

                @Test
                @DisplayName("mapped value is null and provided class is null")
                void mappedValueIsNullAndProvidedClassIsNull() {
                    Predicate<User> predicate = where(User::id).notInstanceOf(null);
                    assertThat(predicate).accepts(new User(null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value is instance of provided class")
                void mappedValueIsInstanceOfProvidedClass() {
                    Predicate<User> predicate = where(User::id).notInstanceOf(Integer.class);
                    assertThat(predicate).rejects(new User(TEST_ID_1));
                }
            }
        }
    }
}

class User {

    private final Integer id;

    public User(Integer id) {
        this.id = id;
    }

    public Integer id() {
        return id;
    }
}