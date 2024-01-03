package org.example.task1_terminal.server;

public interface TerminalServer {
    double checkBalance();

    void withdrawMoney(int amount);

    void depositMoney(int deposit);
}
