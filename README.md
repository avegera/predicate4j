# Predicate4j

Predicate4j is a Java library with an expressive API for building complex predicates using a fluent interface. If you're a fan of the fluent API style in libraries like AssertJ, you'll likely enjoy using this one too.

For example, this code returns a `Predicate<User>` that checks if the role is "admin" and the organization id is not null: 

```java
return where(User::role).isEqualTo("admin").and(User::organizationId).notNull();
```

It allows for the easy creation and combination of predicates for various types (including objects, collections, strings, numbers, etc.).

## Features

The library provides Java predicate builder with fluent API that:

* null safe
* type safe
* has zero compile-time dependencies
* supports predicates for various types:
  * objects
  * strings
  * booleans
  * numbers (integer, long, etc.)
  * collections (List, Set, etc.)

## Installation

Add the following dependency to your `pom.xml` if you're using Maven:

```xml
<dependency>
    <groupId>io.github.avegera</groupId>
    <artifactId>predicate4j</artifactId>
    <version>0.1.0</version>
</dependency>
```
For Gradle:

```groovy
implementation 'io.github.avegera:predicate4j:0.1.0'
```
## Usage

Predicate4j provides a fluent API for building and using Java predicates in a type-safe and null-safe manner. It allows to create complex conditions in a readable and maintainable way.

Let's use the following `User` record for examples below:
```java
record User(Long id, String status, int age, List<String> roles) {
    
}
```

And we create the following users:
```java
List<User> users = Arrays.asList(
        new User(1, "ACTIVE", 35, List.of("Admin", "Manager")), 
        new User(2, "ACTIVE", 18, List.of("User")),
        new User(3, "INACTIVE", 12, List.of("Guest"))
);
```

### Basic Predicates
Here's a basic example of how to create and use predicates with this library and Java Stream API:

```java
List<User> activeUsers = users.stream()
        .filter(where(User::status).isEqualTo("ACTIVE"))
        .collect(toList());
```

### Predicates for Types
The library supports various types of predicates for objects, strings, numbers, and collections.

#### Number Predicates
```java
List<User> adultUsers = users.stream()
        .filter(where().number(User::age).isGreaterThanOrEqualTo(18))
        .collect(toList());
```

#### String Predicates

```java
List<User> usersWithSpecificStatus = users.stream()
        .filter(where().string(User::status).startsWith("ACT"))
        .collect(toList());
```

#### Collection Predicates
```java
List<User> admins = users.stream()
        .filter(where().list(User::roles).contains("Admin"))
        .collect(toList());
```

### Combining Predicates
It's possible to combine multiple predicates using logical operations:

```java
List<User> activeAdultNotAdmins = users.stream()
        .filter(where(User::status).isEqualTo("ACTIVE")
                .and().number(User::age).isGreaterThan(21)
                .and().list(User::roles).notContains("Admin"))
        .collect(toList());
```

### Using Own Predicates

Pass your own predicate that not present in the library:

```java
List<User> customPredicateUsers = users.stream()
        .filter(where(User::status).accepts(status -> status.trim().length() > 5))
        .collect(toList());
```