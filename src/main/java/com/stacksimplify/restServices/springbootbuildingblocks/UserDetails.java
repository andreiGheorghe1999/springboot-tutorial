/**
 * Class name: UserDetails.java
 * Copyright (c)2022, GMV 
 * All rights reserved. 
 * @author angh
 * Project acronym: MPSSERV
 * Module name : springboot-tutorial
 * Java compiler : jdk/ojdk 1.8
 * Description:
 */
package com.stacksimplify.restServices.springbootbuildingblocks;

/**
 * Class name: UserDetails.java Copyright (c)2022, GMV All rights reserved.
 * 
 * @author angh Project acronym: MPSSERV Type name : springboot-tutorial Java compiler : jdk/ojdk
 *         1.8 Description:
 */
public class UserDetails
{
  private String firstName;
  private String lastName;
  private String city;

  /**
   * 
   * @param firstName
   * @param lastName
   * @param city
   */
  public UserDetails(String firstName, String lastName, String city)
  {
    // TODO Auto-generated constructor stub
    this.firstName = firstName;
    this.city = city;
    this.lastName = lastName;
  }

  /**
   * @return the firstName
   */
  public String getFirstName()
  {
    return firstName;
  }

  /**
   * @param firstName
   *          the firstName to set
   */
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  /**
   * @return the lastName
   */
  public String getLastName()
  {
    return lastName;
  }

  /**
   * @param lastName
   *          the lastName to set
   */
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  /**
   * @return the city
   */
  public String getCity()
  {
    return city;
  }

  /**
   * @param city
   *          the city to set
   */
  public void setCity(String city)
  {
    this.city = city;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    return "UserDetails [firstName=" + firstName
           + ", lastName="
           + lastName
           + ", city="
           + city
           + "]";
  }
}
