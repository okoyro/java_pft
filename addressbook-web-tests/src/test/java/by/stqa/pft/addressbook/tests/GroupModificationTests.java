package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {
  @Test
  public void testGroupModification() {

    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", null));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
  }
}
