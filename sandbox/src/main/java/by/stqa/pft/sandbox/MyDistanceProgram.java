package by.stqa.pft.sandbox;

public class MyDistanceProgram {
  public static void main(String[] args) {
    Point p1 = new Point(1, 2);
    //        p1.x = 2;  all attributes are filled out in constructor
    //        p1.y = 3;

    Point p2 = new Point(3, 4);
    //        p2.x = 4;  all attributes are filled out in constructor
    //        p2.y = 5;

    System.out.println("Расстояние между точками = " + p1.calcDistance(p2));
  }

  //  public static double distance(Point p1, Point p2) {
  //    return Math.sqrt(Math.pow((p2.y - p1.y), 2) + Math.pow((p2.x - p1.x), 2));
  //  } there is a static function which can be used to calculate the distance (need to add params: distance(p1,p2))
}
