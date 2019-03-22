package com.ict.ms.monolit.domain;

import com.ict.ms.monolit.domain.vo.UserEmail;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Data
public class User {

    @EmbeddedId
    private UserEmail userId;
    private String firstName;
    private String lastName;
}
