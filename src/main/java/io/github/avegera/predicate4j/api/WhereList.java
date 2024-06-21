package io.github.avegera.predicate4j.api;

import java.util.List;

public interface WhereList<T, R> extends WhereObject<T, List<R>> {

    RichPredicate<T> isEmpty();

    RichPredicate<T> notEmpty();

    RichPredicate<T> hasSize(int size);

    RichPredicate<T> notHaveSize(int size);

    RichPredicate<T> contains(R element);

    RichPredicate<T> notContain(R element);
}