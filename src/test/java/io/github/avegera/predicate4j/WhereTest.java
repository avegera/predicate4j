package io.github.avegera.predicate4j;

import io.github.avegera.predicate4j.test.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import static io.github.avegera.predicate4j.Where.where;
import static java.util.Arrays.asList;
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
    @DisplayName("Predicate from method where().list(mapper)")
    class WhereListImpl {

        @Nested
        @DisplayName(".isEqualTo(value)")
        class IsEqualTo {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped list is equal to provided list")
                void mappedListIsEqualToProvidedList() {
                    List<String> roles = asList("Admin", "User");
                    Predicate<Customer> predicate = where().list(Customer::roles).isEqualTo(roles);
                    assertThat(predicate).accepts(new Customer(roles, null));
                }

                @Test
                @DisplayName("mapped list is equal to provided list with different list implementations")
                void mappedListIsEqualToProvidedListWithDifferentListImplementations() {
                    List<String> roles = new LinkedList<>();
                    roles.add("Admin");
                    roles.add("User");
                    Predicate<Customer> predicate = where().list(Customer::roles).isEqualTo(roles);
                    assertThat(predicate).accepts(new Customer(asList("Admin", "User"), null));
                }

                @Test
                @DisplayName("mapped list is null and provided list is null")
                void mappedListIsNullAndProvidedListIsNull() {
                    Predicate<Customer> predicate = where().list(Customer::roles).isEqualTo(null);
                    assertThat(predicate).accepts(new Customer(null, null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped list is not equal to provided list")
                void mappedListIsNotEqualToProvidedList() {
                    Predicate<Customer> predicate = where().list(Customer::roles).isEqualTo(asList("Manager", "SuperAdmin"));
                    assertThat(predicate).rejects(new Customer(asList("Admin", "User"), null));
                }

                @Test
                @DisplayName("mapped list is not empty and provided list is null")
                void mappedListIsNotEmptyAndProvidedListIsNull() {
                    Predicate<Customer> predicate = where().list(Customer::roles).isEqualTo(null);
                    assertThat(predicate).rejects(new Customer(asList("Admin", "User"), null));
                }

                @Test
                @DisplayName("mapped list is not empty and provided list is empty")
                void mappedListIsNotEmptyAndProvidedListIsEmpty() {
                    Predicate<Customer> predicate = where().list(Customer::roles).isEqualTo(new ArrayList<>());
                    assertThat(predicate).rejects(new Customer(asList("Admin", "User"), null));
                }

                @Test
                @DisplayName("mapped list is null and provided list is not empty")
                void mappedListIsNullAndProvidedListIsNotEmpty() {
                    Predicate<Customer> predicate = where().list(Customer::roles).isEqualTo(asList("Admin", "User"));
                    assertThat(predicate).rejects(new Customer(null, null));
                }

                @Test
                @DisplayName("mapped list is empty and provided value is not empty")
                void mappedListIsEmptyAndProvidedListIsNotEmpty() {
                    Predicate<Customer> predicate = where().list(Customer::roles).isEqualTo(asList("Admin", "User"));
                    assertThat(predicate).rejects(new Customer(new ArrayList<>(), null));
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
                @DisplayName("mapped list is not equal to provided list")
                void mappedListIsNotEqualToProvidedList() {
                    Predicate<Customer> predicate = where().list(Customer::roles).notEqualTo(asList("Manager", "SuperAdmin"));
                    assertThat(predicate).accepts(new Customer(asList("Admin", "User"), null));
                }

                @Test
                @DisplayName("mapped list is not empty and provided list is null")
                void mappedListIsNotEmptyAndProvidedListIsNull() {
                    Predicate<Customer> predicate = where().list(Customer::roles).notEqualTo(null);
                    assertThat(predicate).accepts(new Customer(asList("Admin", "User"), null));
                }

                @Test
                @DisplayName("mapped list is not empty and provided list is empty")
                void mappedListIsNotEmptyAndProvidedListIsEmpty() {
                    Predicate<Customer> predicate = where().list(Customer::roles).notEqualTo(new ArrayList<>());
                    assertThat(predicate).accepts(new Customer(asList("Admin", "User"), null));
                }

                @Test
                @DisplayName("mapped list is null and provided list is not empty")
                void mappedListIsNullAndProvidedListIsNotEmpty() {
                    Predicate<Customer> predicate = where().list(Customer::roles).notEqualTo(asList("Admin", "User"));
                    assertThat(predicate).accepts(new Customer(null, null));
                }

                @Test
                @DisplayName("mapped list is empty and provided value is not empty")
                void mappedListIsEmptyAndProvidedListIsNotEmpty() {
                    Predicate<Customer> predicate = where().list(Customer::roles).notEqualTo(asList("Admin", "User"));
                    assertThat(predicate).accepts(new Customer(new ArrayList<>(), null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped list is equal to provided list")
                void mappedListIsEqualToProvidedList() {
                    List<String> roles = asList("Admin", "User");
                    Predicate<Customer> predicate = where().list(Customer::roles).notEqualTo(roles);
                    assertThat(predicate).rejects(new Customer(roles, null));
                }

                @Test
                @DisplayName("mapped list is equal to provided list with different list implementations")
                void mappedListIsEqualToProvidedListWithDifferentListImplementations() {
                    List<String> roles = new LinkedList<>();
                    roles.add("Admin");
                    roles.add("User");
                    Predicate<Customer> predicate = where().list(Customer::roles).notEqualTo(roles);
                    assertThat(predicate).rejects(new Customer(asList("Admin", "User"), null));
                }

                @Test
                @DisplayName("mapped list is null and provided list is null")
                void mappedListIsNullAndProvidedListIsNull() {
                    Predicate<Customer> predicate = where().list(Customer::roles).notEqualTo(null);
                    assertThat(predicate).rejects(new Customer(null, null));
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
                @DisplayName("mapped list is null")
                void mappedListIsNull() {
                    Predicate<Customer> predicate = where().list(Customer::roles).isNull();
                    assertThat(predicate).accepts(new Customer(null, null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped list is empty")
                void mappedListIsEmpty() {
                    Predicate<Customer> predicate = where().list(Customer::roles).isNull();
                    assertThat(predicate).rejects(new Customer(new ArrayList<>(), null));
                }

                @Test
                @DisplayName("mapped list is not empty")
                void mappedListIsNotEmpty() {
                    Predicate<Customer> predicate = where().list(Customer::roles).isNull();
                    assertThat(predicate).rejects(new Customer(asList("Admin", "User"), null));
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
                @DisplayName("mapped list is empty")
                void mappedListIsEmpty() {
                    Predicate<Customer> predicate = where().list(Customer::roles).notNull();
                    assertThat(predicate).accepts(new Customer(new ArrayList<>(), null));
                }

                @Test
                @DisplayName("mapped list is not empty")
                void mappedListIsNotEmpty() {
                    Predicate<Customer> predicate = where().list(Customer::roles).notNull();
                    assertThat(predicate).accepts(new Customer(asList("Admin", "User"), null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped list is null")
                void mappedListIsNull() {
                    Predicate<Customer> predicate = where().list(Customer::roles).notNull();
                    assertThat(predicate).rejects(new Customer(null, null));
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
                @DisplayName("mapped list is instance of provided class")
                void mappedListIsInstanceOfProvidedClass() {
                    List<String> list = new ArrayList<>();
                    list.add("Admin");
                    list.add("User");
                    Predicate<Customer> predicate = where().list(Customer::roles).isInstanceOf(ArrayList.class);
                    assertThat(predicate).accepts(new Customer(list, null));
                }

                @Test
                @DisplayName("mapped list is null and provided class is null")
                void mappedListIsNullAndProvidedClassIsNull() {
                    Predicate<Customer> predicate = where().list(Customer::roles).isInstanceOf(null);
                    assertThat(predicate).rejects(new Customer(null, null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped list is not instance of provided class")
                void mappedListIsNotInstanceOfProvidedClass() {
                    Predicate<Customer> predicate = where().list(Customer::roles).isInstanceOf(LinkedList.class);
                    assertThat(predicate).rejects(new Customer(asList("Admin", "User"), null));
                }

                @Test
                @DisplayName("mapped list is null and provided class is not null")
                void mappedListIsNullAndProvidedClassIsNotNull() {
                    Predicate<Customer> predicate = where().list(Customer::roles).isInstanceOf(List.class);
                    assertThat(predicate).rejects(new Customer(null, null));
                }

                @Test
                @DisplayName("mapped list is not null and provided class is null")
                void mappedListIsNotNullAndProvidedClassIsNull() {
                    Predicate<Customer> predicate = where().list(Customer::roles).isInstanceOf(null);
                    assertThat(predicate).rejects(new Customer(asList("Admin", "User"), null));
                }

                @Test
                @DisplayName("mapped list is null and provided class is null")
                void mappedListIsNullAndProvidedClassIsNull() {
                    Predicate<Customer> predicate = where().list(Customer::roles).isInstanceOf(null);
                    assertThat(predicate).rejects(new Customer(null, null));
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
                @DisplayName("mapped list is not instance of provided class")
                void mappedListIsNotInstanceOfProvidedClass() {
                    Predicate<Customer> predicate = where().list(Customer::roles).notInstanceOf(LinkedList.class);
                    assertThat(predicate).accepts(new Customer(asList("Admin", "User"), null));
                }

                @Test
                @DisplayName("mapped list is not null and provided class is null")
                void mappedListIsNotNullAndProvidedClassIsNull() {
                    Predicate<Customer> predicate = where().list(Customer::roles).notInstanceOf(null);
                    assertThat(predicate).accepts(new Customer(asList("Admin", "User"), null));
                }

                @Test
                @DisplayName("mapped list is null and provided class is not null")
                void mappedListIsNullAndProvidedClassIsNotNull() {
                    Predicate<Customer> predicate = where().list(Customer::roles).notInstanceOf(ArrayList.class);
                    assertThat(predicate).accepts(new Customer(null, null));
                }

                @Test
                @DisplayName("mapped list is null and provided class is null")
                void mappedListIsNullAndProvidedClassIsNull() {
                    Predicate<Customer> predicate = where().list(Customer::roles).notInstanceOf(null);
                    assertThat(predicate).accepts(new Customer(null, null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped list is instance of provided class")
                void mappedListIsInstanceOfProvidedClass() {
                    List<String> list = new ArrayList<>();
                    list.add("Admin");
                    list.add("User");
                    Predicate<Customer> predicate = where().list(Customer::roles).notInstanceOf(ArrayList.class);
                    assertThat(predicate).rejects(new Customer(list, null));
                }
            }
        }


        @Nested
        @DisplayName(".isEmpty()")
        class IsEmpty {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped list is null")
                void mappedListIsNull() {
                    Predicate<Customer> predicate = where().list(Customer::roles).isEmpty();
                    assertThat(predicate).accepts(new Customer(null, null));
                }

                @Test
                @DisplayName("mapped list is empty")
                void mappedListIsEmpty() {
                    Predicate<Customer> predicate = where().list(Customer::roles).isEmpty();
                    assertThat(predicate).accepts(new Customer(new ArrayList<>(), null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped list is not empty")
                void mappedListIsNotEmpty() {
                    Predicate<Customer> predicate = where().list(Customer::roles).isEmpty();
                    assertThat(predicate).rejects(new Customer(asList("Admin", "User"), null));
                }
            }
        }

        @Nested
        @DisplayName(".notEmpty()")
        class NotEmpty {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped list is not empty")
                void mappedListIsNotEmpty() {
                    Predicate<Customer> predicate = where().list(Customer::roles).notEmpty();
                    assertThat(predicate).accepts(new Customer(asList("Admin", "User"), null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped list is null")
                void mappedListIsNull() {
                    Predicate<Customer> predicate = where().list(Customer::roles).notEmpty();
                    assertThat(predicate).rejects(new Customer(null, null));
                }

                @Test
                @DisplayName("mapped list is empty")
                void mappedListIsEmpty() {
                    Predicate<Customer> predicate = where().list(Customer::roles).notEmpty();
                    assertThat(predicate).rejects(new Customer(new ArrayList<>(), null));
                }
            }
        }

        @Nested
        @DisplayName(".hasSize(size)")
        class HasSize {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped list has the specified size")
                void mappedListHasSpecifiedSize() {
                    Predicate<Customer> predicate = where().list(Customer::roles).hasSize(2);
                    assertThat(predicate).accepts(new Customer(asList("Admin", "User"), null));
                }

                @Test
                @DisplayName("mapped list is empty and specified size is zero")
                void mappedListIsEmptyAndSpecifiedSizeIsZero() {
                    Predicate<Customer> predicate = where().list(Customer::roles).hasSize(0);
                    assertThat(predicate).accepts(new Customer(new ArrayList<>(), null));
                }

                @Test
                @DisplayName("mapped list is null and specified size is zero")
                void mappedListIsNullAndSpecifiedSizeIsZero() {
                    Predicate<Customer> predicate = where().list(Customer::roles).hasSize(0);
                    assertThat(predicate).accepts(new Customer(new ArrayList<>(), null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped list has a different size than specified")
                void mappedListHasDifferentSize() {
                    Predicate<Customer> predicate = where().list(Customer::roles).hasSize(3);
                    assertThat(predicate).rejects(new Customer(asList("Admin", "User"), null));
                }

                @Test
                @DisplayName("mapped list is empty and size is not zero")
                void mappedListIsEmptyAndSizeIsNotZero() {
                    Predicate<Customer> predicate = where().list(Customer::roles).hasSize(1);
                    assertThat(predicate).rejects(new Customer(new ArrayList<>(), null));
                }

                @Test
                @DisplayName("mapped list is null and size is not zero")
                void mappedListIsNullAndSizeIsNotZero() {
                    Predicate<Customer> predicate = where().list(Customer::roles).hasSize(2);
                    assertThat(predicate).rejects(new Customer(null, null));
                }
            }
        }

        @Nested
        @DisplayName(".notHaveSize(size)")
        class NotHaveSize {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped list does not have the specified size")
                void mappedListDoesNotHaveSpecifiedSize() {
                    Predicate<Customer> predicate = where().list(Customer::roles).notHaveSize(3);
                    assertThat(predicate).accepts(new Customer(asList("Admin", "User"), null));
                }

                @Test
                @DisplayName("mapped list is null and specified size is not zero")
                void mappedListIsNullAndSpecifiedSizeIsNotZero() {
                    Predicate<Customer> predicate = where().list(Customer::roles).notHaveSize(2);
                    assertThat(predicate).accepts(new Customer(null, null));
                }

                @Test
                @DisplayName("mapped list is empty and size is not zero")
                void mappedListIsEmptyAndSizeIsNotZero() {
                    Predicate<Customer> predicate = where().list(Customer::roles).notHaveSize(1);
                    assertThat(predicate).accepts(new Customer(new ArrayList<>(), null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped list has the specified size")
                void mappedListHasSpecifiedSize() {
                    Predicate<Customer> predicate = where().list(Customer::roles).notHaveSize(2);
                    assertThat(predicate).rejects(new Customer(asList("Admin", "User"), null));
                }

                @Test
                @DisplayName("mapped list is empty and specified size is zero")
                void mappedListIsEmptyAndSpecifiedSizeIsZero() {
                    Predicate<Customer> predicate = where().list(Customer::roles).notHaveSize(0);
                    assertThat(predicate).rejects(new Customer(new ArrayList<>(), null));
                }

                @Test
                @DisplayName("mapped list is null and specified size is zero")
                void mappedListIsNullAndSpecifiedSizeIsZero() {
                    Predicate<Customer> predicate = where().list(Customer::roles).notHaveSize(0);
                    assertThat(predicate).rejects(new Customer(new ArrayList<>(), null));
                }
            }
        }

        @Nested
        @DisplayName(".contains(element)")
        class Contains {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped list contains the specified element")
                void mappedListContainsSpecifiedElement() {
                    Predicate<Customer> predicate = where().list(Customer::roles).contains("Admin");
                    assertThat(predicate).accepts(new Customer(asList("Admin", "User"), null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped list does not contain the specified element")
                void mappedListDoesNotContainSpecifiedElement() {
                    Predicate<Customer> predicate = where().list(Customer::roles).contains("Manager");
                    assertThat(predicate).rejects(new Customer(asList("Admin", "User"), null));
                }

                @Test
                @DisplayName("mapped list is null")
                void mappedListIsNull() {
                    Predicate<Customer> predicate = where().list(Customer::roles).contains("Admin");
                    assertThat(predicate).rejects(new Customer(null, null));
                }

                @Test
                @DisplayName("mapped list is empty")
                void mappedListIsEmpty() {
                    Predicate<Customer> predicate = where().list(Customer::roles).contains("Admin");
                    assertThat(predicate).rejects(new Customer(new ArrayList<>(), null));
                }
            }
        }

        @Nested
        @DisplayName(".notContain(element)")
        class NotContain {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped list does not contain the specified element")
                void mappedListDoesNotContainSpecifiedElement() {
                    Predicate<Customer> predicate = where().list(Customer::roles).notContains("Manager");
                    assertThat(predicate).accepts(new Customer(asList("Admin", "User"), null));
                }

                @Test
                @DisplayName("mapped list is null")
                void mappedListIsNull() {
                    Predicate<Customer> predicate = where().list(Customer::roles).notContains("Admin");
                    assertThat(predicate).accepts(new Customer(null, null));
                }

                @Test
                @DisplayName("mapped list is empty")
                void mappedListIsEmpty() {
                    Predicate<Customer> predicate = where().list(Customer::roles).notContains("Admin");
                    assertThat(predicate).accepts(new Customer(new ArrayList<>(), null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped list contains the specified element")
                void mappedListContainsSpecifiedElement() {
                    Predicate<Customer> predicate = where().list(Customer::roles).notContains("Admin");
                    assertThat(predicate).rejects(new Customer(asList("Admin", "User"), null));
                }
            }
        }
    }

    @Nested
    @DisplayName("Predicate from method where().string(mapper)")
    class WhereStringImpl {

        @Nested
        @DisplayName(".isEqualTo(value)")
        class IsEqualTo {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped value is equal to provided value")
                void mappedValueIsEqualToProvidedValue() {
                    Predicate<Product> predicate = where().string(Product::name).isEqualTo("Laptop");
                    assertThat(predicate).accepts(new Product("Laptop"));
                }

                @Test
                @DisplayName("mapped value is null and provided value is null")
                void mappedValueIsNullAndProvidedValueIsNull() {
                    Predicate<Product> predicate = where().string(Product::name).isEqualTo(null);
                    assertThat(predicate).accepts(new Product(null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value is not equal to provided value")
                void mappedValueIsNotEqualToProvidedValue() {
                    Predicate<Product> predicate = where().string(Product::name).isEqualTo("Laptop");
                    assertThat(predicate).rejects(new Product("Desktop"));
                }

                @Test
                @DisplayName("mapped value is not null and provided value is null")
                void mappedValueIsNotNullAndProvidedValueIsNull() {
                    Predicate<Product> predicate = where().string(Product::name).isEqualTo(null);
                    assertThat(predicate).rejects(new Product("Laptop"));
                }

                @Test
                @DisplayName("mapped value is null and provided value is not null")
                void mappedValueIsNullAndProvidedValueIsNotNull() {
                    Predicate<Product> predicate = where().string(Product::name).isEqualTo("Laptop");
                    assertThat(predicate).rejects(new Product(null));
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
                    Predicate<Product> predicate = where().string(Product::name).notEqualTo("Laptop");
                    assertThat(predicate).accepts(new Product("Desktop"));
                }

                @Test
                @DisplayName("mapped value is not null and provided value is null")
                void mappedValueIsNotNullAndProvidedValueIsNull() {
                    Predicate<Product> predicate = where().string(Product::name).notEqualTo(null);
                    assertThat(predicate).accepts(new Product("Laptop"));
                }

                @Test
                @DisplayName("mapped value is null and provided value is not null")
                void mappedValueIsNullAndProvidedValueIsNotNull() {
                    Predicate<Product> predicate = where().string(Product::name).notEqualTo("Laptop");
                    assertThat(predicate).accepts(new Product(null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value is equal to provided value")
                void mappedValueIsEqualToProvidedValue() {
                    Predicate<Product> predicate = where().string(Product::name).notEqualTo("Laptop");
                    assertThat(predicate).rejects(new Product("Laptop"));
                }

                @Test
                @DisplayName("mapped value is null and provided value is null")
                void mappedValueIsNullAndProvidedValueIsNull() {
                    Predicate<Product> predicate = where().string(Product::name).notEqualTo(null);
                    assertThat(predicate).rejects(new Product(null));
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
                    Predicate<Product> predicate = where().string(Product::name).isNull();
                    assertThat(predicate).accepts(new Product(null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value is not null")
                void mappedValueIsNotNull() {
                    Predicate<Product> predicate = where().string(Product::name).isNull();
                    assertThat(predicate).rejects(new Product("Laptop"));
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
                    Predicate<Product> predicate = where().string(Product::name).notNull();
                    assertThat(predicate).accepts(new Product("Laptop"));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value is null")
                void mappedValueIsNull() {
                    Predicate<Product> predicate = where().string(Product::name).notNull();
                    assertThat(predicate).rejects(new Product(null));
                }
            }
        }

        @Nested
        @DisplayName(".isEmpty()")
        class IsEmpty {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped value is empty")
                void mappedValueIsEmpty() {
                    Predicate<Product> predicate = where().string(Product::name).isEmpty();
                    assertThat(predicate).accepts(new Product(""));
                }

                @Test
                @DisplayName("mapped value is null")
                void mappedValueIsNull() {
                    Predicate<Product> predicate = where().string(Product::name).isEmpty();
                    assertThat(predicate).accepts(new Product(null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value is not empty")
                void mappedValueIsNotEmpty() {
                    Predicate<Product> predicate = where().string(Product::name).isEmpty();
                    assertThat(predicate).rejects(new Product("Laptop"));
                }
            }
        }

        @Nested
        @DisplayName(".notEmpty()")
        class NotEmpty {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped value is not empty")
                void mappedValueIsNotEmpty() {
                    Predicate<Product> predicate = where().string(Product::name).notEmpty();
                    assertThat(predicate).accepts(new Product("Laptop"));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value is empty")
                void mappedValueIsEmpty() {
                    Predicate<Product> predicate = where().string(Product::name).notEmpty();
                    assertThat(predicate).rejects(new Product(""));
                }

                @Test
                @DisplayName("mapped value is null")
                void mappedValueIsNull() {
                    Predicate<Product> predicate = where().string(Product::name).notEmpty();
                    assertThat(predicate).rejects(new Product(null));
                }
            }
        }

        @Nested
        @DisplayName(".contains(substring)")
        class Contains {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped value contains the substring")
                void mappedValueContainsSubstring() {
                    Predicate<Product> predicate = where().string(Product::name).contains("Lap");
                    assertThat(predicate).accepts(new Product("Laptop"));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value does not contain the substring")
                void mappedValueDoesNotContainSubstring() {
                    Predicate<Product> predicate = where().string(Product::name).contains("Phone");
                    assertThat(predicate).rejects(new Product("Laptop"));
                }

                @Test
                @DisplayName("mapped value is null")
                void mappedValueIsNull() {
                    Predicate<Product> predicate = where().string(Product::name).contains("Lap");
                    assertThat(predicate).rejects(new Product(null));
                }
            }
        }

        @Nested
        @DisplayName(".notContains(substring)")
        class NotContains {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped value does not contain the substring")
                void mappedValueDoesNotContainSubstring() {
                    Predicate<Product> predicate = where().string(Product::name).notContains("Phone");
                    assertThat(predicate).accepts(new Product("Laptop"));
                }

                @Test
                @DisplayName("mapped value is null")
                void mappedValueIsNull() {
                    Predicate<Product> predicate = where().string(Product::name).notContains("Lap");
                    assertThat(predicate).accepts(new Product(null));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value contains the substring")
                void mappedValueContainsSubstring() {
                    Predicate<Product> predicate = where().string(Product::name).notContains("Lap");
                    assertThat(predicate).rejects(new Product("Laptop"));
                }
            }
        }

        @Nested
        @DisplayName(".startsWith(prefix)")
        class StartsWith {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped value starts with the prefix")
                void mappedValueStartsWithPrefix() {
                    Predicate<Product> predicate = where().string(Product::name).startsWith("Lap");
                    assertThat(predicate).accepts(new Product("Laptop"));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value does not start with the prefix")
                void mappedValueDoesNotStartWithPrefix() {
                    Predicate<Product> predicate = where().string(Product::name).startsWith("Comp");
                    assertThat(predicate).rejects(new Product("Laptop"));
                }

                @Test
                @DisplayName("mapped value is null")
                void mappedValueIsNull() {
                    Predicate<Product> predicate = where().string(Product::name).startsWith("Lap");
                    assertThat(predicate).rejects(new Product(null));
                }

                @Test
                @DisplayName("prefix is null")
                void prefixIsNull() {
                    Predicate<Product> predicate = where().string(Product::name).startsWith(null);
                    assertThat(predicate).rejects(new Product("Laptop"));
                }
            }
        }

        @Nested
        @DisplayName(".notStartsWith(prefix)")
        class NotStartsWith {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped value does not start with the prefix")
                void mappedValueDoesNotStartWithPrefix() {
                    Predicate<Product> predicate = where().string(Product::name).notStartsWith("Comp");
                    assertThat(predicate).accepts(new Product("Laptop"));
                }

                @Test
                @DisplayName("mapped value is null")
                void mappedValueIsNull() {
                    Predicate<Product> predicate = where().string(Product::name).notStartsWith("Lap");
                    assertThat(predicate).accepts(new Product(null));
                }

                @Test
                @DisplayName("prefix is null")
                void prefixIsNull() {
                    Predicate<Product> predicate = where().string(Product::name).notStartsWith(null);
                    assertThat(predicate).accepts(new Product("Laptop"));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value starts with the prefix")
                void mappedValueStartsWithPrefix() {
                    Predicate<Product> predicate = where().string(Product::name).notStartsWith("Lap");
                    assertThat(predicate).rejects(new Product("Laptop"));
                }
            }
        }

        @Nested
        @DisplayName(".endsWith(suffix)")
        class EndsWith {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped value ends with the suffix")
                void mappedValueEndsWithSuffix() {
                    Predicate<Product> predicate = where().string(Product::name).endsWith("top");
                    assertThat(predicate).accepts(new Product("Laptop"));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value does not end with the suffix")
                void mappedValueDoesNotEndWithSuffix() {
                    Predicate<Product> predicate = where().string(Product::name).endsWith("phone");
                    assertThat(predicate).rejects(new Product("Laptop"));
                }

                @Test
                @DisplayName("mapped value is null")
                void mappedValueIsNull() {
                    Predicate<Product> predicate = where().string(Product::name).endsWith("top");
                    assertThat(predicate).rejects(new Product(null));
                }

                @Test
                @DisplayName("suffix is null")
                void suffixIsNull() {
                    Predicate<Product> predicate = where().string(Product::name).endsWith(null);
                    assertThat(predicate).rejects(new Product("Laptop"));
                }
            }
        }

        @Nested
        @DisplayName(".notEndsWith(suffix)")
        class NotEndsWith {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped value does not end with the suffix")
                void mappedValueDoesNotEndWithSuffix() {
                    Predicate<Product> predicate = where().string(Product::name).notEndsWith("phone");
                    assertThat(predicate).accepts(new Product("Laptop"));
                }

                @Test
                @DisplayName("mapped value is null")
                void mappedValueIsNull() {
                    Predicate<Product> predicate = where().string(Product::name).notEndsWith("top");
                    assertThat(predicate).accepts(new Product(null));
                }

                @Test
                @DisplayName("suffix is null")
                void suffixIsNull() {
                    Predicate<Product> predicate = where().string(Product::name).notEndsWith(null);
                    assertThat(predicate).accepts(new Product("Laptop"));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value ends with the suffix")
                void mappedValueEndsWithSuffix() {
                    Predicate<Product> predicate = where().string(Product::name).notEndsWith("top");
                    assertThat(predicate).rejects(new Product("Laptop"));
                }
            }
        }

        @Nested
        @DisplayName(".matches(regex)")
        class Matches {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped value matches the regex")
                void mappedValueMatchesRegex() {
                    Predicate<Product> predicate = where().string(Product::name).matches("\\w+top");
                    assertThat(predicate).accepts(new Product("Laptop"));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value does not match the regex")
                void mappedValueDoesNotMatchRegex() {
                    Predicate<Product> predicate = where().string(Product::name).matches("\\d+");
                    assertThat(predicate).rejects(new Product("Laptop"));
                }

                @Test
                @DisplayName("mapped value is null")
                void mappedValueIsNull() {
                    Predicate<Product> predicate = where().string(Product::name).matches("\\w+top");
                    assertThat(predicate).rejects(new Product(null));
                }

                @Test
                @DisplayName("regex is null")
                void regexIsNull() {
                    Predicate<Product> predicate = where().string(Product::name).matches(null);
                    assertThat(predicate).rejects(new Product("Laptop"));
                }
            }
        }

        @Nested
        @DisplayName(".notMatches(regex)")
        class NotMatches {

            @Nested
            @DisplayName("returns true when")
            class ReturnsTrueWhen {

                @Test
                @DisplayName("mapped value does not match the regex")
                void mappedValueDoesNotMatchRegex() {
                    Predicate<Product> predicate = where().string(Product::name).notMatches("\\d+");
                    assertThat(predicate).accepts(new Product("Laptop"));
                }

                @Test
                @DisplayName("mapped value is null")
                void mappedValueIsNull() {
                    Predicate<Product> predicate = where().string(Product::name).notMatches("\\w+top");
                    assertThat(predicate).accepts(new Product(null));
                }

                @Test
                @DisplayName("regex is null")
                void regexIsNull() {
                    Predicate<Product> predicate = where().string(Product::name).notMatches(null);
                    assertThat(predicate).accepts(new Product("Laptop"));
                }
            }

            @Nested
            @DisplayName("returns false when")
            class ReturnsFalseWhen {

                @Test
                @DisplayName("mapped value matches the regex")
                void mappedValueMatchesRegex() {
                    Predicate<Product> predicate = where().string(Product::name).notMatches("\\w+top");
                    assertThat(predicate).rejects(new Product("Laptop"));
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
                    Address address = new Address("USA", null);
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
                    Address address = new Address("Ireland", null);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("second predicate is false")
                void secondPredicateIsFalse() {
                    Address address = new Address("USA", 123);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("both predicates are false")
                void bothPredicatesAreFalse() {
                    Address address = new Address("Ireland", 123);
                    assertThat(predicateConjunction).rejects(address);
                }
            }
        }

        @Nested
        @DisplayName("3 predicates")
        class ThreePredicates {

            private final Predicate<Address> predicateConjunction = where(Address::country).notNull()
                    .and().string(Address::state).startsWith("some")
                    .and(Address::zipCode).isInstanceOf(Integer.class);

            @Nested
            @DisplayName("returns true when")
            class ReturnTrueWhen {

                @Test
                @DisplayName("all predicates are true")
                void allPredicatesAreTrue() {
                    Address address = new Address("USA", "someState", 123);
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
                    Address address = new Address("USA", "testState", 123);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("third predicate is false")
                void thirdPredicateIsFalse() {
                    Address address = new Address("USA", "someState", null);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("two predicates are false")
                void twoPredicatesAreFalse() {
                    Address address = new Address(null, "testState", 123);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("all predicates are false")
                void allPredicatesAreFalse() {
                    Address address = new Address(null, "testState", null);
                    assertThat(predicateConjunction).rejects(address);
                }
            }
        }

        @Nested
        @DisplayName("7 predicates")
        class SevenPredicates {

            private final Predicate<Address> predicateConjunction = where(Address::country).isEqualTo("USA")
                    .and(Address::state).notNull()
                    .and().string(Address::street).notStartsWith("str.")
                    .and().string(Address::building).notEqualTo("xyz")
                    .and(Address::zipCode).isEqualTo(123)
                    .and().list(Address::tenants).contains("Tenant1")
                    .and().booleanValue(Address::active).isTrue();

            @Nested
            @DisplayName("returns true when")
            class ReturnTrueWhen {

                @Test
                @DisplayName("all predicates are true")
                void mappedValueIsFalse() {
                    assertThat(predicateConjunction).accepts(new Address("USA", "testValue", "someStreet", "abc", 123, asList("Tenant1", "Tenant2"), true));
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
                    Address address = new Address("Canada", "testValue", "someStreet", "abc", 123, asList("Tenant1", "Tenant2"), true);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("second predicate is false")
                void secondPredicateIsFalse() {
                    Address address = new Address("USA", null, "someStreet", "abc", 123, asList("Tenant1", "Tenant2"), true);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("third predicate is false")
                void thirdPredicateIsFalse() {
                    Address address = new Address("USA", "testValue", "str. Some Street", "abc", 123, asList("Tenant1", "Tenant2"), true);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("fourth predicate is false")
                void fourthPredicateIsFalse() {
                    Address address = new Address("USA", "testValue", "someStreet", "xyz", 123, asList("Tenant1", "Tenant2"), true);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("fifth predicate is false")
                void fifthPredicateIsFalse() {
                    Address address = new Address("USA", "testValue", "someStreet", "abc", 456, asList("Tenant1", "Tenant2"), true);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("sixth predicate is false")
                void sixthPredicateIsFalse() {
                    Address address = new Address("USA", "testValue", "someStreet", "abc", 123, asList("Tenant2", "Tenant3", "Tenant4"), true);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("seventh predicate is false")
                void seventhPredicateIsFalse() {
                    Address address = new Address("USA", "testValue", "someStreet", "abc", 123, asList("Tenant1", "Tenant2"), false);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("three predicates are false")
                void threePredicatesAreFalse() {
                    Address address = new Address("USA", "testValue", "str. Some Street", "xyz", null, asList("Tenant1", "Tenant2"), true);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("four predicates are false")
                void fourPredicatesAreFalse() {
                    Address address = new Address("Canada", null, "someStreet", "abc", 123, asList("Tenant2", "Tenant3"), false);
                    assertThat(predicateConjunction).rejects(address);
                }

                @Test
                @DisplayName("all predicates are false")
                void allPredicatesAreFalse() {
                    Address address = new Address("USA", "testValue", "someStreet", "abc", 123, asList("Tenant1", "Tenant2"), null);
                    assertThat(predicateConjunction).rejects(address);
                }
            }
        }
    }
}