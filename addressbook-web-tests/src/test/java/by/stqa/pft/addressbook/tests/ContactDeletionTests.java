package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    int before = app.getContactHelper().getContactCount();
    if (!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoNewContactPage();
      app.getContactHelper().createContact(new ContactData("Fname", "Mname", "Lname", "title", "company",
                                                           "987-654-32", "test@test.test", null));
      app.getNavigationHelper().gotoHomePage();
    }
    app.getContactHelper().selectContact(before - 1);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().closeAlert();
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);
  }
}
