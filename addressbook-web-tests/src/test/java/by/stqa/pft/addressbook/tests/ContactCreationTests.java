package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    login();
  }

  @Test
  public void testContactCreation() {
    gotoNewContactPage();
    fillNewContactForm(new ContactData("Fname", "Mname", "Lname", "title", "company", "987-654-32", "test@test.test"));
    submitContactCreation();
  }

  @AfterMethod
  public void tearDown() {
    wd.quit();
  }

  public static boolean isAlertPresent(FirefoxDriver wd) {
    try {
      wd.switchTo().alert();
      return true;
    }
    catch (NoAlertPresentException e) {
      return false;
    }
  }
}
