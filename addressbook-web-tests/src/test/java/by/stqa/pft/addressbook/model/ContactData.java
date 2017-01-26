package by.stqa.pft.addressbook.model;

public class ContactData {
  private String firstname;
  private String middlename;
  private String lastnane;
  private String title;
  private String company;
  private String homephonenumber;
  private String group;
  private String email;

  public ContactData(String firstname, String middlename,
                     String lastnane, String title, String company,
                     String homephonenumber,
                     String email, String group) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastnane = lastnane;
    this.title = title;
    this.company = company;
    this.homephonenumber = homephonenumber;
    this.group = group;
    this.email = email;
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
  //  этот метод необходим для сравнения в тестах объектов типа ContactData
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (middlename != null ? !middlename.equals(that.middlename) : that.middlename != null) return false;
    if (lastnane != null ? !lastnane.equals(that.lastnane) : that.lastnane != null) return false;
    if (title != null ? !title.equals(that.title) : that.title != null) return false;
    if (company != null ? !company.equals(that.company) : that.company != null) return false;
    if (homephonenumber != null ? !homephonenumber.equals(that.homephonenumber) : that.homephonenumber != null) {
      return false;
    }
    if (group != null ? !group.equals(that.group) : that.group != null) return false;
    return email != null ? email.equals(that.email) : that.email == null;

  }

  @Override
  public int hashCode() {
    int result = firstname != null ? firstname.hashCode() : 0;
    result = 31 * result + (middlename != null ? middlename.hashCode() : 0);
    result = 31 * result + (lastnane != null ? lastnane.hashCode() : 0);
    result = 31 * result + (title != null ? title.hashCode() : 0);
    result = 31 * result + (company != null ? company.hashCode() : 0);
    result = 31 * result + (homephonenumber != null ? homephonenumber.hashCode() : 0);
    result = 31 * result + (group != null ? group.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    return result;
  }

  @Override

  public String toString() {
    return "ContactData{" +
           "firstname='" + firstname + '\'' +
           '}';
  }
}
