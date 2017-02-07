package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.GroupData;
import by.stqa.pft.addressbook.model.Groups;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().gotoGroupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test_y");
    app.group().create(group);
    assertThat(app.group().сount(), equalTo(before.size() + 1));
    Groups after = app.group().all();
    assertThat(after, equalTo(before.withAdded
            (group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadGroupCreation() {
    app.goTo().gotoGroupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test'"); //это негативный тест, т.к.  апостроф - запрещенный символ
    app.group().create(group);
    assertThat(app.group().сount(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));
  }
}
