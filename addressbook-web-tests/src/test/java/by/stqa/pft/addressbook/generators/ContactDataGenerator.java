package by.stqa.pft.addressbook.generators;

import by.stqa.pft.addressbook.model.ContactData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    }
    catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    }
    else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    }
    else if (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    }
    else {
      System.out.println("Unrecognized format" + format);
    }
  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getMiddlename(),
                                 contact.getLastnane(), contact.getHomePhone(), contact.getMobilePhone(),
                                 contact.getWorkPhone(), contact.getAddress(), contact.getEmail(),
                                 contact.getEmail2(), contact.getEmail3()));
    }
    writer.close();
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstname(String.format("firstname %s", i))
                           .withMiddlename(String.format("middlename %s", i))
                           .withLastnane(String.format("lastname %s", i))
                           .withHomePhone(String.format("11111111%s", i))
                           .withMobilePhone(String.format("22222222%s", i))
                           .withWorkPhone(String.format("33333333%s", i))
                           .withAddress(String.format("address %s", i))
                           .withEmail(String.format("email1 %s", i))
                           .withEmail2(String.format("email2 %s", i))
                           .withEmail3(String.format("email3 %s", i)));
    }
    return contacts;
  }
}
