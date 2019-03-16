package com.ict.ms.monolit.domain.vo;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserEmail implements Serializable {

    private String email;
}
