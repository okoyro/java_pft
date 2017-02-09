package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDataTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.goTo().newContactPage();
      app.contact().create(new ContactData().withFirstname("Jain").withLastnane("Doe").withAddress("Lenina, 2, Minsk, RB")
                                   .withEmail("jain@mail.test"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().contactInfoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()).stream()
            .map(ContactDataTests::cleaned)
            .filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "").replaceAll("\n", "").replaceAll("H:","")
            .replaceAll("M:","").replaceAll("W:","");
    //"\\s" - это пробелы, "[-()]" - это все дефисы и скобки
  }

  @Test
  public void testContactAddress() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().contactInfoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }

  @Test
  public void testContactEmails() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().contactInfoFromEditForm(contact);

    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3()).stream()
            .map(ContactDataTests::cleaned)
            .filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
  }

  @Test
  public void testAllContactInfo() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditList = app.contact().contactInfoFromEditForm(contact);
    ContactData contactInfoFromFullInfoForm = app.contact().contactInfoFromFullInfoForm(contact);

    assertThat(mergeInfoFromEditForm(contactInfoFromEditList),
               equalTo(ContactDataTests.cleaned(contactInfoFromFullInfoForm.getFullInfo())));
  }

  private String mergeInfoFromEditForm(ContactData contact) {
    return Arrays.asList(contact.getFirstname(), contact.getLastnane(), contact.getAddress(),
                         contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getEmail(),
                         contact.getEmail2(), contact.getEmail3()).stream()
            .map(ContactDataTests::cleaned)
            .filter((s) -> !s.equals("")).collect(Collectors.joining(""));
  }
}

