package exercise;

import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static int getCountOfFreeEmails(List<String> emailsList) {
        return (int) emailsList.stream()
                .filter(email -> email.contains("@gmail") || email.contains("@yandex") || email.contains("@hotmail"))
                .count();
    }
}
// END
