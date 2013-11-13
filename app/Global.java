import models.ContactDB;
import models.UserInfoDB;
import play.Application;
import play.GlobalSettings;
import play.Play;
import views.formdata.ContactFormData;

/**
 * 
 * @author User1
 *
 */
public class Global extends GlobalSettings {
  
  /**
   * Initializes the startup procedure
   */
  public void onStart(Application app) { 
    
    String adminEmail = Play.application().configuration().getString("digits.admin.email");
    String adminPassword = Play.application().configuration().getString("digits.admin.password");
    UserInfoDB.defineAdmin("Administrator", adminEmail, adminPassword);
    
    if(UserInfoDB.adminDefined()) {
      ContactDB.addContact(adminEmail, new ContactFormData("Ghost", "Buster", "555-555-5555", "Work"));
      ContactDB.addContact(adminEmail, new ContactFormData("He", "Man", "000-000-0000", "Mobile"));
    }
    //UserInfoDB.addUserInfo("John Smith", "smith@example.com", "password"); 
    //UserInfoDB.addUserInfo("Mike Jones", "jones@example.com", "password");
    //ContactDB.addContact("smith@example.com", new ContactFormData("John", "Doe", "123-456-7890", "Home"));
    //ContactDB.addContact("smith@example.com", new ContactFormData("Jane", "Doe", "123-456-7890", "Home"));
    //ContactDB.addContact("jones@example.com", new ContactFormData("Ghost", "Buster", "555-555-5555", "Work"));
    //ContactDB.addContact("jones@example.com", new ContactFormData("He", "Man", "000-000-0000", "Mobile"));
  }

}
