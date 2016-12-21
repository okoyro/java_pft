package by.stqa.pft.sandbox;

public class MyDistanceProgram {
  public static void main(String[] args) {
    Point p1 = new Point(5, 4);
    //        p1.x = 2;  all attributes are filled out in constructor
    //        p1.y = 3;

    Point p2 = new Point(7, 8);
    //        p2.x = 4;  all attributes are filled out in constructor
    //        p2.y = 5;

    System.out.println("Расстояние между точками = " + distance(p1, p2));
  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt(((p2.y - p1.y) * (p2.y - p1.y)) + ((p2.x - p1.x) * (p2.x - p1.x)));

  }
}
