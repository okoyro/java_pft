package by.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase1 {

  @Test
  public void testContactDeletion() {
    selectContact();
    deleteSelectedContact();
  }
}
