package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> apartments, int requiredQuantity) {
        return apartments.stream()
                .sorted(Home::compareTo)
                .limit(requiredQuantity)
                .map(Home::toString)
                .collect(Collectors.toList());
    }
}
// END
