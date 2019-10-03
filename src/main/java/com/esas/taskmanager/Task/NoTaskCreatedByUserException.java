package com.esas.taskmanager.Task;

import java.util.function.Supplier;

public class NoTaskCreatedByUserException extends Exception {
    public NoTaskCreatedByUserException(String s) {
        super(s);
    }
}
