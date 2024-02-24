package exercise;

public final class Circle {
    private final Point point;
    private final int radius;

    public Circle(Point point, int radius) {
        this.point = point;
        this.radius = radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("radius=" + radius);
        }
        return Math.PI * Math.pow(radius, 2);
    }

    public int getRadius() {
        return radius;
    }
}
