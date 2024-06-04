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

    @Nested
    @DisplayName("Predicate from method where().booleanValue(mapper)")
    class WhereBooleanImpl {

        @Nested
        @DisplayName("and method isEqualTo(value)")
        class IsEqualTo {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped value is equal to provided value")
                void mappedValueIsEqualToProvidedValue() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).isEqualTo(true);
                    assertThat(predicate).accepts(new Organization(true));
                }

                @Test
                @DisplayName("mapped value is null and provided value is null")
                void mappedValueIsNullAndProvidedValueIsNull() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).isEqualTo(null);
                    assertThat(predicate).accepts(new Organization(null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value is not equal to provided value")
                void mappedValueIsNotEqualToProvidedValue() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).isEqualTo(true);
                    assertThat(predicate).rejects(new Organization(false));
                }

                @Test
                @DisplayName("mapped value is not null and provided value is null")
                void mappedValueIsNotNullAndProvidedValueIsNull() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).isEqualTo(null);
                    assertThat(predicate).rejects(new Organization(false));
                }

                @Test
                @DisplayName("mapped value is null and provided value is not null")
                void mappedValueIsNullAndProvidedValueIsNotNull() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).isEqualTo(true);
                    assertThat(predicate).rejects(new Organization(null));
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
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).notEqualTo(true);
                    assertThat(predicate).accepts(new Organization(false));
                }

                @Test
                @DisplayName("mapped value is not null and provided value is null")
                void mappedValueIsNotNullAndProvidedValueIsNull() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).notEqualTo(null);
                    assertThat(predicate).accepts(new Organization(false));
                }

                @Test
                @DisplayName("mapped value is null and provided value is not null")
                void mappedValueIsNullAndProvidedValueIsNotNull() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).notEqualTo(true);
                    assertThat(predicate).accepts(new Organization(null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value is equal to provided value")
                void mappedValueIsEqualToProvidedValue() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).notEqualTo(true);
                    assertThat(predicate).rejects(new Organization(true));
                }

                @Test
                @DisplayName("mapped value is null and provided value is null")
                void mappedValueIsNullAndProvidedValueIsNull() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).notEqualTo(null);
                    assertThat(predicate).rejects(new Organization(null));
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
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).isNull();
                    assertThat(predicate).accepts(new Organization(null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value is not null")
                void mappedValueIsNotNull() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).isNull();
                    assertThat(predicate).rejects(new Organization(true));
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
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).notNull();
                    assertThat(predicate).accepts(new Organization(true));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value is null")
                void mappedValueIsNull() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).notNull();
                    assertThat(predicate).rejects(new Organization(null));
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
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).isInstanceOf(Boolean.class);
                    assertThat(predicate).accepts(new Organization(true));
                }

                @Test
                @DisplayName("mapped value is null and provided class is null")
                void mappedValueIsNullAndProvidedClassIsNull() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).isInstanceOf(null);
                    assertThat(predicate).rejects(new Organization(null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value is not instance of provided class")
                void mappedValueIsNotInstanceOfProvidedClass() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).isInstanceOf(String.class);
                    assertThat(predicate).rejects(new Organization(true));
                }

                @Test
                @DisplayName("mapped value is null and provided class is not null")
                void mappedValueIsNullAndProvidedClassIsNotNull() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).isInstanceOf(Boolean.class);
                    assertThat(predicate).rejects(new Organization(null));
                }

                @Test
                @DisplayName("mapped value is not null and provided class is null")
                void providedClassIsNull() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).isInstanceOf(null);
                    assertThat(predicate).rejects(new Organization(true));
                }

                @Test
                @DisplayName("mapped value is null and provided class is null")
                void mappedValueIsNullAndProvidedClassIsNull() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).isInstanceOf(null);
                    assertThat(predicate).rejects(new Organization(null));
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
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).notInstanceOf(String.class);
                    assertThat(predicate).accepts(new Organization(true));
                }

                @Test
                @DisplayName("mapped value is null and provided class is not null")
                void mappedValueIsNullAndProvidedClassIsNotNull() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).notInstanceOf(Boolean.class);
                    assertThat(predicate).accepts(new Organization(null));
                }

                @Test
                @DisplayName("provided class is null")
                void providedClassIsNull() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).notInstanceOf(null);
                    assertThat(predicate).accepts(new Organization(true));
                }

                @Test
                @DisplayName("mapped value is null and provided class is null")
                void mappedValueIsNullAndProvidedClassIsNull() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).notInstanceOf(null);
                    assertThat(predicate).accepts(new Organization(null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value is instance of provided class")
                void mappedValueIsInstanceOfProvidedClass() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).notInstanceOf(Boolean.class);
                    assertThat(predicate).rejects(new Organization(true));
                }
            }
        }

        @Nested
        @DisplayName("and method isTrue()")
        class IsTrue {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped value is true")
                void mappedValueIsTrue() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).isTrue();
                    assertThat(predicate).accepts(new Organization(true));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value is false")
                void mappedValueIsFalse() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).isTrue();
                    assertThat(predicate).rejects(new Organization(false));
                }

                @Test
                @DisplayName("mapped value is null")
                void mappedValueIsNull() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).isTrue();
                    assertThat(predicate).rejects(new Organization(null));
                }
            }
        }

        @Nested
        @DisplayName("and method notTrue()")
        class NotTrue {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped value is false")
                void mappedValueIsFalse() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).notTrue();
                    assertThat(predicate).accepts(new Organization(false));
                }

                @Test
                @DisplayName("mapped value is null")
                void mappedValueIsNull() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).notTrue();
                    assertThat(predicate).accepts(new Organization(null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value is true")
                void mappedValueIsTrue() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).notTrue();
                    assertThat(predicate).rejects(new Organization(true));
                }
            }
        }

        @Nested
        @DisplayName("and method isFalse()")
        class IsFalse {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped value is false")
                void mappedValueIsFalse() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).isFalse();
                    assertThat(predicate).accepts(new Organization(false));
                }

                @Test
                @DisplayName("mapped value is Boolean.FALSE")
                void mappedValueIsNotNullAndFalse() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).isFalse();
                    assertThat(predicate).accepts(new Organization(Boolean.FALSE));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value is true")
                void mappedValueIsTrue() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).isFalse();
                    assertThat(predicate).rejects(new Organization(true));
                }

                @Test
                @DisplayName("mapped value is null")
                void mappedValueIsNull() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).isFalse();
                    assertThat(predicate).rejects(new Organization(null));
                }
            }
        }

        @Nested
        @DisplayName("and method notFalse()")
        class NotFalse {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped value is true")
                void mappedValueIsTrue() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).notFalse();
                    assertThat(predicate).accepts(new Organization(true));
                }

                @Test
                @DisplayName("mapped value is null")
                void mappedValueIsNull() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).notFalse();
                    assertThat(predicate).accepts(new Organization(null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value is false")
                void mappedValueIsFalse() {
                    Predicate<Organization> predicate = where().booleanValue(Organization::active).notFalse();
                    assertThat(predicate).rejects(new Organization(false));
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

class Organization {

    private final Boolean active;

    public Organization(Boolean active) {
        this.active = active;
    }

    public Boolean active() {
        return active;
    }
}