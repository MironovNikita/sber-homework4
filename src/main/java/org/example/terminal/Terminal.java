package org.example.terminal;

public interface Terminal {
    int checkAccountBalance();

    void withdrawMoney(int amount);

    void depositMoney(int amount);

    boolean authorization();

    void printMenu();
}
