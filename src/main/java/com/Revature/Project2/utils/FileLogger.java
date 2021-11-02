package com.Revature.Project2.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//TODO: May need to refactor/rewrite this class if we decide to make it a bean
public class FileLogger {
    private static FileLogger fileLogger;
    private static int threshold;
    private static boolean printToConsole;
    private static boolean printToConsoleTemp;

    //Gets the file logger and sets up the file path
    public static FileLogger getFileLogger(){
        if(fileLogger == null) {
            fileLogger = new FileLogger();
        }
        return fileLogger;
    }

    //Adds the exception data to the log file
    public void writeLog(String message, int level) {

        try{
            FileWriter fileWriter = new FileWriter(getLogFileName(), true);
            String logEntry;

            logEntry = formatLogEntry(message);
            if(level >= threshold){
                fileWriter.write(logEntry);
            }

            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //Gets the log file name
    private static String getLogFileName() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return today + ".log";
    }

    //Formats the entries that are saved to the log file
    private  String formatLogEntry(String message){
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String stackInfo = stackTraceElements[3].toString();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return String.format("%s [%s] %s", timestamp, stackInfo, message);
    }
}
