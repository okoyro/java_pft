package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHomePage();
    List<ContactData>  before = app.getContactHelper().getContactList();
    app.getNavigationHelper().gotoNewContactPage();
    app.getContactHelper().fillNewContactForm
            (new ContactData("Jane", "Q", "Poi", "title", "company",
                             "987-654-32", "test@test.test", null), true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData>  after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
    app.getNavigationHelper().gotoHomePage();
  }
}
