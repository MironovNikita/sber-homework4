package org.example.terminal.view;

import org.example.exception.IncorrectSumException;
import org.example.exception.NotEnoughFundsException;
import org.example.terminal.Terminal;

import java.util.Scanner;

public class TerminalViewImpl implements TerminalView {
    private final Terminal terminal;

    public TerminalViewImpl(Terminal terminal) {
        this.terminal = terminal;
    }

    @Override
    public int checkIntInput(Scanner scanner) {
        int num;
        do {
            if (scanner.hasNextInt()) {
                num = scanner.nextInt();
                break;
            } else {
                System.out.print("Не могу распознать число. Введите числовое значение: ");
                scanner.next();
            }
        } while (true);
        return num;
    }

    @Override
    public void printMenu() {
        System.out.print("""
                \nПожалуйста, введите команду от 0 до 3, где:
                1 - Проверить баланс
                2 - Снять деньги
                3 - Внести деньги
                0 - Выход
                                
                Ввод:""");
    }

    @Override
    public void work() {
        Scanner scanner = new Scanner(System.in);
        boolean isWorking = true;

        while (isWorking) {
            printMenu();
            int userCommand = checkIntInput(scanner);
            switch (userCommand) {
                case 1 -> {
                    System.out.println("\nВыбрано: проверить баланс\nНаправляю запрос на сервер...");
                    System.out.println("========================================================");
                    System.out.printf("Ваш текущий баланс: %.2f руб.%n", terminal.checkBalance());
                    System.out.println("========================================================\n");
                    isWorking = wishToContinue();
                }

                case 2 -> {
                    System.out.println("Выбрано: снять деньги\nВведите сумму (кратно 100): ");
                    int withdrawal = checkIntInput(scanner);
                    System.out.println("Направляю запрос на сервер...");
                    try {
                        terminal.withdrawMoney(withdrawal);
                        System.out.println("\n========================================================");
                        System.out.printf("Успешно! Возьмите Ваши деньги!%nВаш текущий баланс: %.2f руб.%n",
                                terminal.checkBalance());
                        System.out.println("========================================================\n");
                        isWorking = wishToContinue();
                    } catch (IncorrectSumException | NotEnoughFundsException exception) {
                        System.out.println(exception.getMessage());
                    }
                }

                case 3 -> {
                    System.out.println("Выбрано: внести деньги\nВведите сумму (кратно 100): ");
                    int deposit = checkIntInput(scanner);
                    System.out.println("Направляю запрос на сервер...");
                    try {
                        terminal.depositMoney(deposit);
                        System.out.println("\n========================================================");
                        System.out.printf("Успешно! Сумма %d руб. внесена!%nВаш текущий баланс: %.2f руб.%n",
                                deposit, terminal.checkBalance());
                        System.out.println("========================================================\n");
                        isWorking = wishToContinue();
                    } catch (IncorrectSumException exception) {
                        System.out.println(exception.getMessage());
                    }
                }

                case 0 -> {
                    System.out.println("Выхожу из терминала...");
                    terminal.finishSession();
                    isWorking = false;
                }
                default -> System.out.println("Извините, такая команда отсутствует :с");
            }
        }
    }

    private boolean wishToContinue() {
        System.out.println("Желаете продолжить?\n1 - Вывести меню\n0 - Выйти из терминала");
        int answer;
        while (true) {
            System.out.println("Ввод: ");
            answer = checkIntInput(new Scanner(System.in));
            switch (answer) {
                case 1 -> {
                    return true;
                }
                case 0 -> {
                    terminal.finishSession();
                    return false;
                }
                default -> System.out.println("Извините, такая команда отсутствует :с");
            }
        }
    }
}