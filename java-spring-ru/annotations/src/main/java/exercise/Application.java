package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;

import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN
        var cls = address.getClass();
        var methodsInfo = getInspectAnnotatedMethodsInfo(cls);
        methodsInfo.forEach(System.out::println);
        // END
    }

    private static List<String> getInspectAnnotatedMethodsInfo(Class<?> cls) {
        return Arrays.stream(cls.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Inspect.class))
                .map(m ->
                        "Method %s returns a value of type %s".formatted(m.getName(), m.getReturnType().getSimpleName())
                )
                .toList();
    }
}
