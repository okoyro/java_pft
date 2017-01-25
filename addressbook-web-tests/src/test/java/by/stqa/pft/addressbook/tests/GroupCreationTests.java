package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData("test_X", "test2", "test3");
    app.getGroupHelper().createGroup(group);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);

    // для проверки прогоняем все элементы списка по циклу
    //находим группу с максимальным числом в параметре id, т.к. справедливо предположить, что у только что созданной
    //группы будет именно такой id

    //    int max = 0;
    //    for (GroupData g : after) {
    //      if (g.getId() > max) {
    //        max = g.getId();
    //      }
    //    }

    //    список превращен в поток, по нему пробегает функция compare и сравниваются объекты типа GroupData (по id)
    //    group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());

    //добавляем ту же группу, которую добавили в тесте
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);


    //    there is an ability to use one method or a few methods to create group
    //    app.getGroupHelper().initGroupCreation();
    //    app.getGroupHelper().fillGroupForm(new GroupData("test1", null, null));
    //    app.getGroupHelper().submitGroupCreation();
    //    app.getGroupHelper().returnToGroupPage();
  }

}
