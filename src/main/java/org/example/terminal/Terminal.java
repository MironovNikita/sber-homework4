package org.example.terminal;

public interface Terminal {
    double checkBalance();

    void withdrawMoney(int amount);

    void depositMoney(int amount);

    boolean authorization();

    void finishSession();
}
