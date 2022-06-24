package com.stacksimplify.restServices.springbootbuildingblocks;

public class UserNotFound extends Exception{

    private static long serialUID = 1L;

    public UserNotFound(String message){
        super(message);
    }
}
