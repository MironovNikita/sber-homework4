package org.example;

import org.example.terminal.Terminal;
import org.example.terminal.TerminalImpl;
import org.example.terminal.TerminalServer;
import org.example.validation.PinValidator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TerminalServer server = new TerminalServer();
        PinValidator validator = new PinValidator();
        Terminal terminal = new TerminalImpl(server, validator);

        System.out.println("Добро пожаловать в наш банк!");

        if (terminal.authorization()) {
            terminal.printMenu();
        }


    }


}