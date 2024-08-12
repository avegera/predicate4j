package io.github.avegera.predicate4j.test.tag;

import com.tngtech.jgiven.annotation.IsTag;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@IsTag
@Retention(RUNTIME)
@Target({METHOD, TYPE})
public @interface Where {

    String value();
}