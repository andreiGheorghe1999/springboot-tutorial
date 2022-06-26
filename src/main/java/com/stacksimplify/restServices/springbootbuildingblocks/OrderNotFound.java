package com.stacksimplify.restServices.springbootbuildingblocks;

public class OrderNotFound extends Exception{
    private static long serialUID = 1L;

    public OrderNotFound(String message){
        super(message);
    }
}
