package by.stqa.pft.sandbox;

public class Primes {

  public static boolean isPrime(int n) {
    //    i++ - это обозначение действия увеличения числа на единицу - инкремент
    for (int i = 2; i < n; i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  // это второй вариант цикла (с использованием "while")
  public static boolean isPrimeWhile(int n) {
    int i = 2;
    while (i < n && n % i == 0) {
      i++;
    }
    return i == n;
  }

  public static boolean isPrime(long n) {
    for (long i = 2; i < n; i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static boolean isPrimeFast(int n) {
    int m = (int) Math.sqrt(n);
    for (int i = 2; i < m / 2; i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }
}
