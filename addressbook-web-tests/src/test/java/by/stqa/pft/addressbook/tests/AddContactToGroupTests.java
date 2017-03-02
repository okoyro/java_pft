package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import by.stqa.pft.addressbook.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertTrue;

public class AddContactToGroupTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    List<ContactData> contactsWOGroups = app.db().contacts().stream()
            .filter(contact -> contact.getGroups().isEmpty())
            .collect(Collectors.toList());

    if (contactsWOGroups.isEmpty()) {
      app.goTo().newContactPage();
      app.contact().create(new ContactData().withFirstname("Jain").withLastnane("Doe").withAddress("Lenina, 2, Minsk, RB")
                                   .withEmail("jain@mail.test"));
    }
    if (app.db().groups().isEmpty()) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("group"));
    }
  }

  @Test
  public void testAddContactToGroup() throws IOException {
    skipIfNotFixed(1);

    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    int contactId = contact.getId();
    app.contact().selectContactById(contactId);
    int groupId = app.contact().selectGroup();
    app.contact().addContactToGroup(contact);

    ContactData dbContact = app.db().contactById(contactId);
    long count = dbContact.getGroups().stream().filter((g) -> (g).getId() == groupId).count();
    assertTrue(count > 0);
  }
}
