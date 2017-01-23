package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);

    //    there is an ability to use one method or a few methods to create group
    //    app.getGroupHelper().initGroupCreation();
    //    app.getGroupHelper().fillGroupForm(new GroupData("test1", null, null));
    //    app.getGroupHelper().submitGroupCreation();
    //    app.getGroupHelper().returnToGroupPage();
  }

}
