package com.Revature.Project2.beans.servicebeans;

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

    private FileLogger() {
        printToConsole = false;
        printToConsoleTemp = false;
        threshold = 3;
    }

    //Gets the file logger and sets up the file path
    public static FileLogger getFileLogger(){
        if(fileLogger == null) {
            fileLogger = new FileLogger();
        }
        return fileLogger;
    }

    //Adds the exception data to the log file
    public void writeLog(String message, int level) {

        try(FileWriter fileWriter = new FileWriter(getLogFileName(), true)){
            String logEntry;

            logEntry = formatLogEntry(message);
            if(level >= threshold){
                fileWriter.write(logEntry);
            }

            if(printToConsole || printToConsoleTemp) {
                System.out.println(logEntry);
                printToConsoleTemp = false;
            }

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

    public FileLogger console() {
        printToConsoleTemp = true;
        return fileLogger;
    }

    public FileLogger threshold(int th) {
        threshold = th;
        return fileLogger;
    }

    public static boolean isPrintToConsole() {
        return printToConsole;
    }

    public static void setPrintToConsole(boolean printToConsole) {
        FileLogger.printToConsole = printToConsole;
    }

    public static int getThreshold() {
        return threshold;
    }

    public static void setThreshold(int threshold) {
        FileLogger.threshold = threshold;
    }

}
