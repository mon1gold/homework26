package homework;

import homework.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TestRunner {
    public static void start(Class<?> testClass) throws Exception {
        Method[] methods = testClass.getDeclaredMethods();


        Method beforeSuiteMethod = findMethodByAnnotation(methods, Test.BeforeSuite.class);

        Method afterSuiteMethod = findMethodByAnnotation(methods, Test.AfterSuite.class);


        List<Method> testMethods = Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(Test.class))
                .collect(Collectors.toList());


        if (beforeSuiteMethod != null) {
            beforeSuiteMethod.invoke(null);
        }


        testMethods.sort(Comparator.comparingInt(m -> m.getAnnotation(Test.class).priority()));


        testMethods.forEach(method -> {
            try {
                method.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        if (afterSuiteMethod != null) {
            afterSuiteMethod.invoke(null);
        }
    }

    private static Method findMethodByAnnotation(Method[] methods, Class<? extends Annotation> annotation) {
        return Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(annotation))
                .findFirst()
                .orElse(null);
    }

}
