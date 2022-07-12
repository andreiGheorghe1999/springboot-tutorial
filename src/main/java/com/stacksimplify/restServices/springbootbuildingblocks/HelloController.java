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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * Class name: Hello.java Copyright (c)2022, GMV All rights reserved.
 * 
 * @author angh Project acronym: MPSSERV Type name : springboot-tutorial Java compiler : jdk/ojdk
 *         1.8 Description:
 */
@RestController
public class HelloController
{
  @Autowired
  private ResourceBundleMessageSource resourceBundleMessageSource;
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

  @GetMapping("/hello-int")
  public String getMessagesInI18Nformat(@RequestHeader(value = "Accept-Language", required = false) String locale){
    //return resourceBundleMessageSource.getMessage("label.hello", null,new Locale(locale));
    //Better : the localeContextHolder does the job for us of identyfying the system language
    return resourceBundleMessageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale());
  }
}
