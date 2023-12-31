package org.example.task1_terminal.terminal;

import org.example.task1_terminal.exception.AccountsLockedException;
import org.example.task1_terminal.exception.IncorrectSumException;
import org.example.task1_terminal.server.TerminalServer;
import org.example.task1_terminal.validation.PinValidator;

public class TerminalImpl implements Terminal {
    private final TerminalServer server;
    private final PinValidator pinValidator;

    public TerminalImpl(TerminalServer server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
    }

    @Override
    public boolean authorization() {
        System.out.println("Добро пожаловать в наш банк!");
        boolean isAuthorized = false;
        while (!pinValidator.getIsAuthorized()) {
            try {
                isAuthorized = pinValidator.authorization();
            } catch (AccountsLockedException e) {
                System.out.println(e.getMessage());
            }
        }
        return isAuthorized;
    }

    @Override
    public double checkBalance() {
        return server.checkBalance();
    }

    @Override
    public void withdrawMoney(int withdrawal) {
        if (withdrawal % 100 != 0 || withdrawal <= 0) {
            throw new IncorrectSumException(withdrawal);
        }
        server.withdrawMoney(withdrawal);
    }

    @Override
    public void depositMoney(int deposit) {
        if (deposit % 100 != 0 || deposit <= 0) {
            throw new IncorrectSumException(deposit);
        }
        server.depositMoney(deposit);
    }

    @Override
    public void finishSession() {
        pinValidator.setAuthorized(false);
    }
}