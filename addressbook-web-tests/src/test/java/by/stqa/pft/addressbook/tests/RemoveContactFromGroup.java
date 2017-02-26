package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import by.stqa.pft.addressbook.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static by.stqa.pft.addressbook.tests.TestBase.app;

public class RemoveContactFromGroup {
  @BeforeMethod
    public void ensurePreconditions() {
      if (app.db().contacts().size() == 0) {
        app.goTo().newContactPage();
        app.contact().create(new ContactData().withFirstname("Jain").withLastnane("Doe").withAddress("Lenina, 2, Minsk, RB")
                                     .withEmail("jain@mail.test"));
      }
      if (app.db().groups().size() == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("group"));
      }
    app.contact().selectGroup();
//    app.contact().addContactToGroup();
    }
  @Test
  public void testRemoveContactFromGroup(){

  }
}
