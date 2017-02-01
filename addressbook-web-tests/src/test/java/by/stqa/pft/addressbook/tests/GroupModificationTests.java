package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().gotoGroupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupModification() {
    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    GroupData group = new GroupData()
            .withId(before.get(index).getId()).withName("test1(ed)").withHeader("test2(ed)").withFooter("test3(ed)");
    app.group().modify(index, group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size());

    // удаляем из списка before тот элемент, который удалили в тесте - его индекс =  before.size() - 1
    // и добавляем в список именно ту группу, которую мы модифицировали
    before.remove(index);
    before.add(group);

    // реализуем упорядочивание списков по id и затем сравниваем их
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

    // реализуем сравнение не списков, а множеств, т.к. мы не можем предсказать, на каком месте в списке окажется
    // модифицированная группа
    // Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
