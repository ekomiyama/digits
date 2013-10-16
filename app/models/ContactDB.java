package models;

import java.util.ArrayList;
import java.util.List;
import views.formdata.ContactFormData;

public class ContactDB {

  private static List<Contact> contacts = new ArrayList<>();
  
  public static Contact addContact(ContactFormData formData) {
    Contact contact = new Contact(formData.firstName, formData.lastName, formData.telephone);
    contacts.add(contact);
    return contact;
  }
  
  public static List<Contact> getContacts() {
    return contacts;
  }
}
