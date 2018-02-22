package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

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

    public static void main(String[] args) throws IOException {
      ContactDataGenerator generator = new ContactDataGenerator();
      JCommander.newBuilder()
              .addObject(generator)
              .build()
              .parse(args);
      generator.run();
    }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    save(contacts, new File(file));
  }

  private void save(List<ContactData> contacts, File file) throws IOException {
      System.out.println(new File(".").getAbsoluteFile());
      Writer writer = new FileWriter(file);
      for (ContactData contact : contacts) {
        writer.write(String.format("%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(), contact.getAddress(), contact.getMobile()));
      }
      writer.close();
    }

  private List<ContactData> generateContacts(int count) {
      List<ContactData> contacts = new ArrayList<ContactData>();
      for (int i = 0; i < count; i++) {
      contacts.add(new ContactData()
              .withFirstname(String.format("test1 %s", i)).withLastname(String.format("test2 %s", i))
              .withAddress(String.format("test3 %s", i)).withMobile(String.format("test3 %s", i)));
    }
    return contacts;
  }

}
