package com.supercode.model;

import javax.validation.constraints.NotBlank;

public class Credentials {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public Credentials() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

