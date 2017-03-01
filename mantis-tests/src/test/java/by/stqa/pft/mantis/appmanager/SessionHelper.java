package by.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase {

  public SessionHelper(ApplicationManager applicationManager) {
    super(applicationManager);
  }

  public void login(String username, String password) {
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[type='submit']"));
  }

  public void loginPage() {
    wd.get(app.getProperty("web.baseUrl"));
  }
}
