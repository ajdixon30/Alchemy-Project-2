package com.Revature.Project2.beans.controllers;

import com.Revature.Project2.beans.pojos.Logger;
import com.Revature.Project2.repos.LogRepo;
import com.Revature.Project2.services.DatabaseLogger;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling UI exceptions
 */
@RestController
public class LogController {
    private DatabaseLogger databaseLogger;
    private final LogRepo logRepo;

    @Autowired
    public LogController(DatabaseLogger databaseLogger, LogRepo logRepo) {
        this.databaseLogger = databaseLogger;
        this.logRepo = logRepo;
    }

    /**
     * Saves a new exception log to the DB
     *
     * @return a ResponseEntity initialized by logger.writeLog(message,level)
     */
    @PostMapping(value = "/logging", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Logger post(@RequestBody Logger logger){
        return databaseLogger.writeUILog(logger.getMessage(), logger.getWarningLevel());
    }
}
