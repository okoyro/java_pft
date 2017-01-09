package by.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }

  public void gotoNewContactPage() {
    clickLink("http://localhost/addressbook/edit.php");
  }

  public void gotoHomePage() {
    clickLink("http://localhost/addressbook/index.php");
  }
}
