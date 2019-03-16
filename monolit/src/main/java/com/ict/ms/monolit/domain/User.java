package com.ict.ms.monolit.domain;

import com.ict.ms.monolit.domain.vo.UserEmail;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class User {

    @EmbeddedId
    private UserEmail userId;
    private String firstName;
    private String lastName;
}
