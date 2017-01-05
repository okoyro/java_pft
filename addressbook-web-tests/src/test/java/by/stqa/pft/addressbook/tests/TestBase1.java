package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.appmanager.ApplicationManager1;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase1 {

  protected final ApplicationManager1 app1 = new ApplicationManager1();

  @BeforeMethod
  public void setUp() throws Exception {
    app1.init();
  }

  @AfterMethod
  public void tearDown() {
    app1.stop();
  }

}
