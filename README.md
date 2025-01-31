# Predicate4j

Predicate4j is a Java library with an expressive API for building complex predicates using a fluent interface. If you're a fan of the fluent API style in libraries like AssertJ, you'll likely enjoy using this one too.

For example, this code returns a `Predicate<User>` that checks if the role is "admin" and the organization id is not null: 

```java
return where(User::role).isEqualTo("admin").and(User::organizationId).notNull();
```

It allows for the easy creation and combination of predicates for various types (including objects, collections, strings, numbers, etc.).

## Features

The library provides Java predicate builder with fluent API that:

* Null-safe
* Type-safe
* Has zero compile-time dependencies
* Supports predicates for different data types, including:
  * objects
  * strings
  * booleans
  * numbers (integer, long, etc.)
  * collections (List, Set, etc.)
  * quantifiers (each, none, at least, exactly)

## Installation

Add the following dependency to your `pom.xml` if you're using Maven:

```xml
<dependency>
    <groupId>io.github.avegera</groupId>
    <artifactId>predicate4j</artifactId>
    <version>0.2.0</version>
</dependency>
```
For Gradle:

```groovy
implementation 'io.github.avegera:predicate4j:0.2.0'
```
## Usage

Predicate4j provides a fluent API for building and using Java predicates in a type-safe and null-safe manner. It allows creating complex conditions in a readable and maintainable way.

Let's use the following `User` record for examples below:
```java
record User(Long id, String status, int age, List<String> roles) {
    
}
```

And we create the following users:
```java
List<User> users = List.of(
        new User(1, "ACTIVE", 35, List.of("Admin", "Manager")), 
        new User(2, "ACTIVE", 18, List.of("User")),
        new User(3, "INACTIVE", 12, List.of("Guest"))
);

```
### Create Basic Predicates

Create reusable predicates for common conditions, such as checking if a user is active:

```java
Predicate<User> isActiveUser = where(User::status).isEqualTo("ACTIVE");
```


### Filter with Stream API
Here's a basic example of how to filter `users` collection using plain Java Stream API. Let's find inactive users:

```java
List<User> inactiveUsers = users.stream()
        .filter(user -> user != null && user.status() != null && (user.status().equals("LOCKED") || user.status().equals("INACTIVE")))
        .toList();
```

With predicate4j it can be replaced with:

```java
List<User> inactiveUsers = users.stream()
        .filter(where(User::status).in("LOCKED", "INACTIVE"))
        .toList();
```

### Hide Null Checks and Focus on Logic

The previous example demonstrates that the library is null-safe by default. It incorporates null-checks before executing any null-sensitive predicates, ensuring that null values do not cause runtime errors.



Similarly, the library handles null values gracefully for mappers. If, for any reason, a nullable mapper is provided, it will not cause an error; instead, it will return `false` by default.

### Build Type-safe Predicates
The library supports various types of predicates for objects, strings, numbers, and collections.

**Note:** Let's use `filter(collection, predicate)` method to simplify examples below:

```java
  public static <T> List<T> filter(Collection<T> collection, Predicate<T> predicate) {
      return collection.stream().filter(predicate).toList();
  }
```

#### Numbers

It allows to compare numbers, for example, users where age >=18:

```java
List<User> adultUsers = filter(users, where().number(User::age).isGreaterThanOrEqualTo(18));
```

#### Strings

Performs string-based checks, such as finding users whose status starts with "ACT":

```java
List<User> filteredUsers = filter(users, where().string(User::status).startsWith("ACT"));
```

#### Collections

Works with collections, e.g. verifying if a user's roles contain a specific value:

```java
List<User> admins = filter(users, where().list(User::roles).contains("Admin"));
```

### Apply Quantifiers

Quantifiers provide elegant and concise support for working with collections, enabling iteration and matching with a laconic syntax. Use the following methods:

* Use `atLeastOne()` to check if at least one element in a collection satisfies a condition:

  ```java
  where().atLeastOne(User::roles).isEqualTo("Admin");
  ```
* Use `each()` to verify that all elements in a collection satisfy a condition:
  
  ```java
  where().each().string(User::roles).notEmpty();
  ```

* `exactly()` to assert that exactly n elements in a collection meet a given condition:

  ```java
  where().exactly(2, User::roles).in(allowableRoles);
  ```
* `none()` to ensure that no elements in a collection satisfy a condition.

  ```java
  where().none(User::roles).isNull();
  ```

### Combine Predicates
It's possible to combine multiple predicates using logical operations:

```java
List<User> activeAdultNotAdmins = filter(users, isActiveAdultNotAdmin());

private Predicate<User> isActiveAdultNotAdmin() {
  return where(User::status).isEqualTo("ACTIVE")
          .and().number(User::age).isGreaterThan(21)
          .and().none().string(User::roles).startsWith("Admin");
}
```

### Use Own Predicates

Pass your own predicate that not present in the library:

```java
List<User> filteredUsers = filter(users, where(User::status).accepts(status -> status.trim().length() > 5));
```