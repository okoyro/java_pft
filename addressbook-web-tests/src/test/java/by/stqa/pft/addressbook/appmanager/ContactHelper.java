package by.stqa.pft.addressbook.appmanager;

import by.stqa.pft.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> rows = wd.findElements(By.xpath("//tr[@name = 'entry']"));   //все строки на странице
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));  //разбили строки на ячейки
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      int id = Integer.parseInt(cells.get(0).findElement(By.xpath("input")).getAttribute("id"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastnane(lastname));
    }
    return contacts;
  }

  public Set<ContactData> all() {
    Set<ContactData> contacts = new HashSet<ContactData>();
    List<WebElement> rows = wd.findElements(By.xpath("//tr[@name = 'entry']"));   //все строки на странице
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));  //разбили строки на ячейки
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      int id = Integer.parseInt(cells.get(0).findElement(By.xpath("input")).getAttribute("id"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastnane(lastname));
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

  public void closeAlert() {
    wd.switchTo().alert().accept();
  }

  public void selectContact(int index) {
    if (!wd.findElement(By.xpath("//tr[@name='entry']/td[1]/input")).isSelected()) {
      wd.findElements(By.xpath("//tr[@name='entry']/td[1]/input")).get(index).click();
    }
  }

  private void selectContactById(int id) {
    wd.findElement(By.cssSelector(".center input[value = '" + id + "']")).isSelected();
    wd.findElement(By.cssSelector(".center input[value = '" + id + "']")).click();

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

  public void create(ContactData contact) {
    fillNewContactForm(contact, true);
    submitContactCreation();
  }

  public void modify(int index, ContactData contact) {
    selectContactForEdit(index);
    fillNewContactForm(contact, false);
    submitContactModification();
    returnToHomePage();
  }

  public void delete(int index) {
    selectContact(index);
    deleteSelectedContact();
    closeAlert();
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    closeAlert();
    returnToHomePage();
  }

  public void deleteSelectedContact() {
    click(By.cssSelector("input[value='Delete']"));
  }

  public int getContactCount() {
    return wd.findElements(By.cssSelector("img[alt='Details']")).size();
  }

  public void returnToHomePage() {
    click(By.linkText("home"));
  }
}
