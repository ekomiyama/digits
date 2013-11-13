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
  
  private static Map<String, Map<Long, Contact>> contacts = new HashMap<String, Map<Long, Contact>>();
  
  /**
   * Returns if user is in database.
   * @param user
   * @return
   */
  public static boolean isUser(String user) {
    return contacts.containsKey(user);
  }
  
  /*
   * Adds a contact to the list or update existing contact
   */
  public static Contact addContact(String user, ContactFormData formData) {
    Contact contact;
    if(formData.id == 0) {
      long id = contacts.size() + 1;
      contact = new Contact(id, formData.firstName, formData.lastName, formData.telephone, formData.telephoneType);
      if(! isUser(user)) {
        contacts.put(user, new HashMap<Long, Contact>());
      }
      contacts.get(user).put(id, contact);
    }else {
      contact = new Contact(formData.id, formData.firstName, formData.lastName, formData.telephone, formData.telephoneType);
      if(! isUser(user)) {
        contacts.put(user, new HashMap<Long, Contact>());
      }
      contacts.get(user).put(formData.id, contact);
    }
    
    return contact;
  }
  
 /**
  * returns the list of contacts
  * @return
  */
  public static List<Contact> getContacts(String user) {
    if(! isUser(user)) {
      return null;
    }
    return new ArrayList<>(contacts.get(user).values());
  }
  
  /**
   * Retrieves a contact with specific id.
   * @param id
   * @return
   */
  public static Contact getContact(String user, long id) {
    if(! isUser(user)) {
      throw new RuntimeException("The User " + user + "does not exist");
    }
    Contact contact = contacts.get(user).get(id);
    if (contact == null) {
      throw new RuntimeException("The Contact does not exist at id: " + id);
    }
    
    return contact;
  }
  
  /**
   * Deletes a contact from the map.
   * @param id
   */
  public static void deleteContact(String user, long id) {
    if (contacts.get(user).get(id) != null) {
    contacts.get(user).remove(id);
    } else {
      return;
    }
  }
}
