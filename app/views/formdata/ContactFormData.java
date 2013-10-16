package views.formdata;
import java.util.ArrayList;
import java.util.List;
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
    
    return(errors.isEmpty()? null: errors);
    
  }
}
