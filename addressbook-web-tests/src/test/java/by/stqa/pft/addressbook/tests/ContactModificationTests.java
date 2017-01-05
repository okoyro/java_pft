package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getContactHelper().selectContactForEdit();
    app.getContactHelper().fillNewContactForm
            (new ContactData("Fname(ed)", "Mname(ed)", "Lname(ed)", "title(ed)", "company(ed)", "234-567-89", "test@test.test(ed)"));
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
  }
}
