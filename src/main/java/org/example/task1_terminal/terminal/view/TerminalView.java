package org.example.task1_terminal.terminal.view;

import java.util.Scanner;

public interface TerminalView {
    int checkIntInput(Scanner scanner);

    void printMenu();

    void work();
}
