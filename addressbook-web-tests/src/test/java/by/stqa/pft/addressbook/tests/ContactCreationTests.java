package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoNewContactPage();
    app.getContactHelper().fillNewContactForm
            (new ContactData("Fname", "Mname", "Lname", "title", "company", "987-654-32", "test@test.test"));
    app.getContactHelper().submitContactCreation();
  }
}
