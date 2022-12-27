package exercise;

import java.util.List;

class App {
    public static List<Integer> take(List<Integer> elements, int count) {
        String implementation = System.getenv("IMPLEMENTATION") != null
                ? System.getenv("IMPLEMENTATION")
                : "right";

        return switch (implementation) {
            case "wrong1" -> Implementations.wrong1(elements, count);
            case "wrong2" -> Implementations.wrong2(elements, count);
            case "wrong3" -> Implementations.wrong3(elements, count);
            default -> Implementations.right(elements, count);
        };
    }
}
