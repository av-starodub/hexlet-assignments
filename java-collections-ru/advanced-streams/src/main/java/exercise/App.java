package exercise;

import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
public class App {
    public static String getForwardedVariables(String data) {
        StringBuilder result = new StringBuilder();
        getEnvironments(data).forEach(
                env -> getVariables(env)
                        .filter(variable -> variable.startsWith("X_FORWARDED_"))
                        .forEach(forwarded -> result.append(getName(forwarded)).append(","))
        );
        return result.substring(0, result.length() - 1);
    }

    private static Stream<String> getEnvironments(String data) {
        return data.lines().filter(line -> line.startsWith("environment="));
    }

    private static Stream<String> getVariables(String env) {
        return Arrays.stream(env.substring(env.indexOf("\"") + 1, env.length() - 1).split(","));
    }

    private static String getName(String forwardedVariable) {
        return forwardedVariable.replaceAll("X_FORWARDED_", "");
    }
}
//END
