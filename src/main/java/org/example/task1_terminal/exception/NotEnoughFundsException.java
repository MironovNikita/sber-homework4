package org.example.task1_terminal.exception;

public class NotEnoughFundsException extends RuntimeException {
    public NotEnoughFundsException() {
        super("Недостаточно средств для совершения операции");
    }
}
