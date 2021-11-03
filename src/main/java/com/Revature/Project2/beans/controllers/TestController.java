package com.Revature.Project2.beans.controllers;

import com.Revature.Project2.beans.TestService;
import com.Revature.Project2.beans.pojos.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping(value = "/test-list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<TestEntity> get(){
        return testService.getAll();
    }

    @PostMapping(value = "/test-list", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public TestEntity post(@RequestBody TestEntity testEntity){
        testService.save(testEntity);
        return testService.getById(testEntity.getId());
    }
}
