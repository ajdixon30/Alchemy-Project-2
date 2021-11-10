package com.Revature.Project2.services;

import com.Revature.Project2.beans.pojos.Logger;
import com.Revature.Project2.repos.LogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;


@Service
@Transactional
public class DatabaseLogger {
    private final LogRepo logRepo;

    @Autowired
    public DatabaseLogger(LogRepo logRepo) {
        this.logRepo = logRepo;
    }

    //Adds the exception data to the log file
    public void writeLog(String message, int level) {
        Logger logger = new Logger(getCurrentDateTime(), formatLogEntry(message), level);
        logRepo.save(logger);
    }

    //Gets the log file name
    private String getCurrentDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(System.currentTimeMillis());
    }

    //Formats the entries that are saved to the log file
    private  String formatLogEntry(String message){
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        StringBuilder stackTrace = new StringBuilder();
        for(StackTraceElement element : stackTraceElements){
            stackTrace.append(element);
            stackTrace.append(System.lineSeparator());
        }
        return String.format("[%s] %s%n", stackTrace, message);
    }


}