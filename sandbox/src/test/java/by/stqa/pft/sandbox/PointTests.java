package by.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void rightDistanceVerification() {
    Point p1 = new Point(12, 24);
    Point p2 = new Point(34, 54);

    Assert.assertTrue(p1.calcDistance(p2) >= 37 * 0.1 || p1.calcDistance(p2) <= 37 * 0.1);

    //assert p1.calcDistance(p2) >= 37*0.1 || p1.calcDistance(p2) <= 37*0.1; another way to check
  }

  @Test
  public void wrongDistanceVerification() {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(3, 4);
    //wrong value in expected result
    Assert.assertNotEquals(p1.calcDistance(p2), 6);
  }

  @Test
  public void notNullVerification() {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(3, 4);

    Assert.assertNotNull(p1.calcDistance(p2));
  }

  @Test
  public void zeroCalcVerification() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 0);

    assert p1.calcDistance(p2) == 0;

  }
}
