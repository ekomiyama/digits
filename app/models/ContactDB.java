package models;

import java.util.List;
import views.formdata.ContactFormData;

/*
 * Creates a list in memory of contacts added to the application.
 */
public class ContactDB {
  
  
  /**
   * Returns if user is in database.
   * @param user
   * @return
   */
  public static boolean isUser(String user) {
    return (UserInfo.find().where().eq("email", user).findUnique() != null);
  }
  
  /*
   * Adds a contact to the list or update existing contact
   */
  public static void addContact(String user, ContactFormData formData) {
    if(formData.id == -1) {
      Contact contact = new Contact(formData.firstName, formData.lastName, formData.telephone, formData.telephoneType);
      UserInfo userInfo = UserInfo.find().where().eq("email", user).findUnique();
      userInfo.addContact(contact);
      contact.setUserInfo(userInfo);
      contact.save();
      userInfo.save();
    } else {
     Contact contact = Contact.find().byId(formData.id);
     contact.setFirstName(formData.firstName);
     contact.setLastName(formData.lastName);
     contact.setTelephone(formData.telephone);
     contact.setTelephoneType(formData.telephoneType);
     contact.save();
      
    }
  }
  
 /**
  * returns the list of contacts
  * @return
  */
  public static List<Contact> getContacts(String user) {
    UserInfo userInfo = UserInfo.find().where().eq("email", user).findUnique();
    if(userInfo == null) {
      return null;
    }else {
      return userInfo.getContacts();
    }
  }
  
  /**
   * Retrieves a contact with specific id.
   * @param id
   * @return
   */
  public static Contact getContact(String user, long id) {
    Contact contact = Contact.find().byId(id);
    if(contact == null) {
      throw new RuntimeException("ID: " + id + " not found");
    }
    
    UserInfo userInfo = contact.getUserInfo();
    if(! user.equals(userInfo.getEmail())) {
      throw new RuntimeException("The User " + user + " is not the same one stored with contact");
    }
   
    return contact;
  }
  
  /**
   * Deletes a contact from the map.
   * @param id
   */
  public static void deleteContact(String user, long id) {
    Contact.find().byId(id).delete();
  }
}
