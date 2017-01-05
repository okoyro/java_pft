package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase1 {
  FirefoxDriver wd;

  @Test
  public void testContactCreation() {
    gotoNewContactPage();
    fillNewContactForm(new ContactData("Fname", "Mname", "Lname", "title", "company", "987-654-32", "test@test.test"));
    submitContactCreation();
  }
 }
