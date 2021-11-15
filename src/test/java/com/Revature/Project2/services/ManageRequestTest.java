package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.Request;
import com.Revature.Project2.repos.RequestRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ManageRequestTest {

    private final RequestRepo requestRepo;
    private final Validation validation;
    private final ManageRequest manageRequest;

    @Autowired
    public ManageRequestTest(RequestRepo requestRepo, Validation validation, ManageRequest manageRequest) {
        this.requestRepo = requestRepo;
        this.validation = validation;
        this.manageRequest = manageRequest;
    }

    @BeforeAll
    public void BeforeAddition() {
        manageRequest.requestAddition("Scarface");

    }

    @Test
    public void testRequestAddition(){
        Assertions.assertEquals(requestRepo.getById(1).getAddRequest(), "Scarface");
    }
}
