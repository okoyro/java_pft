package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import by.stqa.pft.addressbook.model.Contacts;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.goTo().newContactPage();
    File photo = new File("src/test/resources/pft.png");
    ContactData contact = new ContactData().withFirstname("Jain").withLastnane("Doe").
            withAddress("Lenina,10, Brest, Belarus").withEmail("abc@test.test").withEmail2("abc2@test.test")
            .withEmail3("abc3@test.test").withHomePhone("(111)11-1111").withMobilePhone("(222)22-2222")
            .withWorkPhone("(333)33-3333").withPhoto(photo);
    app.contact().fillNewContactForm(contact, true);
    app.contact().submitContactCreation();
    app.goTo().homePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/pft.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}
