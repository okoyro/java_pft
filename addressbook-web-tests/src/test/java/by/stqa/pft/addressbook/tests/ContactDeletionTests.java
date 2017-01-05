package by.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase1 {

  @Test
  public void testContactDeletion() {
    app1.selectContact();
    app1.deleteSelectedContact();
  }
}
