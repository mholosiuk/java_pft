package ru.stqa.pft.sandbox1;

public class Point {

  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public int distance () {

    return 1;
  }

  public void printToConsole(Point p) {
    System.out.println(this.x);
    System.out.println(p.x);
  }

}