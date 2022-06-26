package com.stacksimplify.restServices.springbootbuildingblocks;

public class UsernameNotFoundException extends Exception{
    private static long serialUID = 1L;

    public UsernameNotFoundException(String message){
        super(message);
    }
}
