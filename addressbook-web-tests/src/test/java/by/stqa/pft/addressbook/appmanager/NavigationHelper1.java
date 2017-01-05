package by.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper1 {
  private FirefoxDriver wd;

  public NavigationHelper1(FirefoxDriver wd) {
    this.wd = wd;
  }

  public void gotoNewContactPage() {
    wd.get("http://localhost/addressbook/edit.php");
  }
}
