package exercise;

// BEGIN
public final class Segment {
    private final Point beginPoint;
    private final Point endPoint;
    private final Point midPoint;

    public Segment(Point begin, Point end) {
        beginPoint = begin;
        endPoint = end;
        midPoint = findMidPoint(begin, end);
    }

    public Point getBeginPoint() {
        return beginPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Point getMidPoint() {
        return midPoint;
    }
    private static Point findMidPoint(Point begin, Point end) {
        var x = (begin.getX() + end.getX()) / 2;
        var y = (begin.getY() + end.getY()) / 2;
        return new Point(x, y);
    }
}
// END

