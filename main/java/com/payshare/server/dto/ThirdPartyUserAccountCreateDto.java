package com.payshare.server.dto;

import javax.validation.constraints.Pattern;

public class ThirdPartyUserAccountCreateDto {

    @Pattern(regexp = "[a-z0-9.]+@[a-z0-9][a-z0-9.]*\\.[a-z]{2,3}", message = "The email address is incorrect.")
    public String email;

    public String name;

    public String provider;

    public String get_email(){
        return email;
    }

    public String get_name(){
        return name;   
    }

    public String get_provider(){
        return provider;
    }

    public void set_email(String email){
        this.email = email;
    }

    public void set_name(String name){
        this.name = name;
    }

    public void set_provider(String provider){
        this.provider = provider;
    }
}
