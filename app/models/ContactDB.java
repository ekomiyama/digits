package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import views.formdata.ContactFormData;

/*
 * Creates a list in memory of contacts added to the application.
 */
public class ContactDB {
  
  private static Map<Long, Contact> contacts = new HashMap<>();
  
  /*
   * Adds a contact to the list or update existing contact
   */
  public static Contact addContact(ContactFormData formData) {
    Contact contact;
    if(formData.id == 0) {
      long id = contacts.size() + 1;
      contact = new Contact(id, formData.firstName, formData.lastName, formData.telephone);
      contacts.put(id, contact);
    }else {
      contact = new Contact(formData.id, formData.firstName, formData.lastName, formData.telephone);
      contacts.put(formData.id, contact);
    }
    
    return contact;
  }
  
 /**
  * returns the list of contacts
  * @return
  */
  public static List<Contact> getContacts() {
    return new ArrayList<>(contacts.values());
  }
  
  /**
   * Retrieves a contact with specific id.
   * @param id
   * @return
   */
  public static Contact getContact(long id) {
    Contact contact = contacts.get(id);
    if (contact == null) {
      throw new RuntimeException("The Contact does not exist at id: " + id);
    }
    
    return contact;
  }
  
  /**
   * Deletes a contact from the map.
   * @param id
   */
  public static void deleteContact(long id) {
    if (contacts.get(id) != null) {
    contacts.remove(id);
    } else {
      return;
    }
  }
}
