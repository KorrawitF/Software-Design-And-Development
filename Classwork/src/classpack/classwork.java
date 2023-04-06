package classpack;

public class classwork {
    static void changePrimitive(int x) {
        while (x > 0) {
            System.out.println(x--);
        }
    }

    static void changeReference(point p) {
        while (p.x > 0) {
            System.out.println(p.x--);
        }
    }

    public static void main(String[] args) {
        // point p = new point(2.0, -3.5);
        // System.out.println("X coordinate " + p.x);
        // System.out.println("Y coordinate " + p.y);
        // System.out.println("Distance from origin " + p.distanceFromOrigin() + "\n");

        // p.x = 10;
        // p.y = p.x * p.x;
        // System.out.println("X coordinate " + p.x);
        // System.out.println("X coordinate " + p.y);
        // System.out.println("Distance from origin " + p.distanceFromOrigin());
        // point p = new point(3.0, 4.0);
        // // point q = p;
        // // System.out.println(p.x);
        // // q.x = 13.0;
        // // System.out.println(p.x);
        // int x = 42;
        // changePrimitive(x);
        // System.out.println("X " + x);

        // changeReference(p);
        // System.out.println("p.x " + p.x);
        // String letter = "o";
        // String s = "hello";
        // String t = "hell" + letter;
        // if (s.equals(t)) {
        // System.out.println("EQUAL");
        // } else {
        // System.out.println("NOT EQUAL");
        // }
        // student std;
        // std = new student();
        // std.name = "Andy";
        // System.out.println("std " + std.name);
        // student std1 = std;
        // std1.name = "Brown";
        // System.out.println("std " + std.name);
        // System.out.println("std " + std1.name);
        // final student std2 = new student();
        // std2.name = "John Doe";
        // System.out.println(std2.name);
        // std2.name = "Andy";
        // System.out.println(std2.name);
        // point p = new point(2.0, -3.5);
        // System.out.println("Value of X is " + p.getX());
        // System.out.println("Value of Y is " + p.getY());
        // p.setX(3.0);
        // System.out.println("Value of X is " + p.getX());
        RollDice dice;
        dice = new RollDice();

    }
}
