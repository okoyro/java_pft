package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData("test1", "test2", "test3");
    app.getGroupHelper().createGroup(group);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);

    //добавляем ту же группу, которую добавили в тесте
    before.add(group);

    // для проверки прогоняем все элементы списка по циклу
    //находим группу с максимальным числом в параметре id, т.к. справедливо предположить, что у только что созданной
    //группы будет именно такой id

    int max = 0;
    for (GroupData g : after) {
      if (g.getId() > max) {
        max = g.getId();
      }
    }
    group.setId(max);


    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));


    //    there is an ability to use one method or a few methods to create group
    //    app.getGroupHelper().initGroupCreation();
    //    app.getGroupHelper().fillGroupForm(new GroupData("test1", null, null));
    //    app.getGroupHelper().submitGroupCreation();
    //    app.getGroupHelper().returnToGroupPage();
  }

}
