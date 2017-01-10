package by.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String middlename;
  private final String lastnane;
  private final String title;
  private final String company;
  private final String homephonenumber;
  private String group;
  private final String email;

  public ContactData(String firstname, String middlename,
                     String lastnane, String title, String company,
                     String homephonenumber,
                     String email, String group)
  { this.firstname = firstname;
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
}
