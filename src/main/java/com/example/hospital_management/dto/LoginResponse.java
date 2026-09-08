package com.example.hospital_management.dto;

public class LoginResponse {

    private Integer id;
    private String username;
    private String email;
    private String role;
    private String token;

    public LoginResponse(){

    }

    public LoginResponse(Integer id, String username, String email, String role, String token) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.token = token;

    }

    public Integer getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }
    public String getEmail(){
        return email;
    }
    public String getRole(){
        return role;
    }
    public String getToken(){
        return token;
    }

}
