
package com.Team3.MaitreD.models;

public class RegistrationDTO {
    private String username;
    private String password;
    private String email;
    private String accountType;
    private String imagePath; // Path to the user image

    public RegistrationDTO(){
        super();
    }

    public RegistrationDTO(String username, String email, String password, String accountType, String imagePath){
        this.username = username;
        this.password = password;
        this.email = email;
        this.accountType = accountType;
        this.imagePath = imagePath; // Initialize image path
    }

    // Getter and setter for imagePath
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // Existing getters and setters...
}
