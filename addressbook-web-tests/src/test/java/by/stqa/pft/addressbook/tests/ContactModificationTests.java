package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    int before = app.getContactHelper().getContactCount();
    if (!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoNewContactPage();
      app.getContactHelper().createContact(new ContactData("Fname", "Mname", "Lname", "title", "company",
                                                           "987-654-32", "test@test.test", null));
      app.getNavigationHelper().gotoHomePage();
    }
    app.getContactHelper().selectContactForEdit();
    app.getContactHelper().fillNewContactForm
            (new ContactData("Fname(ed)", "Mname(ed)", "Lname(ed)", "title(ed)", "company(ed)",
                             "234-567-89", "test@test.test(ed)", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }
}
