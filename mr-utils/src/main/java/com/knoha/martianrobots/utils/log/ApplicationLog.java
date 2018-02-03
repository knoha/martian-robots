package com.knoha.martianrobots.utils.log;

import java.text.MessageFormat;

public class ApplicationLog {

    private String task;

    public ApplicationLog(final String task) {
        this.task = task;
    }

    public void print(final String message) {
        System.out.println(MessageFormat.format("[{0}]: {1}", task, message));
    }

    public void print(final String message, final Object... params) {
        final String logMessage = formatLogMessage(message, params);

        System.out.println(MessageFormat.format("[{0}]: {1}", task, logMessage));
    }

    private String formatLogMessage(final String message, final Object... params) {
        final StringBuilder logMessage = new StringBuilder();

        if (message != null) {
            if (params != null && params.length > 0) {
                logMessage.append(MessageFormat.format(message, params));
            } else {
                logMessage.append(message);
            }
        }

        return logMessage.toString();
    }

}
