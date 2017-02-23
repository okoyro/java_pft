package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import by.stqa.pft.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.db().contacts().size() == 0) {
      app.goTo().newContactPage();
      app.contact().create(new ContactData().withFirstname("Fname").withMiddlename("Mname").withLastnane("Lname").
              withAddress("Mainstreet, 2, New York").withEmail("test@test.test"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(before.size()-1));
    assertThat(after, equalTo(before.without(deletedContact)));
  }

}
