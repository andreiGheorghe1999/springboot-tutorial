package com.stacksimplify.restServices.springbootbuildingblocks;

public class UserMapStructDTO {

    private Long userId;
    private String username;
    private String emailAddress;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    private String rolename;

    public Long getUserId() {
        return userId;
    }

    public UserMapStructDTO() {
    }

    public UserMapStructDTO(Long userId, String username, String emailAddress, String rolename) {
        this.userId = userId;
        this.username = username;
        this.emailAddress = emailAddress;
        this.rolename = rolename;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
