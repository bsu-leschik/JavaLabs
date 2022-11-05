package lab7;

import java.awt.*;

public class Line {
    final Point a;
    final Point b;

    private final Color color;

    public Line(Point a, Point b, Color color){
        this.a = a;
        this.b = b;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
