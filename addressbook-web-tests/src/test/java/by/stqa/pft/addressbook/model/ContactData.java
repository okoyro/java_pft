package by.stqa.pft.addressbook.model;

public class ContactData {
  private String id;
  private String firstname;
  private String middlename;
  private String lastnane;
  private String title;
  private String company;
  private String homephonenumber;
  private String group;
  private String email;

  public ContactData(String id, String firstname, String middlename,
                     String lastnane, String title, String company,
                     String homephonenumber,
                     String email, String group) {
    this.id = id;
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastnane = lastnane;
    this.title = title;
    this.company = company;
    this.homephonenumber = homephonenumber;
    this.group = group;
    this.email = email;
  }
// создаем еще один конструктор, в аоторый не передаем id в качестве параметра - это для группы с неизвестным id
  public ContactData(String firstname, String middlename,
                     String lastnane, String title, String company,
                     String homephonenumber,
                     String email, String group) {
    this.id = null;
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastnane = lastnane;
    this.title = title;
    this.company = company;
    this.homephonenumber = homephonenumber;
    this.group = group;
    this.email = email;
  }

  public String getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastnane() {
    return lastnane;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getHomephonenumber() {
    return homephonenumber;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public String toString() {
    return "ContactData{" +
           "id='" + id + '\'' +
           ", firstname='" + firstname + '\'' +
           ", lastnane='" + lastnane + '\'' +
           '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastnane != null ? lastnane.equals(that.lastnane) : that.lastnane == null;

  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastnane != null ? lastnane.hashCode() : 0);
    return result;
  }
}
