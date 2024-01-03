package org.example;

import org.example.task1_terminal.server.TerminalServerImpl;
import org.example.task1_terminal.terminal.Terminal;
import org.example.task1_terminal.terminal.TerminalImpl;
import org.example.task1_terminal.server.TerminalServer;
import org.example.task1_terminal.terminal.view.TerminalView;
import org.example.task1_terminal.terminal.view.TerminalViewImpl;
import org.example.task1_terminal.validation.PinValidator;
import org.example.task2_urlChecker.UrlReader;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TerminalServer server = new TerminalServerImpl();
        PinValidator validator = new PinValidator();
        Terminal terminal = new TerminalImpl(server, validator);
        TerminalView terminalView = new TerminalViewImpl(terminal);

        Scanner scanner = new Scanner(System.in);

        System.out.print("""
                \nЗдравствуйте! Какое упражнение запустить?
                1 - Терминал банкомата
                2 - Вывод содержимого сайта по его URL
                0 - Выход
                                
                Ввод:""");

        int choice = terminalView.checkIntInput(scanner);

        switch (choice) {
            case 1 -> {
                if (terminal.authorization()) {
                    terminalView.work();
                }
            }
            case 2 -> UrlReader.readContent(UrlReader.acceptUrl());
            case 0 -> System.exit(0);
        }
    }
}