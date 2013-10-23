package models;

/*
 * Creates a Contact object.
 */
public class Contact {
  
  private String firstName = "";
  
  private String lastName = "";
  
  private String telephone = "";
  
  private String telephoneType = "";
  
  private long id = 0;
  
  /**
   * Creates new Contact.
   * @param id
   * @param firstName
   * @param lastName
   * @param telephone
   */
  public Contact(long id, String firstName, String lastName, String telephone, String telephoneType) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.telephone = telephone;
    this.telephoneType = telephoneType;
    this.id = id;
  }
  
  /**
   * gets Contact firstName variable.
   * @return
   */
  public String firstName() {
    return firstName;
  }
  
  /**
   * gets Contact lastName variable.
   * @return
   */
  public String lastName() {
    return lastName;
  }
  
  /**
   * gets Contact telephone variable.
   * @return
   */
  public String telephone() {
    return telephone;
  }
  
  /**
   * gets Contact id variable
   * @return
   */
  public long id() {
    return id;
  }
  
  public String telephoneType() {
    return telephoneType;
  }
}
