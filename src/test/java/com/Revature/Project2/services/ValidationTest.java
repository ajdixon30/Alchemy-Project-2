package com.Revature.Project2.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ValidationTest {

    private Validation validation;

    @Autowired
    public ValidationTest(Validation validation){
        this.validation = validation;
    }

    @Test
    public void testForBadLogin(){
        assertEquals(false, validation.validateUser("oooga", "booga"));
    }

}
