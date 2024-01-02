package org.example;

import org.example.server.TerminalServerImpl;
import org.example.terminal.Terminal;
import org.example.terminal.TerminalImpl;
import org.example.server.TerminalServer;
import org.example.terminal.view.TerminalView;
import org.example.terminal.view.TerminalViewImpl;
import org.example.validation.PinValidator;

public class Main {
    public static void main(String[] args) {
        TerminalServer server = new TerminalServerImpl();
        PinValidator validator = new PinValidator();
        Terminal terminal = new TerminalImpl(server, validator);
        TerminalView terminalView = new TerminalViewImpl(terminal);

        System.out.println("Добро пожаловать в наш банк!");

        if (terminal.authorization()) {
            terminalView.work();
        }
    }
}