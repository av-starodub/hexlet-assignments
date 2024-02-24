package exercise;

public final class App {

    public static void printSquare(Circle circle) {
        try {
            var square = Math.round(circle.getSquare());
            System.out.println(square);
        } catch (NegativeRadiusException e) {
            System.out.println("Не удалось посчитать площадь");;
        }
        System.out.println("Вычисление окончено");
    }
}
