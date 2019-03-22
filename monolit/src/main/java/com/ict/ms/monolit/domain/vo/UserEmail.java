package com.ict.ms.monolit.domain.vo;


import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@EqualsAndHashCode(of="email")
public class UserEmail implements Serializable {

    private String email;

    UserEmail(){}

    public UserEmail(String email){
        this.email = email.toLowerCase();
    }

}
