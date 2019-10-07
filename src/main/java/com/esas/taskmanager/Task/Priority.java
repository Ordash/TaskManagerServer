package com.esas.taskmanager.Task;

public enum Priority {
    LOW,MEDIUM,HIGH;

    public static Priority lookup(String value) {
        try {
            return Priority.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid value for my enum blah blah: " + value);
        }
    }
}
