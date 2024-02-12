package exercise;

import lombok.AllArgsConstructor;

// BEGIN
@AllArgsConstructor
public class Cottage implements Home {
    private final double area;
    private final int floorCount;

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public int compareTo(Home another) {
        return Double.compare(area, another.getArea());
    }

    @Override
    public String toString() {
        return String.format("%d этажный коттедж площадью %.1f метров", floorCount, getArea());
    }
}
// END
