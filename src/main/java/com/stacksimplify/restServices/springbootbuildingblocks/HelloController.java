/**
 * Class name: Hello.java
 * Copyright (c)2022, GMV 
 * All rights reserved. 
 * @author angh
 * Project acronym: MPSSERV
 * Module name : springboot-tutorial
 * Java compiler : jdk/ojdk 1.8
 * Description:
 */
package com.stacksimplify.restServices.springbootbuildingblocks;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class name: Hello.java Copyright (c)2022, GMV All rights reserved.
 * 
 * @author angh Project acronym: MPSSERV Type name : springboot-tutorial Java compiler : jdk/ojdk
 *         1.8 Description:
 */
@RestController
public class HelloController
{

  @GetMapping("/helloWorld")
  public String helloWorld()
  {
    return "Hello World";
  }

  @GetMapping("/getUserObject")
  public UserDetails getUserObject()
  {
    return new UserDetails("Andrei", "Gheorghe", "Bucuresti");
  }
}
