package org.example.task1_terminal.exception;

public class AccountsLockedException extends RuntimeException {
    public AccountsLockedException(long remainingTime) {
        super(String.format("Аккаунт заблокирован. Повторите попытку через %s секунд.", remainingTime));
    }
}