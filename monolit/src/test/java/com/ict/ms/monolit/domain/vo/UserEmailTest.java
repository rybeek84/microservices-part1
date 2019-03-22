package com.ict.ms.monolit.domain.vo;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserEmailTest {

    @Test
    public void shouldBeTheSame(){
        assertEquals(new UserEmail("some@email.com"), new UserEmail("some@email.com"));
    }

    @Test
    public void shouldBeCaseInsensitive(){
        assertEquals(new UserEmail("SOME@email.com"), new UserEmail("some@EMAIL.com"));
    }

}