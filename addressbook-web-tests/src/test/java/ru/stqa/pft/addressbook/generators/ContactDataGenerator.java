package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

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
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();

    }

    private void run() throws IOException {
            List<ContactData> contacts = generateContacs(count);
            if (format.equals("xml")){
                saveAsXml(contacts, new File(file));
            }
            else if (format.equals("json")){
                saveAsJson(contacts, new File(file));
            }
            else {
                System.out.println("Unrecognized format" + format);
                return;
            }
        }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        String xml = xStream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private List<ContactData> generateContacs(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i=0; i < count; i++) {
            contacts.add(new ContactData().withFirstname(String.format("Max %s", i))
                    .withLastname(String.format("Bolshakov %s", i)).withMobilePhone(String.format("+7921444444%s", i))
                    .withHomePhone(String.format("333-33-3%s", i)).withWorkPhone(String.format("8812555555%s", i))
                    .withAddress(String.format("Spb %s", i)).withEmail(String.format("Bolshakov%s@gmail.com", i))
                    .withGroup(String.format("test", i)).withEmail1(String.format("Bolshakov%s@mail.com", i))
                    .withEmail2(String.format("Bolshakov%s@me.com", i)));
        }
        return contacts;
    }
}
