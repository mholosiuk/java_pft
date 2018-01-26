package ru.stqa.pft.sandbox1;

public class MyFirstProgram {

    public static void main (String[] args) {
        hello("world");
        hello("user");
        hello("Alex");


        Square s = new Square(5);
        System.out.println("Площадь квадрата " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(4,6);
        System.out.println("Площадь квадрата " + r.a + " и " + r.b + " = " + r.area());


        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);
        System.out.println(p1.distance(p2));
    }


    public static void hello (String somebody) {
        System.out.println("Hello " + somebody + "!");

    }

}