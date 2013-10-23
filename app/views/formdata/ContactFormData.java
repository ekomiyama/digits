package views.formdata;
import java.util.ArrayList;
import java.util.List;
import models.Contact;
import play.data.validation.ValidationError;

/*
 * Backing Class which holds string variables.
 */
public class ContactFormData {
  
  //First Name field
  public String firstName = "";
  
  //Last Name field
  public String lastName = "";
  
  //Digits field
  public String telephone = "";
  
  //Telephone Type Field
  public String telephoneType = "";
  
  //id field
  public long id = 0;
  
  public ContactFormData() {
    //Do Nothing
  }
  
  /**
   * @param firstName
   * @param lastName
   * @param telephone
   * @param telephoneType
   */
  public ContactFormData(String firstName, String lastName, String telephone, String telephoneType) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.telephone = telephone;
    this.telephoneType = telephoneType;
  }
  
  /**
   * Creates a ContactFormData object from a Contact object parameter
   */
  public ContactFormData(Contact contact) {
    this.id = contact.id();
    this.firstName = contact.firstName();
    this.lastName = contact.lastName();
    this.telephone = contact.telephone();
    this.telephoneType = contact.telephoneType();
  }
  
  
  /*
   * Validates form and checks for errors..
   */
  public List<ValidationError> validate() {
    
    List<ValidationError> errors = new ArrayList<>();
    
    if(firstName == "") {
      errors.add(new ValidationError("firstName", "First Name is Required"));
    }
    
    if(lastName == "") {
      errors.add(new ValidationError("lastName", "Last Name is Required"));
    }
    
    if(telephone == "") {
      errors.add(new ValidationError("telephone", "Phone Number is Required"));
    }else if(telephone.length() != 12) {
      errors.add(new ValidationError("telephone", "Phone Number must be xxx-xxx-xxxx"));
    }
    
    if(! TelephoneType.hasType(telephoneType)) {
      errors.add(new ValidationError("telephoneType", "Telephone Type must be a 'Home', 'Work', or 'Mobile'"));
    }
    
    return(errors.isEmpty()? null: errors);
    
  }
}
