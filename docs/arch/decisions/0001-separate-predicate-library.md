# ADR-1. Separate Predicate Library

## Status

| Field         | Value                                  |
|---------------|----------------------------------------|
| Status        | ACCEPTED                               |
| Deciders      | [@avegera](https://github.com/avegera) |
| Created Date  | 2024-06-04                             |
| Decision Date | 2024-06-04                             |

## Context
Java `java.util.function.Predicate` works, but quickly becomes verbose when objects are nullable by default. The constant repetition of `!= null` checks makes even trivial conditions noisy and hard to read.

For example, checking a user’s status with a prefix:

```java
return user -> user != null
    && user.getStatus() != null
    && prefix != null
    && user.getStatus().startsWith(prefix);
```

## Decision Drivers

We are looking for predicate builder solution:

- Null-safety without boilerplate
- Fluent DSL for readability
- Functional mapping over `Function<T,R>` (e.g. `where(User::status).eq("ACTIVE")`)
- Quantifier support (`each`, `none`, `atLeast`, `exactly`)
- General-purpose beyond testing/validation
- Conciseness and developer experience

## Considered Options
- Java SE Predicates
- Guava Predicates
- Hamcrest Matchers
- AssertJ
- java-fluent-validator
- Vavr
- jOOL (jOOQ Lambda)
- StreamEx
- Own implementation (predicate4j)

## Decision Outcome
Chosen option: **Own implementation of predicate builder and predicate library (predicate4j)**.  

Partial alternatives exist, but none combine null-safe mapping DSL, quantifiers, and general-purpose fluency. A dedicated library is justified.

## Pros and Cons of the Options

### Java SE Predicates
- ✅ Built-in, no deps, universal
- ❌ Verbose, no null-safety helpers, no DSL, no quantifiers

**Example**
```java
Predicate<User> p = u ->
    u != null
    && u.getStatus() != null
    && prefix != null
    && u.getStatus().startsWith(prefix);
````

---

### Guava

* ✅ Mature utilities; `Predicates.compose` exists
* ❌ Still verbose for null-safety; not DSL-focused

**Example**

```java
Predicate<String> starts = s -> s != null && prefix != null && s.startsWith(prefix);
Function<User, String> status = User::getStatus;

Predicate<User> p = Predicates.and(
    Predicates.notNull(),
    Predicates.compose(starts::test, status)  // still manual null checks inside
);
```

---

### Hamcrest

* ✅ Expressive for tests
* ❌ Test-focused; awkward for stream filtering/predicates

**Example (matcher, not a Predicate)**

```java
Matcher<User> m = hasProperty("status", anyOf(nullValue(String.class), startsWith(prefix)));
```

---

### AssertJ

* ✅ Great fluent API (in tests)
* ❌ Typically string-field or lambda-based filters; test-domain limited

**Example (filtering in tests)**

```java
var filtered = assertThat(users)
    .filteredOn(u -> u != null 
            && u.getStatus() != null 
            && prefix != null 
            && u.getStatus().startsWith(prefix))
    .extracting(User::getStatus);
```

---

### java-fluent-validator

- ✅ Provides a library of predicates (Object, String, Collection, Map, Comparable, Date-Time) + supports mapping via `Function<T,R>`
- ❌ Validation-focused, not a general-purpose predicate DSL (no readable quantifier DSL)

**Example**
```java
Predicate<User> p = not(nullValue(User::getStatus))
                .and(stringMatches(User::getStatus, "^" + Pattern.quote(prefix) + ".*"));
```

### Vavr

* ✅ Null-safety with `Option`; strong FP composition
* ❌ FP-heavy; not a dedicated predicate DSL

**Example**

```java
Predicate<User> p = u ->
    Option.of(u)
          .map(User::getStatus)
          .map(s -> prefix != null && s.startsWith(prefix))
          .getOrElse(false);
```

---

### jOOL (jOOQ Lambda)

* ✅ Rich FP utilities
* ❌ Not focused on predicate DSL; null-safety still on you

**Example**

```java
Predicate<User> p = u ->
    u != null
    && u.getStatus() != null
    && prefix != null
    && u.getStatus().startsWith(prefix);
```

---

### StreamEx

* ✅ Nice stream helpers (e.g., `nonNull()`)
* ❌ Stream-centric; still no predicate DSL

**Example (as a filter, then repackage if needed)**

```java
List<User> out = StreamEx.of(users)
    .nonNull()
    .filter(u -> u.getStatus() != null)
    .filter(u -> prefix != null && u.getStatus().startsWith(prefix))
    .toList();
```

---

### Own implementation (predicate4j)

* ✅ Null-safe mapping DSL, quantifiers, concise, general-purpose
* ❌ New library to maintain; needs adoption/community

**Example (intended DSL)**

```java
// no explicit null checks in user code
Predicate<User> p = where(User::getStatus).startsWith(prefix);
```