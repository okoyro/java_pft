package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before + 1);

    //    there is an ability to use one method or a few methods to create group
    //    app.getGroupHelper().initGroupCreation();
    //    app.getGroupHelper().fillGroupForm(new GroupData("test1", null, null));
    //    app.getGroupHelper().submitGroupCreation();
    //    app.getGroupHelper().returnToGroupPage();
  }

}
