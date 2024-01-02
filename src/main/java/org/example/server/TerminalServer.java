package org.example.server;

public interface TerminalServer {
    double checkBalance();

    void withdrawMoney(int amount);

    void depositMoney(int deposit);
}
