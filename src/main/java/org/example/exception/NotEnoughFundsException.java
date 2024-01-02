package org.example.exception;

public class NotEnoughFundsException extends RuntimeException {
    public NotEnoughFundsException() {
        super("Недостаточно средств для совершения операции");
    }
}
