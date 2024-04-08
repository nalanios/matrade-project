package com.Team3.MaitreD.models;

public class RegistrationDTO {
	private String username;
    private String password;
    private String email;

    public RegistrationDTO(){
        super();
    }

    public RegistrationDTO(String username, String email, String password){
        super();
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }
    
    public String getEmail(){
        return this.username;
    }

    public void setEmail(String email){
    	this.email = email;
    }
    
    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String toString(){
        return "Registration info: username: " + this.username + "email: " + this.email + " password: " + this.password;
    }
}
