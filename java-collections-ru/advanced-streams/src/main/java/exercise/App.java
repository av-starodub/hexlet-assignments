package exercise;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
public class App {
    public static String getForwardedVariables(String data) {
        return getVariables(getEnvironments(data))
                .filter(variable -> variable.startsWith("X_FORWARDED_"))
                .map(App::getName)
                .collect(Collectors.joining(","));
    }

    private static Stream<String> getEnvironments(String data) {
        return data.lines().filter(line -> line.startsWith("environment="));
    }

    private static Stream<String> getVariables(Stream<String> environments) {
        return environments
                .map(env -> env.substring(env.indexOf("\"") + 1, env.length() - 1))
                .map(l -> l.split(","))
                .flatMap(Arrays::stream);
    }

    private static String getName(String forwardedVariable) {
        return forwardedVariable.replaceAll("X_FORWARDED_", "");
    }
}
//END
