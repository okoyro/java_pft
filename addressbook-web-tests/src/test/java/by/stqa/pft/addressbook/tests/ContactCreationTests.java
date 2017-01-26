package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    int before = app.getContactHelper().getContactCount();
    app.getNavigationHelper().gotoNewContactPage();
    app.getContactHelper().fillNewContactForm
            (new ContactData("Fname", "Mname", "Lname", "title", "company",
                             "987-654-32", "test@test.test", null), true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
    app.getNavigationHelper().gotoHomePage();
  }
}
