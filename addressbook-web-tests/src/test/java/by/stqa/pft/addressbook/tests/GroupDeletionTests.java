package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
  }

  @Test
  public void testGroupDeletion() {
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().deleteSelectedGroup();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);

    // удаляем из списка before тот элемент, который удалили в тесте - его индекс =  before.size() - 1
    // для того, чтобы проверить, что в списке after не присутствует именно этот элемент;
    before.remove(before.size() - 1);

    // для проверки прогоняем все элементы списка after по циклу
    //    for (int i = 0; i < after.size(); i++) {
    //      Assert.assertEquals(before.get(i), after.get(i));
    //    }

    // после генерации методов toString & equals в классе GroupData можно не прогонять элементы списка через цикл, а просто
    // сравнить списки
    Assert.assertEquals(before, after);

  }

}
