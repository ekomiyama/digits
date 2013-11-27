package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import play.db.ebean.Model;

/*
 * Creates a Contact object.
 */
@Entity
public class Contact extends Model{
  
 
  private static final long serialVersionUID = 1L;

  @Id
  private String firstName = "";
  
  private String lastName = "";
  
  private String telephone = "";
  
  private String telephoneType = "";
  
  private long id = -1;
  
  
  @ManyToOne
  private UserInfo userInfo;
  
  /**
   * Creates new Contact.
   * @param firstName
   * @param lastName
   * @param telephone
   */
  public Contact(String firstName, String lastName, String telephone, String telephoneType) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.telephone = telephone;
    this.telephoneType = telephoneType;
  }
  
  /**
   * The EBean ORM finder method for database queries.
   * @return The finder method for contacts.
   */
  public static Finder<Long, Contact> find() {
    return new Finder<Long, Contact>(Long.class, Contact.class);
  }
  
  /**
   * gets Contact firstName variable.
   * @return
   */
  public String firstName() {
    return firstName;
  }
  
  /**
   * sets Contact firstName variable.
   * @return
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  /**
   * gets Contact lastName variable.
   * @return
   */
  public String lastName() {
    return lastName;
  }
  
  /**
   * sets Contact lastName variable.
   * @return
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  /**
   * gets Contact telephone variable.
   * @return
   */
  public String telephone() {
    return telephone;
  }
  
  /**
   * sets Contact telephone variable.
   * @return
   */
  public void setTelephone(String telephone) {
    this.telephone = telephone;
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
  
  public void setTelephoneType(String telephoneType) {
    this.telephoneType = telephoneType;
  }

  /**
   * @return the userInfo
   */
  public UserInfo getUserInfo() {
    return userInfo;
  }

  /**
   * @param userInfo the userInfo to set
   */
  public void setUserInfo(UserInfo userInfo) {
    this.userInfo = userInfo;
  }
}
