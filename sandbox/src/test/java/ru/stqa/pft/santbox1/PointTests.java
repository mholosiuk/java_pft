package ru.stqa.pft.santbox1;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox1.Point;

public class PointTests {

  @Test
  public void testDistance() {
    Point p1 = new Point(2, 3);
    Point p2 = new Point(4, 5);
    assert p1.distance(p2) == 2.8284271247461903;
  }

  @Test
  public void testDistanceFail() {
    Point p1 = new Point(1, 1);
    Point p2 = new Point(1, 1);
    Assert.assertEquals(p1.distance(p2), 2.8284271247461903);
  }

  @Test
  public void testDistanceMoreThan() {
    Point p1 = new Point(2, 3);
    Point p2 = new Point(4, 5);
    assert p1.distance(p2) >= 2;
  }

}

