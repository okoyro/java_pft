package by.stqa.pft.addressbook.appmanager;

import by.stqa.pft.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> rows = wd.findElements(By.xpath("//tr[@name = 'entry']"));   //все строки на странице
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));  //разбили строки на ячейки
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      int id = Integer.parseInt(cells.get(0).findElement(By.xpath("input")).getAttribute("id"));
      ContactData contact = new ContactData(id, firstname, null, lastname, null, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void fillNewContactForm(ContactData contactData, boolean creation) {
    //   параметр "boolean creation" необходим для того, чтобы проверить условие с выпадаюшим списком
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastnane());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("home"), contactData.getHomephonenumber());
    type(By.name("email"), contactData.getEmail());
    //    условие для проверки наличия/отсутствия элемента(выпадаюший список),
    // характерного только для страницы создания нового контакта
    if (creation) {
      //      услвие, проверяющее что выпадающий список пуст(нет групп в данный момент)
      if (contactData.getGroup() != null) {
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      }
    }
    else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void deleteSelectedContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void selectContact(int index) {
    if (!wd.findElement(By.xpath("//tr[@name='entry']/td[1]/input")).isSelected()) {
      wd.findElements(By.xpath("//tr[@name='entry']/td[1]/input")).get(index).click();
    }
  }

  public void closeAlert() {
    wd.switchTo().alert().accept();
  }

  public void selectContactForEdit(int indexForEdit) {
    wd.findElements(By.cssSelector("img[alt=\"Edit\"]")).get(indexForEdit).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.cssSelector("img[alt='Details']"));
  }

  public void createContact(ContactData contact) {
    fillNewContactForm(contact, true);
    submitContactCreation();
  }

  public int getContactCount() {
    return wd.findElements(By.cssSelector("img[alt='Details']")).size();
  }
}
