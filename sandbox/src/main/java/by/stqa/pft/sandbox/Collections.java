package by.stqa.pft.sandbox;

import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String[] args) {
    String[] langs = {"Java", "C#", "Python", "PHP"}; //это массив

    List<String> languages = Arrays.asList("Java", "C#", "Python");  //это список

    for (String l : languages) {
      System.out.println("Я хочу выучить " + l);
    }
  }
}
