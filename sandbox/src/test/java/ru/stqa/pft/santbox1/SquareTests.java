package ru.stqa.pft.santbox1;

import org.testng.annotations.Test;
import ru.stqa.pft.sandbox1.Square;


public class SquareTests {

  @Test
  public void testArea () {
    Square s = new Square(5);
    org.testng.Assert.assertEquals (s.area(), 25.0);
  }
}
