package io.github.avegera.predicate4j;

import io.github.avegera.predicate4j.test.Address;
import io.github.avegera.predicate4j.test.Organization;
import io.github.avegera.predicate4j.test.User;
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
        @DisplayName(".isEqualTo(value)")
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
        @DisplayName(".notEqualTo(value)")
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
        @DisplayName(".isNull()")
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
        @DisplayName(".notNull()")
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
        @DisplayName(".isInstanceOf(clazz)")
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
        @DisplayName(".notInstanceOf(clazz)")
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
        @DisplayName(".isEqualTo(value)")
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
        @DisplayName(".notEqualTo(value)")
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
        @DisplayName(".isNull()")
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
        @DisplayName(".notNull()")
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
        @DisplayName(".isInstanceOf(clazz)")
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
        @DisplayName(".notInstanceOf(clazz)")
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
        @DisplayName(".isTrue()")
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
        @DisplayName(".notTrue()")
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
        @DisplayName(".isFalse()")
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
        @DisplayName(".notFalse()")
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

    @Nested
    @DisplayName("Logical conjunction of")
    class LogicalConjunctionOf {

        @Nested
        @DisplayName("2 predicates")
        class TwoPredicates {

            private final Predicate<Address> predicateConjunction = where(Address::country).isEqualTo("USA").and(Address::zipCode).isNull();

            @Nested
            @DisplayName("returns true when")
            class ReturnTrueWhen {

                @Test
                @DisplayName("all two predicates are true")
                void allPredicatesAreTrue() {
                    Address address = new Address("USA", null, null);
                    assertThat(predicateConjunction).accepts(address);
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnFalseWhen {

                @Test
                @DisplayName("object is null")
                void objectIsNull() {
                    assertThat(predicateConjunction).rejects((Address) null);
                }

                @Test
                @DisplayName("first predicate is false")
                void firstPredicateIsFalse() {
                    Address address = new Address("Ireland", null, null);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("second predicate is false")
                void secondPredicateIsFalse() {
                    Address address = new Address("USA", null, 123);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("both predicates are false")
                void bothPredicatesAreFalse() {
                    Address address = new Address("Ireland", null, 123);
                    assertThat(predicateConjunction).rejects(address);
                }
            }
        }

        @Nested
        @DisplayName("3 predicates")
        class ThreePredicates {

            private final Predicate<Address> predicateConjunction = where(Address::country).notNull()
                    .and(Address::value).notEqualTo("testValue")
                    .and(Address::zipCode).isInstanceOf(Integer.class);

            @Nested
            @DisplayName("returns true when")
            class ReturnTrueWhen {

                @Test
                @DisplayName("all predicates are true")
                void allPredicatesAreTrue() {
                    Address address = new Address("USA", "someValue", 123);
                    assertThat(predicateConjunction).accepts(address);
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnFalseWhen {

                @Test
                @DisplayName("object is null")
                void objectIsNull() {
                    assertThat(predicateConjunction).rejects((Address) null);
                }

                @Test
                @DisplayName("first predicate is false")
                void firstPredicateIsFalse() {
                    Address address = new Address(null, null, null);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("second predicate is false")
                void secondPredicateIsFalse() {
                    Address address = new Address("USA", "testValue", 123);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("third predicate is false")
                void thirdPredicateIsFalse() {
                    Address address = new Address("USA", "someValue", null);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("two predicates are false")
                void twoPredicatesAreFalse() {
                    Address address = new Address(null, "testValue", 123);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("all predicates are false")
                void allPredicatesAreFalse() {
                    Address address = new Address(null, "testValue", null);
                    assertThat(predicateConjunction).rejects(address);
                }
            }
        }

        @Nested
        @DisplayName("7 predicates")
        class SevenPredicates {

            private final Predicate<Address> predicateConjunction = where(Address::country).notNull()
                    .and(Address::zipCode).notNull()
                    .and(Address::value).notNull()
                    .and(Address::country).notEqualTo("USA")
                    .and(Address::country).notEqualTo("Canada")
                    .and(Address::value).isEqualTo("testValue")
                    .and(Address::zipCode).isEqualTo(123);

            @Nested
            @DisplayName("returns true when")
            class ReturnTrueWhen {

                @Test
                @DisplayName("all predicates are true")
                void mappedValueIsFalse() {
                    Address address = new Address("notCanadaAndUSA", "testValue", 123);
                    assertThat(predicateConjunction).accepts(address);
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnFalseWhen {

                @Test
                @DisplayName("object is null")
                void objectIsNull() {
                    assertThat(predicateConjunction).rejects((Address) null);
                }

                @Test
                @DisplayName("first predicate is false")
                void firstPredicateIsFalse() {
                    Address address = new Address(null, "testValue", 123);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("second predicate is false")
                void secondPredicateIsFalse() {
                    Address address = new Address("notCanadaAndUSA", null, 123);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("third predicate is false")
                void thirdPredicateIsFalse() {
                    Address address = new Address("notCanadaAndUSA", "testValue", null);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("fourth predicate is false")
                void fourthPredicateIsFalse() {
                    Address address = new Address("USA", "testValue", 123);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("fifth predicate is false")
                void fifthPredicateIsFalse() {
                    Address address = new Address("Canada", "testValue", 123);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("sixth predicate is false")
                void sixthPredicateIsFalse() {
                    Address address = new Address("notCanadaAndUSA", "anotherValue", 123);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("seventh predicate is false")
                void seventhPredicateIsFalse() {
                    Address address = new Address("notCanadaAndUSA", "testValue", 456);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("three predicates are false")
                void threePredicatesAreFalse() {
                    Address address = new Address("USA", "anotherValue", 456);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("four predicates are false")
                void fourPredicatesAreFalse() {
                    Address address = new Address("USA", "anotherValue", null);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("all predicates are false")
                void allPredicatesAreFalse() {
                    Address address = new Address(null, null, null);
                    assertThat(predicateConjunction).rejects(address);
                }

            }
        }
    }
}