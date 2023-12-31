package org.example.terminal;

import org.example.exception.AccountsLockedException;
import org.example.validation.PinValidator;

public class TerminalImpl implements Terminal {
    private final TerminalServer server;
    private final PinValidator pinValidator;

    public TerminalImpl(TerminalServer server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
    }

    public boolean authorization() {
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
    public void printMenu() {
        System.out.println("\nДобро пожаловать, дорогой пользователь!\nБудет выводиться меню банкомата");
    }

    @Override
    public int checkAccountBalance() {
        return 0;
    }

    @Override
    public void withdrawMoney(int amount) {

    }

    @Override
    public void depositMoney(int amount) {

    }


}
