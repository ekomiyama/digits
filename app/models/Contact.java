package models;

public class Contact {
  
  private String firstName = "";
  
  private String lastName = "";
  
  private String telephone = "";
  
  public Contact(String firstName, String lastName, String telephone) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.telephone = telephone;
  }
  
  
  public String firstName() {
    return firstName;
  }
  
  public String lastName() {
    return lastName;
  }
  
  public String telephone() {
    return telephone;
  }
}
