package org.example.server;

import org.example.exception.NotEnoughFundsException;

public class TerminalServerImpl implements TerminalServer {
    private static double balance = 0.00;

    @Override
    public double checkBalance() {
        return balance;
    }

    @Override
    public void withdrawMoney(int withdrawal) throws NotEnoughFundsException {
        if (balance - withdrawal < 0) {
            throw new NotEnoughFundsException();
        }

        balance -= withdrawal;
    }

    @Override
    public void depositMoney(int deposit) {
        balance += deposit;
    }
}
