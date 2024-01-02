package org.example.validation;

import org.example.exception.AccountsLockedException;

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
        System.out.print("Введите пин-код: ");

        Scanner scanner = new Scanner(System.in);
        boolean verifiedPin = checkCode(scanner);
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

    private boolean checkCode(Scanner scanner) {
        int code;

        if (scanner.hasNextInt()) code = scanner.nextInt();
        else return false;

        if (String.valueOf(code).length() != 4) {
            return false;
        }
        char[] pinToCheck = ("" + code).toCharArray();
        for (int i = 0; i < 4; i++) {
            if (PIN[i] != pinToCheck[i]) return false;
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