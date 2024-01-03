package org.example.task1_terminal.terminal;

public interface Terminal {
    double checkBalance();

    void withdrawMoney(int amount);

    void depositMoney(int amount);

    boolean authorization();

    void finishSession();
}
