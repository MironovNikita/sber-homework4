package org.example.task1_terminal.validation;

import org.example.task1_terminal.exception.AccountsLockedException;

import java.util.Scanner;

public class PinValidator {
    private static final char[] PIN = {'1', '5', '7', '4'};
    private static final int ATTEMPTS = 3;
    private static final long LOCK_TIME_SECONDS = 10;

    private int failedAttempts;
    private long unlockTime;
    private boolean isAuthorized;

    public PinValidator() {
        this.failedAttempts = 0;
        this.unlockTime = 0L;
    }

    public boolean authorization() throws AccountsLockedException {
        System.out.print("Введите пин-код:\n");

        Scanner scanner = new Scanner(System.in);
        boolean verifiedPin = checkCode(pinInput(scanner));
        accountLockTimeCheck();

        if (verifiedPin && getCurrentTime() > unlockTime) {
            failedAttempts = 0;
            unlockTime = 0L;
            isAuthorized = true;
        } else if (failedAttempts != 3) {
            failedAttempts++;
            System.out.printf("Введён неверный пин-код. Осталось попыток: %d\n", ATTEMPTS - failedAttempts);
        }

        if (failedAttempts == 3 && unlockTime == 0L) {
            unlockTime = getCurrentTime() + LOCK_TIME_SECONDS;
        }

        if (getCurrentTime() <= unlockTime) {
            throw new AccountsLockedException(unlockTime - getCurrentTime());
        }

        return isAuthorized;
    }

    private long getCurrentTime() {
        return System.currentTimeMillis() / 1000;
    }

    private void accountLockTimeCheck() {
        if (failedAttempts == 3 && getCurrentTime() > unlockTime) {
            failedAttempts = 0;
            unlockTime = 0L;
        }
    }

    private char[] pinInput(Scanner scanner) {
        char[] checkPin = new char[4];
        char inputChar;
        boolean validChar;

        for (int i = 0; i < PIN.length; i++) {
            validChar = false;

            System.out.print("Введите " + (i + 1) + " цифру пароля: ");
            while (!validChar) {
                inputChar = scanner.nextLine().charAt(0);
                if (Character.isDigit(inputChar)) {
                    checkPin[i] = inputChar;
                    validChar = true;
                } else {
                    System.out.print("Для " + (i + 1) + " цифры пароля требуется символ, являющийся числом: ");
                }
            }
        }
        return checkPin;
    }

    private boolean checkCode(char[] pinInput) {
        if (pinInput.length != 4) {
            return false;
        }

        for (int i = 0; i < 4; i++) {
            if (PIN[i] != pinInput[i]) return false;
        }
        return true;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }

    public boolean getIsAuthorized() {
        return isAuthorized;
    }
}