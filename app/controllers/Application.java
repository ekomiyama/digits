package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.Index;
import views.html.NewContacts;
import views.formdata.ContactFormData;

/**
 * Implements the controllers for this application.
 */
public class Application extends Controller {

  /**
   * Returns the home page. 
   * @return The resulting home page. 
   */
  public static Result index() {
    return ok(Index.render("Welcome to the home page."));
  }
  
  /**
   * Returns page1, a simple example of a second page to illustrate navigation.
   * @return The Page1.
   */
  public static Result newContacts() {
    Form<ContactFormData> formdata = Form.form(ContactFormData.class);
    return ok(NewContacts.render(formdata));
    
  }
  
  /**
   * Returns page1, a simple example of a second page to illustrate navigation.
   * @return The Page1.
   */
  public static Result postContacts() {
    Form<ContactFormData> formdata = Form.form(ContactFormData.class).bindFromRequest();
    ContactFormData data = formdata.get();
    System.out.println(data.firstName + " " + data.lastName + " " + data.telephone);
    Form<ContactFormData> formdata2 = Form.form(ContactFormData.class);
    return ok(NewContacts.render(formdata2));
    
  }
}
