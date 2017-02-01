package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test(enabled = false)
  public void testContactCreation() {
    app.goTo().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.goTo().gotoNewContactPage();
    ContactData contact = new ContactData("John", "Q", "Doe", "title", "company",
                                          "987-654-32", "test@test.test", null);
    app.getContactHelper().fillNewContactForm(contact, true);
    app.getContactHelper().submitContactCreation();
    app.goTo().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
