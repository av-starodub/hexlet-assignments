package exercise;

import lombok.AllArgsConstructor;

// BEGIN
@AllArgsConstructor
public final class Flat implements Home {
    private final double area;
    private final double balconyArea;
    private final int floor;

    @Override
    public double getArea() {
        return area + balconyArea;
    }

    @Override
    public int compareTo(Home another) {
        return Double.compare(getArea(), another.getArea());
    }

    @Override
    public String toString() {
        return String.format("Квартира площадью %.1f метров на %d этаже", getArea(), floor);
    }
}
// END
