package io.github.avegera.predicate4j.test.util;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

//based on beanref library (https://github.com/throwable/beanref)
public class LambdaUtils {

    @SuppressWarnings("rawtypes")
    private static final ConcurrentHashMap<Lambda, LambdaDetails> lambdaCache = new ConcurrentHashMap<>();

    public static <T, R> String getMethodName(Lambda<T, R> lambda) {
        return lambda == null ? "null" : lambdaCache.computeIfAbsent(lambda, LambdaUtils::getLambdaDetails).methodName();
    }

    public static <T, R> String lambdaToString(Lambda<T, R> lambda) {
        return lambda == null ? "null" : lambdaCache.computeIfAbsent(lambda, LambdaUtils::getLambdaDetails).toString();
    }

    private static <T, R> LambdaDetails getLambdaDetails(Lambda<T, R> lambda) {
        SerializedLambda serialized = serialized(lambda);
        if (serialized.getImplMethodName().startsWith("lambda$")) {
            throw new IllegalArgumentException("Not a method reference");
        }
        Class<T> beanClass = getContainingClass(serialized);
        Method method = findMethod(beanClass, serialized.getImplMethodName());
        return new LambdaDetails(beanClass.getSimpleName(), method.getName());
    }

    private static SerializedLambda serialized(Object lambda) {
        try {
            Method writeMethod = lambda.getClass().getDeclaredMethod("writeReplace");
            writeMethod.setAccessible(true);
            return (SerializedLambda) writeMethod.invoke(lambda);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> Class<T> getContainingClass(SerializedLambda lambda) {
        try {
            String className = lambda.getImplClass().replaceAll("/", ".");
            return (Class<T>) Class.forName(className, true, Thread.currentThread().getContextClassLoader());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> Method findMethod(Class<T> clazz, String methodName) {
        final Method getterMethod;
        try {
            getterMethod = clazz.getMethod(methodName);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        if (getterMethod.getParameterCount() > 0) {
            throw new RuntimeException("Illegal getter method: " + methodName);
        }
        if (Void.TYPE.equals(getterMethod.getReturnType())) {
            throw new RuntimeException("Illegal getter method return type: " + methodName + "(" + getterMethod.getReturnType() + ")");
        }
        return getterMethod;
    }
}

class LambdaDetails {

    private final String className;

    private final String methodName;

    public LambdaDetails(String className, String methodName) {
        this.className = className;
        this.methodName = methodName;
    }

    public String methodName() {
        return methodName;
    }

    @Override
    public String toString() {
        return className + "::" + methodName;
    }
}