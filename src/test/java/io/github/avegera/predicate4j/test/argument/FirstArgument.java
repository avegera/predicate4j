package io.github.avegera.predicate4j.test.argument;

import com.tngtech.jgiven.impl.params.DefaultCaseAsProvider;

import java.util.List;

public class FirstArgument extends DefaultCaseAsProvider {

    @Override
    public String as(String caseDescription, List<String> parameterNames, List<?> parameterValues) {
        return super.as("$1", parameterNames, parameterValues);
    }
}