package by.stqa.pft.mantis.tests;

import by.stqa.pft.mantis.model.MailMessage;
import by.stqa.pft.mantis.model.User;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.util.List;

public class ChangePasswordTests extends TestBase {

  private static final String NEW_PASSWORD = "password";

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testChangePasswordByAdmin() throws IOException, MessagingException {
    app.session().login("administrator", "password");
    User user = app.db().userById(2);
    app.user().selectUser(user.getId());
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String resetPwdLink = findResetPasswordLink(mailMessages.get(0), user.getEmail());
    System.out.println(resetPwdLink);
    app.user().finishResetPwd(resetPwdLink, NEW_PASSWORD, user.getRealname());
    app.session().loginPage();
    app.session().login(user.getUsername(), NEW_PASSWORD);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

  private String findResetPasswordLink(MailMessage mailMessage, String email) {
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }


}
