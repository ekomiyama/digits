package controllers;


import java.util.Map;
import models.ContactDB;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.Index;
import views.html.Login;
import views.html.NewContacts;
import views.html.Profile;
import views.formdata.ContactFormData;
import views.formdata.LoginFormData;
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
    String user = Secured.getUser(ctx());
    return ok(Index.render("Home", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), ContactDB.getContacts(user)));
  }
  
  /**
   * Returns page1, a simple example of a second page to illustrate navigation.
   * @return The Page1.
   * @param id
   */
  @Security.Authenticated(Secured.class)
  public static Result newContacts(long id) {
    String user = Secured.getUser(ctx());;
    ContactFormData data = (id == 0) ? new ContactFormData() : new ContactFormData(ContactDB.getContact(user, id));
    Form<ContactFormData> formdata = Form.form(ContactFormData.class).fill(data);
    Map<String, Boolean> telephoneTypeMap = TelephoneType.getTypes();
    return ok(NewContacts.render("NewContact", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), formdata, telephoneTypeMap));
    
  }
  
  /**
   * Returns page1, a simple example of a second page to illustrate navigation.
   * @return The Page1.
   */
  @Security.Authenticated(Secured.class)
  public static Result postContacts() {
    Form<ContactFormData> formdata = Form.form(ContactFormData.class).bindFromRequest();
    String user = Secured.getUser(ctx());;
    if (formdata.hasErrors()) {
      System.out.println("Errors Found");
      Map<String, Boolean> telephoneTypeMap = TelephoneType.getTypes();
      return badRequest(NewContacts.render("NewContact", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), formdata, telephoneTypeMap));
      
    }
    ContactFormData data = formdata.get();
    ContactDB.addContact(user, data);
    Map<String, Boolean> telephoneTypeMap = TelephoneType.addType(data.telephoneType);
    System.out.println(data.firstName + " " + data.lastName + " " + data.telephone);
    Form<ContactFormData> formdata2 = Form.form(ContactFormData.class);
    return ok(NewContacts.render("NewContact", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), formdata2, telephoneTypeMap));
    
  }
  
  /**
   * Deletes a contact from contact list.
   * @param id
   * @return
   */
  @Security.Authenticated(Secured.class)
  public static Result deleteContact(long id) {
    String user = Secured.getUser(ctx());
    ContactDB.deleteContact(user, id);
    return ok(Index.render("Home", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), ContactDB.getContacts(user)));
  }
  
  /**
   * Provides the Login page (only to unauthenticated users). 
   * @return The Login page. 
   */
  public static Result login() {
    Form<LoginFormData> formData = Form.form(LoginFormData.class);
    return ok(Login.render("Login", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), formData));
  }

  /**
   * Processes a login form submission from an unauthenticated user. 
   * First we bind the HTTP POST data to an instance of LoginFormData.
   * The binding process will invoke the LoginFormData.validate() method.
   * If errors are found, re-render the page, displaying the error data. 
   * If errors not found, render the page with the good data. 
   * @return The index page with the results of validation. 
   */
  public static Result postLogin() {

    // Get the submitted form data from the request object, and run validation.
    Form<LoginFormData> formData = Form.form(LoginFormData.class).bindFromRequest();

    if (formData.hasErrors()) {
      flash("error", "Login credentials not valid.");
      return badRequest(Login.render("Login", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), formData));
    }
    else {
      // email/password OK, so now we set the session variable and only go to authenticated pages.
      session().clear();
      session("email", formData.get().email);
      return redirect(routes.Application.index());
    }
  }
  
  /**
   * Logs out (only for authenticated users) and returns them to the Index page. 
   * @return A redirect to the Index page. 
   */
  @Security.Authenticated(Secured.class)
  public static Result logout() {
    session().clear();
    return redirect(routes.Application.index());
  }
  
  /**
   * Provides the Profile page (only to authenticated users).
   * @return The Profile page. 
   */
  @Security.Authenticated(Secured.class)
  public static Result profile() {
    return ok(Profile.render("Profile", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx())));
  }
}
