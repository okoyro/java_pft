package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import by.stqa.pft.addressbook.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

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
  public void testAddContactToGroup() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    int contactId = contact.getId();
    app.contact().selectContactById(contactId);
    int groupId = app.contact().selectGroup();
    app.contact().addContactToGroup(contact);
    ContactData contactData = app.db().contactById(contactId);
    long count = contactData.getGroups().stream().filter((g) -> (g).getId() == groupId).count();
    assert count > 0;
  }
}
