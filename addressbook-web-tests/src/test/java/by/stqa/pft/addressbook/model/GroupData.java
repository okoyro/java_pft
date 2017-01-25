package by.stqa.pft.addressbook.model;

public class GroupData {
  //меняем тип id со string на int потому что надо искать id с наибольшим числовым значением в тесте создания групп
  private int id;
  private String name;
  private String header;
  private String footer;

  public GroupData(String name, String header, String footer) {
    this.id = Integer.MAX_VALUE;
    this.name = name;
    this.header = header;
    this.footer = footer;
  }

  public GroupData(int id, String name, String header, String footer) {
    // вводим параметр id, он уникален, это необходимо для правильной работы тестов в случае сравнения множеств, т.к. во множестве
    // группы с одинаковыми именами воспринимаются как один объект
    this.id = id;
    this.name = name;
    this.header = header;
    this.footer = footer;
  }

  public int getId() {return id;}

  public String getName() {
    return name;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData groupData = (GroupData) o;

    return name != null ? name.equals(groupData.name) : groupData.name == null;

  }

  @Override
  public int hashCode() {
    return name != null ? name.hashCode() : 0;
  }

  @Override

  public String toString() {
    return "GroupData{" +
           "id='" + id + '\'' +
           ", name='" + name + '\'' +
           '}';
  }

  public void setId(int id) {
    this.id = id;
  }

}
