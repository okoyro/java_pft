package by.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase1 {

  @Test
  public void testContactDeletion() {
    if (!wd.findElement(By.xpath("//tr[@name='entry']/td[1]/input")).isSelected()) {
      wd.findElement(By.xpath("//tr[@name='entry']/td[1]/input")).click();
    }
    wd.findElement(By.xpath("//div[@id='content']/form[2]/div[2]/input")).click();
  }
}
