package org.example.exception;

public class IncorrectSumException extends RuntimeException {
    public IncorrectSumException(int amount) {
        super(String.format("Введена некорректная сумма: %d. Сумма должна быть кратна 100 руб и больше 0.", amount));
    }
}
