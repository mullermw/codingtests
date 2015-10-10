package interviewcake.rectangleIntersection;

import interviewcake.condense_meeting_times.CondenseMeetingTimes;
import org.w3c.dom.css.Rect;

import java.util.Arrays;

/**
 * Created by mullermw on 9/3/15.
 */
public class RectangleIntersection {

    public static void main(String[] args) {
        Rectangle rect0 = new Rectangle(10,10,10,10);
        Rectangle rect1 = new Rectangle(15,15,10,10);
        System.out.println(Rectangle.intersection(rect0, rect1));

        rect0 = new Rectangle(10,10,20,20);
        rect1 = new Rectangle(15,15,10,10);
        System.out.println(Rectangle.intersection(rect0, rect1));

        rect0 = new Rectangle(10,10,5,10);
        rect1 = new Rectangle(15,10,5,10);
        System.out.println(Rectangle.intersection(rect0, rect1));

        rect0 = new Rectangle(10,10,10,10);
        rect1 = new Rectangle(10,15,10,10);
        System.out.println(Rectangle.intersection(rect0, rect1));

    }
}

class Rectangle {
    final int x; //leftmost
    final int y; //lower
    final int width;
    final int height;

    public Rectangle (int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rectangle:");
        sb.append(Arrays.asList(x, y, width, height));
        return sb.toString();
    }

    public int getCoordinate(int coordinate) {
        switch(coordinate) {
            case 0:return x;
            case 1:return y;
            default:
                throw new IllegalArgumentException();
        }
    }

    public int getDimension(int coordinate) {
        switch(coordinate) {
            case 0:return width;
            case 1:return height;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static Rectangle intersection(Rectangle r1, Rectangle r2) {

        int numAxes = 2;
        int[][] overlaps = new int[numAxes][2];

        for (int axis=0; axis<numAxes; ++axis) {

            Rectangle lower, higher;
            if (r1.getCoordinate(axis) < r2.getCoordinate(axis)) {
                lower = r1;
                higher = r2;
            } else {
                lower = r2;
                higher = r1;
            }

            if (higher.getCoordinate(axis) >= lower.getCoordinate(axis) + lower.getDimension(axis) ) {
                return null; //no overalap in this axis
            }

            overlaps[axis][0] = higher.getCoordinate(axis);
            overlaps[axis][1] = Math.min(lower.getCoordinate(axis) + lower.getDimension(axis),
                    higher.getCoordinate(axis) + higher.getDimension(axis)) - higher.getCoordinate(axis);

        }

        return new Rectangle(overlaps[0][0], overlaps[1][0], overlaps[0][1], overlaps[1][1]);


    }
}
