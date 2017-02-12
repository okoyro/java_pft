package by.stqa.pft.addressbook.appmanager;

import by.stqa.pft.addressbook.model.ContactData;
import by.stqa.pft.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class ContactHelper extends HelperBase {

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> rows = wd.findElements(By.xpath("//tr[@name = 'entry']"));   //все строки на странице
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));  //разбили строки на ячейки
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String allPhones = cells.get(5).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      int id = Integer.parseInt(cells.get(0).findElement(By.xpath("input")).getAttribute("id"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastnane(lastname).withAllPhones(allPhones)
                           .withAddress(address).withAllEmails(allEmails));
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
    type(By.name("lastname"), contactData.getLastnane());
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    attach(By.name("photo"), contactData.getPhoto());
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
    wd.findElement(By.cssSelector("a[href*='edit.php?id=" + id + "']")).click();

  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void create(ContactData contact) {
    fillNewContactForm(contact, true);
    submitContactCreation();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    fillNewContactForm(contact, false);
    submitContactModification();
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    returnToHomePage();
  }

  public void deleteSelectedContact() {
    click(By.cssSelector("input[value='Delete']"));
  }

  public ContactData contactInfoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getText();
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastnane(lastname).withAddress(address)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }

  public ContactData contactInfoFromFullInfoForm(ContactData contact) {
    initContactFullInfoView(contact.getId());
    String fullInfo = wd.findElement(By.cssSelector("#content")).getText();
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFullInfo(fullInfo);
  }

  private void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value = '%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

    //    wd.findElement(By.xpath(String.format("//input[@value = '%s']/../../td[8]/a"))).click();
    //    wd.findElement(By.xpath(String.format("//tr[.//input[value = '%s']]/td[8]/a"))).click();
    //    wd.findElement(By.cssSelector(String.format("a[href = 'edit.php?id=%s']", id))).click();
  }

  private void initContactFullInfoView(int id) {
    wd.findElement(By.cssSelector(String.format("a[href = 'view.php?id=%s']", id))).click();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.cssSelector("img[alt='Details']"));
  }

  public int getContactCount() {
    return wd.findElements(By.cssSelector("img[alt='Details']")).size();
  }

  public void returnToHomePage() {
    click(By.linkText("home"));
  }
}
