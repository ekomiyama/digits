package controllers;


import java.util.Map;
import models.ContactDB;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.Index;
import views.html.NewContacts;
import views.formdata.ContactFormData;
import views.formdata.TelephoneType;

/**
 * Implements the controllers for this application.
 */
public class Application extends Controller {

  /**
   * Returns the home page. 
   * @return The resulting home page. 
   */
  public static Result index() {
    return ok(Index.render(ContactDB.getContacts()));
  }
  
  /**
   * Returns page1, a simple example of a second page to illustrate navigation.
   * @return The Page1.
   * @param id
   */
  public static Result newContacts(long id) {
    ContactFormData data = (id == 0) ? new ContactFormData() : new ContactFormData(ContactDB.getContact(id));
    Form<ContactFormData> formdata = Form.form(ContactFormData.class).fill(data);
    Map<String, Boolean> telephoneTypeMap = TelephoneType.getTypes();
    return ok(NewContacts.render(formdata, telephoneTypeMap));
    
  }
  
  /**
   * Returns page1, a simple example of a second page to illustrate navigation.
   * @return The Page1.
   */
  public static Result postContacts() {
    Form<ContactFormData> formdata = Form.form(ContactFormData.class).bindFromRequest();
    if (formdata.hasErrors()) {
      System.out.println("Errors Found");
      Map<String, Boolean> telephoneTypeMap = TelephoneType.getTypes();
      return badRequest(NewContacts.render(formdata, telephoneTypeMap));
      
    }
    ContactFormData data = formdata.get();
    ContactDB.addContact(data);
    Map<String, Boolean> telephoneTypeMap = TelephoneType.addType(data.telephoneType);
    System.out.println(data.firstName + " " + data.lastName + " " + data.telephone);
    Form<ContactFormData> formdata2 = Form.form(ContactFormData.class);
    return ok(NewContacts.render(formdata2, telephoneTypeMap));
    
  }
  
  /**
   * Deletes a contact from contact list.
   * @param id
   * @return
   */
  public static Result deleteContact(long id) {
    ContactDB.deleteContact(id);
    return ok(Index.render(ContactDB.getContacts()));
  }
}
