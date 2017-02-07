package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    app.goTo().newContactPage();
    ContactData contact = new ContactData().withFirstname("John").withMiddlename("Q").withLastnane("Doe").withTitle("title").
            withCompany("company").withHomephonenumber("987-654-32").withEmail("test@test.test");
    app.contact().fillNewContactForm(contact, true);
    app.contact().submitContactCreation();
    app.goTo().homePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);
    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
