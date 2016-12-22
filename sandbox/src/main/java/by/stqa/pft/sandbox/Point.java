package by.stqa.pft.sandbox;

public class Point {

  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double calcDistance(Point p2) {
    return Math.sqrt(Math.pow((this.y - p2.y), 2) + Math.pow((this.x - p2.x), 2));
  }
  //  1) один объект -- тот, в котором вызывается метод, он внутри метода доступен под специальным именем this
  //  2) второй объект -- передаётся в метод как значение параметра
}
