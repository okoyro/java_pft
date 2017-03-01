package by.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class UserHelper extends HelperBase {
  public UserHelper(ApplicationManager applicationManager) {
    super(applicationManager);
  }

  public void selectUser(int userId) {
    click(By.cssSelector("a[href='/mantisbt-2.1.0/manage_overview_page.php']"));
    click(By.cssSelector("a[href='/mantisbt-2.1.0/manage_user_page.php']"));
    click(By.cssSelector(String.format("a[href^='manage_user_edit_page.php?user_id=%d']", userId)));
    click(By.cssSelector("input[value='Reset Password']"));
  }

  public void finishResetPwd(String resetPwdLink, String password, String user) {
    wd.get(resetPwdLink);
    type(By.name("realname"), user);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("button[type='Submit']"));
  }
}
