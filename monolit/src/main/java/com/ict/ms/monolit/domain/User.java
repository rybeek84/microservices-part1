package com.ict.ms.monolit.domain;

import com.ict.ms.monolit.domain.vo.UserId;

import javax.persistence.EmbeddedId;

public class User {

    @EmbeddedId
    private UserId userId;
}
