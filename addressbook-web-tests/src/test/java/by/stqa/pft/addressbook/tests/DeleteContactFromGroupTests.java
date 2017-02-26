package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import by.stqa.pft.addressbook.model.GroupData;
import by.stqa.pft.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class DeleteContactFromGroupTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {

    List<ContactData> contactsWithGroups = app.db().contacts().stream()
            .filter(contact -> !contact.getGroups().isEmpty())
            .collect(Collectors.toList());

    if (contactsWithGroups.isEmpty()) {
      Groups groups = app.db().groups();
      if (groups.isEmpty()) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("group"));
        groups = app.db().groups();
      }
      GroupData group = groups.iterator().next();

      app.goTo().newContactPage();
      app.contact().create(new ContactData().withFirstname("Jain").withLastnane("Doe").withAddress("Lenina, 2, Minsk, RB")
                                   .withEmail("jain@mail.test").withGroup(group));
    }
  }

  @Test
  public void testDeleteContactFromGroup() {
    ContactData contactWithGroup = app.db().contacts().stream()
             .filter(contact -> !contact.getGroups().isEmpty())
             .findFirst().get();

    int contactId = contactWithGroup.getId();
    GroupData group = contactWithGroup.getGroups().iterator().next();
    app.goTo().homePage();
    app.contact().selectGroupById(group.getId());
    app.contact().selectContactById(contactId);
    app.contact().removeFromGroup();
  }
}
