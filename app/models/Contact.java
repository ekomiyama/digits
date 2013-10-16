package models;

/*
 * Creates a Contact object.
 */
public class Contact {
  
  private String firstName = "";
  
  private String lastName = "";
  
  private String telephone = "";
  
  private long id = 0;
  
  /*
   * Creates a Contact object.
   */
  public Contact(long id, String firstName, String lastName, String telephone) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.telephone = telephone;
    this.id = id;
  }
  
  /*
   * gets Contact firstName variable
   */
  public String firstName() {
    return firstName;
  }
  
  /*
   * gets Contact lastName variable
   */
  public String lastName() {
    return lastName;
  }
  
  /*
   * gets Contact telephone variable
   */
  public String telephone() {
    return telephone;
  }
  
  /*
   * gets Contact id variable
   */
  public long id() {
    return id;
  }
}
