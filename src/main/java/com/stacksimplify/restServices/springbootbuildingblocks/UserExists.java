package com.stacksimplify.restServices.springbootbuildingblocks;

public class UserExists extends Exception{
    private static long serialUID = 1L;

    public UserExists(String message){
        super(message);
    }
}
